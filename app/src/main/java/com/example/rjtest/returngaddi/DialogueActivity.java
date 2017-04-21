
package com.example.rjtest.returngaddi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogueActivity extends AppCompatActivity  {

    EditText emailValidate;
    Button msendbtn;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);
        msendbtn = (Button) findViewById(R.id.show_button);

        emailValidate = (EditText) findViewById(R.id.dialogue_email);


 //       msendbtn.setOnClickListener(this);
//
//
//    }
//
//    @Override
//    public void onClick(View v) {


//
//        email = emailValidate.getText().toString().trim();
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//        if (email.matches(emailPattern)) {
//            emailValidate.requestFocus();
//            emailValidate.setError("valid email address");
//
//        }
//        else
//        {
//            emailValidate.requestFocus();
//            emailValidate.setError("Invalid email address");
//
//        }
//
//    }
    }
//    public final static boolean isValidEmail(CharSequence target) {
//        if (target == null) {
//            return false;
//        } else {
         //   return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
//        }
//    }
}