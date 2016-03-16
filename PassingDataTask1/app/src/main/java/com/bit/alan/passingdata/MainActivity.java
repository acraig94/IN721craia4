package com.bit.alan.passingdata;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSettings = (Button) findViewById(R.id.btn_Settings);

        btnSettings.setOnClickListener(new settingsHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        TextView tvUsername = (TextView) findViewById(R.id.tv_Username);

        if (requestCode == 0 && resultCode == Activity.RESULT_OK)
        {
            String result = data.getStringExtra("requestedResult");
            tvUsername.setText(result);

        }
    }

    public class settingsHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, Settings_Activity.class);

            startActivityForResult(changeActivityIntent, 0);
        }

    }


}
