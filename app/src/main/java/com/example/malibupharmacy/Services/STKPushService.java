package com.example.malibupharmacy.Services;
import com.example.malibupharmacy.Model.AccessToken;
import com.example.malibupharmacy.Model.StkPush;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface STKPushService {
    @POST("mpesa/stkpush/v1/processrequest")
    public Call<StkPush> sendPush(@Body StkPush stkPush);

    @GET("oauth/v1/generate?grant_type=client_credentials")
    public Call<AccessToken> getAccessToken();
}
