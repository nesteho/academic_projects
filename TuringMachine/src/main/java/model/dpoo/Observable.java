package model.dpoo;

import model.PropertyName;

/**
 * Observable in the observer-observable pattern.
 */
public interface Observable {
    void registerObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers(PropertyName propertyChange, Object... args);
}
