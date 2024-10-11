#include "bag.h"
#include "defaultBricks.h"
#include <stdexcept>
#include <iostream>
#include <random>

Bag::Bag() : size(7), lastGettedBrick(-1),seed(static_cast<unsigned int>(std::random_device{}())), gen(seed),
    bricks({DefaultBricks::brickI, DefaultBricks::brickL, DefaultBricks::brickZ,
              DefaultBricks::brickJ, DefaultBricks::brickS, DefaultBricks::brickT, DefaultBricks::brickSquare}) {
    shuffle();

}

Brick Bag::getBrick() {

    if (bricks.empty()) {
        throw std::invalid_argument("Bag is empty");
    }
    nextBrick.reset();
    if (lastGettedBrick == bricks.size() - 1) {
        lastGettedBrick = -1;
        shuffle();
    }
    if (lastGettedBrick == bricks.size() - 2) {

        anticipateNextBrick();
        return bricks[++lastGettedBrick];
    }

    ++lastGettedBrick;
    nextBrick = bricks[lastGettedBrick + 1]; // Save nextBrick
    return bricks[lastGettedBrick];
}

void Bag::shuffle() {
    gen.seed(seed);
    std::shuffle(std::begin(bricks), std::end(bricks), gen);
}

void Bag::anticipateNextBrick() {
    gen.seed(seed);
    std::vector<Brick> bricksCopy = bricks;
    std::shuffle(std::begin(bricksCopy), std::end(bricksCopy), gen);
    nextBrick = bricksCopy[0];
}
Brick Bag::getNextBrick() {
    return nextBrick.value();
}
