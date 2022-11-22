package com.example.malibupharmacy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.example.malibupharmacy.Services.DarajaApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.example.malibupharmacy.Model.AccessToken;
import com.example.malibupharmacy.Model.StkPush;

//utils
import com.example.malibupharmacy.Utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.example.malibupharmacy.AppConstants.Constants.BUSINESS_SHORT_CODE;
import static com.example.malibupharmacy.AppConstants.Constants.CALLBACKURL;
import static com.example.malibupharmacy.AppConstants.Constants.PARTYB;
import static com.example.malibupharmacy.AppConstants.Constants.PASSKEY;
import static com.example.malibupharmacy.AppConstants.Constants.TRANSACTION_TYPE;

public class DeliveryDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;
    Button mPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        DBHelper dbHelper = new DBHelper(this);

        TextInputEditText location = (TextInputEditText) findViewById(R.id.textLocationEt);
        TextInputEditText street = (TextInputEditText) findViewById(R.id.textStreetEt);
        TextInputEditText additionalInfo = (TextInputEditText) findViewById(R.id.textDeliveryEt);
        Button saveButton = (Button) findViewById(R.id.btn_save);
        mPay = (Button) findViewById(R.id.btn_pay) ;
        mProgressDialog = new ProgressDialog(this);
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(false); //Set True to enable logging, false to disable.

        mPay.setOnClickListener(this);

        getAccessToken();
        saveButton.setOnClickListener(v -> {
            if (location.getText().toString().equals("") || street.getText().toString().equals("") || additionalInfo.getText().toString().equals("")) {
                Toast.makeText(DeliveryDetailsActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean saved = dbHelper.saveDeliveryInfo(location.getText().toString(), street.getText().toString(), additionalInfo.getText().toString());

            if (saved) {
                Toast.makeText(DeliveryDetailsActivity.this, "Delivery details saved", Toast.LENGTH_SHORT).show();
                DeliveryDetailsActivity.this.onBackPressed();
            } else {
                Toast.makeText(DeliveryDetailsActivity.this, "Delivery details could not be saved", Toast.LENGTH_SHORT).show();
            }
        });
        /*payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryDetailsActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });*/
    }
    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }

//    @Override
    public void onClick(View view) {
        if (view== mPay){
//            String phone_number = mPhone.getText().toString();
//            String amount = mAmount.getText().toString();
            performSTKPush("0112383274","1");
        }
    }


    public void performSTKPush(String phone_number,String amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        StkPush stkPush = new StkPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(amount),
                Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "MPESA Android Test", //Account reference
                "Testing"  //Transaction description
        );

        mApiClient.setGetAccessToken(false);

        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<StkPush>() {
            @Override
            public void onResponse(@NonNull Call<StkPush> call, @NonNull Response<StkPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d("post submitted to API. %s", response.body());
                    } else {
                        Timber.e("Response %s", response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StkPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}