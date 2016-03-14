package com.bit.alan.fragmentspractical;

import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnImageFragment = (Button) findViewById(R.id.btn_ImageView);
        Button btnListViewFragment = (Button) findViewById(R.id.btn_ListView);

        btnImageFragment.setOnClickListener(new imageViewButtonHandle());
        btnListViewFragment.setOnClickListener(new listViewButtonHandle());

    }

    public class imageViewButtonHandle implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Fragment imageviewFragment = new ShowImageFragment();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.listview_fragment_container, imageviewFragment);
            ft.commit();
        }
    }

    public class listViewButtonHandle implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Fragment listViewFragment = new ListViewFragment();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.listview_fragment_container, listViewFragment);
            ft.commit();
        }
    }







}
