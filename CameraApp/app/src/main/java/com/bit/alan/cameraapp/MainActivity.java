package com.bit.alan.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private String photoName;
    private File photoFile;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.imageView);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
        Button btnPhotoIntent = (Button) findViewById(R.id.btn_Photo);

        btnPhotoIntent.setOnClickListener(new btnPhotoHandler());
    }

    public class btnPhotoHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            photoFile = CreateFile();
            photoUri = Uri.fromFile(photoFile);

            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

            startActivityForResult(imageCaptureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            String realFilePath = photoFile.getPath();
            Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);

            iv1.setImageBitmap(userPhotoBitmap);
            iv2.setImageBitmap(userPhotoBitmap);
            iv3.setImageBitmap(userPhotoBitmap);
            iv4.setImageBitmap(userPhotoBitmap);
            Log.e("On result", "True");
            Log.e("rlf", realFilePath);
        }
        else{
            Log.e("On result", "False");
        }

    }

    public File CreateFile(){
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDirectory = new File(imageRootPath, "CameraDemo");
        if (!imageStorageDirectory.exists()){
            imageStorageDirectory.mkdirs();
        }
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        photoName = "IMG_" + timeStamp + ".jpg";
        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + photoName);
        return photoFile;
    }




}
