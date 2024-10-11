#ifndef BAG_H
#define BAG_H

#include "brick.h"
#include <vector>
#include <random>
#include <optional>

/*!
 * \file bag.h
 * \brief Represents a Bag of Bricks
 *
 */
class Bag {
    unsigned size ;
    int lastGettedBrick;
    std::vector<Brick> bricks;
    std::optional<Brick> nextBrick;
    const unsigned seed;
    std::mt19937 gen;

    friend class BagTest;
    friend class Game;

    void anticipateNextBrick();

public :


    /*!
     * \brief Constructs the plate of the game
     */
    Bag();

    /*!
     * \brief Shuffles the bricks contained in the bag
     */
    void shuffle();

    /*!
     * \brief getBrick
     * \return  a brick of the bag
     */
    Brick getBrick();

    /*!
     * \brief Retrieves the next brick from the bag.
     * @return The next brick from the bag.
     */
    Brick getNextBrick();
};

#endif // BAG_H
