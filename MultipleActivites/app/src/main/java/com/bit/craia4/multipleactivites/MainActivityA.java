package com.bit.craia4.multipleactivites;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView display = (TextView) findViewById(R.id.tb_DisplayActivity);
        Button changeButton = (Button) findViewById(R.id.button_Change);

        display.setText("Activity A");
        changeButton.setOnClickListener(new changeActivityButton());
    }

    public class changeActivityButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntend = new Intent(MainActivityA.this, MainActivityB.class);
            startActivity(changeActivityIntend);
        }
    }
}
