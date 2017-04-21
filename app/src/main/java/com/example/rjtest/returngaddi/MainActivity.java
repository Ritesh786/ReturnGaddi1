
package com.example.rjtest.returngaddi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.example.rjtest.returngaddi.Customer_dashBoard.Main2Activity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.rjtest.returngaddi.R.id.dialog_text;
//import static com.example.rjtest.returngaddi.R.id.dialogue_check_input;
import static com.example.rjtest.returngaddi.R.id.dialogue_email;
import static com.example.rjtest.returngaddi.R.id.show_button;

//import static com.example.rjtest.returngaddi.R.id.dialoge_edit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView tv;
    Button signupbtn, forgetbtn,mloginbtn;

    EditText memail,mloginusername,mloginpassword;

    android.app.AlertDialog.Builder builder;

    RadioGroup rg;

    RadioButton mtransportbtn,mcustomerbtn;

    public static final String KEY_transport = "trans";
    public static final String KEY_customer = "cust";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rg = (RadioGroup) findViewById(R.id.mixedradio_btn);

        mtransportbtn = (RadioButton) findViewById(R.id.check_transporter);

        mcustomerbtn = (RadioButton) findViewById(R.id.check_customer);

        mloginusername = (EditText) findViewById(R.id.login_username);
        mloginpassword = (EditText) findViewById(R.id.login_password);

        mloginbtn = (Button) findViewById(R.id.btnlogin);


        signupbtn = (Button) findViewById(R.id.btnsignup);
        signupbtn.setOnClickListener(this);

      forgetbtn = (Button) findViewById(R.id.btnfp);

       forgetbtn.setOnClickListener(this);

           signupbtn.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        isOnline();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnsignup:
                Intent myintent = new Intent(MainActivity.this, joinRgActivity.class);
                startActivity(myintent);
                break;

            case R.id.btnfp:
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_dialogue, null);
                memail = (EditText) mview.findViewById(R.id.dialogue_email);
                Button mdibtn = (Button) mview.findViewById(R.id.show_button);
                mdibtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        String forgot_email = memail.getText().toString().trim();

                        if (forgot_email.matches(emailPattern)) {
                            Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mbuilder.setView(mview);
                AlertDialog dialog = mbuilder.create();
                dialog.show();
                break;
        }

    }


            public boolean isOnline() {
                ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
                    Toast.makeText(MainActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.check_customer:

                        final String radiocust = mcustomerbtn.getText().toString().trim();
                        final String username = mloginusername.getText().toString().trim();
                        final String password = mloginpassword.getText().toString().trim();

                        String REGISTER_CUSTOM = "http://www.returngaddi.com/AppReception?usrType=" + radiocust + "&id=" + username + "&pass=" + password + "";

                        Map<String, String> params = new HashMap<String, String>();
                        params.put(KEY_customer, radiocust);
                        params.put(KEY_USERNAME, username);
                        params.put(KEY_PASSWORD, password);

                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, REGISTER_CUSTOM, null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        List<String> allNames = new ArrayList<String>();

                                        JSONArray cast = null;
                                        JSONObject actor = null;
                                        try {
                                            cast = response.getJSONArray("cust_Data");
                                            for (int i = 0; i < cast.length(); i++) {
                                                actor = cast.getJSONObject(i);
                                                String name = actor.getString("cust_name");
                                                allNames.add(name);
                                            }
                                            Toast.makeText(MainActivity.this, allNames.toString(), Toast.LENGTH_LONG).show();
                                            if(allNames.contains("cust_name")) {

                                                mloginbtn.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v)
                                                    {
                                                        Intent mloginintent = new Intent (MainActivity.this, Main2Activity.class);
                                                        startActivity(mloginintent);
                                                    }
                                                });

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Something Went Wrong...", Toast.LENGTH_LONG).show();
                                    }
                                }) {

                            @Override
                            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                                try {
                                    String jsonString = new String(response.data,
                                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

                                    JSONObject result = null;

                                    if (jsonString != null && jsonString.length() > 0)
                                        result = new JSONObject(jsonString);

                                    return Response.success(result,
                                            HttpHeaderParser.parseCacheHeaders(response));
                                } catch (UnsupportedEncodingException e) {
                                    return Response.error(new ParseError(e));
                                } catch (JSONException je) {
                                    return Response.error(new ParseError(je));
                                }
                            }
                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        requestQueue.add(stringRequest);
                        break;

                    case R.id.check_transporter:


                        final String radiotrans = mcustomerbtn.getText().toString().trim();
                        final String usernametrans = mloginusername.getText().toString().trim();
                        final String passwordtrans = mloginpassword.getText().toString().trim();


                        String REGISTER_TRANS = "http://www.returngaddi.com/AppReception?usrType=" + radiotrans + "&id=" + usernametrans + "&pass=" + passwordtrans + "";

                        Map<String, String> params1;
                        params1 = new HashMap<String, String>();
                        params1.put(KEY_transport, radiotrans);
                        params1.put(KEY_USERNAME, usernametrans);
                        params1.put(KEY_PASSWORD, passwordtrans);

                        JsonObjectRequest stringRequesttrans = new JsonObjectRequest(Request.Method.GET, REGISTER_TRANS,null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        List<String> allNames = new ArrayList<String>();


                                        List<String> allNames1 = new ArrayList<String>();

                                        JSONArray cast1 = null;
                                        JSONObject actor =null;
                                        try {
                                            cast1 = response.getJSONArray("trans_Data");
                                            for (int i=0; i<cast1.length(); i++) {
                                                actor = cast1.getJSONObject(i);
                                                String name = actor.getString("trans_name");
                                                allNames1.add(name);
                                            }
                                            Toast.makeText(MainActivity.this, allNames1.toString(), Toast.LENGTH_LONG).show();

                                            if(allNames1.contains("trans_name"))
                                            {
                                                mloginbtn.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v)
                                                    {
                                                        Intent mlogintrans = new Intent(MainActivity.this,Main2Activity.class);
                                                        startActivity(mlogintrans);
                                                    }
                                                });

                                            }



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Something Went Wrong...", Toast.LENGTH_LONG).show();
                                    }
                                }){

                            @Override
                            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                                try {
                                    String jsonString = new String(response.data,
                                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

                                    JSONObject result = null;

                                    if (jsonString != null && jsonString.length() > 0)
                                        result = new JSONObject(jsonString);

                                    return Response.success(result,
                                            HttpHeaderParser.parseCacheHeaders(response));
                                } catch (UnsupportedEncodingException e) {
                                    return Response.error(new ParseError(e));
                                } catch (JSONException je) {
                                    return Response.error(new ParseError(je));
                                }
                            }
                        };
                        //      {


//                                 params = new HashMap<String, String>();
//                                params.put(KEY_transport, radiotrans);
//                                params.put(KEY_USERNAME, usernametrans);
//                                params.put(KEY_PASSWORD, passwordtrans);

                        RequestQueue requestQueuetrans = Volley.newRequestQueue(MainActivity.this);
                        requestQueuetrans.add(stringRequesttrans);

                        break;
                    //    }

                }


    }






        }











