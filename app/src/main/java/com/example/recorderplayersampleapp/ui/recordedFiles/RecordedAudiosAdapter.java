package com.example.recorderplayersampleapp.ui.recordedFiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recorderplayersampleapp.R;

import java.util.ArrayList;

/**
 * Recorded Audios Adapter
 */
public class RecordedAudiosAdapter extends RecyclerView.Adapter<RecordedAudiosAdapter.ViewHolder> {
    private ArrayList<String> audioSamplesList;
    private final RecordedAudiosAdapterCallback mListener;

    /**
     * Instantiates a new Adapter
     *
     * @param list the list
     */
    public RecordedAudiosAdapter(final ArrayList<String> list, final RecordedAudiosAdapterCallback callback) {
        this.audioSamplesList = list;
        mListener = callback;
    }

    public void setData(final ArrayList<String> list) {
        this.audioSamplesList = list;
        notifyDataSetChanged();
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
        String recordedFile = audioSamplesList.get(position);
        Context context = holder.itemView.getContext();
        holder.ivMediaImage.setImageDrawable(context.getDrawable(R.drawable.ic_record));
        holder.tvMusicTitle.setText(recordedFile);

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.playMedia(recordedFile);
            }
        });
    }

    @Override
    public int getItemCount() {
        return audioSamplesList.size();
    }

    /**
     * The type View holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMusicTitle;
        private final ImageView ivMediaImage;

        /**
         * Instantiates a new View holder.
         *
         * @param v the v
         */
        ViewHolder(final View v) {
            super(v);
            ivMediaImage = v.findViewById(R.id.ivMediaImage);
            tvMusicTitle = v.findViewById(R.id.tvMusicTitle);
            v.findViewById(R.id.tvMusicArtist).setVisibility(View.GONE);
        }
    }

    /**
     * Recorded Audios Adapter Callback
     */
    public interface RecordedAudiosAdapterCallback {
        /**
         * play media
         *
         * @param recordedFilePath recordedFilePath
         */
        void playMedia(String recordedFilePath);
    }
}
