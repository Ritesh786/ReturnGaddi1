package com.example.rjtest.returngaddi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rjtest.returngaddi.Customer_dashBoard.Main2Activity;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
   Button msubmitbtn;
    Toolbar appbar;
    private RadioButton radiocustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        radiocustom = (RadioButton) findViewById(R.id.check_c);

        msubmitbtn = (Button) findViewById(R.id.submit_button_customer);
        msubmitbtn.setOnClickListener(this);

        appbar = (Toolbar) findViewById(R.id.toolcustom);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       radiocustom.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
            if(radiocustom.isChecked())
            {
                if (view == msubmitbtn) {
                    Intent submitcustomintent = new Intent(CustomerActivity.this, Main2Activity.class);
                    startActivity(submitcustomintent);
                }
            }
            else
            {
               Toast.makeText(CustomerActivity.this,"Please Select The Terms And Condition Button",Toast.LENGTH_SHORT).show();
            }


    }
}
