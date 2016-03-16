package com.example.harsh.mortgagecalculatorbyharsh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

// declare buttons

    private Button calculate;
    private TextView result;
    private EditText amtbrr;
    private SeekBar interestrate;
    private RadioButton r1, r2, r3;
    private CheckBox taxins;
    private TextView covered;
    private RadioGroup radioGroup;

// method for seekbar where various operations takes place including onprogress onstart and onstop
    public void seebbar()
    {
        interestrate = (SeekBar) findViewById(R.id.ir);
        covered = (TextView) findViewById(R.id.covered);
        interestrate.setMax(1000);

        interestrate.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {
                    double progress_value;

                    @Override
                    //tracking the progress of seekbar
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = (double) (progress);
                        covered.setText("Interest Rate : " + (progress / 100.00) + " / " + (double) (interestrate.getMax() / 100.00));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        covered.setText("Interest Rate : " + progress_value / 100.00 + " / " + (double) (interestrate.getMax() / 100.00));
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        seebbar();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                                              {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId)
                                                  {
                                                      RadioButton rb = (RadioButton) group.findViewById(checkedId);
                                                      if (rb !=null && checkedId > 0)
                                                      {
                                                          Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                                                      }

                                                }
                                              }


        );
    }

    public void onClear(View v)

    {
        radioGroup.clearCheck();
    }
    public void onSubmit(View v)
    {

        RadioButton rb = (RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();

    }

    // method where all buttons and textview ids are linked
    private void init()
    {
        calculate = (Button) findViewById(R.id.cal);
        result = (TextView) findViewById(R.id.res);
        amtbrr = (EditText) findViewById(R.id.ab);
        interestrate = (SeekBar) findViewById(R.id.ir);
        r1 = (RadioButton) findViewById(R.id.lt1);
        r2 = (RadioButton) findViewById(R.id.lt2);
        r3 = (RadioButton) findViewById(R.id.lt3);
        taxins = (CheckBox) findViewById(R.id.ti);
        covered = (TextView) findViewById(R.id.covered);

        // method call to calculate
        calculate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        EditText amtbrr = (EditText) findViewById(R.id.ab);
        String amt = amtbrr.getText().toString();
        String hello = getResources().getString(R.string.v);
        if(TextUtils.isEmpty(amt))
        {
            amtbrr.setError(hello);
            return;
        }
            double h = interestrate.getProgress();
            double i = h/100;
        RadioGroup group;
        int checkedId;

        RadioGroup rb1 = (RadioGroup) findViewById(R.id.radioGroup);
        int g = rb1.getCheckedRadioButtonId();

        if (g == -1)
        {
            Toast.makeText(MainActivity.this, "Select any Loan Term to Calculate", Toast.LENGTH_SHORT).show();
        }

        // Actual calculation

        // if the interest rate is set to 0
        if (i != 0)
        {


                switch (v.getId())
                {
                    case R.id.cal:

                        // if radio button 1 is selected i.e 7 years
                        if (r1.isChecked())
                        {
                            // if tax and insurance is checked
                            if (taxins.isChecked())
                            {
                                double M = (Double.parseDouble(amt) * (((i)/1200) / (1 - (Math.pow((1 + (i / 1200)), (-7*12)))))) + ((0.1 / 100) * Double.parseDouble(amt));
                                result.setText(String.valueOf(M));
                            }
                            else
                            {
                                double M = (Double.parseDouble(amt) * (((i)/1200) / (1 - (Math.pow((1 + (i / 1200)), (-7*12))))));
                                result.setText(String.valueOf(M));
                            }
                        }

                        //if radio button 2 is selected i.e 15 years
                        if (r2.isChecked())
                        {

                            //tax and insurance is checked
                            if (taxins.isChecked())
                            {
                                double M = (Double.parseDouble(amt) * (((i)/1200) / (1 - (Math.pow((1 + (i / 1200)), (-15*12)))))) + ((0.1 / 100) * Double.parseDouble(amt));
                                result.setText(String.valueOf(M));
                            }
                            else
                            {
                                double M = (Double.parseDouble(amt) * (((i)/1200) / (1 - (Math.pow((1 + (i / 1200)), (-15*12))))));
                                result.setText(String.valueOf(M));

                            }
                        }

                        // if radio button 1 is selected i.e 30 years
                        if (r3.isChecked())
                        {

                            //tax and insurance is checked
                            if (taxins.isChecked())
                            {
                                double M = (Double.parseDouble(amt) * (((i)/1200) / (1 - (Math.pow((1 + (i / 1200)), (-30*12)))))) + ((0.1 / 100) * Double.parseDouble(amt));
                                result.setText(String.valueOf(M));
                            }
                            else
                            {
                                double M = (Double.parseDouble(amt) * (((i)/1200) / (1 - (Math.pow((1 + (i / 1200)), (-30*12))))));
                                result.setText(String.valueOf(M));
                            }
                        }
                }

            }
            else
            {
                switch (v.getId())
                {
                    case R.id.cal:

                        // if radio button 1 is selected i.e 7 years
                        if (r1.isChecked())
                        {
                            //tax and insurance is checked
                            if (taxins.isChecked())
                            {
                                double M = (Double.parseDouble(amt) / (7*12)) + ((0.1 / 100) * Double.parseDouble(amt));
                                result.setText(String.valueOf(M));
                            } else {
                                double M = (Double.parseDouble(amt) / (7*12));
                                result.setText(String.valueOf(M));
                            }
                        }

                        // if radio button 1 is selected i.e 15 years
                        if (r2.isChecked())
                        {
                            //tax and insurance is checked
                            if (taxins.isChecked())
                            {
                                double M = (Double.parseDouble(amt) / (15*12)) + ((0.1 / 100) * Double.parseDouble(amt));
                                result.setText(String.valueOf(M));
                            }
                            else
                            {
                                double M = (Double.parseDouble(amt) / (15*12));
                                result.setText(String.valueOf(M));
                            }
                        }

                        // if radio button 1 is selected i.e 30 years
                        if (r3.isChecked())
                        {
                            //tax and insurance is checked
                            if (taxins.isChecked())
                            {
                                double M = (Double.parseDouble(amt) / (30*12)) + ((0.1 / 100) * Double.parseDouble(amt));
                                result.setText(String.valueOf(M));
                            }
                            else
                            {
                                double M = (Double.parseDouble(amt) / (30*12));
                                result.setText(String.valueOf(M));
                            }
                        }
                }
            }
        }
    }


