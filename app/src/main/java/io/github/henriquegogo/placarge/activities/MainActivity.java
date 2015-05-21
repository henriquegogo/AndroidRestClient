package io.github.henriquegogo.placarge.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.henriquegogo.placarge.AsyncTaskResponse;
import io.github.henriquegogo.placarge.ConnectionProxy;
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
    public void onAsyncTaskFinish(String output) {
        try {
            List<Match> matches = new Matches(output).matches;
            showMatches(matches);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showMatches(List<Match> matches) {
        MatchesAdapter matchesAdapter = new MatchesAdapter(getApplicationContext().getApplicationContext(), R.layout.layout_match_item, matches);
        matchesListView.setAdapter(matchesAdapter);
    }

    public class MatchesAdapter extends ArrayAdapter<Match> {
        private List<Match> matches;
        private Context context;

        public MatchesAdapter(Context context, int resourceId, List<Match> matches) {
            super(context, resourceId, matches);

            this.matches = matches;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.layout_match_item, null);
            }

            Match match = matches.get(position);
            if (match != null) {
                TextView matchWhereTextView = (TextView) view.findViewById(R.id.matchWhereTextView);
                matchWhereTextView.setText(match.getWhere());
            }

            return view;
        }
    }
}
