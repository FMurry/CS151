package com.sixamigos.sjsucanvasapp.canvas;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Alex Heritier
 */
public class GetCoursesTask extends AsyncTask<HashMap<String, String>, String, JSONObject> {
    private static final String TAG = "canvas.GetCoursesTask";
    private static final String COURSE_URL= "https://sjsu.instructure.com/api/v1/courses";

    private CanvasCallback callback;

    @Override
    protected JSONObject doInBackground(HashMap<String, String>[] params) {
        JSONObject result = null;
        try {
            result = ServerTaskUtility.sendData(COURSE_URL, params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return result;
    }

    public void setCallback(CanvasCallback callback) {
        this.callback = callback;
    }

    protected void onProgressUpdate(String... progress) {

    }

    protected void onPostExecute(JSONObject result) {
        callback.call(result);
    }
}
