package io.github.henriquegogo.placarge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import io.github.henriquegogo.placarge.R;

public class MatchPreviewActivity extends ActionBarActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_preview);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String link = intent.getStringExtra(getString(R.string.MATCH_LINK));

        webView = (WebView) findViewById(R.id.webView);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }
}
