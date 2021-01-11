package com.example.recorderplayersampleapp.data.db;

/**
 * DB table
 */
public final class Db {

    /**
     * Prevent instantiation
     */
    private Db() {
    }


    /**
     * The type Table.
     */
    public static final class Table {

        /**
         * The constant DEVICE_TOKEN.
         */
        public static final String DEVICE_TOKEN = "paper_device_token";
        /**
         * The constant ACCESS_TOKEN.
         */
        public static final String ACCESS_TOKEN = "paper_access_token";
        /**
         * The constant DIALOG_VISIBILITY
         */
        public static final String DIALOG_VISIBILITY = "DIALOG_VISIBILITY";
        /**
         * RECORDED_AUDIOS_LIST
         */
        public static final String RECORDED_AUDIOS_LIST = "RECORDED_AUDIOS_LIST";

        /**
         * Prevent instantiation
         */
        private Table() {
        }
    }
}
