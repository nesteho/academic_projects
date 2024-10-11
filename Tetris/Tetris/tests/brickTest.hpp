#include "../metier/brick.h"

class BrickTest : public Brick {
public:

    BrickTest(std::vector<Position> brickPositions, unsigned int numero)  : Brick(brickPositions,  numero){

    }

    int sizeTest() const{

        return size();

    }

    const std::vector<Position>& getPositionsTest() const {

        return getPositions();


    }

    unsigned int getNumeroTest() const{

        return getNumero();


    }
    void rotateTest(bool sens){

        return rotate(sens);


    }

};
