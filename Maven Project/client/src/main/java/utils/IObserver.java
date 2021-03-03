package utils;

public interface IObserver<T> {
    void notify(T observable);
}
