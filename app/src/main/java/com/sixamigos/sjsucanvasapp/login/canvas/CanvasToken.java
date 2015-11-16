package com.sixamigos.sjsucanvasapp.login.canvas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by christopherbachner on 11/6/15.
 */
public class CanvasToken {

    private static String canvasToken = null;


    /**
     * Sets new access token that has been pasted by the user in loginactivity.
     *
     * @param cacheFile File in the cache directory of the context
     * @param token     The pasted token.
     */
    protected static void setCanvasToken(String token, File cacheFile) {
        BufferedWriter bufferedWriter;

        canvasToken = token;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(cacheFile, false));
            bufferedWriter.write(canvasToken);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used internally to check if an access token is already existent in the cache
     *
     * @param cacheFile File in the cache directory of the context
     * @return
     */

    protected static boolean readCanvasToken(File cacheFile) {
        try {
            if (cacheFile.exists()) {
                canvasToken = new BufferedReader(new FileReader(cacheFile)).readLine();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
