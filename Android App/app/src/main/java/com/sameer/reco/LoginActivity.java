package com.sameer.reco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.emailinput);
        etPassword = findViewById(R.id.typepassword);

        findViewById(R.id.loginaccount).setOnClickListener(this);
        findViewById(R.id.signupp).setOnClickListener(this);
    }


    private void registerActivity() {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(registerIntent);


    }


    private void userLogin() {
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();


        String url = "http://134.209.144.24/api/";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("class", "Patient");
        params.put("action", "login");
        params.put("username", username);
        params.put("password", password);
        client.get(url, params, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String str = new String(responseBody);

                Splash.prefconfig.writeProfile(str);
                Splash.prefconfig.writeLoginStatus(true);

                JsonElement json = new JsonParser().parse(str);
                System.out.println(json.getAsJsonObject().get("result"));


                if (str.contains("org.reco.health.Patient")) {
                    Intent UserIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                    LoginActivity.this.startActivity(UserIntent);
                    Splash.prefconfig.writeName(json.getAsJsonObject().get("result").getAsJsonObject().get("firstName").getAsString());
                    Splash.prefconfig.writePatientID(json.getAsJsonObject().get("result").getAsJsonObject().get("patientId").getAsString());
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String str = new String(responseBody);
                System.out.println("Results from http" + str);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }


        });

        etUserName.setText("");
        etPassword.setText("");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginaccount:
                userLogin();
                break;

            case R.id.signupp:
                registerActivity();
                break;
        }
    }

}
