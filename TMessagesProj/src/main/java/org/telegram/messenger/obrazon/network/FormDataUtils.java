package org.telegram.messenger.obrazon.network;

import androidx.annotation.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FormDataUtils {
    @NonNull
    public static RequestBody createPartFromString(String message) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, message);
    }


    @NonNull
    public static RequestBody createBodyFromBytes(byte[] params) {
        return RequestBody.create(MediaType.parse("application/octet-stream"), params);
    }

}
