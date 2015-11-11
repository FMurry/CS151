package com.sixamigos.sjsucanvasapp.login.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.home.HomeActivity;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

/**
 * Created by christopherbachner on 11/6/15.
 */

public class LogInActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);

    WebView webView = (WebView) findViewById(R.id.logInwebView);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("https://sjsu.instructure.com/profile/settings");

  }

  /** Christopher Bachner
   * Gets called when user hits Log In button.
   * @param view
   */
  public void logIn (View view)
  {
    EditText logInEditText = (EditText)findViewById(R.id.logInEditText);


    if (logInEditText.length() == 0)
    {
      ((TextView)findViewById(R.id.logInErrorMessage)).setVisibility(TextView.VISIBLE);
    }
    else
    {
      CanvasToken.
    }


    Intent i = new Intent(this, HomeActivity.class);
    startActivity(i);

  }

}
