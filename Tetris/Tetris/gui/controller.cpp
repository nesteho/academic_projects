#include "controller.h"
#include <iostream>
#include <QThread>

Controller::Controller(QObject *parent) : QObject(parent)
{
    welcomeView = new WelcomeView;
    welcomeView->addPlayButtonHandler(this);
    welcomeView->show();
    gameTimer = new QTimer(this);
    connect(gameTimer, &QTimer::timeout, this, &Controller::handleMoveDownPressed);

}

Controller::~Controller()
{
    delete gameTimer;
    delete welcomeView;
    delete playView;
}


void Controller::startGameTimer() {
    double timeUnit = game->getCurTimeUnit();
    std::cout<<timeUnit <<std::endl;
    gameTimer->start(static_cast<int>(timeUnit * 1000)); // Convert to milliseconds and start QTimer
}

void Controller::startGameTimerSlot() {
    double timeUnit = game->getCurTimeUnit();
    gameTimer->start(static_cast<int>(timeUnit * 1000)); // Convert to milliseconds and start QTimer
}


/*
void Controller::chrono()
{
    // Créer une instance de QThread
    QThread* thread = new QThread(this);

    // Connecter la méthode chrono du jeu au signal started() du thread
    connect(thread, &QThread::started, [&]() {
        // Définir la vitesse initiale de descente en fonction du niveau initial
        double timeUnit = game->getCurTimeUnit();// -1 pour prendre en compte l'index 0 du tableau

        // Boucle de jeu
        while (!game->isOver()) {
            // Déplacer la brique vers le bas
            game->translateBrick('d');

            // Attendre l'unité de temps appropriée en fonction du niveau du joueur
            std::this_thread::sleep_for(std::chrono::duration<double>(timeUnit));

            // Mettre à jour la vitesse de descente en fonction du niveau actuel du joueur
            timeUnit = game->getCurTimeUnit(); // -1 pour prendre en compte l'index 0 du tableau
        }
        std::cout << "Game Over" << std::endl;
    });

    // Connecter le thread au signal finished() pour être informé de sa fin
    connect(thread, &QThread::finished, thread, &QThread::deleteLater);

    // Démarrer le thread
    thread->start();

    // Attendre que le thread se termine
    thread->wait();
}*/


void Controller::handlePlayButtonAction()
{
    // Récupérer les valeurs de WelcomeView
    auto height = welcomeView->getHeight();
    auto initialLevel = welcomeView->getInitialLevel();
    auto width = welcomeView->getWidth();
    auto initialPlateState = welcomeView->getInitialPlateState();

    game.emplace(height, width, initialLevel, initialPlateState);
    playView = new PlayView(game->getState());
    game->registerObserver(playView);

    connectSignals();
    playView->show();
    welcomeView->hide();
    game->startTimer();
    startGameTimer();
    //chrono();
}

void Controller::handleMoveLeftPressed() {
    game->translateBrick('l'); // Translate left
}

void Controller::handleMoveRightPressed() {
    game->translateBrick('r'); // Translate right
}

void Controller::handleMoveDownPressed() {
    mutex.lock();
    game->translateBrick('d'); // Translate down
    mutex.unlock();
}

void Controller::handleRotateClockwisePressed() {
    game->rotate(true); // Rotate clockwise
}

void Controller::handleRotateCounterClockwisePressed() {
    game->rotate(false); // Rotate counterclockwise
}

void Controller::handleDropPressed() {
    game->drop(); // Drop the brick
}
void Controller::connectSignals() {
    connect(playView, &PlayView::moveLeftPressed, this, &Controller::handleMoveLeftPressed);
    connect(playView, &PlayView::moveRightPressed, this, &Controller::handleMoveRightPressed);
    connect(playView, &PlayView::moveDownPressed, this, &Controller::handleMoveDownPressed);
    connect(playView, &PlayView::rotateClockwisePressed, this, &Controller::handleRotateClockwisePressed);
    connect(playView, &PlayView::rotateCounterClockwisePressed, this, &Controller::handleRotateCounterClockwisePressed);
    connect(playView, &PlayView::dropPressed, this, &Controller::handleDropPressed);
}




