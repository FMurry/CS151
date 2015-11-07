package com.sixamigos.sjsucanvasapp.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sixamigos.sjsucanvasapp.home.HomeActivity;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

import java.net.URL;

public class LogInActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    CanvasToken canvasToken = new CanvasToken("sjsu.instructure.com", this);

    //if logged in
    Intent i = new Intent(this, HomeActivity.class);
    startActivity(i);

    canvasToken.getCanvasToken();


  }
}
