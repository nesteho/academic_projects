#ifndef DIRECTION_H
#define DIRECTION_H

#include <utility>

/*!
 * \file direction.h
 * \brief Alias for 2D direction represented as a pair of integers.
 */
using DIRECTION2D = std::pair<int,int>;

/*!
 * \brief Struct containing predefined 2D directional vectors.
 */
struct DIRECTIONS{
    static const DIRECTION2D DOWN;
    static const DIRECTION2D LEFT;
    static const DIRECTION2D RIGHT;
};


#endif // DIRECTION_H
