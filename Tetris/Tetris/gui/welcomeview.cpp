#include "welcomeview.h"
#include "ui_welcomeview.h"
#include "playbuttonhandler.h"
#include <iostream>
WelcomeView::WelcomeView(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::WelcomeView)
{
    ui->setupUi(this);
    this->setStyleSheet("background-color: #403E3E  ;");
    ui->label_4->setStyleSheet("font-size: 24pt; font-family: Arial; color: #D9392B;");
    ui->label_2->setStyleSheet("color: #D9392B;");
    ui->label_3->setStyleSheet("color: #D9392B;");
    ui->label_5->setStyleSheet("color: #D9392B;");
    ui->label->setStyleSheet("color: #D9392B;");
    ui->playButton->setStyleSheet("background-color: #18557D;");

      this->setWindowTitle("Welcom to TETRIS");
 }

WelcomeView::~WelcomeView()
{
    delete ui;
}
 void WelcomeView::addPlayButtonHandler(Controller *controller)
{
        PlayButtonHandler *handler = new PlayButtonHandler(controller,this);
        connect(ui->playButton, &QPushButton::clicked, handler, &PlayButtonHandler::handleButtonClick);
        // Deletion of the PlayButtonHandler object when the view is destroyed.
        connect(this, &WelcomeView::destroyed, handler, &QObject::deleteLater);
}


unsigned WelcomeView::getHeight() const {
    return ui->height->value();
}

unsigned WelcomeView::getInitialLevel() const {
    return ui->initialLevel->value();
}

unsigned WelcomeView::getWidth() const {
    return ui->width->value();
}
bool WelcomeView::getInitialPlateState() const {
    return ui->plateInitialState->isChecked();
}
