package com.example.recorderplayersampleapp.data.entity.audioSamples;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.recorderplayersampleapp.data.entity.CommonResponse;

import java.util.ArrayList;

public class AudioSamplesList extends CommonResponse {
    @SerializedName("music")
    @Expose
    private ArrayList<Music> music = null;

    public ArrayList<Music> getMusic() {
        return music;
    }

    public void setMusic(ArrayList<Music> music) {
        this.music = music;
    }

}
