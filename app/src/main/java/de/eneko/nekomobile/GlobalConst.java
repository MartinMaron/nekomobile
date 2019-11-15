package de.eneko.nekomobile;

public class GlobalConst {
    public static final String longDateTimeFormat = "dd.MM.yyyy:HH:mm";
    public static final String dateFormat = "dd.MM.yyyy";
    public static final String dayMonthDateFormat = "dd-MM";
    public static final String longDateTimeStamp = "yyyyMMddHHmmss";


    public static final String PATH_NEKOMOBILE = "//storage/emulated/0/nekomobile";
    public static final String PATH_NEKOMOBILE_PICTURES = "//storage/emulated/0/nekomobile/bilder";
    public static final String PATH_SONTEX = "//storage/emulated/0/Supercom";
    public static final String PATH_EXIM = "//storage/emulated/0/Exim";

    public static final String NEKOMOBILE_USER = "christof";
    // Service wlaczamy i wylaczamy w konstruktorze NekoDropBox

    public static final Integer DROPBOX_SERVICE_INTERVALL = 3600000;   // 1 Stunde
    public static final String DROPBOX_SYNC_WIFI_NAMES = "easybox;eneko";
}
