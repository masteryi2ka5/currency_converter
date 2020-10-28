package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private List<String> items;
    private String currency1, currency2;
    private double weight1, weight2;
    private EditText edtAmount;
    private TextView tvResult;
    private Spinner spinner1, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        items.add("USD");
        items.add("EUR");
        items.add("JPY");
        items.add("GBP");
        items.add("AUD");
        items.add("CAD");
        items.add("CHF");
        items.add("CNH");
        items.add("SEK");
        items.add("NZD");
        items.add("VND");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, R.id.text_view, items);

        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                currency1 = items.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                currency2 = items.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        edtAmount = findViewById(R.id.edtAmount);
        tvResult = findViewById(R.id.tvResult);

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                double amount = s.toString().length() != 0 ? Double.parseDouble(s.toString()) : 0;
                weight1 = setWeight(currency1);
                weight2 = setWeight(currency2);
                double result = amount * weight1 / weight2;
                tvResult.setText(String.valueOf((double) round(result * 100) / 100));
            }
        });

    }

    public double setWeight(String currency) {
        switch(currency) {
            case "USD":
                return 23177;
            case "EUR":
                return 27384;
            case "JPY":
                return 221;
            case "GBP":
                return 30191;
            case "AUD":
                return 16510;
            case "CAD":
                return 17545;
            case "CHF":
                return 25544;
            case "CNH":
                return 3457;
            case "SEK":
                return 2650;
            case "NZD":
                return 15483;
            default:
                return 1;
        }
    }

}