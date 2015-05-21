package io.github.henriquegogo.placarge.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;

import io.github.henriquegogo.placarge.AsyncTaskResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
import io.github.henriquegogo.placarge.R;
import io.github.henriquegogo.placarge.entities.Matches;


public class MainActivity extends ActionBarActivity implements AsyncTaskResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new ConnectionProxy(this).execute("http://matchesjson.herokuapp.com/matches.json");
    }

    @Override
    public void onAsyncTaskFinish(String output) {
        TextView mainTextContent = (TextView) findViewById(R.id.main_text_content);
        mainTextContent.setText(output);

        try {
            Matches matches = new Matches(output);
            Log.d("MATCHES: ", matches.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
