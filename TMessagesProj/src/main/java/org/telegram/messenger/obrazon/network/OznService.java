package org.telegram.messenger.obrazon.network;

import android.util.Log;

import org.telegram.tgnet.NativeByteBuffer;

public class OznService {

    public static void sendBuffer(String type, final NativeByteBuffer buff) {
        if (buff.buffer != null) {
            // ByteBuffer to byte array
            byte[] bytes = new byte[buff.buffer.remaining()];
            buff.buffer.get(bytes, 0, bytes.length);
            RxUtil.networkConsumer(WebService.service.sendData(FormDataUtils.createBodyFromBytes(bytes), type),
                    responseBody -> Log.d("mylog_request", "response: " + responseBody.string()),
                    throwable -> Log.d("mylog_request", "throwable " + throwable.getMessage()));
        }
    }
}
