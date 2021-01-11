package com.example.recorderplayersampleapp.base.interactor;


import com.example.recorderplayersampleapp.data.db.BaseCommonData;

/**
 * Base Interactor Impl
 */
public class BaseInteractorImpl implements BaseInteractor {

    @Override
    public void clearSessionManager() {
        BaseCommonData.clearData();
    }

}
