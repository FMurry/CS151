package com.sixamigos.sjsucanvasapp.login.canvas;

/**
 * Created by christopherbachner on 11/6/15.
 */
public class CanvasToken {

    private String CanvasToken = null;

    /**
     * Used by other classes to get the token. If token does not exist, authorize method will be called.
     * @return Returns token as String.
     */
    public String getCanvasToken() {

        try {

            if (CanvasToken == null) {
                this.authorize();
            }
            return CanvasToken;

        } catch (CanvasLoginFailureException e)
        {
            System.out.printf(e.getLocalizedMessage());
            return null;
        }

    }


    /**
     * Gets called if token is not null. Opens WebView and performs complete OAUTH2.
     * @throws CanvasLoginFailureException If for some reason the login fails or user cancels login.
     */
    private void authorize() throws CanvasLoginFailureException
    {
        throw new CanvasLoginFailureException("Not implemented yet.");

    }
}
