#ifndef PLATETEST_HPP
#define PLATETEST_HPP
#include "../metier/game.h"

class PlateTest : public Plate{

public :
    bool addBrickTest(Brick brick){


        return addBrick(brick);

    }

    bool collisionTest(int& row, int& col) {


        return collision(row, col);

    }

    bool isLineTest(const unsigned& index) {


        return isLine(index);

    }

    void clearLineTest(const unsigned& index) {


        clearLine(index);

    }

    bool checkIndexTest(int row, int col) {


        return checkIndex(row, col);

    }

    void nextBrickTest() {


        nextBrick();

    }

    void swapRowTest(const unsigned& index) {


        swapRow(index);

    }

    bool translateTest(DIRECTION2D dir) {


        return translate(dir);

    }

    void cleanTest() {


        clean();

    }

    void rotateTest(bool sens) {


        rotate(sens);

    }

    unsigned dropTest() {


        return drop();

    }

    unsigned getLineBrickTest() const {


        return getLineBrick();

    }

    const std::vector<std::vector<unsigned int>>& getCases() const {

        return getCases();

    }

};


#endif // PLATETEST_HPP
