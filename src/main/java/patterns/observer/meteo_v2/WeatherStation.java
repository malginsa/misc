package patterns.observer.meteo_v2;

public class WeatherStation {
    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        new ForecastDisplay(weatherData);
//        weatherData.addObserver(new StatisticsDisplay();

        weatherData.setMeasurements(-5, 70, 770);
        weatherData.setMeasurements(-3, 75, 780);
        weatherData.setMeasurements(3, 95, 770);
    }
}
