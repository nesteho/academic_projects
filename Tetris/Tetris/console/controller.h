#ifndef CONTROLLER_H
#define CONTROLLER_H
#include "vue.h"
#include "game.h"
#include <optional>

/**
 * @brief Represents the Controller of the MVC architecture
 */
class Controller {
    Vue vue;
    std::optional<Game> game; // the game will be created once and not through user input

    /*!
     * \brief Asks the user an input
     * \param value the value where the input would be saved
     * \param message the asking message to display
     * \param min The minimum allowed value for the input
     * \param max The maximum allowed value for the input
     */
    void askInput(int& value, const std::string& message, int min, int max);

    /*!
     * \brief Asks the user an input
     * \param value the value where the input would be saved
     * \param message the asking message to display
     */
    void askInput(bool& value, const std::string& message);

    /*!
     * \brief initializeGame
     * \param height
     * \param width
     * \param level
     * \param emptyPlate
     */
    void initializeGame(int height, int width, int level, bool emptyPlate) ;

    /*!
     * \brief ask player to display game help or not
     */
    void askDisplayHelp();

    /*!
    * @brief Plays a move
    * @return whether the player want to play another move or quit
    */
    bool playAMove();

    /*!
    * \brief Split a string into substrings based on whitespace characters.
    * \param str The string to split.
    * \return A vector of substrings.
    */
    std::vector<std::string> split(const std::string& str);


public:
    /**
     * @brief Constructs an instance of the Controller class
     */
    Controller();

    /*!
     * \brief Starts the game loop
     */
    void start();

    /**
     * @brief Translates the brick in the given direction
     * @param direction The direction in which to move the brick
     */
    void translateBrick(char direction);

    /**
     * @brief Rotates the brick in the specified direction.
     * @param sens The rotation direction
     */
    void rotateBrick(bool sens);

    /**
     * @brief Drops the current brick
     */
    void dropBrick();

    /**
     * @brief Manages the game timer
     */
    void chrono();

    /**
     * @brief Gets the current state of the game
     */
    void getGameState();

    /**
     * @brief Handles user input.
     * @param height The height of the plate
     * @param width The width of the plate
     * @param level The initial level of the play
     */
    void userInput(int& height, int& width, int& level,bool& emptyPlate); // ref to input/output parameter

    /*!
     * \brief  Asks the user for a valid command
     * \return a valid command
     */
    std::string askCommand();

};

#endif // CONTROLLER_H
