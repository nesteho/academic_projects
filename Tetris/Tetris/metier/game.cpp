#include "game.h"
#include "plate.h"
#include "timeUnits.h"
#include <thread>
//#include <chrono>
#include "gamestate.h"
#include "gametimer.h"

Game::Game(bool forTest, int height, int width, int initLevel, bool emptyPlate):bag(), MAX_LINES(150),  MAX_SCORE(100000)
    ,gameTimer()
{
    if(( initLevel < 1) && (initLevel > TimeUnits::MAX_LEVEL ) ){
        throw std::invalid_argument("Niveau initial invalide");
    }
    if (height < 1 ||  height >= 100) {
        throw std::invalid_argument("Height invalide");
    }
    if (width < 1 || width >= 100) {
        throw std::invalid_argument("Witdh invalide");
    }
    level = initLevel;
    levelIncreased = false;
    over = false;
    hasWin =false;
    lines=0;
    score=0;
    curTimeUnit = TimeUnits::timesUnits[level-1];
    plate = Plate(height, width);
}

/*
void Game::chrono() {
    while (!isOver()) {
        std::cout << "auto down" << std::endl;
        translateBrick('d');

        // Attendre le prochain intervalle de temps
        std::this_thread::sleep_for(std::chrono::duration<double>(curTimeUnit));

   }
}*/


void Game::startTimer(){
    gameTimer.start();
}

Game::Game(int height, int width, int initLevel, bool emptyPlate)
    : Game(false,height,width,initLevel,emptyPlate)
{
    if (!emptyPlate) {
        fillPlate();
    }
    else{
        addBrick();
    }
}

void Game::checkPlateDimensions(int height, int width) {

    if (height < 0 ) {
        throw std::invalid_argument("Invalid plate height");
    }
    if (width < 0) {
        throw std::invalid_argument("Invalid plate width");
    }
}

void Game::addBrick() {
    if(!over){
        Brick brick = bag.getBrick();
        over = !plate.addBrick(brick);
        notifyObservers(PropertyName::ADD_BRICK);
    }
}

void Game::setLevel() {
    if (!levelIncreased) {
        if(lines % 10 == 0 && lines != 0  && level < TimeUnits::timesUnits.size()){
            level++;
            levelIncreased = true;
        }
    }
    else if (lines % 10 != 0) {
        levelIncreased = false; // Réinitialiser uniquement si lines n'est pas un multiple de 10
    } // éviter que pls drop augmentent le niveau slm car lines est tjr %10
}

void Game::setCurTimeUnit() {
    if (level < TimeUnits::MAX_LEVEL) {
        curTimeUnit = TimeUnits::timesUnits[level-1];
    }
}

void Game::nextBrick() {
    plate.nextBrick();
    addBrick();
}


void Game::increaseScore(int drop) {
    int l = plate.getLineBrick();
    int factor = 0;
    switch (l) {
    case 0:
    case 1:
        factor = 40;
        break;
    case 2:
        factor = 100;
        break;
    case 3:
        factor = 300;
        break;
    case 4:
        factor = 1200;
        break;
    default:
        throw std::invalid_argument("Incorrect number of completed lines");
    }
    score += (factor * l + drop) * level;
    checkOver();
}

void Game::checkOver() {
    if(score >= MAX_SCORE || lines >= MAX_LINES){
        hasWin=true;
        gameTimer.stop();
    }
}

void Game::checkTimer(){
    if( gameTimer.elapsed() >= std::chrono::hours(1)){
        hasWin=true;
        gameTimer.stop();
    }
}

void Game::translateBrick(char direction) {
    if(over){
        return;
    }
    DIRECTION2D dir;
    switch (direction) {
    case 'd':
        dir = DIRECTIONS::DOWN;
        break;
    case 'l':
        dir = DIRECTIONS::LEFT;
        break;
    case 'r':
        dir = DIRECTIONS::RIGHT;
        break;
    default:
        throw std::invalid_argument("Invalid direction"); 
    }

    bool setNextBrick = !plate.translate(dir) && dir == DIRECTIONS::DOWN;
    if(setNextBrick){
        nextBrick();
    }
    checkTimer();
    notifyObservers(PropertyName::MOVE);
}

void Game::rotate(bool sens) {
    if(over){
        return;
    }
    plate.rotate(sens);
    notifyObservers(PropertyName::MOVE);
    checkTimer();
}

void Game::drop() {
    if(over){
        return;
    }
    unsigned drop = plate.drop();
    checkTimer();
    setLines();
    setLevel();
    setCurTimeUnit();
    increaseScore(drop);
    nextBrick();
    notifyObservers(PropertyName::MOVE);
}

int Game::getLevel() {
    return level;
}

double Game::getCurTimeUnit(){
    return curTimeUnit;
}

int Game::getLines() {
    return lines;
}

void Game::setLines() {
    lines += plate.getLineBrick();
}

int Game::getScore() {
    return score;
}

bool Game::isOver() {
    return over;
}

const std::vector<std::vector<unsigned int>>&  Game::getCases() const {
    return plate.getCases();
}

char Game::randomDirection(std::mt19937& gen) {
    std::uniform_int_distribution<> directionDis(0, 2);
    char direction;
    switch (directionDis(gen)) {
    case 0:
        direction = 'd';
        break;
    case 1:
        direction = 'r';
        break;
    case 2:
        direction = 'l';
        break;
    }
    return direction;
}

bool Game::randomRotation(std::mt19937& gen) {
    std::uniform_int_distribution<> rotationDis(0, 1); // 0 for clockwise, 1 for counterclockwise
    return rotationDis(gen) == 0; // true for clockwise, false for counterclockwise
}

void Game::fillPlate() {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dis(2, 5); // Random number of bricks
    std::uniform_int_distribution<> actionDis(1, 2); // Random action (1: translate, 2: rotate)

    addBrick();
    int randomNum = dis(gen);
    for (int i = 0; i < randomNum; ++i) {
        std::uniform_int_distribution<> actionCountDis(1, 5);
        int actionCount = actionCountDis(gen);

        // Loop to perform a certain number of random actions before dropping the brick
        for (int j = 0; j < actionCount; ++j) {
            int action = actionDis(gen); // Choose a random action
            if (action == 1) {
                char d = randomDirection(gen);
                translateBrick(d); // Random translation
            } else {
                bool clockwise = randomRotation(gen);
                rotate(clockwise); // Random rotation
            }
        }
        drop(); // drop calls nextBrick which calls addBrick etc.
    }
}


void Game::registerObserver(Observer* obs) {
    observers.insert(obs);
}

 void Game::removeObserver(Observer* obs){
    observers.erase(obs);
}

void Game::notifyObservers(PropertyName propertyChange)  {
    for (auto observer : observers) {
        observer->update(propertyChange,getState());
    }
}
GameState Game::getState(){
    auto next = bag.getNextBrick();
    return GameState (score, level, lines, over, hasWin, getCases(), next);
}
