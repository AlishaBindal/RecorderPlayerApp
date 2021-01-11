package com.example.recorderplayersampleapp.audiorecording;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AttachmentLocalData
 */
public class AttachmentLocalData implements Parcelable {

    private String filePath = "";
    private String mimeType = "";
    private String caption;
    private long fileSize;
    private String type;
    private int width;
    private int height;

    /**
     * Instantiates a new Attachement picker data.
     */
    public AttachmentLocalData() {
    }


    /**
     * Instantiates a new Attachement picker data.
     *
     * @param in the in
     */
    protected AttachmentLocalData(Parcel in) {
        filePath = in.readString();
        mimeType = in.readString();
        caption = in.readString();
        fileSize = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(filePath);
        dest.writeString(mimeType);
        dest.writeString(caption);
        dest.writeLong(fileSize);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<AttachmentLocalData> CREATOR = new Creator<AttachmentLocalData>() {
        @Override
        public AttachmentLocalData createFromParcel(Parcel in) {
            return new AttachmentLocalData(in);
        }

        @Override
        public AttachmentLocalData[] newArray(int size) {
            return new AttachmentLocalData[size];
        }
    };

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath the file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets mime type.
     *
     * @return the mime type
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets mime type.
     *
     * @param caption the caption
     */
    public void setMimeType(String caption) {
        this.mimeType = caption;
    }


    /**
     * Gets file size.
     *
     * @return the file size
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Sets file size.
     *
     * @param fileSize the file size
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
