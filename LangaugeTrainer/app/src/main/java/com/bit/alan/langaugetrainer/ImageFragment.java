package com.bit.alan.langaugetrainer;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);

        Bundle bundle = getArguments();
        Word currentWord = (Word) bundle.getSerializable("currentWord");
        int questionNumber = (int) bundle.getSerializable("questionNumber");
        questionNumber++;
        TextView tv = (TextView)v.findViewById(R.id.tv_imageTxt);
        tv.setText("Q" + questionNumber + ". Select the article");
        ImageView iv = (ImageView) v.findViewById(R.id.imageView);
        iv.setImageResource(currentWord.getImageID());

        return v;
    }

}
