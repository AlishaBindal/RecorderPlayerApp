package com.example.recorderplayersampleapp.ui.home;

import com.example.recorderplayersampleapp.base.interactor.BaseInteractor;
import com.example.recorderplayersampleapp.data.entity.audioSamples.AudioSamplesList;
import com.example.recorderplayersampleapp.data.network.ApiError;

/**
 * Home Interactor
 */
public interface HomeInteractor extends BaseInteractor {

    /**
     * fetch Audio Samples List
     *
     * @param mApiListener the m api listener
     */
    void fetchAudioSamplesList(fetchAudioSamplesListener mApiListener);

    /**
     * The interface Api listener.
     */
    interface fetchAudioSamplesListener {
        /**
         * On success.
         *
         * @param audioSamplesList audioSamplesList
         */
        void onSuccess(AudioSamplesList audioSamplesList);

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        void onFailure(ApiError apiError, Throwable throwable);
    }
}
