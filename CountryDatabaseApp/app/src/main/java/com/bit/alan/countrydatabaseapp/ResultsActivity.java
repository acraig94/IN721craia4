package com.bit.alan.countrydatabaseapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    //SQLiteDatabase countryDB;
    ListView lv_city;
    ArrayList<String> cityArray;
    String selectedCountry;
    TextView countryText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        countryText = (TextView) findViewById(R.id.tv_selectedCountryText);
        lv_city = (ListView) findViewById(R.id.lv_Results);
        Intent intent = getIntent();
        cityArray = intent.getStringArrayListExtra("cityList");
        selectedCountry = intent.getStringExtra("country");
        countryText.setText(selectedCountry);

        setUpList();

    }

    public void setUpList()
    {
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityArray);
        lv_city.setAdapter(cityAdapter);
    }

}
