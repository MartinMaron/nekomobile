package de.eneko.nekomobile;

public class GlobalConst {
    public static final String longDateTimeFormat = "dd.MM.yyyy:HH:mm";
    public static final String dateFormat = "dd.MM.yyyy";
    public static final String dayMonthDateFormat = "dd-MM";
    public static final String longDateTimeStamp = "yyyyMMddHHmmss";

    public static final Integer DAYS_TO_ARCHIVE = -7;

    public static final String PATH_NEKOMOBILE = "//storage/emulated/0/nekomobile";
    public static final String PATH_NEKOMOBILE_PICTURES = "//storage/emulated/0/nekomobile/bilder";
    public static final String PATH_SONTEX_OLD = "//storage/emulated/0/Supercom/roads";
    public static final String PATH_SONTEX = "//storage/emulated/0/Supercom";
    public static final String PATH_EXIM = "//storage/emulated/0/Exim";

    public static final String NEKOMOBILE_USER = "test";
    // Service wlaczamy i wylaczamy w konstruktorze NekoDropBox

    public static final Integer DROPBOX_SERVICE_INTERVALL = 3600000;   // 1 Stunde
    public static final String DROPBOX_SYNC_WIFI_NAMES = "Dom;dom";


    //Sontex
    public static final String SONTEX_SAVEDBY_SWNAME = "Tools Supercom Android App";
    public static final String SONTEX_SAVEDBY_SWVERSION = "3.0.4";
    public static final String SONTEX_ROAD_VERSION = "6.3";
    public static final String SONTEX_longDateTimeFormat = "yyyy-MM-dd HH:mm:ss";


}
