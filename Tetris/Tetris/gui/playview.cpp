#include "playview.h"
#include "ui_playview.h"
#include <QItemSelection>
#include <QDateTime>
#include <iostream>

PlayView::PlayView(const GameState& state,QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::PlayView),
    elapsedTime(0, 0, 0) //Initialize elapsedTime to 0:0:0.
{
    ui->setupUi(this);
    table = ui->tableWidget;
    table->setEditTriggers(QAbstractItemView::NoEditTriggers);
    table->setSelectionMode(QAbstractItemView::NoSelection);
    table->verticalHeader()->setVisible(false);
    table->horizontalHeader()->setVisible(false);

    nextBrickWidget = ui->nextBrickWidget;
    table->verticalHeader()->setVisible(false);
    table->horizontalHeader()->setVisible(false);
    table->setStyleSheet("background-color: #000000;");


       timer = new QTimer(this);
       connect(timer, &QTimer::timeout, this, &PlayView::updateTimer);
       timer->start(1000); //Update every 1000 milliseconds (1 second).


       label_over = ui->label_over;
       label_over->hide();

        this->setWindowTitle("Tetris");
        this->setStyleSheet("background-color: #403E3E  ;");
        auto brick = state.nextBrick.value();
        initializeNextBrick(brick);
        displayNextBrick(brick);
        initializeTable(state);
        displayTable(state);

}
void PlayView::initializeNextBrick(Brick nextB){
    // Initialize nextBrickWidget
    unsigned size = nextB.size();
    nextBrickWidget->setRowCount(size);
    nextBrickWidget->setColumnCount(size);

    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size; ++j) {
            QTableWidgetItem* item = new QTableWidgetItem();
            nextBrickWidget->setItem(i, j, item);
        }
    }
}
void PlayView::displayNextBrick(Brick nextB) {
    int maxWidth = 0;
    int maxHeight = 0;
    for (const auto& position : nextB.getPositions()) {
        maxWidth = std::max(maxWidth, std::abs(position.getX()));
        maxHeight = std::max(maxHeight, std::abs(position.getY()));
    }

    // Calculate the offset to center the brick
    int offsetX = (nextBrickWidget->columnCount() - maxWidth) / 2;
    int offsetY = (nextBrickWidget->rowCount() - maxHeight) / 2;

    // Reset the table by clearing all backgrounds
    for (int i = 0; i < nextBrickWidget->rowCount(); ++i) {
        for (int j = 0; j < nextBrickWidget->columnCount(); ++j) {
            QTableWidgetItem* item = nextBrickWidget->item(i, j);
            if (item) {
                item->setBackground(Qt::lightGray); // or any default color you prefer
            }
        }
    }
    // Loop through the positions to populate the nextBrickWidget with colored items
    for (const auto& position : nextB.getPositions()) {
        int i = position.getX() + offsetX;
        int j = position.getY() + offsetY;
        QTableWidgetItem* item = nextBrickWidget->item(i, j);
        if (item) {
            // Set color based on the value in the board
            item->setBackground(makeBrickColor(nextB.getNumero()));
        }
    }
}
void PlayView::displayTable(const GameState& state) {

    if (state.nextBrick.has_value()) {
        displayNextBrick(state.nextBrick.value());
    }
    // Get the dimensions of the board from the state
    int numRows = static_cast<int>(state.cases.size());
    int numCols = numRows > 0 ? static_cast<int>(state.cases[0].size()) : 0;

    // Loop through the board to populate the table with colored items
    for (int i = 0; i < numRows; ++i) {
        for (int j = 0; j < numCols; ++j) {
            QTableWidgetItem* item = table->item(i, j);
            if (item) {
                // Set color based on the value in the board
                auto brickNumber = state.cases[i][j];
                item->setBackground(makeBrickColor(brickNumber));
            }
        }
    }

    int squareSize = qMin(table->width() / numCols, table->height() / numRows);
    for (int col = 0; col < numCols; ++col) {
        nextBrickWidget->setColumnWidth(col, squareSize);
    }
    nextBrickWidget->adjustSize();
}
void PlayView::initializeTable(const GameState& state){
    table->setStyleSheet("background-color: #403E3E  ;");
    // Get the dimensions of the board from the state
    int numRows = static_cast<int>(state.cases.size());
    int numCols = numRows > 0 ? static_cast<int>(state.cases[0].size()) : 0;

    table->setRowCount(numRows);
    table->setColumnCount(numCols);
    table->setFixedSize(table->horizontalHeader()->length() + table->verticalHeader()->width(),
                        table->verticalHeader()->length() + table->horizontalHeader()->height());

    int squareSize = qMin(table->width() / numCols, table->height() / numRows);

    for (int i = 0; i < numRows; ++i) {
        table->setRowHeight(i, squareSize);
        for (int j = 0; j < numCols; ++j) {
            QTableWidgetItem* item = new QTableWidgetItem();
            table->setColumnWidth(j, squareSize);
            item->setBackground(Qt::lightGray);
            table->setItem(i, j, item);
        }
    }
}


Qt::GlobalColor PlayView::makeBrickColor(unsigned value) {
    switch (value) {
    case 1:
        return Qt::red;
    case 2:
        return Qt::cyan;
    case 3:
        return Qt::darkMagenta;
    case 4:
        return Qt::darkRed;
    case 5:
        return Qt::darkGray;
    case 6:
        return Qt::blue;
    case 7:
        return Qt::green;
    }
    return Qt::lightGray;
}

void PlayView::keyPressEvent(QKeyEvent *event) {
    switch (event->key()) {
    case Qt::Key_Left:
        emit moveLeftPressed();
        break;
    case Qt::Key_Right:
        emit moveRightPressed();
        break;
    case Qt::Key_Down:
        emit moveDownPressed();
        break;
    case Qt::Key_Up:
        emit rotateClockwisePressed();
        break;
    case Qt::Key_Control:
        emit rotateCounterClockwisePressed();
        break;
    case Qt::Key_Space:
        emit dropPressed();
        break;
    }
}

void PlayView::updateTimer() {
    elapsedTime = elapsedTime.addSecs(1);
    QString formattedTime = elapsedTime.toString("hh:mm:ss");
    ui->label_timer->setText(formattedTime);

}

void PlayView::updateLevel(unsigned level) {
    ui->label_level->setText("Level: " + QString::number(level));
}

void PlayView::updateScore(unsigned score) {
    ui->label_score->setText("Score: " + QString::number(score));
}


void PlayView::updateLines(unsigned lines) {
    ui->label_nbLines->setText("Lines: " + QString::number(lines));
}

void PlayView::update(PropertyName propertyChange, const GameState& state) {
    if (propertyChange == PropertyName::MOVE || propertyChange == PropertyName::ADD_BRICK ) {
        displayTable(state);
    }
    updateLevel(state.level);
    updateScore(state.score);
    updateLines(state.lines);
    updateGameOver(state.over,state.hasWin);
}

void PlayView::updateGameOver(bool over, bool hasWin){
    if (over || hasWin) {
        timer->stop();

        if(hasWin){
            ui->label_over->setFont(QFont("Chalkboard", 120));
            ui->label_over->setStyleSheet("color:#00FF00; background-color:#000000;");
            ui->label_over->setText("YOU WIN");

        }else{
            ui->label_over->setFont(QFont("Chalkboard", 120));
            ui->label_over->setStyleSheet("color:#BF1E1E  ; background-color:#000000  ;");
            ui->label_over->setText(" Game Over");
    }
    label_over->show();

    }else {
        label_over->hide();
    }
}

void PlayView::cleanupTable() {
    int numRows = table->rowCount();
    int numCols = table->columnCount();

    for (int i = 0; i < numRows; ++i) {
        for (int j = 0; j < numCols; ++j) {
            QTableWidgetItem* item = table->item(i, j);
            if (item) {
                delete item;
            }
        }
    }
}
void PlayView::cleanupNextBrick() {
    int numRows = nextBrickWidget->rowCount();
    int numCols = nextBrickWidget->columnCount();

    for (int i = 0; i < numRows; ++i) {
        for (int j = 0; j < numCols; ++j) {
            QTableWidgetItem* item = nextBrickWidget->item(i, j);
            if (item) {
                delete item;
            }
        }
    }
}

PlayView::~PlayView()
{

    cleanupTable();
    cleanupNextBrick();
    delete timer;
    delete ui;
}
