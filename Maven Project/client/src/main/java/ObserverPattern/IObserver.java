package ObserverPattern;

public interface IObserver<T> {
    void notify(T observable);
}
