package com.example.recorderplayersampleapp.ui.home;


import com.example.recorderplayersampleapp.base.interactor.BaseInteractorImpl;
import com.example.recorderplayersampleapp.data.entity.audioSamples.AudioSamplesList;
import com.example.recorderplayersampleapp.data.network.ApiError;
import com.example.recorderplayersampleapp.data.network.ResponseResolver;
import com.example.recorderplayersampleapp.data.network.RestClient;

/**
 * Home Interactor Impl
 */
public class HomeInteractorImpl extends BaseInteractorImpl implements HomeInteractor {

    @Override
    public void fetchAudioSamplesList(fetchAudioSamplesListener mApiListener) {
        RestClient.getApiInterface().fetchAudioSamplesList().enqueue(new ResponseResolver<AudioSamplesList>() {
            @Override
            public void onSuccess(AudioSamplesList audioSamplesList) {
                mApiListener.onSuccess(audioSamplesList);
            }

            @Override
            public void onError(ApiError error) {
                mApiListener.onFailure(error, null);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mApiListener.onFailure(null, throwable);
            }
        });
    }
}
