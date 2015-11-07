package com.sixamigos.sjsucanvasapp.login.canvas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.sixamigos.sjsucanvasapp.login.LogInActivity;

/**
 * Created by christopherbachner on 11/6/15.
 */
public class CanvasToken {

    private static String CanvasToken = null;
    private String CanvasServer = null;
    private Activity logInActivity = null;

    /**
     * Creates a CanvasToken object that handles authorization with canvasServer and sets up token so further API calls can be done.
     * @param canvasServer CanvasServer that should be used for the authorization. (e.g. sjsu.instructure.com) (No http etc.)
     * @param logInActivity A reference to any activity where the OAUTH2 web view should be opened if token does not exist.
     */
    public CanvasToken(String canvasServer, Activity logInActivity) {
        CanvasServer = canvasServer;
        this.logInActivity = logInActivity;
    }


    //TODO implement saved encrypted token

    /**
     * Used by other classes to get the token. If token does not exist, authorize method will be called.
     *
     * @return Returns token as String.
     */
    public String getCanvasToken() {

        try {

            if (CanvasToken == null) {
                this.authorize();
            }
            return CanvasToken;

        } catch (CanvasLoginFailureException e) {
            System.out.printf(e.getLocalizedMessage());
            return null;
        }

    }


    /**
     * Gets called if token is not null. Opens WebView and performs complete OAUTH2.
     *
     * @throws CanvasLoginFailureException If for some reason the login fails or user cancels login.
     */
    private void authorize() throws CanvasLoginFailureException {
        try {
            logInActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + this.CanvasServer)));
            CanvasToken = "123";
        } catch (Exception e)
        {

        }

    }
}
