package io.github.henriquegogo.placarge;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.github.henriquegogo.placarge.entities.Match;

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
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_match_item, null);

        Match match = matches.get(position);
        if (match != null) {
            TextView matchWhereTextView = (TextView) convertView.findViewById(R.id.matchWhere);
            TextView hourOfMatch = (TextView) convertView.findViewById(R.id.hourOfMatch);
            TextView homeTeamName = (TextView) convertView.findViewById(R.id.homeTeamName);
            TextView homeScore = (TextView) convertView.findViewById(R.id.homeScore);
            ImageView homeShield = (ImageView) convertView.findViewById(R.id.homeShield);
            TextView guestTeamName = (TextView) convertView.findViewById(R.id.guestTeamName);
            TextView guestScore = (TextView) convertView.findViewById(R.id.guestScore);
            ImageView guestShield = (ImageView) convertView.findViewById(R.id.guestShield);

            matchWhereTextView.setText(match.getWhere().toUpperCase());
            hourOfMatch.setText(getHourFromDateString(match.getDate()));

            homeTeamName.setText(match.getHomeTeam().getName());
            homeScore.setText(String.valueOf(match.getHomeScore()));
            guestTeamName.setText(match.getGuestTeam().getName());
            guestScore.setText(String.valueOf(match.getGuestScore()));

            new ImageDownloader(homeShield).execute(match.getHomeTeam().getShield());
            new ImageDownloader(guestShield).execute(match.getGuestTeam().getShield());
        }

        return convertView;
    }

    private String getHourFromDateString(String dateString) {
        String hour = "";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH:mm");

        try {
            Date date = inputDateFormat.parse(dateString);
            hour = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return hour;
    }
}