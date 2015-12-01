package com.sixamigos.sjsucanvasapp.canvas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.sixamigos.sjsucanvasapp.home.HomeActivity;

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
public class GetCoursesTask extends AsyncTask<HashMap<String, String>, Integer, JSONObject> {
    private static final String TAG = "canvas.GetCoursesTask";
    private static final String COURSE_URL= "https://sjsu.instructure.com/api/v1/courses";

    private CanvasCallback callback;
    private ProgressDialog dialog;

    public GetCoursesTask(Context context) {
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Gathering Courses");
        dialog.show();
    }

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

    protected void onPostExecute(JSONObject result) {
        if (dialog.isShowing())
            dialog.dismiss();
        callback.call(result);
    }
}
