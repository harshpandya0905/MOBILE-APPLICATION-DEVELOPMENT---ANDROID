package com.example.harsh.zoodirectory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class animallisting extends AppCompatActivity implements View.OnClickListener
{
    String[] Animallist;
    int[] images = {R.drawable.a, R.drawable.d, R.drawable.c, R.drawable.b, R.drawable.e, R.drawable.f};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animallisting);
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


        listView = (ListView) findViewById(R.id.list_view);

        Resources res = getResources();

        Animallist = res.getStringArray(R.array.AnimalListing);

        CustomAdapter adapter = new CustomAdapter(this, Animallist,images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            Intent i = new Intent(getApplicationContext(), AnimalDetail.class);
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                                            {

                                                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position+1) + " row is selected", Toast.LENGTH_LONG).show();
                                                i.putExtra("gettex",position);

                                                if(position==0)
                                                {
                                                    startActivity(i);
                                                }

                                                if(position==1)
                                                {
                                                    startActivity(i);
                                                }
                                                if(position==2)
                                                {
                                                    startActivity(i);
                                                }
                                                if(position==3)
                                                {
                                                    startActivity(i);
                                                }
                                                if(position==4)
                                                {
                                                    startActivity(i);
                                                }

                                                if(position==5)
                                                {
                                                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which)
                                                        {
                                                            switch (which)
                                                            {
                                                                case DialogInterface.BUTTON_POSITIVE:
                                                                    startActivity(i);
                                                                    break;

                                                                case DialogInterface.BUTTON_NEGATIVE:
                                                                    break;

                                                            }

                                                        }
                                                    };
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                                    builder.setTitle("WARNING !! ");
                                                    builder.setMessage("This is a scary animal .. do you want to continue??").setPositiveButton("Yes", dialogClickListener)
                                                            .setNegativeButton("No", dialogClickListener).show();
                                                }
                                            }
                                        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            Intent intent = new Intent(animallisting.this,ZooInformation.class);
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

class CustomAdapter extends ArrayAdapter<String>
{
    int [] images;
    String [] animalname;
    Context context;

    //Constructor
    CustomAdapter(Context c, String AnimalListing[], int imgs[])
    {
        super(c, R.layout.single_row, R.id.textView3, AnimalListing);
        this.context = c;
        this.images = imgs;
        this.animalname= AnimalListing;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflator.inflate(R.layout.single_row, parent, false);
        ImageView myImage = (ImageView)row.findViewById(R.id.rowImage);
        TextView myanimal = (TextView)row.findViewById(R.id.textView3);
        myImage.setImageResource(images[position]);
        myanimal.setText(animalname[position]);
        return row;
    }
}