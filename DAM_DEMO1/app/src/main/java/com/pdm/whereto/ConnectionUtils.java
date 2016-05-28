package com.pdm.whereto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {

    public static boolean isConnectedToInternet(AppCompatActivity activity) {

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String getResponseFromURL(String link) throws IOException {

        HttpURLConnection connection;

        URL url = new URL(link);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        InputStream isr = new BufferedInputStream(connection.getInputStream());
        byte[] bytes = new byte[10000];
        ByteArrayOutputStream stream = new ByteArrayOutputStream(10000);
        while (true) {
            int numRead = isr.read(bytes);
            if (numRead == -1)
                break;
            stream.write(bytes, 0, numRead);
        }

        String response = new String(stream.toByteArray(), "UTF-8");
        isr.close();
        connection.disconnect();

        return response;
    }

    public static List<Location> parseResponse(String response) throws JSONException {

        List<Location> locations = new ArrayList<>();
        JSONArray array = new JSONArray(response);

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            locations.add(new Location(obj.getInt("id"), obj.getString("type"), obj.getString("address"), obj.getString("name"), obj.getDouble("lat"), obj.getDouble("lng")));
        }

        return locations;
    }
}