#ifndef POSITION_H
#define POSITION_H

#include <utility>

/*!
 * \file position.h
 * \brief The position class represents a 2D position with x and y coordinates.
 *
 */
class Position
{
    std::pair<int,int> pos;

public:
    /*!
     * \brief Constructs a Position object with the specified x and y coordinates.
     * \param x The x-coordinate of the position. Default is 0.
     * \param y The y-coordinate of the position. Default is 0.
     */
    Position(int x=0 ,int y=0 ) : pos(std::make_pair(x,y))
    {}

    /*!
     * \brief Gets the x-coordinate of the position.
     * \return The x-coordinate of the position.
     */
    int getX() const {return pos.first;}

    /*!
     * \brief Gets the y-coordinate of the position.
     * \return The y-coordinate of the position.
     */
    int getY() const {return pos.second;}

    /*!
     * \brief Sets the x-coordinate of the position.
     * \param x The new x-coordinate of the position.
     */
    void setX(int x) { pos.first = x; }

    /*!
     * \brief Sets the y-coordinate of the position.
     * \param y The new y-coordinate of the position.
     */
    void setY(int y) { pos.second = y; }

    /*!
     * \brief Copy constructor.
     * \param other The position object to copy.
     */
    Position(const Position& other) : pos(std::make_pair(other.getX(), other.getY())) {}

    /*!
     * \brief Assignment operator.
     * \param other The position object to copy.
     * \return A reference to the current position object.
     */
    Position& operator=(const Position& other)  {
        if (this != &other) {
            pos.first = other.getX();
            pos.second = other.getY();
        }
        return *this;
    }
};

#endif // POSITION_H
