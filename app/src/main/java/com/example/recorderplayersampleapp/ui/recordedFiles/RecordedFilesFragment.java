package com.example.recorderplayersampleapp.ui.recordedFiles;

import android.Manifest;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recorderplayersampleapp.R;
import com.example.recorderplayersampleapp.audiorecording.AttachmentLocalData;
import com.example.recorderplayersampleapp.audiorecording.AudioListener;
import com.example.recorderplayersampleapp.audiorecording.RecordAudioListener;
import com.example.recorderplayersampleapp.audiorecording.RecordingItem;
import com.example.recorderplayersampleapp.base.view.BaseFragment;
import com.example.recorderplayersampleapp.data.db.BaseCommonData;
import com.example.recorderplayersampleapp.permission.PermissionUtil;
import com.example.recorderplayersampleapp.permission.SystemPermissionActivity;
import com.example.recorderplayersampleapp.ui.mediaPlayer.MediaPlayerActivity;

import java.util.ArrayList;

import static android.view.View.GONE;


/**
 * Recorded Files Fragment
 */
public class RecordedFilesFragment extends BaseFragment implements AudioListener, RecordedAudiosAdapter.RecordedAudiosAdapterCallback {
    private static final int REQ_CODE_AUDIO = 1;
    private RecordAudioListener recordAudioListener;
    private LinearLayout audioLayout;
    private Chronometer recordChronometer;
    private RecyclerView rvRecordedAudios;
    private RecordedAudiosAdapter audiosAdapter;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RecordedFilesFragment getInstance() {
        return new RecordedFilesFragment();
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_recorded_files;
    }

    @Override
    protected void initView(final View view) {
        super.initView(view);
        audioLayout = view.findViewById(R.id.layout_chat_audio_container);
        recordChronometer = view.findViewById(R.id.chat_audio_record_chronometer);
        recordAudioListener = new RecordAudioListener(getContext());
        recordAudioListener.setOnAudioListener(this);
        view.findViewById(R.id.fabRecordFile).setOnLongClickListener(v -> {
            onLongClickRecordAudio(view);
            return false;
        });
        rvRecordedAudios = view.findViewById(R.id.rvRecordedAudios);
        setData();
    }

    private void setData() {
        rvRecordedAudios.setLayoutManager(new LinearLayoutManager(getContext()));
        if (BaseCommonData.getRecordedAudiosList() != null && !BaseCommonData.getRecordedAudiosList().isEmpty()) {
            audiosAdapter = new RecordedAudiosAdapter(BaseCommonData.getRecordedAudiosList(), this);
            rvRecordedAudios.setAdapter(audiosAdapter);
        }
    }

    private void onLongClickRecordAudio(final View view) {
        view.findViewById(R.id.fabRecordFile).setOnTouchListener(recordAudioListener);
        if (recordAudioListener.isRecordingPossible()) {
            if (PermissionUtil.hasPermissions(getContext(), Manifest.permission.RECORD_AUDIO)) {
                recordAudioListener.startRecord();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onStop(RecordingItem recordingItem) {
        rvRecordedAudios.setVisibility(View.VISIBLE);
        AttachmentLocalData attachementLocalData = new AttachmentLocalData();
        attachementLocalData.setFilePath(recordingItem.getFilePath());
        String mFileName = recordingItem.getFilePath();
        ArrayList<String> recordedAudiosList = new ArrayList<>();
        if (BaseCommonData.getRecordedAudiosList() != null && !BaseCommonData.getRecordedAudiosList().isEmpty()) {
            recordedAudiosList = BaseCommonData.getRecordedAudiosList();
        }
        recordedAudiosList.add(mFileName);
        BaseCommonData.saveRecordedAudiosList(recordedAudiosList);
        Log.v("FILE PATH", mFileName);
        audiosAdapter = new RecordedAudiosAdapter(BaseCommonData.getRecordedAudiosList(), this);
        rvRecordedAudios.setAdapter(audiosAdapter);
        attachementLocalData.setMimeType(RecordAudioListener.EXTENSTION);
        audioLayout.setVisibility(GONE);
    }

    @Override
    public void onStartRecording() {
        audioLayout.setVisibility(View.VISIBLE);
        recordChronometer.setBase(SystemClock.elapsedRealtime());
        rvRecordedAudios.setVisibility(GONE);
        recordChronometer.start();
    }

    @Override
    public void onError(Exception e) {
        audioLayout.setVisibility(GONE);
    }

    public void requestPermission() {
        startActivityForResult(SystemPermissionActivity.createIntent(getContext(),
                SystemPermissionActivity.AUDIO), REQ_CODE_AUDIO);

    }

    @Override
    public void playMedia(final String recordedFilePath) {
        startActivity(MediaPlayerActivity.Companion.getIntent(getContext(), recordedFilePath));
    }
}
