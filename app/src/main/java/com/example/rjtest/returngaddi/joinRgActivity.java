package com.example.rjtest.returngaddi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.rjtest.returngaddi.DashBoard.ReturnGaddi;

public class joinRgActivity extends AppCompatActivity implements View.OnClickListener {
    Button mtransport, mcustomer;
    Toolbar appbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_rg);
        mtransport = (Button) findViewById(R.id.btn_transport);
        mtransport.setOnClickListener(this);
        mcustomer = (Button) findViewById(R.id.btn_customer);
        mcustomer.setOnClickListener(this);

        appbar = (Toolbar) findViewById(R.id.tooljoin);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transport:
            Intent transintent = new Intent(joinRgActivity.this, RegisterActivity.class);
            startActivity(transintent);
            overridePendingTransition(R.anim.animation, R.anim.animation2);
            break;
            case  R.id.btn_customer:
                Intent customintent = new Intent(joinRgActivity.this,CustomerActivity.class);
                startActivity(customintent);
                overridePendingTransition(R.anim.animation, R.anim.animation2);
                break;
            default:
                break;
        }

}}