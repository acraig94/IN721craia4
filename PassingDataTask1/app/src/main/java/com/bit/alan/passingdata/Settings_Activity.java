package com.bit.alan.passingdata;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnReturn = (Button) findViewById(R.id.btn_Return);
        btnReturn.setOnClickListener(new returnHandler());
    }

    public class returnHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            EditText txt = (EditText) findViewById(R.id.txt_UserInput);
            String username = txt.getText().toString();

            if (username.length() < 5)
            {
                String error = "Username must be atleast 5 characters long";
                Toast.makeText(Settings_Activity.this, error, Toast.LENGTH_LONG).show();
            }
            else {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("requestedResult", username);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }

}
