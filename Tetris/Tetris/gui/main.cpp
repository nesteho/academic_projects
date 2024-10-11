#include <QApplication>
#include "controller.h"
#include <iostream>
int main(int argc, char *argv[])
{
    QApplication application(argc, argv);
    Controller controller;
    return application.exec();

}
