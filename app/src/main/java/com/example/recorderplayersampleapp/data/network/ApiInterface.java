package com.example.recorderplayersampleapp.data.network;

import com.example.recorderplayersampleapp.data.entity.audioSamples.AudioSamplesList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The API interface for your application
 */
public interface ApiInterface {
    /**
     * fetchAudioSamplesList
     *
     * @return audio samples
     */
    @GET("automotive-media/music.json")
    Call<AudioSamplesList> fetchAudioSamplesList();
}
