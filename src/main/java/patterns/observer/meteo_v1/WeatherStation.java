package patterns.observer.meteo_v1;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        weatherData.registerObserver(new StatisticsDisplay());
        weatherData.registerObserver(new ForecastDisplay());
        for(int i=0; i<3; i++) {
            weatherData.generateRandomData();
        }
    }
}
