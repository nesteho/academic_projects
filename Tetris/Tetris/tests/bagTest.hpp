#ifndef BAGTEST_HPP
#define BAGTEST_HPP
#include "../metier/bag.h"

class BagTest : public Bag{

public:

    BagTest() : Bag(){}

    void shuffleTest() {

        shuffle();

    }

    Brick getBrickTestTest() {

        return getBrick();

    }

};
#endif // BAGTEST_HPP
