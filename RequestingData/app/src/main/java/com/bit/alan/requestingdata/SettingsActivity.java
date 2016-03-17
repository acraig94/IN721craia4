package com.bit.alan.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView txt = (TextView) findViewById(R.id.tv_SettingsText);
        int colour = txt.getTextColors().getDefaultColor();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("requestedColor", colour);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }
}
