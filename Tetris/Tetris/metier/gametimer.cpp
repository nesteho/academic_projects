#include "gametimer.h"
GameTimer::GameTimer() : running(false) {}

void GameTimer::start() {
    startTime = std::chrono::steady_clock::now();
    running = true;
}

void GameTimer::stop() {
    stopTime = std::chrono::steady_clock::now();
    running = false;
}

std::chrono::milliseconds GameTimer::elapsed() {
    if (running) {
        return std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::steady_clock::now() - startTime);
    } else {
        return std::chrono::duration_cast<std::chrono::milliseconds>(stopTime - startTime);
    }
}
