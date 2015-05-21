package io.github.henriquegogo.placarge.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import org.json.JSONException;

import java.util.List;

import io.github.henriquegogo.placarge.AsyncTaskResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
import io.github.henriquegogo.placarge.MatchesAdapter;
import io.github.henriquegogo.placarge.R;
import io.github.henriquegogo.placarge.entities.Match;
import io.github.henriquegogo.placarge.entities.Matches;

public class MainActivity extends ActionBarActivity implements AsyncTaskResponse {
    private ListView matchesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matchesListView = (ListView) findViewById(R.id.matchesListView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new ConnectionProxy(this).execute("http://matchesjson.herokuapp.com/matches.json");
    }

    @Override
    public void onAsyncTaskFinish(String output) throws JSONException {
        List<Match> matches = new Matches(output).matches;
        showMatches(matches);
    }

    private void showMatches(List<Match> matches) {
        MatchesAdapter matchesAdapter = new MatchesAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_match_item, matches);
        matchesListView.setAdapter(matchesAdapter);
    }
}
