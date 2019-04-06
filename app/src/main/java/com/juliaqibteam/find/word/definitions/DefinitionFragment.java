package com.juliaqibteam.find.word.definitions;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.juliaqibteam.find.word.definitions.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DefinitionFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_WORD_NUMBER = "word_number";
    private View view;
    int wordNumber;
    String wordArray[] = new String[]{"definitions", "synonyms", "antonyms", "examples", "typeOf"};

    SearchView searc_box;
    JSONObject jsonBody;
    String url;
    ProgressDialog mDialog;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String wordResult;
    JSONObject jsonResSave;
    ProgressDialog progressDialog;

    public DefinitionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_definition, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        String wordName[] = new String[]{
                DefinitionFragment.this.getResources().getString(R.string.nav_definitions),
                DefinitionFragment.this.getResources().getString(R.string.nav_synonyms),
                DefinitionFragment.this.getResources().getString(R.string.nav_antonyms),
                DefinitionFragment.this.getResources().getString(R.string.nav_examples),
                DefinitionFragment.this.getResources().getString(R.string.nav_typeOf)
        };
        super.onActivityCreated(savedInstanceState);
        wordNumber = getArguments().getInt(ARG_WORD_NUMBER);
        getActivity().setTitle(wordName[wordNumber]);

        final ImageButton button = getActivity().findViewById(R.id.search_btn);
        button.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        TextView textView = getActivity().findViewById(R.id.no_result);
        textView.setVisibility(View.GONE);


        ListView lv = getActivity().findViewById(R.id.results);
        lv.setAdapter(null);
        ListView listView = getActivity().findViewById(R.id.results);
        listView.setVisibility(View.VISIBLE);
        searc_box = getActivity().findViewById(R.id.search_box);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getActivity().getResources().getString(R.string.loading));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        String data = searc_box.getQuery().toString();
        url = "https://wordsapiv1.p.mashape.com/words/" + data + "/" + wordArray[wordNumber];
        RequestQueue mQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //extView res = getActivity().findViewById(R.id.results);
                        ListView lv = getActivity().findViewById(R.id.results);
                        List<String> resList = new ArrayList<String>();

                        try {
                            jsonArray = response.getJSONArray(wordArray[wordNumber]);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "EROR", Toast.LENGTH_LONG).show();
                        }
                        resList.add("");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    if (wordArray[wordNumber] == "definitions") {
                                        jsonResSave = jsonArray.getJSONObject(i);
                                        wordResult = jsonResSave.getString("definition");
                                    } else if (wordArray[wordNumber] == "synonyms" || wordArray[wordNumber] == "antonyms" || wordArray[wordNumber] == "examples" || wordArray[wordNumber] == "typeOf" || wordArray[wordNumber] == "hasTypes" || wordArray[wordNumber] == "partOf" || wordArray[wordNumber] == "hasParts"
                                            || wordArray[wordNumber] == "instanceOf" || wordArray[wordNumber] == "hasInstances" || wordArray[wordNumber] == "entails" || wordArray[wordNumber] == "similarTo" || wordArray[wordNumber] == "also" || wordArray[wordNumber] == "hasCategories" || wordArray[wordNumber] == "inCategory") {
                                        wordResult = String.valueOf(jsonArray.getString(i));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                resList.add(wordResult);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, resList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                TextView textView = getActivity().findViewById(R.id.no_result);
                                textView.setVisibility(View.GONE);
                                lv.setAdapter(adapter);
                                progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText(getActivity(),
                                    "Es un objecte", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                ListView listView = getActivity().findViewById(R.id.results);
                listView.setVisibility(View.GONE);
                TextView textView = getActivity().findViewById(R.id.no_result);
                textView.setVisibility(View.VISIBLE);
                Log.e("TAG", error.getMessage(), error);
                Toast.makeText(getActivity(),
                        "ERORRR 404", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Mashape-Key", "API_KEY");
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/json");
                return params;
            }
        };
        mQueue.add(jsonObjectRequest);
    }


}
