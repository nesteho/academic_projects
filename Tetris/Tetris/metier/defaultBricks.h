#ifndef DEFAULTBRICKS_H
#define DEFAULTBRICKS_H

#include "brick.h"
#include "position.h"
/**
 * \file defaultBrick.h
 * \brief The DefaultBricks struct represents a collection of default brick types.
 */
struct DefaultBricks
{
    /*!
     * \brief I represents a brick type.
     */
    static const Brick brickI;

    /*!
     * \brief L represents a brick type.
     */
    static const Brick brickL;

    /*!
     * \brief Z represents a brick type.
     */
    static const Brick brickZ;

    /*!
     * \brief J represents a brick type.
     */
    static const Brick brickJ;

    /*!
     * \brief S represents a brick type.
     */
    static const Brick brickS;

    /*!
     * \brief T represents a brick type.
     */
    static const Brick brickT;

    /*!
     * \brief Square represents a brick type.
     */
    static const Brick brickSquare;
};

#endif // DEFAULTBRICKS_H
