package com.bit.alan.langaugetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_NewSession = (Button) findViewById(R.id.btn_NewSession);
        btn_NewSession.setOnClickListener(new btnNewSessionHandler());

    }

    public class btnNewSessionHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent goQuestionActivity = new Intent(HomeActivity.this, QuestionActivity.class);
            startActivity(goQuestionActivity);
        }
    }
}
