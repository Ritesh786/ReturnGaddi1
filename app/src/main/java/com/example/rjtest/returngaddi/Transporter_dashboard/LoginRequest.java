package com.example.rjtest.returngaddi.Transporter_dashboard;

import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.rjtest.returngaddi.R;

/**
 * Created by Fujitsu on 17/02/2017.
 */

public class LoginRequest extends StringRequest {

    RadioButton mtransportbtn,mcustomerbtn;
    EditText mloginusername,mloginpassword;

    public LoginRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {

        super(method, url, listener, errorListener);
    }
}
