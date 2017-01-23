package patterns.observer.meteo_v2;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private Observable observer;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observer) {
        this.observer = observer;
        observer.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if(obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current: temp= " + temperature + "  humidity= " + humidity);
    }
}
