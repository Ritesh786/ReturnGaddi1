package com.example.rjtest.returngaddi;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.example.rjtest.returngaddi.Customer_dashBoard.Main2Activity;
import com.example.rjtest.returngaddi.DashBoard.ReturnGaddi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button msubmitbtntrans;
    Toolbar appbar;
    private RadioButton radiotrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        radiotrans = (RadioButton) findViewById(R.id.check_trans);
        msubmitbtntrans = (Button) findViewById(R.id.submit_button);
        msubmitbtntrans.setOnClickListener(this);
        appbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radiotrans.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (radiotrans.isChecked()) {
            if (view == msubmitbtntrans) {
                Intent submittransintent = new Intent(RegisterActivity.this, ReturnGaddi.class);
                startActivity(submittransintent);
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Please Select The Terms And Condition Button", Toast.LENGTH_SHORT).show();
        }


    }
}