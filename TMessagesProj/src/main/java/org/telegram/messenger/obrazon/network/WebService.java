package org.telegram.messenger.obrazon.network;

import android.util.Log;

import org.telegram.messenger.BuildVars;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface WebService {

    OkHttpClient client = HttpsUtil.getUnsafeOkHttpClient(new MyInterceptor());

    static final String BASE_URL_CRM_DEV = BuildVars.OZN_API;
    static final String HEADER_AUTH_DEV = "Bearer ";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL_CRM_DEV)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    WebService service = retrofit.create(WebService.class);


    /*
     *Login
     * */
    @POST("/api/{type}")
    Observable<ResponseBody> sendData(@Body RequestBody bytes,
                                      @Path("type") String type);


    class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .build();
            long t1 = System.nanoTime();
            System.out.println(
                    String.format("Sending request %s on %n%s", request.url(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            System.out.println(
                    String.format("Received response for %s in %.1fms%n%s", response.request().url(),
                            (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

}
