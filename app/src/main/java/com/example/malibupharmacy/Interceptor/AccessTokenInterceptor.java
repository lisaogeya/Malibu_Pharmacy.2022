package com.example.malibupharmacy.Interceptor;
import android.util.Base64;

import androidx.annotation.NonNull;
import com.example.malibupharmacy.BuildConfig;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {
    public AccessTokenInterceptor() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //this is hardcoded because it is a test
        String keys = "CudJddsixc1eOyGB4iDS7EfShaBUL7i9" + ":" + "t5MhQDTeuzGYce3L";

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();
        return chain.proceed(request);
    }
}
