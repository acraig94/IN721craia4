package com.bit.alan.topartists;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Artist> artistList;
    ArrayList<Integer> countList;

    ListView lv_Artists;
    Button btn_fill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistList = new ArrayList<Artist>();
        countList = new ArrayList<Integer>();

        lv_Artists = (ListView) findViewById(R.id.lv_Artists);
        btn_fill = (Button) findViewById(R.id.btn_Fill);

        btn_fill.setOnClickListener(new fillButtonListener());

        //ArtistAdapter artistAdapter = new ArtistAdapter(this, R.layout.custom_artist_listview, artistList);



    }

    public class fillButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v)
        {
            TopArtistAPI APIThread = new TopArtistAPI();
            APIThread.execute();
        }
    }

    public class TopArtistAPI extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            String JSONString = null;

            try {
                String urlString = "http://ws.audioscrobbler.com/2.0/?" +
                        "api_key=58384a2141a4b9737eacb9d0989b8a8c&" +
                        "method=chart.getTopArtists&" +
                        "limit=20&" +
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

            return JSONString;
        }

        protected void onPostExecute(String s){
            try {
                JSONObject jsonData = new JSONObject(s);
                JSONObject artistsData = jsonData.getJSONObject("artists");
                JSONArray artistArray = artistsData.getJSONArray("artist");
                for (int i = 0; i < artistArray.length(); i++)
                {
                    JSONObject currentEvent = artistArray.getJSONObject(i);
                    String bandName = currentEvent.getString("name");
                    int playcount = currentEvent.getInt("playcount");
                    Artist artist = new Artist(bandName, playcount);
                    artistList.add(artist);
                    //countList.add(playcount);
                }

            } catch (JSONException e) {e.printStackTrace();}

            ArtistAdapter artistAdapter = new ArtistAdapter(MainActivity.this, android.R.layout.simple_list_item_1, artistList);
            lv_Artists.setAdapter(artistAdapter);

        }


    }

    public class ArtistAdapter extends ArrayAdapter<Artist>
    {
        public ArtistAdapter(Context context, int resources, ArrayList<Artist> objects)
        {
            super(context, resources, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View customView = inflater.inflate(R.layout.custom_artist_listview, container, false);

            TextView tvName = (TextView) customView.findViewById(R.id.tv_bandName);
            TextView tvCount = (TextView) customView.findViewById(R.id.tv_count);

            Artist currentArtist = getItem(position);

            tvName.setText(currentArtist.Name());
            tvCount.setText(""+currentArtist.Count());

            return customView;
        }

    }






}
