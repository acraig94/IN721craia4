package com.bit.alan.languagepreference;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner languageSpinner;
    Spinner colourSpinner;
    Button btnSubmit;
    TextView tv_Greeting;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("prefsLanguage", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        btnSubmit = (Button) findViewById(R.id.btn_Submit);
        languageSpinner = (Spinner)findViewById(R.id.spinner_Languages);
        colourSpinner = (Spinner)findViewById(R.id.spinner_Colour);
        tv_Greeting = (TextView) findViewById(R.id.tv_language);

        String[] languages = {"French","German","Spanish"};
        String[] colours = {"Red", "Blue", "Yellow"};

        int layoutID = android.R.layout.simple_spinner_item;
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<String>(this, layoutID, languages);
        ArrayAdapter<String> coloursAdapter = new ArrayAdapter<String>(this, layoutID, colours);

        languageSpinner.setAdapter(languageAdapter);
        colourSpinner.setAdapter(coloursAdapter);

        String languagePreference = prefs.getString("language", null);
        String colourPreference = prefs.getString("colour", null);

        if(languagePreference != null){
            tv_Greeting.setText(getLanguageText(languagePreference));
        }
        if(colourPreference != null){
            tv_Greeting.setTextColor(getTextColour(colourPreference));
        }

        btnSubmit.setOnClickListener(new btnSubmitHandler());

    }

    public class btnSubmitHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            String selectedLanguage = languageSpinner.getSelectedItem().toString();
            String selectedColour = colourSpinner.getSelectedItem().toString();

            prefsEditor.putString("language", selectedLanguage);
            prefsEditor.putString("colour", selectedColour);
            prefsEditor.commit();

            tv_Greeting.setText(getLanguageText(selectedLanguage));
            tv_Greeting.setTextColor(getTextColour(selectedColour));

            Log.d("lang ", selectedLanguage);
            Log.d("lang output ",getLanguageText(selectedLanguage));
            Log.d("colour ", selectedColour);
            Log.d("colour output ", "" + getTextColour(selectedColour));

        }
    }

    public void setGreeting()
    {
        String selectedLanguage = languageSpinner.getSelectedItem().toString();
        String selectedColour = colourSpinner.getSelectedItem().toString();
        tv_Greeting.setText(getLanguageText(selectedLanguage));
        tv_Greeting.setTextColor(getTextColour(selectedColour));
        Log.d("colour ",selectedColour);
    }

    public String getLanguageText(String s)
    {
        String str = "";
        switch (s)
        {
            case "French":
                str = "Bonjour Le Monde";
                break;
            case "German":
                str = "Hallo Welt";
                break;
            case "Spanish":
                str = "Hola Mundo";
                break;
            default:
                break;
        }

        return str;

    }

    public int getTextColour(String s)
    {
        int i;
        switch (s)
        {
            case "Red":
                i = Color.RED;
                break;
            case "Blue":
                i = Color.BLUE;
                break;
            case "Yellow":
                i = Color.YELLOW;
                break;
            default:
                i = Color.BLACK;
        }
        return i;
    }

}
