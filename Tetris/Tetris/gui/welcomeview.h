#ifndef WELCOMEVIEW_H
#define WELCOMEVIEW_H

#include <QMainWindow>

class Controller;

namespace Ui {
class WelcomeView;
}

class WelcomeView : public QMainWindow
{
    Q_OBJECT

public:
    explicit WelcomeView(QWidget *parent = nullptr);
    ~WelcomeView();
    // Methods for retrieving the values of UI elements.
    unsigned getHeight() const;
    unsigned getWidth() const;
    unsigned getInitialLevel() const;
    bool getInitialPlateState() const;

public slots:
    void addPlayButtonHandler(Controller *controller);

private:
    Ui::WelcomeView *ui;
};

#endif // WELCOMEVIEW_H
