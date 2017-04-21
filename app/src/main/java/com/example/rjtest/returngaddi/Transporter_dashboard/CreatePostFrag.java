
package com.example.rjtest.returngaddi.Transporter_dashboard;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.example.rjtest.returngaddi.MySingleton;
import com.example.rjtest.returngaddi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import static com.example.rjtest.returngaddi.R.id.cost_create;
import static com.example.rjtest.returngaddi.R.id.createpost_submit;
import static com.example.rjtest.returngaddi.R.id.dialogue_email;
import static com.example.rjtest.returngaddi.R.id.drplocation;
import static com.example.rjtest.returngaddi.R.id.picklocation;
import static com.example.rjtest.returngaddi.R.id.vehicle;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePostFrag extends Fragment implements View.OnClickListener {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText mdate,mvehicle,mcost;
    AutoCompleteTextView mdrop,mpick;
    Button mcreatebtn;
    AlertDialog.Builder builder;
    String url="";
    ArrayAdapter<CharSequence> adapter;

    public CreatePostFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        mdate = (EditText) view.findViewById(R.id.date);
        mpick = (AutoCompleteTextView) view.findViewById(R.id.picklocation);
        mdrop = (AutoCompleteTextView) view.findViewById(R.id.drplocation);
        mvehicle = (EditText) view.findViewById(R.id.vehicle);
        mcost = (EditText) view.findViewById(R.id.cost_create);
        mcreatebtn = (Button) view.findViewById(R.id.createpost_submit);
        builder = new AlertDialog.Builder(getContext());
        String[] countries = getResources().getStringArray(R.array.CityNames);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countries);
        mpick.setAdapter(adapter);
        mdrop.setAdapter(adapter);

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mcreatebtn.setOnClickListener(this);
        mdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
//
//        private void updateLabel() {
//
//            String myFormat = "MM/dd/yy"; //In which you need put here
//            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//             mdate.setEditText(sdf.format(myCalendar.getTime()));
//           // mdate.setedi(sdf.format(myCalendar.getTime()));
//        }
         return view;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mdate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {

        final String pickup_lcn,drp_lcn,date,abt_vehicle,cost_vehicle;
        pickup_lcn = mpick.getText().toString();
        drp_lcn = mdrop.getText().toString();
        date= mdate.getText().toString();
        cost_vehicle = mcost.getText().toString();
        abt_vehicle = mvehicle.getText().toString();

        if (TextUtils.isEmpty(pickup_lcn)) {
            mpick.requestFocus();
            mpick.setError("This Field Is Mandatory");
        }

            else  if(TextUtils.isEmpty(drp_lcn))
            {
                mdrop.requestFocus();
                mdrop.setError("This Field Is Mandatory");
            }

            else if(TextUtils.isEmpty(date))
            {
                mdate.requestFocus();
                mdate.setError("This Field Is Mandatory");
            }

        else if(TextUtils.isEmpty(cost_vehicle))
        {
            mcost.requestFocus();
            mcost.setError("This Field Is Mandatory");
        }

            else if(TextUtils.isEmpty(abt_vehicle))
            {
                mvehicle.requestFocus();
                mvehicle.setError("This Field Is Mandatory");
            }

        else{
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            builder.setTitle("Server Response");
                            builder.setMessage("Response "+response);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mpick.setText("");
                                    mdrop.setText("");
                                    mdate.setText("");
                                    mcost.setText("");
                                    mvehicle.setText("");
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
                params.put("pick_loction" , pickup_lcn);
                params.put("drop_loction" , drp_lcn);
                params.put("transport_date" , date);
                params.put("vehicle_cost",cost_vehicle);

                params.put("vehicle_type" , abt_vehicle);
                return params;
            }
        };
        MySingleton.getTnstance(getContext()).addTorequestQueue(stringRequest);


    }
}

}


