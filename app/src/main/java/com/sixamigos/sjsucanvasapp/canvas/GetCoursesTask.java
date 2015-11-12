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
    //private static final String COURSE_URL= "http://facebook.com";

    private CanvasCallback callback;

    @Override
    protected JSONObject doInBackground(HashMap<String, String>[] params) {
        JSONObject result = null;
        /*try {
            result = ServerTaskUtility.sendData(URL, params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
            return null;
        }*/
        try {
            URL url = new URL(COURSE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("Bearer", CanvasConnector.DEBUG_TOKEN.toCharArray());
                }
            });

            System.out.println("Original URL: " + con.getURL());
            System.out.println("response code: " + con.getResponseCode());
            con.connect();
            System.out.println("Connected URL: " + con.getURL());
            System.out.println("response code: " + con.getResponseCode());
            InputStream is = con.getInputStream();
            System.out.println("Redirected URL: " + con.getURL());
            System.out.println("response code: " + con.getResponseCode());
            is.close();
            /*
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            int responseCode = urlConnection.getResponseCode();
            System.out.println(responseCode);
            String line = "";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            urlConnection.disconnect();
            */
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
            return null;
        }
        return result;
    }

    public void setCallback(CanvasCallback callback) {
        this.callback = callback;
    }

    protected void onProgressUpdate(String... progress) {

    }

    protected void onPostExecute(JSONObject result) {

    }
}
