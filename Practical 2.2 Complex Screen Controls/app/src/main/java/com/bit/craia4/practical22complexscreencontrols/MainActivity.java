package com.bit.craia4.practical22complexscreencontrols;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button confirmButton = (Button) findViewById(R.id.button_Confirm);

        confirmButton.setOnClickListener(new ConfirmButtonClickHandle());

        String[] months =
                {
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
                };

        Spinner monthSpinner = (Spinner)findViewById(R.id.spinner_months);
        int layoutID = android.R.layout.simple_spinner_item;
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, layoutID, months);
        monthSpinner.setAdapter(monthAdapter);

    }

    public class ConfirmButtonClickHandle implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            RadioGroup InstrumentGroup = (RadioGroup) findViewById(R.id.rdoGroup_Instrument);
            int selectedID = InstrumentGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = (RadioButton) findViewById(selectedID);

            Spinner monthSpinner = (Spinner)findViewById(R.id.spinner_months);
            String monthSelection = monthSpinner.getSelectedItem().toString();

            Toast.makeText(MainActivity.this, "You have enrolled for " + selectedRadio.getText() + " lessons in " + monthSelection, Toast.LENGTH_LONG).show();


        }
    }
}
