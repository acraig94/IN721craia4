package com.bit.alan.langaugetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView tv_score = (TextView) findViewById(R.id.tv_score);
        Button btn_Replay = (Button) findViewById(R.id.btn_Replay);
        Button btn_Exit = (Button) findViewById(R.id.btn_Exit);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        tv_score.setText("You scored " + score + "/11");

        btn_Replay.setOnClickListener(new btnReplayHandler());
        btn_Exit.setOnClickListener(new btnExitHandler());


    }

    public class btnReplayHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent goQuestionActivity = new Intent(ResultsActivity.this, QuestionActivity.class);
            startActivity(goQuestionActivity);
        }
    }

    public class btnExitHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

}
