package com.example.i851409.communityapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i851409.communityapp.data.Channel;
import com.example.i851409.communityapp.data.Item;
import com.example.i851409.communityapp.service.WeatherServiceCallback;
import com.example.i851409.communityapp.service.YahooWeatherService;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    //private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView c;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private String cit;

    private TextView last_updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);

        c = (TextView)findViewById(R.id.textView88);

        Intent refreshs = getIntent();
        cit =  refreshs.getExtras().getString("city");

        c.setText(cit);
        //Instantiating the widgets
        //weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
             last_updated = (TextView) findViewById(R.id.textView19);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();
        service.refreshWeather(cit);

        //Fetching the current date and time of the Device
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        last_updated.setText(formattedDate);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uninstall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //Statements to Uninstall the Application
        if(id == R.id.item_uninstall){
            //Now call the appropriate Intent to uninstall the application
            startActivity(WelcomePageActivity.intent_uninstall);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
