package com.ca.api.samples.magsample;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ca.mas.core.auth.AuthenticationException;
import com.ca.mas.foundation.MASCallback;
import com.ca.mas.foundation.MASUser;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        final TextView txtUser = (TextView) findViewById(R.id.txtUser);
        final TextView txtPassword = (TextView) findViewById(R.id.txtPassword);

        String username = txtUser.getText().toString();
        String password = txtPassword.getText().toString();

        Log.d("LoginActivity", FirebaseInstanceId.getInstance().getToken());

        MASUser.login(username, password.toCharArray(), new MASCallback<MASUser>() {
            public Handler getHandler() {
                return new Handler(Looper.getMainLooper());
            }

            public void onSuccess(MASUser masUser) {
                Toast.makeText(getApplicationContext(), "Logged as: " + masUser.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();

                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);

                finish();
            }

            public void onError(Throwable t) {
                if (t.getCause() instanceof AuthenticationException){
                    Toast.makeText(getApplicationContext(), "Login / Senha Inválidos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "LoginFailed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
