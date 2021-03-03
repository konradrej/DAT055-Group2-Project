package util;

public interface IObservable<T> {
    void addObserver(IObserver<T> obs);
    void removeObserver(IObserver<T> obs);
}
