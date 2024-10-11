#ifndef GAMESTATE_H
#define GAMESTATE_H

#include <vector>
#include <optional>
#include "brick.h"

struct GameState {
    unsigned score;
    unsigned level;
    unsigned lines;
    bool over;
    bool hasWin;
    const std::vector<std::vector<unsigned int>>& cases;
    std::optional<Brick> nextBrick;
    GameState(unsigned score, unsigned level, unsigned lines, bool over,bool hasWin,
              const std::vector<std::vector<unsigned int>>& cases,
              const std::optional<Brick>& nextBrick)
        : score(score), level(level), lines(lines), over(over),hasWin(hasWin),
        cases(cases), nextBrick(nextBrick) {}
};

#endif // GAMESTATE_H
