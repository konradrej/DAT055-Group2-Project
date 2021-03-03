package util;

public interface IObserver<T> {
    void notify(T observable);
}
