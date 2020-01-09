package org.telegram.messenger.obrazon.network;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.obrazon.network.model.Message;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {

    OkHttpClient client = HttpsUtil.getUnsafeOkHttpClient(new MyInterceptor());
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildVars.OZN_API).client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();
    WebService service = retrofit.create(WebService.class);

    /*
     *sendData
     * */
    @POST("/api/{type}")
    Observable<ResponseBody> sendData(@Body RequestBody bytes, @Path("type") String type);


    /*
     *sendDataMessage
     * */
    @POST("/api/{type}")
    Observable<ResponseBody> sendData(@Body Message message, @Path("type") String type);

    /*
     *sendDataString
     * */
    @POST("/api/{type}")
    Observable<ResponseBody> sendData(@Body String event, @Path("type") String type);



    class MyInterceptor implements Interceptor {
        @SuppressLint("DefaultLocale")
        @NotNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder().build();
            long t1 = System.nanoTime();
            System.out.println(String.format("Sending request %s on %n%s", request.url(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            System.out.println(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

}
