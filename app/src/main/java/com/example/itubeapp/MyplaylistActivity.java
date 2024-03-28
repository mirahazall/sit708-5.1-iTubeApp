// MyPlaylistActivity.java
package com.example.itubeapp;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MyplaylistActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPlaylist;
    private PlaylistAdapter playlistAdapter;
    private PlaylistDbHelper dbHelper;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplaylist);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyplaylistActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new PlaylistDbHelper(this);
        List<String> playlistItems = getPlaylistItems();
        if (playlistItems == null || playlistItems.isEmpty()) {
            Toast.makeText(this, "Playlist is empty", Toast.LENGTH_SHORT).show();
            // Handle case when playlist is empty
        } else {
            recyclerViewPlaylist = findViewById(R.id.recyclerViewPlaylist);
            recyclerViewPlaylist.setLayoutManager(new LinearLayoutManager(this));
            playlistAdapter = new PlaylistAdapter(playlistItems);
            recyclerViewPlaylist.setAdapter(playlistAdapter);
        }
    }

    private List<String> getPlaylistItems() {
        List<String> playlistItems = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM playlist", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String videoUrl = cursor.getString(cursor.getColumnIndexOrThrow("video_url"));
                playlistItems.add(videoUrl);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return playlistItems;
    }
}
