package com.szubov.android_hw_52;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeliveryFormActivity extends AppCompatActivity {

    private Button mBtnDeliveryOk;
    private Button mBtnReturnFromDeliveryForm;
    private Spinner mSpinnerCountries;
    private Spinner mSpinnerCities;
    private Spinner mSpinnerHouseNumbers;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_form);

        initViews();

    }

    private void initViews() {
        mBtnDeliveryOk = findViewById(R.id.btnDeliveryOk);
        mSpinnerCountries = findViewById(R.id.spinnerCountries);
        mSpinnerCities = findViewById(R.id.spinnerCities);
        mSpinnerHouseNumbers = findViewById(R.id.spinnerHouseNumbers);
        mBtnReturnFromDeliveryForm = findViewById(R.id.btnReturnFromDeliveryForm);

        mSpinnerHouseNumbers.setPrompt(getText(R.string.prompt_house_numbers));
        mSpinnerCountries.setPrompt(getText(R.string.prompt_countries));
        mSpinnerCities.setPrompt(getText(R.string.prompt_cities));

        initSpinnerCountries();
        initHouseNumbersSpinner();

        mBtnDeliveryOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User clicked btn deliveryOk in DeliveryFormActivity");

                Toast.makeText(DeliveryFormActivity.this,
                        getText(R.string.delivery_address) + "\n" +
                        mSpinnerCountries.getSelectedItem().toString() + "\n" +
                        mSpinnerCities.getSelectedItem().toString() + "\n" +
                        getText(R.string.house_number).toString() +
                        mSpinnerHouseNumbers.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                mSpinnerCountries.setSelection(0);
                mSpinnerHouseNumbers.setSelection(0);
            }
        });

        mBtnReturnFromDeliveryForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initHouseNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i= 1; i <= 50; i++) {
            houseNumbers[i-1] = i;
        }
        ArrayAdapter<Integer> adapterNumbersHouse = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, houseNumbers);
        mSpinnerHouseNumbers.setAdapter(adapterNumbersHouse);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this,
                R.array.countries_list, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCountries.setAdapter(adapterCountries);

        mSpinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "User selected spinnerCountries item in DeliveryFormActivity");
                String[] countries = getResources().getStringArray(R.array.countries_list);
                initSpinnerCities(countries[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapterCities =  null;

        switch (country) {
            case "Россия":
                adapterCities = ArrayAdapter.createFromResource(this,
                        R.array.russia_cities_list, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapterCities = ArrayAdapter.createFromResource(this,
                        R.array.ukraine_cities_list, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapterCities = ArrayAdapter.createFromResource(this,
                        R.array.belarus_cities_list, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapterCities != null) {
            adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerCities.setAdapter(adapterCities);
        }
    }
}