#ifndef CONTROLLER_H
#define CONTROLLER_H
#include <optional>
#include "game.h"
#include "welcomeview.h"
#include "playview.h"
#include <QMutex>
#include "timeUnits.h"
class Controller : public QObject
{
    Q_OBJECT
public:
    Controller(QObject *parent = nullptr);
    ~Controller();
    void startGameTimer() ;

public slots:
    void handlePlayButtonAction();
    void handleMoveLeftPressed();
    void handleMoveRightPressed();
    void handleMoveDownPressed();
    void handleRotateClockwisePressed();
    void handleRotateCounterClockwisePressed();
    void handleDropPressed();

private:
    WelcomeView * welcomeView;
    PlayView * playView;
    std::optional<Game> game;
    QMutex mutex;
    void connectSignals();
    //void chrono();
    void brickFallLoop();
   QTimer* gameTimer;

signals:
  void startGameTimerSignal();
public slots:
  void startGameTimerSlot();

};

#endif // CONTROLLER_H
