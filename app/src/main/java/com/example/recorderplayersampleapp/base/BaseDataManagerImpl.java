package com.example.recorderplayersampleapp.base;

import com.example.recorderplayersampleapp.data.db.BaseDbHelper;
import com.example.recorderplayersampleapp.data.db.BaseDbHelperImpl;

import java.util.ArrayList;

/**
 * Base Data Manager Impl
 **/
public class BaseDataManagerImpl implements BaseDataManager {
    private final BaseDbHelper mBaseDbHelper;

    /**
     * Instantiates a new Data manager.
     */
    public BaseDataManagerImpl() {
        this.mBaseDbHelper = new BaseDbHelperImpl();
    }

    @Override
    public String getAccessToken() {
        return mBaseDbHelper.getAccessToken();
    }

    @Override
    public void saveAccessToken(final String accessToken) {
        mBaseDbHelper.saveAccessToken(accessToken);
    }

    @Override
    public void clearSessionManager() {
        mBaseDbHelper.clearSessionManager();
    }

    @Override
    public boolean getDialogVisibility() {
        return mBaseDbHelper.getDialogVisibility();
    }

    @Override
    public void setDialogVisibility(boolean isVisible) {
        mBaseDbHelper.setDialogVisibility(isVisible);
    }

    @Override
    public void saveRecordedAudiosList(ArrayList<String> recordedAudiosList) {
        mBaseDbHelper.saveRecordedAudiosList(recordedAudiosList);
    }

    @Override
    public ArrayList<String> getRecordedAudiosList() {
        return mBaseDbHelper.getRecordedAudiosList();
    }

}
