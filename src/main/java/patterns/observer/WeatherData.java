package patterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherData implements Subject{

    Random random = new Random();
    List<Observer> observers;
    private float temperature;
    private float pressure;
    private float humidity;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer newcomer) {
        observers.add(newcomer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, pressure, humidity);
        }
    }

    public void generateRandomData() {
        temperature = random.nextInt(40) - 20;
        pressure = random.nextInt(50) + 750;
        humidity = random.nextInt(100);
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return pressure;
    }

    public float getPressure() {
        return humidity;
    }

    public void measurementsChanged(){
        notifyObservers();
    }
}
