package com.ca.api.samples.magsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ca.mas.core.MobileSso;
import com.ca.mas.core.gui.AbstractLogonActivity;

/**
 * Created by siqal01 on 7/11/2016.
 */
public class LoginActivity extends AbstractLogonActivity{
    private Button btnLogin;
    private TextView txtUsuario;
    private TextView txtSenha;
    private MobileSso sso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtSenha = (TextView) findViewById(R.id.txtPassword);
    }

    public void login(View v) {
        sendCredentialsIntent(txtUsuario.getText().toString(), txtSenha.getText().toString());
        setResult(RESULT_OK);
        finish();
    }
}
