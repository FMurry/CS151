package com.sixamigos.sjsucanvasapp.canvas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

import org.json.JSONObject;

import java.util.HashMap;

  public class GetAssignmentsTask extends AsyncTask<HashMap<String, String>, Integer, JSONObject> {
    private static final String TAG = "canvas.GetCoursesTask";
    private String COURSE_URL= "https://sjsu.instructure.com/api/v1/courses/";

    private CanvasCallback callback;
    private ProgressDialog dialog;

    public GetAssignmentsTask(Context context, int id) {
      dialog = new ProgressDialog(context);
      COURSE_URL += id;
      COURSE_URL += "/assignments";
    }

    @Override
    protected void onPreExecute() {
      dialog.setMessage("Gathering Assignments");
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
