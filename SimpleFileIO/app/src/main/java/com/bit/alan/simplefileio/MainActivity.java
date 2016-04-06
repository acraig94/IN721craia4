package com.bit.alan.simplefileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cityNameArray;
    ListView lv_City;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFill = (Button) findViewById(R.id.btn_Fill);
        lv_City = (ListView) findViewById(R.id.lv_Places);
        cityNameArray = new ArrayList<String>();

        String assetFileName = "citys.txt";
        AssetManager am = getAssets();

        InputStream is = null;
        try {
            is = am.open(assetFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);

        String newCity;
        try {
            while((newCity = br.readLine()) != null){
                cityNameArray.add(newCity);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnFill.setOnClickListener(new btnFillHandler());

    }

    public class btnFillHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            setUpList();
        }
    }

    public void setUpList(){
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityNameArray);
        lv_City.setAdapter(cityAdapter);
    }


}
