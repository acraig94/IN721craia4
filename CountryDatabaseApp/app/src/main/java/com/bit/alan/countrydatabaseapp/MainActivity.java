package com.bit.alan.countrydatabaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase countryDB;
    ListView lv_Countrys;
    ArrayList<String> countryArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryDB = openOrCreateDatabase("countryDB", MODE_PRIVATE, null);
        lv_Countrys = (ListView) findViewById(R.id.lv_Country);
        countryArray = new ArrayList<String>();
        dropTable();
        createTable();
        populateDB();
        getCountries();
        setUpList();

        lv_Countrys.setOnItemClickListener(new CountryListViewHandler());

    }

    public class CountryListViewHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position, long id)
        {
            String selectedCountry = (String) list.getItemAtPosition(position).toString();
            Intent resultsIntent = new Intent(MainActivity.this, ResultsActivity.class);
            resultsIntent.putExtra("country", selectedCountry);
            resultsIntent.putExtra("cityList", getCities(selectedCountry));
            startActivity(resultsIntent);
        }
    }

    public void setUpList()
    {
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryArray);
        lv_Countrys.setAdapter(countryAdapter);
    }

    public void dropTable()
    {
        String selectQuery = "DROP TABLE tblCity";
        countryDB.execSQL(selectQuery);
    }

    public void getCountries()
    {
        String selectQuery = "SELECT DISTINCT countryName FROM tblCity ORDER BY countryName";
        Cursor recordSet = countryDB.rawQuery(selectQuery, null);
        int recordCount = recordSet.getCount();
        int countryNameIndex = recordSet.getColumnIndex("countryName");
        recordSet.moveToFirst();
        for (int i = 0; i < recordCount; i++)
        {
            String countryName = recordSet.getString(countryNameIndex);
            countryArray.add(countryName);
            recordSet.moveToNext();
        }
    }

    public ArrayList<String> getCities(String selectedCountry)
    {
        ArrayList<String> cityArray = new ArrayList<String>();
        String selectQuery = "SELECT cityName FROM tblCity WHERE countryName = '" + selectedCountry + "' ORDER BY cityName";
        Cursor recordSet = countryDB.rawQuery(selectQuery, null);
        int recordCount = recordSet.getCount();
        int cityNameIndex = recordSet.getColumnIndex("cityName");
        recordSet.moveToFirst();
        for (int i = 0; i < recordCount; i++)
        {
            String countryName = recordSet.getString(cityNameIndex);
            cityArray.add(countryName);
            recordSet.moveToNext();
        }
        return cityArray;
    }

    public void createTable()
    {
        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" +
                                "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "cityName TEXT NOT NULL, " +
                                "countryName TEXT NOT NULL);";
        countryDB.execSQL(createQuery);
    }

    public void populateDB()
    {
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Amsterdam', 'Netherlands')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Rotterdam', 'Netherlands')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Zutphen', 'Netherlands')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Budapest', 'Hungary')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Debrecen', 'Hungary')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Boston', 'USA')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'New York', 'USA')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Istanbul', 'Turkey')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Trabzon', 'Turkey')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Bodrum', 'Turkey')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Barcelona', 'Spain')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Madrid', 'Spain')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Malaga', 'Spain')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Glasgow', 'Scotland')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Stirling', 'Scotland')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Dundee', 'Scotland')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Aberdeen', 'Scotland')");
        countryDB.execSQL("INSERT INTO tblCity VALUES(null, 'Inverness', 'Scotland')");

    }





}
