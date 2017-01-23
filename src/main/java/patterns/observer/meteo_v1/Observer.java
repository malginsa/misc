package patterns.observer.meteo_v1;

public interface Observer {
    void update(float temperature, float pressure, float humidity);
}
