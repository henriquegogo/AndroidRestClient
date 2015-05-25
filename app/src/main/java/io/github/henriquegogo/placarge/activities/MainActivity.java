package io.github.henriquegogo.placarge.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.List;

import io.github.henriquegogo.placarge.AsyncTaskResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
import io.github.henriquegogo.placarge.MatchesAdapter;
import io.github.henriquegogo.placarge.R;
import io.github.henriquegogo.placarge.entities.Match;
import io.github.henriquegogo.placarge.entities.Matches;

public class MainActivity extends ActionBarActivity implements AsyncTaskResponse {
    public static final String MATCH_LINK_URL_STRING = "http://matchesjson.herokuapp.com/matches.json";
    private ListView matchesListView;
    private List<Match> matches;
    private MatchesAdapter matchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchesListView = (ListView) findViewById(R.id.matchesListView);
        matchesListView.setOnItemClickListener(onClickMatchListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new ConnectionProxy(this).execute(MATCH_LINK_URL_STRING);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            new ConnectionProxy(this).execute(MATCH_LINK_URL_STRING);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAsyncTaskFinish(String output) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String matchesCache = sharedPreferences.getString(String.valueOf(R.string.matches_cache), null);

        if (output != null) {
            SharedPreferences.Editor sharedPreferencesEditor = getPreferences(Context.MODE_PRIVATE).edit();
            sharedPreferencesEditor.putString(getString(R.string.matches_cache), output);
            sharedPreferencesEditor.apply();

            matches = new Matches(output).matches;
            showMatches(matches);
        }
        else if (matchesCache != null) {
            matches = new Matches(matchesCache).matches;
            showMatches(matches);
        }
    }

    private void showMatches(List<Match> matches) {
        matchesAdapter = new MatchesAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_match_item, matches);
        matchesListView.setAdapter(matchesAdapter);
    }

    OnItemClickListener onClickMatchListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Match matchSelected = (Match) parent.getAdapter().getItem(position);
            Intent intent = new Intent(getApplicationContext(), MatchPreviewActivity.class);
            intent.putExtra(getString(R.string.match_link_label), matchSelected.getLink());
            startActivity(intent);
        }
    };
}
