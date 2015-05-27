package io.github.henriquegogo.placarge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.henriquegogo.placarge.entities.Match;
import io.github.henriquegogo.placarge.entities.Team;

public class TeamsAdapter extends ArrayAdapter<Team> {
    private List<Team> teams = null;
    private Context context;

    public TeamsAdapter(Context context, int resourceId, List<Team> teams) {
        super(context, resourceId, teams);

        this.teams = teams;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_team_item, null);

        Team team = teams.get(position);
        if (team != null) {
            ImageView teamShield = (ImageView) convertView.findViewById(R.id.teamShield);
            TextView teamName = (TextView) convertView.findViewById(R.id.teamName);

            teamName.setText(team.getName());
            new ImageDownloader(teamShield).execute(team.getShield());
        }

        return convertView;
    }
}