#include "plate.h"
#include "iostream"

Plate::Plate(unsigned height, unsigned width)
    :  height(height) , width(width), empty(true), curBrick(), curBrickCenter(), lineBrick(0){

    if ( height>MAXHEIGHT || width>MAXWIDTH){
        throw std::invalid_argument("not possible");
    }
    cases.resize(height, std::vector<unsigned int>(width, 0));
}


bool Plate::addBrick(Brick brick) {
    //To access members with optional: If the type is primitive, use .value().attr, otherwise use ->.
    //For brick: It's a copy of a copy of a copy that gets destroyed at the end of the scope }, but it exists through it.
    if (curBrick){ // true if it contains a brick
        throw std::invalid_argument("il y a déjà une brique courante");
    }

    curBrick = brick; // Cloning (but modifiable copy): like const:
                                        //lives during addBrick curBrickCenter = Position(0, (width - 1)/2);
    clean();
    curBrickCenter = Position(0,width/2);
    bool possible = true;
    for (const auto& pos : curBrick->getPositions()) {
        int row = pos.getX();
        int col = pos.getY()+ curBrickCenter->getY();
        possible &= checkIndex(row, col) && !collision(row, col);
    }
    if (!possible) {
        return false;
    }
    for (const auto& pos : curBrick->getPositions()) {
        cases[curBrickCenter->getX() + pos.getX()][curBrickCenter->getY() + pos.getY()] = curBrick->getNumero();
    }

    empty=false;

    return true;
}


bool Plate::collision(int& row, int& col) {
    return cases[row][col] != 0;
}


void Plate::clean() {
    unsigned i = height - 1;
    cleanRecursive(i);
}

void Plate::cleanRecursive(unsigned i) {
    if (i == 0)
        return;

    if (isLine(i)) {
        clearLine(i);
        lineBrick++;
        swapRow(i);
        // Recursive call with the same value of i because the line has been deleted.
        cleanRecursive(i);
    } else {
        //Recursive call with the decremented value of i because we are moving to the previous line."
        cleanRecursive(i - 1);
    }
}


void Plate::swapRow(const unsigned& index){
    for(int i=index ; i>0;--i){
        std::swap(cases[i],cases[i - 1]);
    }
}

bool Plate::isLine(const unsigned& index) {
    for (unsigned col = 0; col < width; ++col) {
        if (cases[index][col] == 0) {
            return false;
        }
    }
    return true;
}

void Plate::clearLine(const unsigned& index) {
    for (unsigned j = 0; j < width; j++) {

        cases[index][j] = 0;
    }  
}

bool Plate::checkIndex(int row, int col) {
    return (row >= 0 && row < height && col >= 0 && col < width);
}


void Plate::nextBrick() {
    curBrick.reset();
    curBrickCenter.reset();
    lineBrick = 0;
}

unsigned Plate::getLineBrick() const{
    return lineBrick;
}

bool Plate::translate(DIRECTION2D dir) {
    if (!curBrick) {
        throw std::runtime_error("No current brick to translate.");
    }

    // Save the current positions of the brick.
    const std::vector<Position>& currentPositions = curBrick->getPositions();

    // Remove the brick from the board by setting occupied squares to 0.
    for (const auto& pos : currentPositions) {
        cases[curBrickCenter->getX() + pos.getX()][curBrickCenter->getY() +pos.getY()] = 0;
    } // 17    1

    // Apply the translation of the brick in the given direction.
    curBrickCenter->setX(curBrickCenter->getX() + dir.first);
    curBrickCenter->setY(curBrickCenter->getY() + dir.second);

    // Check if the new positions are valid.
    bool possible = true;
    for (const auto& pos : currentPositions) {
        // Calculate the coordinates of the new position on the board.
        int newX = curBrickCenter->getX() + pos.getX();
        int newY = curBrickCenter->getY() + pos.getY();
        possible &= checkIndex(newX, newY) && !collision(newX,newY);
    }

    // If translation is not possible, translate CurBrickCenter in the opposite direction.
    if (!possible) {
        curBrickCenter->setX(curBrickCenter->getX() - dir.first);
        curBrickCenter->setY(curBrickCenter->getY() -  dir.second);
    }

    // Update the board based on the final position.
    for (const auto& pos : currentPositions) {
        cases[curBrickCenter->getX() + pos.getX()][curBrickCenter->getY() + pos.getY()] =
            curBrick->getNumero();
    }
    return possible;
}

unsigned Plate::drop() {

    if (!curBrick) {
        throw std::runtime_error("No current brick to drop.");
    }
    unsigned distance = 0;
    while (translate(DIRECTIONS::DOWN)){
        ++distance;
    }
    clean(); // seulement quand la brique est immobile : pas a chq mvt
    return distance;
}


void Plate::rotate(bool sens) {
    if (!curBrick) {
        throw std::runtime_error("No current brick to rotate.");
    }

    // Remove the brick from the board by setting occupied squares to 0.
    for (const auto& pos : curBrick->getPositions()) {
        cases[curBrickCenter->getX() + pos.getX()][curBrickCenter->getY() +pos.getY()] = 0;
    }

    curBrick->rotate(sens);

    bool possible = true;
    // Check if the new positions are valid.
    for (const auto& pos : curBrick->getPositions()) {
        // Check if the position is out of the board or if the new position is occupied.
        int newX = curBrickCenter->getX() + pos.getX();
        int newY = curBrickCenter->getY() + pos.getY();
        possible &= checkIndex(newX, newY) && !collision(newX,newY);
    }

    if (!possible){
        curBrick->rotate(!sens);
    }

    // Display the bricks on the board.
    for (const auto& pos : curBrick->getPositions()) {
        cases[curBrickCenter->getX() + pos.getX()][curBrickCenter->getY() +pos.getY()] = curBrick->getNumero();
    }
}

const std::vector<std::vector<unsigned int>>& Plate::getCases() const {
    return cases;
}


