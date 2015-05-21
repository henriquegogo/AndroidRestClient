package io.github.henriquegogo.placarge;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
            TextView matchWhereTextView = (TextView) convertView.findViewById(R.id.matchWhereTextView);
            matchWhereTextView.setText(match.getWhere());
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ACAO", "clicou");
            }
        });

        return convertView;
    }
}