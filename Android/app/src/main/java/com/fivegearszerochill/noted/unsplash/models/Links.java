package com.fivegearszerochill.noted.unsplash.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("html")
    @Expose
    private String html;
    @SerializedName("download")
    @Expose
    private String download;
    @SerializedName("download_location")
    @Expose
    private String downloadLocation;

    public Links() {
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public void setDownloadLocation(String downloadLocation) {
        this.downloadLocation = downloadLocation;
    }

    public String getSelf() {
        return self;
    }

    public String getHtml() {
        return html;
    }

    public String getDownload() {
        return download;
    }

    public String getDownloadLocation() {
        return downloadLocation;
    }
}
