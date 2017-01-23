package patterns.observer.meteo_v1;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private final WeatherData weatherData;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        weatherData.registerObserver(this);
        this.weatherData = weatherData;
    }

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current: temp= " + temperature + "  humidity= " + humidity);
    }
}
