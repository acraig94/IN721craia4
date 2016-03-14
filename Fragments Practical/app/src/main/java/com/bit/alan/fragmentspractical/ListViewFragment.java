package com.bit.alan.fragmentspractical;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {


    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View fragmentView = inflater.inflate(R.layout.fragment_list_view, container, false);

        ListView lvIntruments = (ListView) fragmentView.findViewById(R.id.listView);

        String[] instruments = {"accordion", "bassoon", "clarinet", "dulcimer", "english horn", "flute"};
        ArrayAdapter<String> instrumentsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, instruments);

        lvIntruments.setAdapter(instrumentsAdapter);

        // Inflate the layout for this fragment
        return fragmentView;
        //inflater.inflate(R.layout.fragment_list_view, container, false);
    }

}
