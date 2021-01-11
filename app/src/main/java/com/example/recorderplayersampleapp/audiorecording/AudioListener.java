package com.example.recorderplayersampleapp.audiorecording;

/**
 * The interface Audio listener.
 *
 * @author netodevel
 */
public interface AudioListener {

    /**
     * On stop.
     *
     * @param recordingItem the recording item
     */
    void onStop(RecordingItem recordingItem);

    /**
     * On start.
     */
    void onStartRecording();

    /**
     * On error.
     *
     * @param e the e
     */
    void onError(Exception e);

    /**
     * Request permission.
     */
    void requestPermission();
}
