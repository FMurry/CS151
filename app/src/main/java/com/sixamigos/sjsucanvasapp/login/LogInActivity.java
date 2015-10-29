package com.sixamigos.sjsucanvasapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sixamigos.sjsucanvasapp.home.HomeActivity;

public class LogInActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //if logged in
    Intent i = new Intent(this, HomeActivity.class);
    startActivity(i);
  }
}
