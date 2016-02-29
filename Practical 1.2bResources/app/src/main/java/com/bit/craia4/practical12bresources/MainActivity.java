package com.bit.craia4.practical12bresources;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDisplay = (TextView) findViewById(R.id.tvDisplay);

        Resources resourceResolver = getResources();
        int datesArray[] = resourceResolver.getIntArray(R.array.FebFridays);

        String txtDisplay = "February Fridays are on: ";
        String txtDates = "";

        for (int i = 0; i < datesArray.length; i++)
        {
            txtDates += datesArray[i] + " ";
        }

        /*for(int i: datesArray)
        {
            txtDisplay += datesArray;
        }*/
        txtDisplay += txtDates;
        tvDisplay.setText(txtDisplay);

    }
}
