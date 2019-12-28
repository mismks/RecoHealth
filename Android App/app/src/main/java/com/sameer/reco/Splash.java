package com.sameer.reco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    Handler handler;
    Runnable r;
    public static Preference prefconfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefconfig = new Preference(this);
        prefconfig.writeLoginStatus(false);

        handler = new Handler();
    }
    public void openWindow() {
        r = new Runnable() {
            public void run() {


                if (prefconfig.readLoginStatus()) {
                    Intent WelcomeIntent = new Intent(Splash.this, UserAreaActivity.class);
                    Splash.this.startActivity(WelcomeIntent);

                } else {
                    Intent LoginIntent = new Intent(Splash.this, LoginActivity.class);
                    Splash.this.startActivity(LoginIntent);
                }
                Splash.this.finish();
            }
        };
        handler.postDelayed(r, 3000);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        openWindow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }
}
