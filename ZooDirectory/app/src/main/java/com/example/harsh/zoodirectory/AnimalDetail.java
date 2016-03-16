package com.example.harsh.zoodirectory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalDetail extends AppCompatActivity implements View.OnClickListener {

    private TextView animal;
    private ImageView img;
    private TextView animaldis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);
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


        Intent i = getIntent();
        int temp = i.getIntExtra("gettex", 0);

        animal = (TextView) findViewById(R.id.an);

        img = (ImageView) findViewById(R.id.im);

        animaldis = (TextView) findViewById(R.id.andis);


        if (temp == 0) {
            animal.setText("TIGER");
            img.setImageResource(R.drawable.a);
            animaldis.setText("The tiger (Panthera tigris) is the largest cat species, reaching a total body length of up to 3.38 m (11.1 ft) over curves and exceptionally weighing up to 388.7 kg (857 lb) in the wild. Its has a pattern of dark vertical stripes.");

        }

        if (temp == 1) {
            animal.setText("LION");
            img.setImageResource(R.drawable.d);
            animaldis.setText("The lion (Panthera leo) is one of the five big cats in the genus Panthera and a member of the family Felidae. The commonly used term African lion collectively denotes the several subspecies found in Africa. With some males exceeding 250 kg.");
        }
        if (temp == 2) {
            animal.setText("CHEETAH");
            img.setImageResource(R.drawable.c);
            animaldis.setText("The cheetah (Acinonyx jubatus) is a big cat in the subfamily Felinae that inhabits most of Africa and parts of Iran. It is the only extant member of the genus Acinonyx. The cheetah can run upto 120.7 km/h, faster than any other land animal.");
        }
        if (temp == 3) {
            animal.setText("ELEPHANT");
            img.setImageResource(R.drawable.b);
            animaldis.setText("Elephants are large mammals of the family Elephantidae and the order Proboscidea. Elephants are scattered throughout sub-Saharan Africa, South Asia, and Southeast Asia. Elephantidae is the only surviving family of the order Proboscidea.");
        }
        if (temp == 4) {
            animal.setText("KANGAROO");
            img.setImageResource(R.drawable.e);
            animaldis.setText("As with the terms wallaroo and wallaby,kangaroo refers to a polyphyletic grouping of species. All refer to members of the same taxonomic family.Kangaroos have large, powerful hind legs, large feet for leaping, a long tail for balance.");
        }
        if (temp == 5) {
            animal.setText("BEAR");
            img.setImageResource(R.drawable.f);
            animaldis.setText("Common characteristics of modern bears include large bodies with stocky legs, long snouts, shaggy hair, plantigrade paws with five claws, and short tails. While the polar bear are carnivorous, and the giant panda feeds on bamboo.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.uns)
        {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:com.example.harsh.zoodirectory"));
            startActivity(intent);
        }

        if (id == R.id.info)
        {
            Intent intent = new Intent(AnimalDetail.this,ZooInformation.class);
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
