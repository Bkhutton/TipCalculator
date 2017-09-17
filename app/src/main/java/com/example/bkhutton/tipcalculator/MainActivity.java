package com.example.bkhutton.tipcalculator;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate = (Button) findViewById(R.id.calculateButton);
        calculate.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {
        calculateTip();
    }

    private float getPercent() {

        RadioButton ten = (RadioButton) findViewById(R.id.ten);
        RadioButton fifteen = (RadioButton) findViewById(R.id.fifteen);
        RadioButton twenty = (RadioButton) findViewById(R.id.twenty);

        if (ten.isChecked()) {
            return .1f;
        }
        if (fifteen.isChecked()) {
            return .15f;
        }
        if (twenty.isChecked()) {
            return .2f;
        }

        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calculateTip() {
        EditText amountText = (EditText) findViewById(R.id.amount);
        TextView output = (TextView) findViewById(R.id.outputText);
        String amount = amountText.getText().toString();

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        if (amount.matches("")) {
            output.setText("Enter a bill amount");
            return;
        }

        float bill = Float.valueOf(amount);
        float tip = bill * getPercent();


        output.setText("Your tip will be " + numberFormat.format(tip));
    }
}
