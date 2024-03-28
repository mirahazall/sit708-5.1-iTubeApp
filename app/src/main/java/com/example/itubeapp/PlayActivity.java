package com.example.itubeapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {
Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String videoUrl = getIntent().getStringExtra("video_url");
        String embeddedUrl = "https://www.youtube.com/embed/" + getVideoId(videoUrl);
        webView.loadUrl(embeddedUrl);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getVideoId(String url) {
        // Extract video ID from YouTube URL
        String videoId = null;
        if (url != null && url.trim().length() > 0) {
            String[] separated = url.split("v=");
            if (separated.length > 1) {
                videoId = separated[1];
            } else {
                videoId = url.substring(url.lastIndexOf("/") + 1);
            }
        }
        return videoId;
    }
}
