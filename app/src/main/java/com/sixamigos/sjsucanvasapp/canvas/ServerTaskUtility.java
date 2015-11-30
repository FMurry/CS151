package com.sixamigos.sjsucanvasapp.canvas;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Alex Heritier
 */
public class ServerTaskUtility {
    public static final String TAG = "ServerTaskUtility";

    public static JSONObject sendData(String url, HashMap<String, String> data) throws JSONException {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpParams params = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 3000);
        HttpConnectionParams.setSoTimeout(params, 3000);

        try {
            // Add your data
            String requestUrl = url + "?";
            Iterator iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iterator.next();
                requestUrl += entry.getKey() + "=" + entry.getValue() + "&";
            }
            Log.e("URL", requestUrl);
            HttpGet httpget = new HttpGet(requestUrl);

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httpget);

            // According to the JAVA API, InputStream constructor do nothing.
            //So we can't initialize InputStream although it is not an interface
            InputStream inputStream = response.getEntity().getContent();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();

            String bufferedStrChunk = null;
            while((bufferedStrChunk = bufferedReader.readLine()) != null){
                stringBuilder.append(bufferedStrChunk);
            }

            //Log.d(TAG, stringBuilder.toString());
            Log.e("URL", url);
            String jsonString = stringBuilder.toString();
            Log.e("JSONSTRING", jsonString);

            JSONObject returnObject = null;
            try {
                returnObject = new JSONObject(jsonString);
                returnObject.put("_is_array", false);
            } catch (JSONException e) {
                returnObject = new JSONObject();
                returnObject.put("_wrapped_array", new JSONArray(jsonString));
                returnObject.put("_is_array", true);
            }
            return returnObject;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
