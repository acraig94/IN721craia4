package com.bit.alan.formattedfileio;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> eventArray;
    ListView lv_Event;
    String jsonInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFill = (Button) findViewById(R.id.btn_Fill);
        lv_Event = (ListView) findViewById(R.id.lv_Events);
        eventArray = new ArrayList<String>();
        jsonInput = loadJSONFromAsset();

        getEventList();


        btnFill.setOnClickListener(new btnFillHandler());
        lv_Event.setOnItemClickListener(new EventListViewHandler());

    }

    public class EventListViewHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position, long id)
        {
            String selectedEvent = (String) list.getItemAtPosition(position).toString();

            try {
                JSONObject eventData = new JSONObject(jsonInput);
                JSONObject eventsData = eventData.getJSONObject("events");
                JSONArray eventsArray = eventsData.getJSONArray("event");
                for (int i = 0; i < eventsArray.length(); i++)
                {
                    JSONObject currentEvent = eventsArray.getJSONObject(i);
                    String event = currentEvent.getString("title");
                    if (event.equals(selectedEvent)){
                        String description = currentEvent.getString("description");
                        Toast.makeText(MainActivity.this, description, Toast.LENGTH_LONG).show();
                    }
                }

            } catch (JSONException e) {e.printStackTrace();}


        }
    }

    public String loadJSONFromAsset() {
        String fileName = "dunedin_events.json";
        InputStream inputStream = null;
        AssetManager am = getAssets();
        String input = "";
        try {
            inputStream = am.open(fileName);
            int fileSize = inputStream.available();
            byte[] JSONBuffer = new byte[fileSize];
            inputStream.read(JSONBuffer);
            inputStream.close();
            input = new String(JSONBuffer);
            Log.e("load file", input);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error on load", "error was on load file");
        }

        return input;

    }

    public void getEventList() {

        try {
            JSONObject eventData = new JSONObject(jsonInput);
            JSONObject eventsData = eventData.getJSONObject("events");
            JSONArray eventsArray = eventsData.getJSONArray("event");
            for (int i = 0; i < eventsArray.length(); i++)
            {
                JSONObject currentEvent = eventsArray.getJSONObject(i);
                String event = currentEvent.getString("title");
                Log.e("event string", event);
                eventArray.add(event);
            }

        } catch (JSONException e) {e.printStackTrace();}


    }

    public class btnFillHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            setUpList();
        }
    }

    public void setUpList(){
        ArrayAdapter<String> eventAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventArray);
        lv_Event.setAdapter(eventAdapter);

        for (int i = 0; i < eventArray.size(); i++)
        {
            Log.e("event", eventArray.get(i));
        }
    }


}
