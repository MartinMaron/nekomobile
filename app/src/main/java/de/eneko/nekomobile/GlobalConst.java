package de.eneko.nekomobile;

public class GlobalConst {
    public static final String longDateTimeFormat = "dd.MM.yyyy:HH:mm";
    public static final String dateFormat = "dd.MM.yyyy";
    public static final String dayMonthDateFormat = "dd-MM";
    public static final String longDateTimeStamp = "yyyyMMddHHmmss";

    public static final Integer DAYS_TO_ARCHIVE = -70;

    public static final String PATH_NEKOMOBILE = "//storage/emulated/0/nekomobile";
    public static final String PATH_NEKOMOBILE_PICTURES = "//storage/emulated/0/nekomobile/bilder";
    public static final String PATH_SONTEX = "//storage/emulated/0/Supercom/roads";
    public static final String PATH_QUNDIS = "//storage/emulated/0/qundis";
    public static final String PATH_SONTEX_OLD = "//storage/emulated/0/Supercom/roads";
    public static final String PATH_EXIM = "//storage/emulated/0/Exim";

    public static final String NEKOMOBILE_USER = "michal";
    // Service wlaczamy i wylaczamy w konstruktorze NekoDropBox

    public static final Integer DROPBOX_SERVICE_INTERVALL = 3600000;   // 1 Stunde
    public static final String DROPBOX_SYNC_WIFI_NAMES = "Dom;dom";
    
    //Sontex
    public static final String SONTEX_SAVEDBY_SWNAME = "Tools Supercom Android App";
    public static final String SONTEX_SAVEDBY_SWVERSION = "3.0.4";
    public static final String SONTEX_ROAD_VERSION = "6.3";
    public static final String SONTEX_longDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String SONTEX_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZjJkMmE0Zi0yZGJmLTQwYjMtODljZi05MWRlYzI3ZTViZDkifQ.eyJpYXQiOjE2Njk5Njc4ODMsImp0aSI6IjVmY2M1YjdiLTA5NWEtNGRlMy1hNWI2LTgwOTlmMjIxZDU4MCIsImlzcyI6Imh0dHBzOi8vc3NvLmV4Y2hhbmdlLXBsYXRmb3JtLmFwcC9hdXRoL3JlYWxtcy9zb25leGEiLCJhdWQiOiJodHRwczovL3Nzby5leGNoYW5nZS1wbGF0Zm9ybS5hcHAvYXV0aC9yZWFsbXMvc29uZXhhIiwic3ViIjoiOTJmMzM3MTQtNzEyMS00OTZjLWEwNTMtZjE5NzhiZGJjY2ZiIiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im5naW54LWFwaSIsInNlc3Npb25fc3RhdGUiOiIxNDczMzRkNy1hMzU3LTRmZjctODhkOC1mM2UxNGU1MGViYTIiLCJzY29wZSI6ImVtYWlsIHByb2ZpbGUgb2ZmbGluZV9hY2Nlc3MiLCJzaWQiOiIxNDczMzRkNy1hMzU3LTRmZjctODhkOC1mM2UxNGU1MGViYTIifQ.Uoe_GI38sAucGSpMCt0H3g-x5dNPFeDKcmAZ6HSTIGU";

}
