package com.sixamigos.sjsucanvasapp.canvas;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
            String requestUrl = url;
            Iterator iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iterator.next();
                BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                System.out.println(nameValuePair);
                requestUrl += "?" + nameValuePair.getName() + "=" + nameValuePair.getValue();
            }
            HttpGet httpget = new HttpGet(url);

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
            String jsonString = stringBuilder.toString();
            System.out.println(jsonString);
            return new JSONObject(jsonString.substring(0, jsonString.length())); // remove the while(1)

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
