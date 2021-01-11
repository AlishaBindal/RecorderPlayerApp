package com.example.recorderplayersampleapp.ui.sampleAudios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recorderplayersampleapp.R;
import com.example.recorderplayersampleapp.data.entity.audioSamples.Music;

import java.util.ArrayList;

/**
 * SampleAudiosAdapter
 */
public class SampleAudiosAdapter extends RecyclerView.Adapter<SampleAudiosAdapter.ViewHolder> {
    private final ArrayList<Music> audioSamplesList;
    private final SampleAudiosAdapterCallback mListener;

    /**
     * Instantiates a new Adapter
     *
     * @param list the list
     */
    public SampleAudiosAdapter(final ArrayList<Music> list, final SampleAudiosAdapterCallback callback) {
        this.audioSamplesList = list;
        mListener = callback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_audios, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int pos) {
        final int position = holder.getAdapterPosition();
        Music audioSamples = audioSamplesList.get(position);

        holder.tvMusicTitle.setText(audioSamples.getTitle());
        holder.tvMusicArtist.setText(audioSamples.getArtist());

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.playMedia(audioSamples);
            }
        });
    }

    @Override
    public int getItemCount() {
        assert audioSamplesList != null;
        return audioSamplesList.size();
    }

    /**
     * The type View holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMusicTitle, tvMusicArtist;

        /**
         * Instantiates a new View holder.
         *
         * @param v the v
         */
        ViewHolder(final View v) {
            super(v);
            tvMusicTitle = v.findViewById(R.id.tvMusicTitle);
            tvMusicArtist = v.findViewById(R.id.tvMusicArtist);
        }
    }

    /**
     * Sample Audios Adapter Callback
     */
    public interface SampleAudiosAdapterCallback {
        /**
         * play media
         *
         * @param musicDetail musicDetail
         */
        void playMedia(Music musicDetail);
    }
}
