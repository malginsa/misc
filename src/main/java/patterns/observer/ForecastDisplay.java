package patterns.observer;

public class ForecastDisplay implements Observer, DisplayElement {

    private float futureTemperature;
    private float futurePressure;
    private float futureHumidity;

    public ForecastDisplay() {
        futureTemperature = 0;
        futurePressure = 0;
        futureHumidity = 0;
    }

    @Override
    public void update(float temperature, float pressure, float humidity) {
        if (futureTemperature == 0) {
            futureTemperature = temperature;
            futurePressure = pressure;
            futureHumidity = humidity;
        } else {
            futureTemperature = (futureTemperature + temperature) / 2;
            futurePressure = (futurePressure + pressure) / 2;
            futureHumidity = (futureHumidity + humidity) / 2;
        }
    }

    @Override
    public void display() {
        System.out.println("Forecast: "
                + futureTemperature + "  "
                + futurePressure + "  "
                + futureHumidity);
    }
}
