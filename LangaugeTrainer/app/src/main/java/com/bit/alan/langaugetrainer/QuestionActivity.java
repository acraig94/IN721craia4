package com.bit.alan.langaugetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    Fragment imageFragment;
    FragmentManager fm;
    FragmentTransaction ft;
    Manager cs;
    Button btn_Submit;
    Button btn_Next;
    int selectedID; // selected rdo button
    RadioGroup articleGroup;
    RadioButton selectedRadio;
    RadioButton rdo_der;
    RadioButton rdo_die;
    RadioButton rdo_das;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        cs = new Manager();
        btn_Submit = (Button) findViewById(R.id.btn_Submit);
        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setVisibility(View.INVISIBLE);
        articleGroup = (RadioGroup) findViewById(R.id.rdo_group);
        rdo_das = (RadioButton) findViewById(R.id.rdo_Das);
        rdo_die = (RadioButton) findViewById(R.id.rdo_Die);
        rdo_der = (RadioButton) findViewById(R.id.rdo_Der);

        loadFragment();
        btn_Submit.setOnClickListener(new btnSubmitHandler());
        btn_Next.setOnClickListener(new btnNextHandler());
    }

    public class btnSubmitHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            if (articleGroup.getCheckedRadioButtonId() == -1)
            {
                Toast.makeText(QuestionActivity.this, "Please make a selection", Toast.LENGTH_LONG).show();
            }
            else{
                selectedID = articleGroup.getCheckedRadioButtonId();
                selectedRadio = (RadioButton) findViewById(selectedID);

                String radioSelection = selectedRadio.getText().toString();
                rdo_das.setEnabled(false);
                rdo_der.setEnabled(false);
                rdo_die.setEnabled(false);

                if (cs.getCurrentQuestion().checkAnswer(selectedRadio.getText().toString())) // if user answer equals correct answer
                {
                    cs.addScore();
                    selectedRadio.setTextColor(Color.GREEN);
                    Toast.makeText(QuestionActivity.this, "Correct!", Toast.LENGTH_LONG).show();
                }
                else { //wrong
                    selectedRadio.setTextColor(Color.RED);
                    Toast.makeText(QuestionActivity.this, "Incorrect. The answer is " + cs.getCurrentQuestion().getArticle(), Toast.LENGTH_LONG).show();
                }
                articleGroup.clearCheck();
                btn_Submit.setEnabled(false);
                btn_Submit.setVisibility(View.INVISIBLE);
                btn_Next.setEnabled(true);
                btn_Next.setVisibility(View.VISIBLE);
            }
        }
    }

    public class btnNextHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            articleGroup.clearCheck();
            selectedRadio.setTextColor(Color.BLACK);
            btn_Submit.setEnabled(true);
            btn_Submit.setVisibility(View.VISIBLE);
            btn_Next.setEnabled(false);
            btn_Next.setVisibility(View.INVISIBLE);
            rdo_das.setEnabled(true);
            rdo_der.setEnabled(true);
            rdo_die.setEnabled(true);

            if (cs.isLastQuestion())
            {
                Intent resultsIntent = new Intent(QuestionActivity.this, ResultsActivity.class);
                resultsIntent.putExtra("score", cs.getScore());
                startActivity(resultsIntent);
            }
            else {
                cs.nextQuestion();
                loadFragment();
                //Toast.makeText(QuestionActivity.this, "" + cs.getQuestionNumber() , Toast.LENGTH_LONG).show(); // Test purposes
            }
        }
    }

    public void loadFragment()
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentWord", cs.getCurrentQuestion());
        bundle.putSerializable("questionNumber", cs.getQuestionNumber());
        imageFragment = new ImageFragment();
        imageFragment.setArguments(bundle);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, imageFragment);
        ft.commit();
    }

}
