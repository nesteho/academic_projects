#ifndef PLATE_H
#define PLATE_H

#include <vector>
#include "brick.h"
#include "direction.h"
#include <memory>
#include "position.h"
#include "bag.h"
#include <optional>
/*!
 * \file plate.h
 * \brief Represents the plate of the Tetris Game
 *
 */
class Plate{

    std::vector<std::vector<unsigned int>> cases;
    unsigned height;
    unsigned width;
    bool empty;
    std::optional<Brick> curBrick;
    std::optional<Position> curBrickCenter; // Represents the center of the current brick WITHIN the board
    unsigned lineBrick; // Sentinel value 1, -1, or std::optional and reset
    static const int MAXHEIGHT = 100;
    static const int MAXWIDTH = 50;
    friend class Game;
    friend class PlateTest;
    friend class GameTest;

    /*!
     * \brief Constructs the plate of the game
     */
    Plate(unsigned height = 20, unsigned width= 10);

    /*!
     * \brief addBrick the given brick in the plate
     * \param brick the brick to add in the plate
     * \return whether or not the brick was added in the plate
     */
    bool addBrick(Brick brick);

    /*!
    * \brief Checks for collision at a specified row and column.
    *
    * \param row The row to check for collision.
    * \param col The column to check for collision.
    * \return true if there is a collision at the specified row and column, false otherwise.
    */
    bool collision(int& row, int& col);

    /*!
     * \brief Finds if the row at the given index is a line or not
     * \param index the index of the row
     * \return if the row at the given index is a line or not
     */
    bool isLine(const unsigned& index);

    /*!
     * \brief Clears the line at the given index
     * \param index the index of the line to clear
     */
    void clearLine(const unsigned& index);

    void cleanRecursive(unsigned i) ;
    /*!
    * \brief Checks if the given row and column indices are within the bounds of the plate.
    * \param row The row index to be checked.
    * \param col The column index to be checked.
    * \return true if the row and column indices are within the bounds of the plate, false otherwise.
    */
    bool checkIndex(int row, int col) ;

    /*!
     * \brief Sets the plate ready for the next brick to add
     */
    void nextBrick();

    /*!
     * \brief Swaps the current row with the row above it
     * \param index the index of the row to swap
     */
    void swapRow(const unsigned& index);

public:

    /*!
     * \brief Translates the current brick in the given direction
     * \param dir the given direction
     * \return whether or not the current brick was translated
     */
    bool translate(DIRECTION2D dir);

    /*!
     * \brief Removes newly formed lines from the plate
     */
    void clean();

    /*!
     * \brief Rotates the current brick
     * \param sens The direction of rotation
     */
    void rotate(bool sens);

    /*!
     * \brief Drops the current brick at the end of the plate
     * \return the height of the drop realized with the current brick
     */
    unsigned drop();

    /*!
     * \brief Gets the current number of lines realized with the current brick
     * \return the current  number of lines realized with the current brick
     */
    unsigned getLineBrick() const;


    /*!
     * \brief Gets the plate of the game
     * \return the plate of the game
     */
    const std::vector<std::vector<unsigned int>>& getCases() const ;
};

#endif // PLATE_H
