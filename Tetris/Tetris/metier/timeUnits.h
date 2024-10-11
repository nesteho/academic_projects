#ifndef TIMEUNITS_H
#define TIMEUNITS_H
#include <array>
/**
 * \file timeUnits.h
 * \brief The TimeUnits class represents a class with attributes related to time units.
 */
class TimeUnits
{

public:
    /*!
     * \brief MAX_LEVEL represents the maximum level.
     */
    static constexpr int MAX_LEVEL =20;

    /*!
     * \brief timeUnits represents an array of time units.
     */
    static const std::array<double,MAX_LEVEL > timesUnits;
};


#endif // TIMEUNITS_H


