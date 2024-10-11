#include "brick.h"
#include "iostream"

Brick::Brick(std::vector<Position> brickPositions, unsigned int numero) :
    positions(brickPositions), center(brickPositions[0]),numero(numero) {
}

void Brick::rotate(bool sens) {
    int centerX = center.getX();
    int centerY = center.getY();

    // Traverse all brick positions.
    for (auto& pos : positions) {
        //Calculating coordinates relative to the center.
        int relativeX = pos.getX() - centerX;
        int relativeY = pos.getY() - centerY;

        //Perform a 90-degree rotation.
        if (sens) {
           //r cw
            int temp = relativeX;
            relativeX = relativeY;
            relativeY = -temp;
        } else {
            //r ccw
            int temp = relativeX;
            relativeX = -relativeY;
            relativeY = temp;
        }

       //Update the new absolute coordinates.
        pos = Position(centerX + relativeX, centerY + relativeY);
    }
}


int Brick::size() const {
    return positions.size();
}

const std::vector<Position>& Brick::getPositions() const {
    return positions;
}

unsigned int Brick::getNumero() const {
    return numero;
}

Brick& Brick::operator= (const Brick &other){ //implicitely used in shuffle( that implicitely use swap)
    if (this != &other){
        positions=other.positions;
        numero = other.numero;
    }
    return *this;
}
