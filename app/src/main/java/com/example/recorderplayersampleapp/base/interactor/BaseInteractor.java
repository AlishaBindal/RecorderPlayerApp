package com.example.recorderplayersampleapp.base.interactor;

import com.example.recorderplayersampleapp.data.entity.CommonResponse;
import com.example.recorderplayersampleapp.data.network.ApiError;

/**
 * Base Interactor
 */
public interface BaseInteractor {

    /**
     * Clear session manager.
     */
    void clearSessionManager();

    /**
     * The interface Api listener.
     */
    interface ApiListener {
        /**
         * On success.
         *
         * @param commonResponse the common response
         */
        void onSuccess(CommonResponse commonResponse);

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        void onFailure(ApiError apiError, Throwable throwable);
    }
}
