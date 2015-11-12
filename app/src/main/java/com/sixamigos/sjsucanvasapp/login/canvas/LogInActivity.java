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
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.home.HomeActivity;

import org.w3c.dom.Text;

/**
 * Created by christopherbachner on 11/6/15.
 */

public class LogInActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);

    findViewById(R.id.logInErrorMessage).setVisibility(View.INVISIBLE);

    WebView webView = (WebView) findViewById(R.id.logInWebView);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("https://sjsu.instructure.com/profile/settings");
    webView.requestFocus();
    /** DEBUG */ logIn(null);
  }

  /**
   * Gets called when user hits Log In button. Checks if token has correct size or not. If size is correct, home activity gets launched.
   * @param view
   */
  public void logIn (View view)
  {
    /** ## CHRIS' CODE ##
     *
    EditText logInEditText = (EditText)findViewById(R.id.logInEditText);
    TextView errorText = (TextView)findViewById(R.id.logInErrorMessage);
    String errorString = errorText.getText().toString();

    if (logInEditText.length() == 0 || logInEditText.length() != 67)
    {

      errorText.setText(errorString + logInEditText.length());
      errorText.setVisibility(TextView.VISIBLE);

    }
    else
    {
      CanvasToken.setCanvasToken(logInEditText.getText().toString());
      Intent i = new Intent(this, HomeActivity.class);
      startActivity(i);
    }
    */

    /** MY CODE */
    CanvasToken.setCanvasToken(CanvasConnector.DEBUG_TOKEN);
    Intent i = new Intent(this, HomeActivity.class);
    startActivity(i);
  }

}
