package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity
{
  private TextView password;
  private TextView username;
  private EditText userNameError;
  private EditText passwordError;

  /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       password = (TextView) findViewById(R.id.password);
       username = (TextView) findViewById(R.id.username);
      userNameError = (EditText) findViewById(R.id.username_error);
      passwordError = (EditText) findViewById(R.id.password_error);

      password.setOnTouchListener(new View.OnTouchListener() {
         @Override
         public boolean onTouch(View view, MotionEvent motionEvent) {
           passwordError.setVisibility(View.GONE);
           return false;
         }
       });
      username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
          userNameError.setVisibility(View.GONE);
        }
      });
        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            if(validCredentials()){
              Intent intent = new Intent(getApplicationContext(), LoginCompleteActivity.class);
              startActivity(intent);

            }
          }
        });
    }

  private Boolean validCredentials() {
   if(username.getText().toString().isEmpty()){
      userNameError.setVisibility(View.VISIBLE);
      userNameError.setText("Enter valid username");
    }

    if(password.getText().toString().isEmpty()){
      passwordError.setVisibility(View.VISIBLE);
      passwordError.setText("Enter valid password");
    }
    if(userNameError.getVisibility() == View.VISIBLE || passwordError.getVisibility() == View.VISIBLE)
      return false;
    return true;
  }
  
    
}
