package io.github.henriquegogo.placarge.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.henriquegogo.placarge.AsyncTaskResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
import io.github.henriquegogo.placarge.MatchesAdapter;
import io.github.henriquegogo.placarge.R;
import io.github.henriquegogo.placarge.TeamsAdapter;
import io.github.henriquegogo.placarge.entities.Match;
import io.github.henriquegogo.placarge.entities.Matches;
import io.github.henriquegogo.placarge.entities.Team;

public class MainActivity extends ActionBarActivity implements AsyncTaskResponse {
    public static final String MATCH_LINK_URL_STRING = "http://matchesjson.herokuapp.com/matches.json";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView teamsListView;
    private ListView matchesListView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Matches matches;
    private MatchesAdapter matchesAdapter;
    private List<Team> teams;
    private TeamsAdapter teamsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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
        loadMatches();
    }

    @Override
    public void onAsyncTaskFinish(String output) {
        swipeRefreshLayout.setRefreshing(false);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String matchesCache = sharedPreferences.getString(String.valueOf(R.string.matches_cache), null);

        if (output != null) {
            SharedPreferences.Editor sharedPreferencesEditor = getPreferences(Context.MODE_PRIVATE).edit();
            sharedPreferencesEditor.putString(getString(R.string.matches_cache), output);
            sharedPreferencesEditor.apply();

            matches = new Matches(output);
            showMatches(matches);
        }
        else if (matchesCache != null) {
            matches = new Matches(matchesCache);
            showMatches(matches);
        }
    }

    private void showMatches(Matches matches) {
        matchesAdapter = new MatchesAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_match_item, matches.getMatches());
        matchesListView.setAdapter(matchesAdapter);

        teams = matches.getTeams();
        showTeams(teams);
    }

    private void showTeams(List<Team> teams) {
        teamsAdapter = new TeamsAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_team_item, teams);
        teamsListView.setAdapter(teamsAdapter);
    }

    private void loadMatches() {
        swipeRefreshLayout.setRefreshing(true);
        new ConnectionProxy(this).execute(MATCH_LINK_URL_STRING);
    }

    OnItemClickListener onClickTeamListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
            loadMatches();
        }
    };
}
