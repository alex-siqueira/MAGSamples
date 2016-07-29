package com.ca.api.samples.magsample;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ca.mas.core.MAGResultReceiver;
import com.ca.mas.core.MobileSso;
import com.ca.mas.core.MobileSsoFactory;
import com.ca.mas.core.error.MAGError;
import com.ca.mas.core.http.MAGRequest;
import com.ca.mas.core.http.MAGResponse;
import com.ca.mas.core.http.MAGResponseBody;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends Activity {
    private Button btnLogoff;
    private TextView result;
    private MobileSso sso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (sso == null) {
            sso = MobileSsoFactory.getInstance(this);
        }

        btnLogoff = (Button) findViewById(R.id.btnLogoff);
        result = (TextView) findViewById(R.id.result);
    }

    public void invoke(View view) {
        result.setText("");

        MAGRequest.MAGRequestBuilder requestBuilder = new MAGRequest.MAGRequestBuilder(sso.getURI(sso.getPrefix() + "/magsample"));

        MAGRequest request = requestBuilder
                .responseBody(MAGResponseBody.jsonBody())
                .build();
        sso.processRequest(request, new MAGResultReceiver<JSONObject>(new Handler()) {

            @Override
            public void onSuccess(final MAGResponse<JSONObject> response) {
                Snackbar success = Snackbar.make(findViewById(R.id.layout), response.getResponseMessage(), Snackbar.LENGTH_SHORT);
                result.setText(response.getBody().getContent().toString());
                success.show();
            }

            @Override
            public void onError(MAGError error) {
                Snackbar err = Snackbar.make(findViewById(R.id.layout), error.getCause().getMessage(), Snackbar.LENGTH_SHORT);
                err.show();
            }

            @Override
            public void onRequestCancelled() {
                Snackbar cancel = Snackbar.make(findViewById(R.id.layout), "Cancelled", Snackbar.LENGTH_SHORT);
                cancel.show();
            }
        });

        if (sso.isAppLogon()) {
            btnLogoff.setEnabled(true);
        }
    }

    public void logoff(View view) {
        sso.logout(false);
        btnLogoff.setEnabled(false);
    }
}
