package com.sixamigos.sjsucanvasapp.login.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sixamigos.sjsucanvasapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by christopherbachner on 11/12/15.
 */
public class LoginWebViewActivity extends AppCompatActivity {

    @Bind(R.id.logInWebView) WebView mWebView;

    /**
     * Shows the webview for the user, so the user can manually generate and copy the access token.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_web);
        ButterKnife.bind(this);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://sjsu.instructure.com/profile/settings");
        mWebView.getSettings().setJavaScriptEnabled(true);

    }

    /**
     * Launches help activity.
     */
    protected void showHelp() {
        //Intent i = new Intent(this, HomeActivity.class);
        //startActivity(i);
    }
}
