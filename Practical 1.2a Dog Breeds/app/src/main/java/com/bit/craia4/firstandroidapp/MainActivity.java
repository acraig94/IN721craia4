package com.bit.craia4.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView RandText = (TextView) findViewById(R.id.textDisplay);
        String[] dogBreeds = {"Poodle","Labrador", "Shar Pei", "NewFoundland"};
        String selectedBreed = "";
        Random rand = new Random();

        switch (rand.nextInt(4))
        {
            case 0:
                selectedBreed = dogBreeds[0];
                break;
            case 1:
                selectedBreed = dogBreeds[1];
                break;
            case 2:
                selectedBreed = dogBreeds[2];
                break;
            case 3:
                selectedBreed = dogBreeds[3];
                break;
        }

        RandText.setText(selectedBreed);


    }
}
