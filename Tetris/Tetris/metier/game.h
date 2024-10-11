#ifndef GAME_H
#define GAME_H

#include "direction.h"
#include "bag.h"
#include "plate.h"
#include "brick.h"
#include <memory>
#include <iostream>
#include <set>
#include "observer/observable.h"
#include "observer/observer.h"
#include "gamestate.h"
#include "gametimer.h"
#include <chrono>

/*!
 * \file game.h
 * \brief Represents a play of the Tetris Game
 *
 */
class Game : public Observable{

    Bag bag;
    Plate plate;
    unsigned score;
    unsigned level;
    bool levelIncreased;
    bool over;
    double curTimeUnit;
    int lines;
    const unsigned MAX_LINES;
    const unsigned MAX_SCORE;
    std::set<Observer*> observers;
    bool hasWin;
    GameTimer gameTimer;

    friend class GameTest;

    /*!
     * \brief Adds a brick to the plate
     */
    void addBrick();

    /*!
     * \brief Sets the level of the game
     */
    void setLevel();


    /**
     * \brief setCurTimeUnit
     */
    void setCurTimeUnit();

    /*!
     * \brief Checks whether or not the game is over
     */
    void checkOver();


    /*!
     * \brief Sets the plate ready for the next brick to add
     */
    void nextBrick();

    /*!
     * \brief Increases the score.
     * \param drop the drop made with the current Brick
     */
    void increaseScore(int drop);

    /*!
     * \brief Checks whether the plate dimensions are valid
     */
    void checkPlateDimensions(int height, int width);

    /*!
     * \brief Fills the plate randomly
     */
    void fillPlate();

    /*!
     * \brief Generates a random direction
     */
    char randomDirection(std::mt19937& gen);

    /*!
     * \brief Generates a random rotation
     */
    bool randomRotation(std::mt19937& gen) ;


    Game(bool forTest, int height = 20, int width = 10, int initLevel = 1, bool emptyPlate = true);
    void notifyObservers(PropertyName propertyChange) override;
    void checkTimer();

public:
    /*!
     * \brief Constructs of the game
     * \param height the height of the plate
     * \param width the width of the plate
     * \param initLevel the initial level of  the game
     * \@param emptyPlate if the intial plate is empty or not
     */
    Game(int height = 20, int width = 10, int initLevel = 1, bool emptyPlate = true);

    /*!
     * \brief Translates the current brick in the given direction
     * \param dir the given direction
     */
    void translateBrick(char direction);

    /*!
     * \brief Rotates the current brick in the given sens
     * \param sens the given sens
     */
    void rotate(bool sens);

    /*!
     * \brief Drops the current brick at the end of the plate
     */
    void drop();

    //void chrono();

    /*!
     * \brief Gets the current level of the game
     * \return the current level of the game
     */
    int getLevel();

    /*!
     * \brief Gets the current number of lines realized in the game
     * \return the current  number of lines realized in the game
     */
    int getLines();

    /*!
     * \brief Gets the current score realized in the game
     * \return the current score realized in the game
     */
    int getScore();

    /*!
     * \brief Gets the current state of the game
     * \return whether or not the game is over
     */
    bool isOver();

    /*!
     * \brief Sets the counter of lines made during the play
     */
    void setLines();

    double getCurTimeUnit();

    GameState getState();

    /*!
     * \brief Gets the plate of the game
     * \return the plate of the game
     */
    const std::vector<std::vector<unsigned int>>&  getCases() const;

    Game& operator=(const Game& other); // move assignation operator

    void registerObserver(Observer* obs) override;
    void removeObserver(Observer* obs) override;

    void startTimer();

};
#endif // GAME_H

