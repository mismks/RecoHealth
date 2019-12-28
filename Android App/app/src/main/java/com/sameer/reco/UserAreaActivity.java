package com.sameer.reco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        textview = findViewById(R.id.txt_user_name);
        textview.setText("Welcome " + Splash.prefconfig.readName());
        findViewById(R.id.ProfileActivity).setOnClickListener(this);
        findViewById(R.id.ReportsActivity).setOnClickListener(this);
        findViewById(R.id.Logout).setOnClickListener(this);
        Splash.prefconfig.writeLoginStatus(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ProfileActivity:
                Intent ProfileIntent = new Intent(UserAreaActivity.this, ProfileActivity.class);
                UserAreaActivity.this.startActivity(ProfileIntent);
                break;

            case R.id.ReportsActivity:
                Intent ReportsIntent = new Intent(UserAreaActivity.this, ReportsActivity.class);
                UserAreaActivity.this.startActivity(ReportsIntent);
                break;

            case R.id.Logout:
                Intent LogoutIntent = new Intent(UserAreaActivity.this, LoginActivity.class);
                UserAreaActivity.this.startActivity(LogoutIntent);
                Splash.prefconfig.writeLoginStatus(false);
                break;
        }
    }
}
