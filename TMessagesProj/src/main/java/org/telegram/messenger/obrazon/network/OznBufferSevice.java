package org.telegram.messenger.obrazon.network;

import com.google.android.exoplayer2.util.Log;

import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

import static org.telegram.messenger.BuildVars.OZN_UPDATE_SERVER;

public class OznBufferSevice {

    public  static void send(final NativeByteBuffer buff, final TLObject message, String type){
        if (((TLRPC.Updates) message).message!=null){
            // ByteBuffer to byte array
            byte[] bytes = new byte[buff.buffer.remaining()];
            buff.buffer.get(bytes, 0, bytes.length);
            Log.d("mylog_request", "request: " + ((TLRPC.Updates) message).message);
            RxUtil.networkConsumer(WebService.service.sendData(FormDataUtils.createBodyFromBytes(bytes), type),
                    responseBody -> {
                        Log.d("mylog_request", "response: " + responseBody.string());
                    },
                    throwable -> {
                        Log.d("mylog_request", "throwable " + throwable.getMessage());
                    });
        }
    }

}
