#ifndef GAMETIMER_H
#define GAMETIMER_H

#include <chrono>

class GameTimer {
public:
    GameTimer();
    void start();
    void stop();
    std::chrono::milliseconds elapsed();

private:
    std::chrono::steady_clock::time_point startTime;
    std::chrono::steady_clock::time_point stopTime;
    bool running;
};

#endif // GAMETIMER_H
