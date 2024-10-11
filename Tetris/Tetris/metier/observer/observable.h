#ifndef OBSERVABLE_H
#define OBSERVABLE_H
#include "../gamestate.h"

// Forward declaration of Observer class
class Observer;

// Enum for property change
enum PropertyName {
    MOVE,ADD_BRICK
};

class Observable {
public:
    // Register an observer
    virtual void registerObserver(Observer* obs) = 0;

    // Remove an observer
    virtual void removeObserver(Observer* obs) = 0;

    // Notify observers of a property change
    virtual void notifyObservers(PropertyName propertyChange) = 0;
};

#endif // OBSERVABLE_H
