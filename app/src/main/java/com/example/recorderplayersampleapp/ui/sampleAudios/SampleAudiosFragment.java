package com.example.recorderplayersampleapp.ui.sampleAudios;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recorderplayersampleapp.R;
import com.example.recorderplayersampleapp.base.view.BaseFragment;
import com.example.recorderplayersampleapp.data.entity.audioSamples.Music;
import com.example.recorderplayersampleapp.ui.home.HomePresenter;
import com.example.recorderplayersampleapp.ui.home.HomePresenterImpl;
import com.example.recorderplayersampleapp.ui.home.HomeView;
import com.example.recorderplayersampleapp.ui.mediaPlayer.MediaPlayerActivity;

import java.util.ArrayList;


/**
 * Sample Audios Fragment
 */
public class SampleAudiosFragment extends BaseFragment implements HomeView, SampleAudiosAdapter.SampleAudiosAdapterCallback {
    private HomePresenter presenter;
    private RecyclerView rvSampleAudios;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SampleAudiosFragment getInstance() {
        return new SampleAudiosFragment();
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_sample_audios;
    }

    @Override
    public void attachComponent() {
        super.attachComponent();
        presenter = new HomePresenterImpl(getDeviceName(), this);
        presenter.onAttach(this);
    }

    @Override
    protected void initView(final View view) {
        super.initView(view);
        apiCallGetSampleAudios();
        setRecyclerViewLayout(view);
    }

    private void setRecyclerViewLayout(final View view) {
        rvSampleAudios = view.findViewById(R.id.rvSampleAudios);
        rvSampleAudios.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void refreshData() {
        super.refreshData();
        apiCallGetSampleAudios();
    }

    private void apiCallGetSampleAudios() {
        if (checkNetworkConnectedAndShowError()) {
            presenter.fetchAudioSamplesList();
        }
    }

    @Override
    public void onDetach() {
        presenter.onDetach();
        super.onDetach();
    }

    @Override
    public void onGetAudiosSamples(final ArrayList<Music> samplesList) {
        SampleAudiosAdapter audiosAdapter = new SampleAudiosAdapter(samplesList, this);
        rvSampleAudios.setAdapter(audiosAdapter);
    }

    @Override
    public void playMedia(final Music musicDetail) {
        startActivity(MediaPlayerActivity.Companion.getIntent(getContext(), musicDetail.getSource()));
    }
}
