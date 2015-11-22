package com.sixamigos.sjsucanvasapp.login.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.home.HomeActivity;

import org.w3c.dom.Text;

import java.io.File;

/**
 * Created by christopherbachner on 11/6/15.
 */

public class LogInActivity extends AppCompatActivity {

    /**
     * Checks if a session file is already existing.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        if (CanvasToken.readCanvasToken(new File(this.getCacheDir(), "accessToken.tmp"))) {
            this.launchHome();
        } else {
            this.continueLogIn();
        }


    }

    /**
     * Handles user input
     */
    private void continueLogIn() {
        final EditText logInEditText = (EditText) findViewById(R.id.logInEditText);
        final Button logInButton = (Button) findViewById(R.id.logInButton);
        final TextView errorText = (TextView) findViewById(R.id.logInErrorMessage);


        errorText.setVisibility(View.INVISIBLE);
        logInButton.setEnabled(false);
        logInEditText.requestFocus();
        logInEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (logInEditText.length() == 67) {
                    logInButton.setEnabled(true);
                    errorText.setVisibility(TextView.INVISIBLE);
                } else {
                    logInButton.setEnabled(false);
                    errorText.setVisibility(TextView.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Used to fix wrong status bar color
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

    }

    /**
     * Gets called when user hits Log In button. Is only enabled if the input text is exactly 67 characters long. If size is correct, home activity gets launched.
     *
     * @param view
     */
    public void logIn(View view) {
        EditText logInEditText = (EditText) findViewById(R.id.logInEditText);
        TextView errorText = (TextView) findViewById(R.id.logInErrorMessage);
        String errorString = errorText.getText().toString();

        if (logInEditText.length() == 0 || logInEditText.length() != 67) {

            errorText.setText(errorString + logInEditText.length());
            errorText.setVisibility(TextView.VISIBLE);
        } else {
            CanvasToken.setCanvasToken(logInEditText.getText().toString(), new File(this.getCacheDir(), "accessToken.tmp"));
            this.launchHome();
        }

    }

    /**
     * Launches home instance
     */
    private void launchHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    /**
     * Launches the webview for the user to be able to generate the token manually.
     *
     * @param view
     */
    public void requestAccess(View view) {
        startActivity(new Intent(this, LoginWebViewActivity.class));
    }

    /**
     * Allows user to skip setup to only use non-canvas features.
     *
     * @param view
     */
    public void skipSetup(View view) {
        CanvasToken.setCanvasToken("LOCAL", new File(this.getCacheDir(), "accessToken.tmp"));
        this.launchHome();
    }

}


