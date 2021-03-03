package util;

/**
 * Interface for observable classes.
 *
 * @author Jakob Ståhl, Konrad Rej
 * @version 2021-03-03
 */
public interface IObservable<T> {
    void addObserver(IObserver<T> obs);

    void removeObserver(IObserver<T> obs);
}
