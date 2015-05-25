package io.github.henriquegogo.placarge;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectionProxy extends AsyncTask<String, Void, String> {
    public AsyncTaskResponse asyncTaskResponse = null;

    public ConnectionProxy(AsyncTaskResponse asyncTaskResponse) {
        this.asyncTaskResponse = asyncTaskResponse;
    }

    @Override
    protected String doInBackground(String... params) {
        String responseString = null;
        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDefaultUseCaches(true);
            urlConnection.setUseCaches(true);
            urlConnection.setRequestMethod("GET");
            InputStream inputStream = urlConnection.getInputStream();
            responseString = getStringFromStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    @Override
    protected void onPostExecute(String output) {
        asyncTaskResponse.onAsyncTaskFinish(output);
    }

    private String getStringFromStream(InputStream inputStream) throws IOException {
        char[] buffer = new char[0x10000];
        StringBuilder out = new StringBuilder();
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int read;
        while ((read = inputStreamReader.read(buffer, 0, buffer.length)) > 0) {
            out.append(buffer, 0, read);
        }
        return out.toString();
    }
}
