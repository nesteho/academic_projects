package model.observer;
import java.util.ArrayList;
import java.util.List;

/**
 * /**
 * This class represents an observable object, or "data" in the model-view
 * paradigm. It can be subclassed to represent an object that the application
 * wants to have observed.
 * <p>
 * An observable object can have one or more observers. An observer may be any
 * object that implements interface {@code Observer}. After an observable
 * instance changes, an application calling the {@code Observable}'s
 * {@code notifyObservers} method causes all of its observers to be notified of
 * the change by a call to their {@code update} method.
 * <p>
 * The order in which notifications will be delivered is unspecified. The
 * default implementation provided in the Observable class will notify Observers
 * in the order in which they registered interest, but subclasses may change
 * this order, use no guaranteed order, deliver notifications on separate
 * threads, or may guarantee that their subclass follows this order, as they
 * choose.
 * <p>
 * Note that this notification mechanism has nothing to do with threads and is
 * completely separate from the {@code wait} and {@code notify} mechanism of
 * class {@code Object}.
 * <p>
 * When an observable object is newly created, its set of observers is empty.
 * Two observers are considered the same if and only if the {@code equals}
 * method returns true for them.
 *
 * @author jlc
 */
public class Observable {
    private final List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public synchronized void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }
    public void notifyObservers(Object arg) {
        for (Observer observer : observers) {
            observer.update(this, arg);
        }
    }
    public synchronized int countObservers() {
        return observers.size();
    }
}
