package com.bit.alan.locationpractical;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand;
    private CoordinateGenerator cg;
    private Button btnGen;
    private TextView tvLatLng;
    private TextView tvCity;
    private TextView tvError;
    private ImageView ivImg;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();

        btnGen = (Button) findViewById(R.id.btn_Generate);
        tvLatLng = (TextView) findViewById(R.id.tv_LatLng);
        tvCity = (TextView) findViewById(R.id.tv_City);
        tvError = (TextView) findViewById(R.id.tv_Error);
        ivImg = (ImageView) findViewById(R.id.iv_img);

        btnGen.setOnClickListener(new btnGenListener());

    }

    public class btnGenListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            LocationAPI APIThread = new LocationAPI();
            progress = ProgressDialog.show(MainActivity.this, "Loading","Finding city", true);
            APIThread.execute();
        }
    }



    public class LocationAPI extends AsyncTask<Void, Void, Location>
    {
        @Override
        protected Location doInBackground(Void... params) {

            String JSONString = null;
            Location location = null;
            while(location == null) {
                cg = new CoordinateGenerator(rand);
                try {
                    String urlString = "http://www.geoplugin.net/extras/location.gp?lat=" + cg.GetLat() +
                            "&long=" + cg.GetLng() + "&format=json";

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("JSONSTRING", JSONString);
                JSONObject geoData;
                try {
                    geoData = new JSONObject(JSONString);
                    if (!geoData.optString("geoplugin_place", "error").equals("error")) {
                        String city = geoData.getString("geoplugin_place");
                        String countryCode = geoData.getString("geoplugin_countryCode");
                        location = new Location(city, countryCode, cg.GetLat(), cg.GetLng());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return location;
        }

        protected void onPostExecute(Location l){
            progress.dismiss();
            tvCity.setText(l.toString());
            tvLatLng.setText(l.GetPos());
            ImageRetriever ir = new ImageRetriever(l.GetCity(), ivImg, tvError, MainActivity.this);
            Bitmap img = ir.GetBitMap();
            if (img != null){
                //ivImg.setImageBitmap(img);
            }
            else {
                Log.e("setting img view", "img is null");
            }
        }


    }




}
