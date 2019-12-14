package org.telegram.messenger.obrazon.network;

import androidx.annotation.NonNull;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FormDataUtils {
    @NonNull
    public static RequestBody createBodyFromBytes(byte[] params) {
        return RequestBody.create(MediaType.parse("application/octet-stream"), params);
    }
}
