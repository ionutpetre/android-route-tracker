package com.pdm.whereto;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class InitialDumpTask extends AsyncTask<Void, Void, Void> {

    private static final String URL = "http://handmaderevolution.ro/whereTo.json";
    private Context _context;
    private ProgressDialog _dialog;

    public InitialDumpTask(Context context) {

        _context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        _dialog = new ProgressDialog(_context);
        _dialog.setTitle(R.string.loading);
        _dialog.setMessage(_context.getResources().getString(R.string.wait));
        _dialog.setIndeterminate(true);
        _dialog.setCancelable(false);
        _dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            String response = ConnectionUtils.getResponseFromURL(URL);
            List<Location> locations = ConnectionUtils.parseResponse(response);
            DatabaseHandler.getInstance(_context).addLocations(locations);
        } catch (Exception e) {
            Log.e("Connect", "Error while trying to get initial data!", e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void param) {

        _dialog.dismiss();
    }
}