package com.sixamigos.sjsucanvasapp.canvas;

import android.preference.PreferenceActivity;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 1/23/15.
 */
public class ServerTaskUtility {
    public static final String TAG = "ServerTaskUtility";

    public static JSONObject sendData(String url, HashMap<String, String> data) throws JSONException {
        // Create a new HttpClient and Post Header
        /*
        HttpClient httpclient = new DefaultHttpClient();
        HttpParams params = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 3000);
        HttpConnectionParams.setSoTimeout(params, 3000);
        HttpPost httppost = new HttpPost(url);

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            Iterator iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) iterator.next();
                BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                System.out.println(nameValuePair);
                nameValuePairs.add(nameValuePair);
            }
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            for (Header h : httppost.getAllHeaders()) {
                System.out.println(h.toString());
            }
            System.out.println(httppost.getURI());
            HttpResponse response = httpclient.execute(httppost);

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
            return new JSONObject(jsonString.substring(9, jsonString.length())); // remove the while(1)

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
            for (Header h : httpget.getAllHeaders()) {
                System.out.println(h.toString());
            }
            System.out.println(httpget.getURI());
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
