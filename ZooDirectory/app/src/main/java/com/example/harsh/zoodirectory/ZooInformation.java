package com.example.harsh.zoodirectory;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ZooInformation extends AppCompatActivity implements View.OnClickListener {

    public Button Phonecalls;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent u = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       Phonecalls = (Button)findViewById(R.id.ph);

        Phonecalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callInt = new Intent(Intent.ACTION_DIAL);
                callInt.setData(Uri.parse("tel:8888888"));

                   try {
                       startActivity(callInt);
                   }
                   catch (Exception e)
                   {
                       Toast.makeText(ZooInformation.this, "e + " + e, Toast.LENGTH_LONG).show();
                   }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.uns)
        {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:com.example.harsh.zoodirectory"));
            startActivity(intent);
        }

        if (id == R.id.info)
        {
            Intent intent = new Intent(ZooInformation.this,ZooInformation.class);
            intent.setData(Uri.parse("package:com.example.harsh.zoodirectory"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {

    }
}
