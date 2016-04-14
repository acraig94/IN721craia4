package com.bit.alan.supportusersearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_Search;
    ListView lv_Artists;
    ArrayList<String> artistList;
    ArrayAdapter<String> artistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistList = new ArrayList<String>();

        lv_Artists = (ListView) findViewById(R.id.lv_Artists);
        btn_Search = (Button) findViewById(R.id.btn_Fill);


        btn_Search.setOnClickListener(new searchButtonListener());

    }

    public class searchButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v)
        {
            EditText editText = (EditText) findViewById(R.id.editText);
            String input = editText.getText().toString();

            TopArtistAPI APIThread = new TopArtistAPI();
            APIThread.execute(input);
        }
    }

    public class TopArtistAPI extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String JSONString = null;

            try {
                String userArtist = params[0];
                Log.e("userArtistBackground", userArtist);
                String urlString = "http://ws.audioscrobbler.com/2.0/?" +
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&" +
                        "artist="+userArtist+"&"+
                        "method=artist.getSimilar&" +
                        "limit=10&" +
                        "format=json";

                URL urlObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    InputStream is = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(reader);
                    String responseStr;
                    StringBuilder strBuilder = new StringBuilder();
                    while ((responseStr = br.readLine()) != null) {
                        strBuilder = strBuilder.append(responseStr);
                    }
                    JSONString = strBuilder.toString();
                }
            }catch (Exception e){e.printStackTrace();};
            Log.e("jsonString", JSONString);
            return JSONString;
        }

        protected void onPostExecute(String s){
            try {
                JSONObject jsonData = new JSONObject(s);
                JSONObject artistsData = jsonData.getJSONObject("similarartists");
                JSONArray artistArray = artistsData.getJSONArray("artist");
                //Log.e("artistArrayLength", ""+artistArray.length());
                for (int i = 0; i < artistArray.length(); i++)
                {
                    JSONObject currentArtist = artistArray.getJSONObject(i);
                    String bandName = currentArtist.getString("name");
                    //Artist artist = new Artist(bandName);
                    //Log.e("bandName", bandName);
                    artistList.add(bandName);
                    //countList.add(playcount);
                }

            } catch (JSONException e) {e.printStackTrace();}

            
            artistAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, artistList);

            for (int i = 0; i < artistAdapter.getCount(); i++) {
                artistAdapter.remove(artistAdapter.getItem(i));
            }
            artistAdapter.notifyDataSetChanged();
            
            lv_Artists.setAdapter(artistAdapter);

        }


    }



}
