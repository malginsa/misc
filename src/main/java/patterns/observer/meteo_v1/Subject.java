package patterns.observer.meteo_v1;

public interface Subject {
    void registerObserver(Observer newcomer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
