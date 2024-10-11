#include "playbuttonhandler.h"

PlayButtonHandler::PlayButtonHandler(Controller *controller, QObject *parent)
    : QObject(parent), controller(controller) {}

void PlayButtonHandler::handleButtonClick() {
    if (controller) {
        controller->handlePlayButtonAction();
    }
}
