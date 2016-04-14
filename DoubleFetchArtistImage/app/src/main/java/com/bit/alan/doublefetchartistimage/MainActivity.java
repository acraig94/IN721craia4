package com.bit.alan.doublefetchartistimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn_Show;
    ImageView iv;

    String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Show = (Button) findViewById(R.id.btn_Show);
        iv = (ImageView) findViewById(R.id.imageView);

        btn_Show.setOnClickListener(new showButtonListener());
    }

    public class showButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v)
        {
            TopArtistAPI APIThread = new TopArtistAPI();
            APIThread.execute();
        }
    }

    public class ImageAPI extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... params) {

            String imgURL = params[0];
            Bitmap bitmap = null;
            try {
                URL urlObject = new URL(imgURL);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    InputStream is = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }
            }catch (Exception e){e.printStackTrace();};

            return bitmap;
        }

        protected void onPostExecute(Bitmap b)
        {
            iv.setImageBitmap(b);
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
                        "limit=1&" +
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

                JSONObject topArtist = artistArray.getJSONObject(0);
                JSONArray artistImages = topArtist.getJSONArray("image");

                for (int i = 0; i < artistImages.length(); i++) {
                    JSONObject image = artistImages.getJSONObject(i);
                    if (image.getString("size").equals("mega"))
                    {
                        imageURL = image.getString("#text");
                    }
                }
            } catch (JSONException e) {e.printStackTrace();}
            Log.e("imageURL", imageURL);
            ImageAPI imageAPI = new ImageAPI();
            imageAPI.execute(imageURL);
        }


    }




}
