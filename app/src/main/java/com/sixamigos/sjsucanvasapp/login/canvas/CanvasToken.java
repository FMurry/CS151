package com.sixamigos.sjsucanvasapp.login.canvas;

/**
 * Created by christopherbachner on 11/6/15.
 */
public class CanvasToken {

    private static String canvasToken = null;

    //TODO implement saved encrypted token

    /**
     * Sets new access token that has been pasted by the user in loginactivity.
     * @param token The pasted token.
     */
    protected static void setCanvasToken(String token) {
        canvasToken = token;
    }

    /**
     * Used by other classes to get the token. If token does not exist, an exception will occur.
     *
     * @return Returns token as String.
     */
    public static String getCanvasToken() throws CanvasLoginFailureException {

        if (canvasToken == null) {
            throw new CanvasLoginFailureException("No token has been set.");
        }
        return canvasToken;


    }

}
