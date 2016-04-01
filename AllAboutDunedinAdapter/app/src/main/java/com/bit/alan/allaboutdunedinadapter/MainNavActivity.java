package com.bit.alan.allaboutdunedinadapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainNavActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        setUpNavList();

        ListView navListView = (ListView) findViewById(R.id.LV_navScreen);
        navListView.setOnItemClickListener(new ScreenNavListClickHandler());

    }



    public class ScreenNavListClickHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position, long id)
        {
            String clickedItem = (String) list.getItemAtPosition(position).toString();
            Intent goToIntent;

            switch (clickedItem)
            {
                case "Services":
                    goToIntent = new Intent(MainNavActivity.this, SubActivityServices.class);
                    break;
                case "Fun Things To Do":
                    goToIntent = new Intent(MainNavActivity.this, SubActivityFunThings.class);
                    break;
                case "Dining":
                    goToIntent = new Intent(MainNavActivity.this, SubActivityDining.class);
                    break;
                case "Shopping":
                    goToIntent = new Intent(MainNavActivity.this, SubActivityShopping.class);
                    break;
                default:
                    goToIntent = null;
                    break;
            }

            startActivity(goToIntent);

        }
    }

    public void setUpNavList()
    {
        String[] screens = {"Services", "Fun Things To Do", "Dining", "Shopping"};
        ArrayAdapter<String> navAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, screens);

        ListView NavListView = (ListView) findViewById(R.id.LV_navScreen);
        NavListView.setAdapter(navAdapter);
    }


}
