package com.example.alan.eventhandle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button EventTester = (Button) findViewById(R.id.button_EventTester);
        EditText MainText = (EditText) findViewById(R.id.editText_Main);

        EventTester.setOnClickListener(new NormalClickHandler());
        EventTester.setOnLongClickListener(new LongClickHandler());

        MainText.setOnKeyListener(new AtSymbolClickHandler());
    }

    public class NormalClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this, "Normal click", Toast.LENGTH_LONG).show();
        }
    }

    public class LongClickHandler implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View v)
        {
            Toast.makeText(MainActivity.this, "Long click", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    public class AtSymbolClickHandler implements View.OnKeyListener
    {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            /* Task 2.1
            if (keyCode == KeyEvent.KEYCODE_AT)
            {
                Toast.makeText(MainActivity.this, "Don't type @", Toast.LENGTH_LONG).show();
            }
            */

            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                EditText MainText = (EditText) findViewById(R.id.editText_Main);
                String username = MainText.getText().toString();

                if (username.length() == 8)
                {
                    Toast.makeText(MainActivity.this, "Thank you " + username, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Usernames must be 8 characters, " + username, Toast.LENGTH_LONG).show();
                }
            }


            return true;
        }
    }


}
