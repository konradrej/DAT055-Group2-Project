package utils;

import java.util.ArrayList;
import java.util.Collection;

public class ObservableSupport<T> implements IObservable<T> {
    private final Collection<IObserver<T>> observers;
    private final T observable;

    public ObservableSupport(T observable) {
        this.observable = observable;
         observers = new ArrayList<>();
    }

    @Override
    public void addObserver(IObserver<T> obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver<T> obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for(IObserver<T> o : observers) {
            o.notify(this.observable);
        }
    }
}
