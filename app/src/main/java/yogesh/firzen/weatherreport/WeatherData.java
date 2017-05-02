package yogesh.firzen.weatherreport;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class WeatherData {
    private String city;
    private String country;
    private double temp;
    private double tempMin;
    private double tempMax;
    private String humidity;
    private String pressure;
    private String description;
    private Bitmap image;
    private double lat;
    private double lon;
    private long sunset;
    private long sunrise;
    private double windSpeed;
    private double windDeg;

    public WeatherData(JSONObject json) throws JSONException {
        //JSONObject json = new JSONObject(data);
        city = json.getString("name");
        country = json.getJSONObject("sys").getString("country");
        temp = json.getJSONObject("main").getDouble("temp");
        tempMin = json.getJSONObject("main").getDouble("temp_min");
        tempMax = json.getJSONObject("main").getDouble("temp_max");
        humidity = json.getJSONObject("main").getInt("humidity") + "%";
        description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        lat = json.getJSONObject("coord").getDouble("lat");
        lon = json.getJSONObject("coord").getDouble("lon");
        sunset = json.getJSONObject("sys").getLong("sunset");
        sunrise = json.getJSONObject("sys").getLong("sunrise");
        windSpeed = json.getJSONObject("wind").getLong("speed");
        windDeg = json.getJSONObject("wind").getLong("deg");
        pressure = json.getJSONObject("main").getDouble("pressure") + " mm Hg";
        try {
            String imgd = json.getJSONArray("weather").getJSONObject(0).getString("icon");
            URL img = new URL("http://openweathermap.org/img/w/" + imgd + ".png");
            image = BitmapFactory.decodeStream(img.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }
}
