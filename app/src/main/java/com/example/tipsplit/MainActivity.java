package com.example.tipsplit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    private TextView totalTip;
    private TextView totalCost;
    private TextView costPerPerson;
    private TextView overage;
    private EditText input1;
    private EditText input2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalTip = (TextView)findViewById(R.id.displayTip);
        totalCost = (TextView)findViewById(R.id.displayTotal);
        costPerPerson = (TextView) findViewById(R.id.displayPerPerson);
        overage = (TextView) findViewById(R.id.costOverage);
        input1 = (EditText) findViewById(R.id.userInput1);
        input2 = (EditText) findViewById(R.id.totalPeople);

        //TextView dtView
    }
    public void onClick (View v) {

        //input1 = (EditText) findViewById(R.id.userInput1);
        int temp = 0;
        if(input1.getText().toString().isEmpty()) {
            //System.out.println("in here");
            radioGroup = findViewById(R.id.tipPercentages);
            int id = radioGroup.getCheckedRadioButtonId();
            radioButton=findViewById(id);
            //radioButton.setClickable(false);
            radioButton.setChecked(false);
            Toast.makeText(MainActivity.this, "Please add a cost first", Toast.LENGTH_LONG).show();
        }
    else if (!input1.getText().toString().isEmpty()){
            radioGroup = findViewById(R.id.tipPercentages);
            int id = radioGroup.getCheckedRadioButtonId();
            radioButton=findViewById(id);
            radioButton.setClickable(true);
            float num1 = Float.parseFloat(input1.getText().toString());
            if (Float.parseFloat(input1.getText().toString()) <= 0.0) {
                Toast.makeText(MainActivity.this, "Please submit a positive whole number", Toast.LENGTH_LONG).show();
                input1.getText().clear();
                totalCost.setText("");
                totalTip.setText("");
            }
            float total = num1;

            float percent;
            float tip = 0;

            radioGroup = findViewById(R.id.tipPercentages);
            id = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(id);
            String cool = radioButton.getText().toString().substring(0, 2);
            percent = Float.parseFloat(cool);
            tip = ((float) (total * (percent / 100.0)));

            //my way of getting the numbers to two decimal places
            DecimalFormat frmt = new DecimalFormat();
            frmt.setMaximumFractionDigits(2);

            totalTip.setText("$" +Float.toString(Float.parseFloat(frmt.format(tip))));
            float newTotal = total + tip;
            newTotal = Float.parseFloat(frmt.format(newTotal));
            //totalCost.setText(Float.toString(Float.parseFloat(frmt.format(newTotal))));
            totalCost.setText("$" + Float.toString(newTotal));
        }


        //while(radioButton.isChecked()!=true) {
            //((RadioButton) v).setChecked(false);
       // }






    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putString("TIP", totalTip.getText().toString());
        outState.putString("COST", totalCost.getText().toString());
        outState.putString("PERSON", costPerPerson.getText().toString());
        outState.putString("OVERAGE", overage.getText().toString());
        //per the lecture call super last
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        totalTip.setText(savedInstanceState.getString("TIP"));
        totalCost.setText(savedInstanceState.getString("COST"));
        costPerPerson.setText(savedInstanceState.getString("PERSON"));
        overage.setText(savedInstanceState.getString("OVERAGE"));
    }

    public void clearText (View v) {
        totalCost.setText("");
        totalTip.setText("");
        costPerPerson.setText("");
        overage.setText("");
        input1.getText().clear();
        input2.getText().clear();

        radioGroup = findViewById(R.id.tipPercentages);
        int id = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(id);
        radioButton.setChecked(false);


    }

    public void handleText (View v) {
       //TextView totalCost = (TextView)findViewById(R.id.displayTotal);
       //TextView costPerPerson = (TextView)findViewById(R.id.displayPerPerson);
       //EditText input2 = (EditText) findViewById(R.id.totalPeople);
       //TextView overage = (TextView) findViewById(R.id.costOverage);

        if(input1.getText().toString().isEmpty() || totalTip.getText().toString().isEmpty() || totalCost.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please add a cost and tip percentage first", Toast.LENGTH_LONG).show();
        }

        else if ((!input1.getText().toString().isEmpty() || !totalTip.getText().toString().isEmpty() || !totalCost.getText().toString().isEmpty())){
            int num2 = Integer.parseInt(input2.getText().toString());
            int size = totalCost.getText().toString().length();
            String temp1 = totalCost.getText().toString().substring(1, size);
            float total = Float.parseFloat(temp1.toString());

            if(num2<=0) {
                Toast.makeText(MainActivity.this, "Please add a positive number of people", Toast.LENGTH_LONG).show();

            }
            else if(num2>0) {
            double perPerson = total/num2;
       perPerson = Math.round(perPerson * 10.00)/10.00;
       //perPerson = perPerson/100;
       String lol = String.format("%.2f", perPerson);
       costPerPerson.setText("$" + lol);
       //costPerPerson.setText(String.format(Double.toString(perPerson), "%.2f" ));
       double ov = (perPerson * num2) - total;
       ov = Math.round(ov * 100.00)/100.00;
       //ov = ov/10.00;
       overage.setText("$" + Double.toString(ov)); } }










   }
}