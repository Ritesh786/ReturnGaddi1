
package com.example.rjtest.returngaddi.Transporter_dashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rjtest.returngaddi.MainActivity;
import com.example.rjtest.returngaddi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPostFrag extends Fragment {

    ListView mlstview;
    public static final String JSON_URL = "";
    String[] str = {"Ritesh","iboooojj","inonooiin","ohoonoiu","ibojnon"};

    public MyPostFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_my_post, container, false);
        mlstview = (ListView) mview.findViewById(R.id.post_list);
        sendRequest();

        return mview;
    }


    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Something Went wrong..." , Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomAdapter cl = new CustomAdapter(getContext(), ParseJSON.pickplace, ParseJSON.drpplace, ParseJSON.dattext, ParseJSON.vehiclename, ParseJSON.costtext);
        mlstview.setAdapter(cl);
    }


}

