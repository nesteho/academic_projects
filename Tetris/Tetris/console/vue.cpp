#include "vue.h"
#include <iostream>
#include <iomanip>

void Vue::displayWelcome() {
    std::cout << "******Welcome to the Tetris game!******" << std::endl;
}
void Vue::displayHelp() {
    std::cout << "Tetris Game Commands:\n"
              << "- Translate the current brick: t <direction>\n"
              << "- Rotate the current brick: r <sens> \n"
              << "- Drop the current brick to the bottom: d\n"
              << "- Quit the game: q\n"
              << "direction : l (left), r (right), d(down)\n"
              << " sens : cw (clockwise), ccw (counterclockwise)\n\n";
}
void Vue::displayScore(int score) {
    std::cout << "The current Score is: " << score << std::endl;
}
void Vue::displayMessage(std::string message) {
    std::cout << message<< std::endl;
}

void Vue::displayLevel(int level) {
    std::cout << "The current Level is: " << level << std::endl;
}

void Vue::displayLines(int lines) {
    std::cout << "The current Lines cleared is: " << lines << std::endl;
}

void Vue::displayGameOver() {
    std::cout << "The Game Is Over " << std::endl;
}
void Vue::displayGamePlate(const std::vector<std::vector<unsigned int>> &cases){
    for (size_t i = 0; i < cases.size(); ++i) {
        for (size_t j = 0; j < cases[i].size(); ++j) {
            if (cases[i][j] == 0) {
                std::cout << ".";
            } else {
                std::cout << cases[i][j];
            }
        }
        std::cout << std::endl; // New line after each line of the plate
    }
}
std::pair<std::pair<int, int>, std::pair<int, int>> Vue::findActualPlate(const std::vector<std::vector<Brick*>>& cases) {
    int firstNotNullRow = -1;
    int firstNotNullCol = -1;
    int lastNotNullRow = -1;
    int lastNotNullCol = -1;

    for (unsigned i = 0; i < cases.size(); ++i) {
        for (unsigned j = 0; j < cases[i].size(); ++j) {
            if (cases[i][j] != nullptr) {
                if (firstNotNullRow == -1 || firstNotNullRow > i) {
                    firstNotNullRow = i;
                }
                if (firstNotNullCol == -1 || firstNotNullCol > j) {
                    firstNotNullCol = j;
                }
                if (lastNotNullRow < i) {
                    lastNotNullRow = i;

                }
                if (lastNotNullCol < j) {
                    lastNotNullCol = j;
                }
            }
        }
    }

    // Before returning, check if any of the four values is still -1
    if (firstNotNullRow == -1) firstNotNullRow = 0;
    if (firstNotNullCol == -1) firstNotNullCol = 0;
    if (lastNotNullRow == -1) lastNotNullRow = cases.size() -1;
    if (lastNotNullCol == -1) lastNotNullCol = cases[0].size() -1;

    return std::make_pair(std::make_pair(firstNotNullRow, firstNotNullCol), std::make_pair(lastNotNullRow, lastNotNullCol));
}
