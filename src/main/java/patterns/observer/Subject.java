package patterns.observer;

public interface Subject {
    void registerObserver(Observer newcomer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
