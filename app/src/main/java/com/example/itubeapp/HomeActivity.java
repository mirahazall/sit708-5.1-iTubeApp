package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button buttonPlay;
    Button buttonAddToPlayList;
    Button buttonMyPlaylist;
    EditText editTextUrl;
    private PlaylistDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        editTextUrl = findViewById(R.id.editTextUrl);
        buttonPlay = findViewById(R.id.buttonPlay);
        editTextUrl.setLongClickable(true);
        editTextUrl.setTextIsSelectable(true);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {

                    String url = editTextUrl.getText().toString().trim();
                    if (!url.isEmpty()) {
                        Intent intent = new Intent(HomeActivity.this, PlayActivity.class);
                        intent.putExtra("video_url", url);
                        startActivity(intent);
                    } else {
                        Toast.makeText(HomeActivity.this, "Please enter a valid YouTube URL", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        dbHelper = new PlaylistDbHelper(this);
        Button buttonAddToPlayList = findViewById(R.id.buttonAddToPlayList);
        buttonAddToPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                String url = editTextUrl.getText().toString().trim();
                if (!url.isEmpty()) {
                    dbHelper.addPlaylistItem(url);
                    Toast.makeText(HomeActivity.this, "Added to playlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter a valid YouTube URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonMyPlaylist = findViewById(R.id.buttonMyPlaylist);
        buttonMyPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent intent = new Intent(HomeActivity.this, MyplaylistActivity.class);
                startActivity(intent);
            }
        });
    }
}