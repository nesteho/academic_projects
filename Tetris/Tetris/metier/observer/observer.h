#ifndef OBSERVER_H
#define OBSERVER_H

class Observer {
public:
    /*!
     * \brief Notifies observators over a change of state
     */
    virtual void update(PropertyName propertyChange, const GameState& state) = 0;
};

#endif // OBSERVER_H
