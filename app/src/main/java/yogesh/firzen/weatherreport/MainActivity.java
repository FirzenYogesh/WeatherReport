package yogesh.firzen.weatherreport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!K.checkLocationCoarsePermission(this) && !K.checkLocationFinePermission(this))
                K.getPermissionFor(this, new String[]{K.LOCATION.COARSE, K.LOCATION.FINE});
            else if (!K.checkLocationCoarsePermission(this) && K.checkLocationFinePermission(this))
                K.getPermissionFor(this, K.LOCATION.COARSE);
            else if (K.checkLocationCoarsePermission(this) && !K.checkLocationFinePermission(this))
                K.getPermissionFor(this, K.LOCATION.FINE);
        }*/
        ((SwipeRefreshLayout) findViewById(R.id.refresh)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeatherReport();
            }
        });
        findViewById(R.id.find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherReport();
            }
        });
        ((EditText) findViewById(R.id.loc)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
                    getWeatherReport();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            //startActivity(new Intent(this, Settings.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getWeatherReport() {
        new AsyncTask<Void, Void, Void>() {

            private String location;
            private WeatherData data;

            @Override

            protected void onPreExecute() {
                ((SwipeRefreshLayout) findViewById(R.id.refresh)).setRefreshing(true);
                location = (((EditText) findViewById(R.id.loc))).getText().toString();
                findViewById(R.id.datas).setVisibility(View.GONE);
                findViewById(R.id.wait).setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    data = new WeatherData(Weather.getWeatherByName(location));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                ((SwipeRefreshLayout) findViewById(R.id.refresh)).setRefreshing(false);
                ((ImageView) findViewById(R.id.pic)).setImageBitmap(data.getImage());
                findViewById(R.id.datas).setVisibility(View.VISIBLE);
                findViewById(R.id.wait).setVisibility(View.GONE);
                ((TextView) findViewById(R.id.loca)).setText(data.getCity() + "," + data.getCountry());
                ((TextView) findViewById(R.id.curtemp)).setText(String.format("%.0f°C", data.getTemp()));
                ((TextView) findViewById(R.id.mintemp)).setText(String.format("Min Temp : %.0f°C", data.getTempMin()));
                ((TextView) findViewById(R.id.maxtemp)).setText(String.format("Max Temp : %.0f°C", data.getTempMax()));
                ((TextView) findViewById(R.id.desc)).setText(data.getDescription().toUpperCase());
                ((TextView) findViewById(R.id.hum)).setText("Humidity : " + data.getHumidity());
                ((TextView) findViewById(R.id.press)).setText("Pressure : " + data.getPressure());
                ((TextView) findViewById(R.id.windspd)).setText("Speed : " + data.getWindSpeed() + " Km/s");
                /*((TextView) findViewById(R.id.sset)).setText("Sunset :\n" + new SimpleDateFormat("hh:mm:ss aa").format(new Date(data.getSunset())));
                ((TextView) findViewById(R.id.srise)).setText("Sunrise :\n" + new SimpleDateFormat("hh:mm:ss aa").format(new Date(data.getSunrise())));*/

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
