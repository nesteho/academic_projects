#ifndef BRICK_H
#define BRICK_H

#include "position.h"
#include <vector>
/*!
 * \file brick.h
 * \brief Represents a Brick of the Tetris Game
 *
 */
class Brick{

    unsigned int numero;
    const Position center;
    std::vector<Position> positions;

    friend struct DefaultBricks;
    friend class BrickTest;
    /*!
     * \brief Constructs a Brick object
     * \param brickPositions The positions occupied by the brick
     * \param numero The number representing the brick
     */
    Brick(std::vector<Position> brickPositions,unsigned int numero);

public:

    /*!
     * \brief Copy assignment operator
     * \param other The brick to copy from
     * \return Reference to the assigned brick
     */
    Brick& operator= (const Brick &other);

    /*!
     * \brief Gets the size of a brick
     * \return the number of positions of the brick
     */
    int size() const;

    /*!
    * \brief Gets the positions of the brick
    * \return The positions of the brick
    */
    const std::vector<Position>& getPositions() const ;
    /*!
    * \brief Returns the brick number.
    * \return The brick number.
    */
    unsigned int getNumero() const;

    /*!
     * \brief Rotates the current brick
     * \param sens The direction of rotation (true for clockwise, false for counterclockwise)
     */
    void rotate(bool sens);
};
#endif // BRICK_H
