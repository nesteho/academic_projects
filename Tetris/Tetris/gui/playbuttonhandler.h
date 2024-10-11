#ifndef PLAYBUTTONHANDLER_H
#define PLAYBUTTONHANDLER_H

#include <QObject>
#include <QPushButton>
#include "controller.h"

class PlayButtonHandler : public QObject
{
    Q_OBJECT

public:
    PlayButtonHandler(Controller *controller, QObject *parent = nullptr);

public slots:
    void handleButtonClick();

private:
    Controller * controller;
};

#endif // PLAYBUTTONHANDLER_H
