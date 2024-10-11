#include "controller.h"
#include <regex>
#include <sstream>
Controller::Controller() : vue() {
    vue.displayWelcome();
    askDisplayHelp();
    int height = -1, width = -1, level = -1;
    bool emptyPlate;
    userInput(height, width, level,emptyPlate);
    initializeGame(height, width, level,emptyPlate);
    getGameState();
}

void Controller::start() {

    bool timeTostop=false;
    while (!timeTostop &&  !game->isOver()) {

        bool wrongMove = true;
        while (wrongMove){
            try {
                timeTostop = playAMove();
                wrongMove = false;
            }catch (const std::exception& e) {
                std::cerr << "Error: " << e.what() << std::endl;
            }
        }
    }
}
bool Controller::playAMove() {

    std::string toSplit = askCommand();
    auto command = split(toSplit);

    switch (command[0][0]) { // first character of the first word of the command
    case 't': {
        char direction = command[1][0];
        translateBrick(direction);
        break;
    }
    case 'r': {
        bool clockwise = (command[1].compare("cw") == 0);
        rotateBrick(clockwise);
        break;
    }
    case 'd':
        dropBrick();
        break;
    default:  // 'q'
        return true;
    }
    getGameState();
   // std::cout << "leaving Controller::playAMove" << std::endl;

    return false ;
}

void Controller::translateBrick(char direction) {
    game->translateBrick(direction);
}

void Controller::rotateBrick(bool clockwise) {
    game->rotate(clockwise);
}

void Controller::dropBrick() {
    game->drop();
}

/*void Controller::chrono() {
    game->chrono();
}*/

void Controller::getGameState() {
    auto& cases = game->getCases();
    auto lines = game->getLines();
    auto score = game->getScore();
    auto level = game->getLevel();
    auto over =game->isOver();
    if(over){
        vue.displayGameOver();
    }
    vue.displayLevel(level);
    vue.displayLines(lines);
    vue.displayScore(score);
    vue.displayGamePlate(cases);
}

void Controller::askInput(int& value, const std::string& message, int min, int max) {
    bool validInput = false;
    while (!validInput) {
        std::string input;
        vue.displayMessage(message);
        std::getline(std::cin, input);

        if (input.empty()) {
            break;
        }

        std::regex positiveIntegerPattern("^\\d+$");
        if (std::regex_match(input, positiveIntegerPattern)) {
            int inputValue = std::stoi(input);
            if (inputValue >= min && inputValue <= max) {
                value = inputValue;
                validInput = true;
            } else {
                std::cout << "Invalid input. Please enter a number between " << min << " and " << max << std::endl;
            }
        } else {
            std::cout << "Invalid input. Please enter a valid number (positive integer)" << std::endl;
        }
    }
}

void Controller::initializeGame(int height, int width, int level, bool emptyPlate){

    struct DefaultParams { // to avoid 16 if-elseif-else

        int height = 20;
        int width = 10;
        int level = 1;
        //   bool emptyPlate = true; useless
    };
    DefaultParams defaultParams;

    if (height == -1) {
        height = defaultParams.height;
    }
    if (width == -1) {
        width = defaultParams.width;
    }
    if (level == -1) {
        level = defaultParams.level;
    }
    game.emplace(height, width, level, emptyPlate);
}

void Controller::askInput(bool& value, const std::string& message){
    std::string input;
    vue.displayMessage(message);
    std::getline(std::cin, input);
    value = input.empty();
}
void Controller::askDisplayHelp() {

    std::cout << "Enter anything if you would like to see the game help? Press enter if not: " << std::endl;
    std::string choice;
    std::getline(std::cin, choice);
    if (!choice.empty()) {
        std::cout << std::endl;
        vue.displayHelp();
    }
    std::cout << std::endl;
}

void Controller::userInput(int& height, int& width, int& level,bool& emptyPlate) {
    askInput(height,"Enter plate height number between 1-100 (or press Enter for default- 20): ",1,100);
    askInput(width,"Enter plate width number between 1-100 (or press Enter for default- 10): ",1,100);
    askInput(level,"Enter level number between 1-20(or press Enter for default - 1):",1,20);
    askInput(emptyPlate,"Enter initial plate state : anything for partially filled "
                         "or press Enter for default-empty): ");
}

std::string Controller::askCommand(){
    std::string message = "Enter the command for the move you want to make";
    std::string command;
    do {
        std::cout << message << std::endl;
        std::getline(std::cin, command);
    } while (!std::regex_match(command, std::regex("t\\s+(l|r|d)\\s*"))
             && !std::regex_match(command, std::regex("r\\s+(cw|ccw)\\s*"))
             && !std::regex_match(command, std::regex("d\\s*"))
             && !std::regex_match(command, std::regex("q\\s*")));

    return command;
}
std::vector<std::string> Controller::split(const std::string& str) {
    std::istringstream iss(str);
    std::vector<std::string> tokens;
    std::string token;

    while (iss >> token) {
        tokens.push_back(token);
    }
    return tokens;
}


