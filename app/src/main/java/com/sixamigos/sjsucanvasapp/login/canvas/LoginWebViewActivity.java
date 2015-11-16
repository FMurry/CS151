package com.sixamigos.sjsucanvasapp.login.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.home.HomeActivity;

/**
 * Created by christopherbachner on 11/12/15.
 */
public class LoginWebViewActivity extends AppCompatActivity {

    /**
     * Shows the webview for the user, so the user can manually generate and copy the access token.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_web);

        WebView webView = (WebView) findViewById(R.id.logInWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sjsu.instructure.com/profile/settings");
        webView.getSettings().setJavaScriptEnabled(true);

    }

    /**
     * Launches help activity.
     */
    protected void showHelp()
    {
        //Intent i = new Intent(this, HomeActivity.class);
        //startActivity(i);
    }
}
