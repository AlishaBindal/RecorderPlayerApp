package com.example.recorderplayersampleapp.audiorecording;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Record audio listener
 */
public final class RecordAudioListener implements View.OnTouchListener {

    private boolean isPlaying = false;
    private boolean isPausing = false;
    private AudioListener mAudioListener;
    private AudioRecording mAudioRecording;
    private final WeakReference<Context> contextWeakReference;
    public static final String EXTENSTION = "-audio.mp3";

    public RecordAudioListener(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    public void setOnAudioListener(AudioListener audioListener) {
        this.mAudioListener = audioListener;
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isPlaying && !isPausing) {
                isPausing = true;
                stopRecord(true);
            }
            view.setOnTouchListener(null);
        } else {
            return false;
        }
        return true;
    }

    public void startRecord() {
        isPlaying = true;
        if (mAudioListener != null) {
            AudioListener audioListener = new AudioListener() {
                @Override
                public void onStop(RecordingItem recordingItem) {
                    mAudioListener.onStop(recordingItem);
                }

                @Override
                public void onStartRecording() {
                    mAudioListener.onStartRecording();
                }


                @Override
                public void onError(Exception e) {
                    mAudioListener.onError(e);
                }

                @Override
                public void requestPermission() {

                }
            };

            if (contextWeakReference.get() != null) {
                this.mAudioRecording =
                        new AudioRecording(contextWeakReference.get())
                                .setNameFile("/" + UUID.randomUUID() + EXTENSTION)
                                .start(audioListener);
            }
        }
    }

    private void stopRecord(final Boolean cancel) {
        if (mAudioListener != null) {
            final Handler handler = new Handler();
            handler.post(() -> {
                mAudioRecording.stop(cancel);
                isPlaying = false;
                isPausing = false;
            });
        }
    }

    public boolean isRecordingPossible() {
        return !isPlaying && !isPausing;
    }
}
