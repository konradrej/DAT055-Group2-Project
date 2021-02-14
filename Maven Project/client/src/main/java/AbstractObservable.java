import java.beans.PropertyChangeSupport;

public abstract class AbstractObservable implements Observable {
    private final PropertyChangeSupport obs = new PropertyChangeSupport(this);

    @Override
    public void addObserver(final Observer obs){
        this.obs.addPropertyChangeListener(obs);
    }

    @Override
    public void removeObserver(final Observer obs) {
        this.obs.removePropertyChangeListener(obs);
    }

    void notifyObservers(String propertyName, Object o){
        obs.firePropertyChange(propertyName, null, o);
    }
}
