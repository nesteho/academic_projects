#ifndef PLAYVIEW_H
#define PLAYVIEW_H

#include <QMainWindow>
#include <QLabel>
#include <QTableWidget>
#include "game.h"
#include <QKeyEvent>
#include <QElapsedTimer>
#include <QTimer>
#include <QTime>
#include "observer/observer.h"
#include "gamestate.h"

namespace Ui {
class PlayView;
}

class PlayView : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    explicit PlayView(const GameState& state,QWidget *parent = nullptr);
    ~PlayView();
protected:
    void keyPressEvent(QKeyEvent *event) override;

private:
    Ui::PlayView *ui;
    QTableWidget *table;
    QTableWidget * nextBrickWidget;

    QTimer *timer;
    QTime elapsedTime; // New member to store the elapsed time.
    Qt::GlobalColor makeBrickColor(unsigned value);

    QLabel* label_over; // Pointer to the QLabel for displaying 'Game Over'.

    void displayNextBrick(Brick nextB);
    void initializeNextBrick(Brick nextB);
    void updateLevel(unsigned level);
    void updateScore(unsigned score) ;
    void updateLines(unsigned lines) ;
    void update(PropertyName propertyChange, const GameState& state) override;
    void updateGameOver(bool over, bool hasWin);
    void cleanupTable();
    void cleanupNextBrick();
    void displayTable(const GameState& state);
    void initializeTable(const GameState& state);

signals:
    void moveLeftPressed();
    void moveRightPressed();
    void moveDownPressed();
    void rotateClockwisePressed();
    void rotateCounterClockwisePressed();
    void dropPressed();
private slots:
    void updateTimer();

};

#endif // PLAYVIEW_H
