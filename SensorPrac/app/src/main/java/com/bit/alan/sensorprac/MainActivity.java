package com.bit.alan.sensorprac;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mLight;
    Sensor mAccelerometer;
    TextView tvOutput;
    int x;
    int y;
    int z;
    int posX;
    int posY;
    ImageView ball;
    int ballWidth;
    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        tvOutput = (TextView) findViewById(R.id.tv_output);
        ball = (ImageView) findViewById(R.id.imageView);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        posX = 0;
        posY = 0;
        ballWidth = ball.getWidth();

        Log.e("ball matrix", ball.getImageMatrix() + "");
        //ball.

        tvOutput.setText("Test");

    }



    public class SensorBtnHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }

    public class SensorEventHandler implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            x = (int) event.values[0];  // z
            y = (int) event.values[1];  // x
            z = (int) event.values[2];  // y
            float sX = event.values[0];
            float sY = event.values[1];
            float sZ = event.values[2];
            Log.e("x , y , z", x + ", " + y + ", " + z);
            //ball.setScaleY(sX);
            //ball.setScaleY(sY);
            if (sX < 0){
                sX = 0;
            }
            if (sY < 0){
                sY = 0;
            }
            if (sX*200 > screenWidth )
            {
                ball.setX(screenWidth-ballWidth);
            }
            else { ball.setX(sX*200); }
            if (sY*200 > screenHeight){
                ball.setY(screenWidth-ballWidth);
            }
            else {
                ball.setY(sY * 200);
            }
            //ball.
            //tvOutput.setX(sX*10);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(new SensorEventHandler(), mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(new SensorEventHandler());
    }



}
