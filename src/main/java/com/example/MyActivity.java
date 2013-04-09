package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

      password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
          passwordError.setVisibility(View.GONE);
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
      return true;
  }
  
    
}
