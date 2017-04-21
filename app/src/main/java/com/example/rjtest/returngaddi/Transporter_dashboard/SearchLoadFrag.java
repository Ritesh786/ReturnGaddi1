
package com.example.rjtest.returngaddi.Transporter_dashboard;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rjtest.returngaddi.MySingleton;
import com.example.rjtest.returngaddi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLoadFrag extends Fragment implements View.OnClickListener {
    Calendar calenda;
    DatePickerDialog.OnDateSetListener date;
    EditText mdepdate;
    Button msearchcbmmt;
    AlertDialog.Builder builder;
    AutoCompleteTextView pickuulkn,droplkn;
    String searchurl= "";

    public SearchLoadFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View mview= inflater.inflate(R.layout.fragment_search_load, container, false);
        mdepdate = (EditText) mview.findViewById(R.id.dep_date);
        msearchcbmmt = (Button) mview.findViewById(R.id.Search_submit);
        builder = new AlertDialog.Builder(getContext());
         pickuulkn = (AutoCompleteTextView) mview.findViewById(R.id.pickup_lcn);
        String[] countries = getResources().getStringArray(R.array.CityNames);
       droplkn = (AutoCompleteTextView) mview.findViewById(R.id.dropof_lcn);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countries);
        pickuulkn.setAdapter(adapter);
        droplkn.setAdapter(adapter);

        calenda = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calenda.set(Calendar.YEAR, year);
                calenda.set(Calendar.MONTH, monthOfYear);
                calenda.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        msearchcbmmt.setOnClickListener(this);
        mdepdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, calenda
                        .get(Calendar.YEAR), calenda.get(Calendar.MONTH),
                        calenda.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return mview;
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mdepdate.setText(sdf.format(calenda.getTime()));
    }

    @Override
    public void onClick(View v) {

        final String s_date,s_picklkn,s_drplkn;
        s_date= mdepdate.getText().toString();
        s_picklkn = pickuulkn.getText().toString();
        s_drplkn = droplkn.getText().toString();


        if(TextUtils.isEmpty(s_picklkn))
        {
            pickuulkn.requestFocus();
            pickuulkn.setError("This Field Is Mandatory");
        }

        else if(TextUtils.isEmpty(s_drplkn))
        {
            droplkn.requestFocus();
            droplkn.setError("This Field Is Mandatory");
        }

        else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, searchurl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            builder.setTitle("Server Response");
                            builder.setMessage("Response "+response);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mdepdate.setText("");
                                    pickuulkn.setText("");
                                    droplkn.setText("");
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }

                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(),"Some Thing Wrong",Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map <String,String> params = new HashMap<String, String>();
                    params.put("dep_date" , s_date);
                    params.put("pick_lkn" , s_picklkn);
                    params.put("drp_lkn" , s_drplkn);
                    return params;
                }
            };
            MySingleton.getTnstance(getContext()).addTorequestQueue(stringRequest);


        }

    }
}
