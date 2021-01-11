package com.example.recorderplayersampleapp.ui.home;

import com.example.recorderplayersampleapp.base.view.BaseView;
import com.example.recorderplayersampleapp.data.entity.audioSamples.Music;

import java.util.ArrayList;

/**
 * Home View
 */
public interface HomeView extends BaseView {

    void onGetAudiosSamples(ArrayList<Music> audiosList);
}
