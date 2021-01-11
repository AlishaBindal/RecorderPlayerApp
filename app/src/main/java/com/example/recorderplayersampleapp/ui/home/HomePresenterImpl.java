package com.example.recorderplayersampleapp.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.recorderplayersampleapp.base.presenter.BasePresenterImpl;
import com.example.recorderplayersampleapp.data.entity.audioSamples.AudioSamplesList;
import com.example.recorderplayersampleapp.data.network.ApiError;

import static com.example.recorderplayersampleapp.data.network.AppConstant.SESSION_EXPIRED;


/**
 * Home Presenter Impl
 */
public class HomePresenterImpl extends BasePresenterImpl implements HomePresenter {

    private final HomeView mHomeView;
    private final HomeInteractor mHomeInteractor;

    public HomePresenterImpl(final String deviceName, @NonNull HomeView homeView) {
        super(deviceName);
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void fetchAudioSamplesList() {
        if (isViewAttached()) {
            mHomeView.showLoading();
        }
        mHomeInteractor.fetchAudioSamplesList(new HomeInteractor.fetchAudioSamplesListener() {
            @Override
            public void onSuccess(AudioSamplesList audioSamplesList) {
                if (isViewAttached()) {
                    mHomeView.hideLoading();
                    mHomeView.onGetAudiosSamples(audioSamplesList.getMusic());
                }
            }

            @Override
            public void onFailure(ApiError apiError, Throwable throwable) {
                if (isViewAttached()) {
                    mHomeView.hideLoading();
                    if (apiError != null) {
                        mHomeView.showErrorMessage(apiError.getMessage(), () -> {
                            if (apiError.getStatusCode() == SESSION_EXPIRED) {
                                mHomeView.restartApp();
                            }
                        });
                    } else if (throwable != null) {
                        mHomeView.showErrorMessage(throwable.getMessage());
                        if (throwable.getCause() != null)
                            Log.v("error", throwable.getCause().getMessage() + " " + throwable.getCause().getLocalizedMessage());
                    }
                }
            }
        });
    }

}
