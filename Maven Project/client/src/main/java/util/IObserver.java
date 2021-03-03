package util;

/**
 * Interface for observer classes.
 *
 * @author Jakob Ståhl, Konrad Rej
 * @version 2021-03-03
 */
public interface IObserver<T> {
    void notify(T observable);
}
