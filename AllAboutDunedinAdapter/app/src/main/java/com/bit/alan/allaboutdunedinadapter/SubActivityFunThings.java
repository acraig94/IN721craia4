package com.bit.alan.allaboutdunedinadapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubActivityFunThings extends AppCompatActivity {

    Place[] placeArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity_fun_things);

        initDataArray();

        PlaceArrayAdapter placeAdapter = new PlaceArrayAdapter(this, R.layout.custom_listview_item, placeArray);

        ListView lvPlaces = (ListView) findViewById(R.id.lvPlaces);
        lvPlaces.setAdapter(placeAdapter);
    }

    private void initDataArray()
    {
        Resources resourceMachine = getResources();
        Drawable larnachImage = resourceMachine.getDrawable(R.drawable.larnach_castle);
        Drawable moanaImage = resourceMachine.getDrawable(R.drawable.moana_pool);
        Drawable MonarchImage = resourceMachine.getDrawable(R.drawable.monarch);
        Drawable OctagonImage = resourceMachine.getDrawable(R.drawable.octagon);
        Drawable OlvestonImage = resourceMachine.getDrawable(R.drawable.olveston);
        Drawable PeninsulaImage = resourceMachine.getDrawable(R.drawable.peninsula);

        placeArray = new Place[6];
        placeArray[0] = new Place("Larnach", larnachImage);
        placeArray[1] = new Place("Moana Pool", moanaImage);
        placeArray[2] = new Place("Monarch Cruise", MonarchImage);
        placeArray[3] = new Place("Octagon", OctagonImage);
        placeArray[4] = new Place("Olveston", OlvestonImage);
        placeArray[5] = new Place("Peninsula", PeninsulaImage);

    }

    public class PlaceArrayAdapter extends ArrayAdapter<Place>
    {
        public PlaceArrayAdapter(Context context, int resources, Place[] objects)
        {
            super(context, resources, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            LayoutInflater inflater = LayoutInflater.from(SubActivityFunThings.this);
            View customView = inflater.inflate(R.layout.custom_listview_item, container, false);

            ImageView itemImageView = (ImageView) customView.findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView) customView.findViewById(R.id.tvItemWords);

            Place currentItem = getItem(position);

            itemImageView.setImageDrawable(currentItem.Image());
            itemTextView.setText(currentItem.toString());

            return customView;
        }

    }
}
