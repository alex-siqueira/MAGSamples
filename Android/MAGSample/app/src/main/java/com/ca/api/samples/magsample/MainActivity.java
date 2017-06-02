package com.ca.api.samples.magsample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ca.mas.core.http.MAGResponseBody;
import com.ca.mas.foundation.MAS;
import com.ca.mas.foundation.MASAuthenticationListener;
import com.ca.mas.foundation.MASCallback;
import com.ca.mas.foundation.MASOtpAuthenticationHandler;
import com.ca.mas.foundation.MASRequest;
import com.ca.mas.foundation.MASResponse;
import com.ca.mas.foundation.MASUser;
import com.ca.mas.foundation.auth.MASAuthenticationProviders;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        startMAS();

        if (MASUser.getCurrentUser() == null){
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);

            finish();
        }
    }

    private void startMAS() {
        MAS.start(this);
        MAS.debug();

        MAS.setAuthenticationListener(new MASAuthenticationListener() {
            @Override
            public void onAuthenticateRequest(Context context, long requestId, MASAuthenticationProviders providers) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity (intent);
                finish();
            }

            @Override
            public void onOtpAuthenticateRequest(Context context, MASOtpAuthenticationHandler handler) {
                Log.d("MAS", "OTP Login flow");
            }
        });
    }

    public void invoke(View view){
        MASRequest.MASRequestBuilder reqBuilder = new MASRequest.MASRequestBuilder(new Uri.Builder().encodedPath("/gpm/servicos").build());

        MASRequest req = reqBuilder.responseBody(MAGResponseBody.jsonBody()).get().build();
        MAS.invoke(req, new MASCallback<MASResponse<JSONObject>>() {
            @Override
            public void onSuccess(MASResponse<JSONObject> result) {
                textView.setText(result.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MAS", "Error");
            }
        });
    }

    public void logout(View view){
        if (MASUser.getCurrentUser() != null){
            MASUser.getCurrentUser().logout(new MASCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    Log.d("MAS", "Logged off");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("MAS", "Error! " + e.getLocalizedMessage());
                }
            });
        }
    }
}
