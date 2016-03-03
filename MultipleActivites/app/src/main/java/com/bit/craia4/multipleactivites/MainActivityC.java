package com.bit.craia4.multipleactivites;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView display = (TextView) findViewById(R.id.tb_DisplayActivity);
        Button changeButton = (Button) findViewById(R.id.button_Change);

        display.setText("Activity C");
        changeButton.setOnClickListener(new changeActivityButton());
    }

    public class changeActivityButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Uri goSeeGoogle = Uri.parse("http://www.google.com");
            Intent googleIntend = new Intent(Intent.ACTION_VIEW, goSeeGoogle);
            startActivity(googleIntend);
        }
    }
}
