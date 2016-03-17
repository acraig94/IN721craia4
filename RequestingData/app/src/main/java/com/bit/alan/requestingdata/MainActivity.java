package com.bit.alan.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChangeColour = (Button) findViewById(R.id.btn_ChangeColour);

        btnChangeColour.setOnClickListener(new settingsHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        TextView tvMainTxt = (TextView) findViewById(R.id.tv_MainText);

        if (requestCode == 0 && resultCode == Activity.RESULT_OK)
        {
            int txtColor = data.getIntExtra("requestedColor", 0);

            tvMainTxt.setTextColor(txtColor);
        }
    }

    public class settingsHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivityForResult(changeActivityIntent, 0);
        }

    }
}
