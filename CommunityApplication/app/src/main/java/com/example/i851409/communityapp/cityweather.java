package com.example.i851409.communityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class cityweather extends AppCompatActivity {
    private EditText city;
    String cities;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityweather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        city = (EditText) findViewById(R.id.editText48);
        go = (Button) findViewById(R.id.button59);


        cities = city.getText().toString();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(cityweather.this,WeatherActivity.class);

                refresh.putExtra("city",cities);
                startActivity(refresh);
                //finish();

            }
        });





    }

}
