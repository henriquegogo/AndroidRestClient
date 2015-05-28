package io.github.henriquegogo.placarge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.List;

import io.github.henriquegogo.placarge.ConnectionProxyResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
import io.github.henriquegogo.placarge.MatchesAdapter;
import io.github.henriquegogo.placarge.R;
import io.github.henriquegogo.placarge.TeamsAdapter;
import io.github.henriquegogo.placarge.entities.Match;
import io.github.henriquegogo.placarge.entities.Matches;
import io.github.henriquegogo.placarge.entities.Team;

public class MainActivity extends ActionBarActivity implements ConnectionProxyResponse {
    public static final String MATCH_LINK_URL_STRING = "http://matchesjson.herokuapp.com/matches.json";
    public static final String CACHE_MATCHES_STRING = "CACHE_MATCHES_STRING";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView teamsListView;
    private ListView matchesListView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Matches matches;
    private MatchesAdapter matchesAdapter;
    private List<Team> teams;
    private TeamsAdapter teamsAdapter;
    private Team teamSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        teamsListView = (ListView) findViewById(R.id.teamsListView);
        teamsListView.setOnItemClickListener(onClickTeamListener);

        matchesListView = (ListView) findViewById(R.id.matchesListView);
        matchesListView.setOnItemClickListener(onClickMatchListener);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.matchesRefresh);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMatchesFromCache();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConnectionProxyFinish(String output) {
        swipeRefreshLayout.setRefreshing(false);

        if (output != null && !output.isEmpty()) {
            setCacheMatchesString(output);
            matches = new Matches(output);
            showMatches(matches);

        }
        else if (!getCacheMatchesString().isEmpty()) {
            loadMatchesFromCache();
        }
    }

    private void loadMatchesFromCache() {
        String cacheMatchesString = getCacheMatchesString();

        if (cacheMatchesString != null && !cacheMatchesString.isEmpty()) {
            matches = new Matches(cacheMatchesString);
            showMatches(matches);

        } else {
            loadMatches();
        }
    }

    private void loadMatches() {
        swipeRefreshLayout.setRefreshing(true);
        new ConnectionProxy(this).execute(MATCH_LINK_URL_STRING);
    }

    private void showMatches(Matches matches) {
        List<Match> matchesList = matches.getMatches(teamSelected);
        matchesAdapter = new MatchesAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_match_item, matchesList);
        matchesListView.setAdapter(matchesAdapter);
        teams = matches.getTeams();
        showTeams(teams);
    }

    private void showTeams(List<Team> teams) {
        teamsAdapter = new TeamsAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_team_item, teams);
        teamsListView.setAdapter(teamsAdapter);
    }

    private void setCacheMatchesString(String matchesString) {
        PreferenceManager
                .getDefaultSharedPreferences(getBaseContext()).edit()
                .putString(CACHE_MATCHES_STRING, matchesString).commit();
    }

    private String getCacheMatchesString() {
        String matchesString = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext())
                .getString(CACHE_MATCHES_STRING, "");

        return matchesString;
    }

    OnItemClickListener onClickTeamListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            teamSelected = (Team) parent.getAdapter().getItem(position);
            drawerLayout.closeDrawers();
            loadMatchesFromCache();
        }
    };

    OnItemClickListener onClickMatchListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Match matchSelected = (Match) parent.getAdapter().getItem(position);
            Intent intent = new Intent(getApplicationContext(), MatchPreviewActivity.class);
            intent.putExtra(getString(R.string.match_link_label), matchSelected.getLink());
            startActivity(intent);
        }
    };

    OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            teamSelected = null;
            loadMatches();
        }
    };
}
