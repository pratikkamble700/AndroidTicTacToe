package com.pratik.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
;
    EditText edt_firstPlayerName,edt_secoundPlayerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_firstPlayerName = (EditText) findViewById(R.id.edt_firstPlayerName);
        edt_secoundPlayerName = (EditText) findViewById(R.id.edt_secoundPlayerName);
    }

    public void onProceedToPlay(View view) {
        if(TextUtils.isEmpty(edt_firstPlayerName.getText())){
            edt_firstPlayerName.setError("Enter First Player Name");
        }else if(TextUtils.isEmpty(edt_secoundPlayerName.getText())){
            edt_secoundPlayerName.setError("Enter First Player Name");
        }else{
            MainActivity.firstPlayerName = edt_firstPlayerName.getText().toString();
            MainActivity.secoundPlayerName = edt_secoundPlayerName.getText().toString();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }
}
