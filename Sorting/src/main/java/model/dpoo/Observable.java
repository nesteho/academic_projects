package model.dpoo;

/**
 * Observable in the observer-observable pattern.
 */
public interface Observable {
    void registerObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers(Object... args);
}

