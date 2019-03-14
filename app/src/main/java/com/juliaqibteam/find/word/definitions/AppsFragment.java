package com.juliaqibteam.find.word.definitions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;


public class AppsFragment extends Fragment {

    public AppsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);

        getActivity().setTitle(getActivity().getResources().getString(R.string.nav_more_apps));
        SearchView searchView = getActivity().findViewById(R.id.search_box);
        searchView.setVisibility(View.GONE);

        ImageButton imageButton = getActivity().findViewById(R.id.search_btn);
        imageButton.setVisibility(View.GONE);


        return view;
    }

}
