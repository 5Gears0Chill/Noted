package com.fivegearszerochill.noted.editor.api;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @Multipart
    @POST("upload")
    Call<FileUploadResponse> uploadFile(@Part MultipartBody.Part file);
}
