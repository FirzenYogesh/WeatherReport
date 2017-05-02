package yogesh.firzen.weatherreport;

import android.location.Location;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import yogesh.firzen.mukkiasevaigal.M;


public class Weather {
    private static final String WEATHER_BY_NAME = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String WEATHER_BY_ID = "http://api.openweathermap.org/data/2.5/weather?id=%s&units=metric";
    private static final String WEATHER_BY_COORD = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";

    public static JSONObject getWeatherByName(String city) {
        try {
            URL url = new URL(String.format(WEATHER_BY_NAME, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", "9d09e235e071754814f0d297a7425fe4");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder(1024);
            String tmp;
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                return null;
            }
            M.L(M.Type.DEBUG, "WeatherByName", json);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject getWeatherByID(String city) {
        try {
            URL url = new URL(String.format(WEATHER_BY_ID, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", "9d09e235e071754814f0d297a7425fe4");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder(1024);
            String tmp;
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                return null;
            }
            M.L(M.Type.DEBUG, "WeatherByID", json);
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject getWeatherByCoord(Location location) {
        try {
            URL url = new URL(String.format(WEATHER_BY_COORD, location.getLatitude() + "", location.getLongitude() + ""));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", "9d09e235e071754814f0d297a7425fe4");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder(1024);
            String tmp;
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();
            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                return null;
            }
            M.L(M.Type.DEBUG, "WeatherByCoord", json);
            return data;
        } catch (Exception e) {
            return null;
        }
    }
}
