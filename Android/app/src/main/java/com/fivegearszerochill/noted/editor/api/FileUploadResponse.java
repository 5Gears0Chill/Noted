package com.fivegearszerochill.noted.editor.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileUploadResponse {
    @Expose
    @SerializedName("error")
    public String error;
    @Expose
    @SerializedName("url")
    public String url;

    public FileUploadResponse(String error, String url) {
        this.error = error;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
