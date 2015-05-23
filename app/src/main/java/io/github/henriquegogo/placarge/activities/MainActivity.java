package io.github.henriquegogo.placarge.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import io.github.henriquegogo.placarge.AsyncTaskResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
import io.github.henriquegogo.placarge.MatchesAdapter;
import io.github.henriquegogo.placarge.R;
import io.github.henriquegogo.placarge.entities.Match;
import io.github.henriquegogo.placarge.entities.Matches;

public class MainActivity extends ActionBarActivity implements AsyncTaskResponse {
    private ListView matchesListView;
    private List<Match> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matchesListView = (ListView) findViewById(R.id.matchesListView);
        matchesListView.setOnItemClickListener(new OnClickMatchListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        new ConnectionProxy(this).execute("http://matchesjson.herokuapp.com/matches.json");
    }

    @Override
    public void onAsyncTaskFinish(String output) {
        matches = new Matches(output).matches;
        showMatches(matches);
    }

    private void showMatches(List<Match> matches) {
        MatchesAdapter matchesAdapter = new MatchesAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_match_item, matches);
        matchesListView.setAdapter(matchesAdapter);
    }

    private static class OnClickMatchListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("ACAO", "Clicou no item " + position);
        }
    }
}
