// PlaylistAdapter.java
package com.example.itubeapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private List<String> playlistItems;

    public PlaylistAdapter(List<String> playlistItems) {
        this.playlistItems = playlistItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String videoUrl = playlistItems.get(position);
        holder.textViewVideoUrl.setText(videoUrl);
    }

    @Override
    public int getItemCount() {
        return playlistItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewVideoUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewVideoUrl = itemView.findViewById(R.id.textViewVideoUrl);
        }
    }
}

