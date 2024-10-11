#ifndef VUE_H
#define VUE_H

#include <iostream>
#include "../metier/brick.h"
#include <vector>

/**
 * @brief Represents The Vue of the MVC architecture
 */
class Vue {

    /*!
     * \brief Finds the actual plate limits to display
     * \param cases the game plate
     * \return the actual plate limits to display
     */
    std::pair<std::pair<int, int>, std::pair<int, int>> findActualPlate(
        const std::vector<std::vector<Brick*>>& cases);
    std::vector<Brick>::iterator begin() ;

    // Function end() to enable iteration
    std::vector<Brick>::iterator end() ;
public:

    /*!
     * \brief Displays the welcoming message
     */
    void displayWelcome();

    /**
     * Display the commands of the Tetris game
    */
    void displayHelp();

    /**
     * @brief Displays the current score
     * @param score The current score of the play
     */
    void displayScore(int score);

    /**
     * @brief Displays the current game level
     * @param level The current level of the play
     */
    void displayLevel(int level);

    /**
     * @brief Displays the number of lines cleared
     * @param lines The number of lines cleared in the play
     */
    void displayLines(int lines);

    /**
     * @brief Displays whether the game is finished or not
     * @param over the state of the game
    */
    void displayGameOver();

    /**
     * @brief Displays the provided message
     * @param message the message to display
    */
    void displayMessage(std::string message);


    /*!
     * \brief Displays the plate of the game
     * \param gameBoard the plate of the game
     */
    void displayGamePlate(const std::vector<std::vector<unsigned int>>& cases);

};

#endif // VUE_H
