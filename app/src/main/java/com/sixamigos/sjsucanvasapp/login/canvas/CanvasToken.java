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

    private static String canvasToken = null;
    private String canvasServer = null;
    private Activity logInActivity = null;
    private static String clientID = "123"; //TODO needs to be changed

    /**
     * Creates a canvasToken object that handles authorization with canvasServer and sets up token so further API calls can be done.
     * @param canvasServer canvasServer that should be used for the authorization. (e.g. sjsu.instructure.com) (No http etc.)
     * @param logInActivity A reference to any activity where the OAUTH2 web view should be opened if token does not exist.
     */
    public CanvasToken(String canvasServer, Activity logInActivity) {
        this.canvasServer = canvasServer;
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

            if (canvasToken == null) {
                this.authorize();
            }
            return canvasToken;

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
            logInActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + this.canvasServer + "/login/oauth2/auth?client_id=" + clientID + "&response_type=code")));
            canvasToken = "123"; //TODO do whatever i need to do
        } catch (Exception e)
        {

        }

    }
}
