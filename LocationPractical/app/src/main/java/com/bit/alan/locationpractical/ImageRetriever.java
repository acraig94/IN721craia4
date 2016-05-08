package com.bit.alan.locationpractical;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
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

/**
 * Created by Alan on 8/05/2016.
 */
public class ImageRetriever {

    private String searchTag;
    private Bitmap bitmap;
    private ImageView iv;
    private TextView tv;
    private ProgressDialog pd;

    public ImageRetriever(String searchTag, ImageView iv, TextView tv, Activity main){
        this.searchTag = searchTag;
        this.iv = iv;
        iv.setImageResource(android.R.color.transparent);
        this.tv = tv;
        tv.setText("");
        bitmap = null;
        pd = ProgressDialog.show(main, "Loading","Looking for image", true);
        ImageAPI imageAPI = new ImageAPI();
        imageAPI.execute();

    }

    public class ImageAPI extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String JSONString = null;
            try {
                String urlString =
                                "https://api.flickr.com/services/rest/?" +
                                "&method=flickr.photos.search" +
                                "&api_key=eda41a123d459be0f85276d37290651e" +
                                "&tags=" + searchTag +
                                "&format=json" +
                                "&nojsoncallback=1";
                Log.e("o url string", urlString);
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
            Log.e("img json", JSONString);
            return JSONString;
        }

        protected void onPostExecute(String s){
            JSONObject searchData;
            try {
                searchData = new JSONObject(s);
                JSONObject photos = searchData.getJSONObject("photos");
                int nPhotosReturned = photos.getInt("total");

                if (nPhotosReturned != 0){
                    JSONArray photoArray = photos.getJSONArray("photo");
                    JSONObject mainPhoto = photoArray.getJSONObject(0);
                    int farmId = mainPhoto.getInt("farm");
                    int server = mainPhoto.getInt("server");
                    int photoId = mainPhoto.getInt("id");
                    String secret = mainPhoto.getString("secret");

                    String imgURL = "https://farm"+ farmId + ".staticflickr.com/" + server + "/"+ photoId + "_" + secret + ".jpg";
                    Log.e("img url", imgURL);
                    UrlAPI urlApi = new UrlAPI();
                    urlApi.execute(imgURL);
                }
                else {
                    tv.setText("Image not found");
                    pd.dismiss();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public class UrlAPI extends AsyncTask<String,Void,Bitmap>
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
            bitmap = b;
            iv.setImageBitmap(bitmap);
            pd.dismiss();
        }

    }

    public Bitmap GetBitMap(){
        return bitmap;
    }


}
