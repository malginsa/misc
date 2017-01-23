package patterns.observer.meteo_v1;

import java.util.Arrays;

public class StatisticsDisplay implements Observer, DisplayElement {

    private int count;
    private double[] temperature;
    private double[] pressure;
    private double[] humidity;

    public StatisticsDisplay() {
        count = 0;
        temperature = new double[1000];
        pressure = new double[1000];
        humidity = new double[1000];
    }

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature[++count] = temperature;
        this.pressure[count] = pressure;
        this.humidity[count] = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("Statistics: "
                + Arrays.stream(this.temperature).sum() / count + "  "
                + Arrays.stream(this.pressure).sum() / count + "  "
                + Arrays.stream(this.humidity).sum() / count);
    }
}
