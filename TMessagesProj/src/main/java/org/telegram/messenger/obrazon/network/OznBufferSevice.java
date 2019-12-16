package org.telegram.messenger.obrazon.network;

import android.util.Log;
import org.telegram.messenger.obrazon.network.model.Message;
import org.telegram.tgnet.NativeByteBuffer;

public class OznBufferSevice {

    public static void sendOznBuffer(final NativeByteBuffer buff, String type) {
        byte[] bytes = new byte[]{};
        if (buff.buffer != null) {
            bytes = new byte[buff.buffer.remaining()];
            buff.buffer.get(bytes, 0, bytes.length);
        }
        RxUtil.networkConsumer(WebService.service.sendData(FormDataUtils.createBodyFromBytes(bytes), type),
                responseBody -> Log.d("mylog_request", "response: " + responseBody.string()),
                throwable -> Log.d("mylog_request", "throwable " + throwable.getMessage()));
    }

    public static void sendOznMessage(final Message message, String type) {
        RxUtil.networkConsumer(WebService.service.sendData(message, type),
                responseBody -> Log.d("mylog_request", "response: " + responseBody.string()),
                throwable -> Log.d("mylog_request", "throwable " + throwable.getMessage()));
    }

    public static void sendOznEvent(final String event, String type) {
        RxUtil.networkConsumer(WebService.service.sendData(event, type),
                responseBody -> Log.d("mylog_request", "response: " + responseBody.string()),
                throwable -> Log.d("mylog_request", "throwable " + throwable.getMessage()));
    }

}
