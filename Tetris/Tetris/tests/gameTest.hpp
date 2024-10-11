#ifndef GAMETESTER_HPP
#define GAMETESTER_HPP
#include "../metier/game.h"

class GameTest : public Game {

public:

    GameTest(bool forTest, int height, int width, int initLevel, bool emptyPlate)
        : Game(forTest, height, width, initLevel, emptyPlate) {

    }



    GameTest( int height, int width, int initLevel, bool emptyPlate)
        : Game( height, width, initLevel, emptyPlate) {

    }

    bool getEmptyForTest(){
        return plate.empty;
    }

    auto getCurBrickCenterForTest(){
        return plate.curBrickCenter;
    }

    void addBrickTest() {

        addBrick();

    }

    void addPlateBrickTest(Brick brick) {
        plate.addBrick(brick);
    }

    void setLevelTest() {

        setLevel();

    }

    void setCurTimeUnitTest() {

        setCurTimeUnit();

    }

    void checkOverTest() {

        checkOver();

    }

    void nextBrickTest() {

        nextBrick();

    }

    void increaseScoreTest(int drop) {

        increaseScore(drop);

    }

    void checkPlateDimensionsTest(int height, int width) {

        checkPlateDimensions(height, width);

    }

    void fillPlateTest() {

        fillPlate();

    }

    char randomDirectionTest(std::mt19937& gen) {

        return randomDirection(gen);

    }

    bool randomRotationTest(std::mt19937& gen) {

        return randomRotation(gen);

    }
};
#endif // GAMETESTER_HPP
