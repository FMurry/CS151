package com.sixamigos.sjsucanvasapp.login.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.helpscreen.HelpScreen;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by christopherbachner on 11/12/15.
 * Modify by Khoa Vo on 11/28/15
 */
public class LoginWebViewActivity extends AppCompatActivity {
    Button helpButton;

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
        showHelp();
    }

    /**
     * Launches help activity.
     */
    protected void showHelp() {
        helpButton = (Button) findViewById(R.id.logInHelpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginWebViewActivity.this, HelpScreen.class);
                startActivity(i);
                finish();
            }
        });



    }
}
