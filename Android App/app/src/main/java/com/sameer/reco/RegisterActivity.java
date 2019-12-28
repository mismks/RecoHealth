package com.sameer.reco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import java.util.ArrayList;
import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {

    private TextView mDisplayDate;
    private TextView AllergiesCheckbox;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String[] listItems;
    private boolean[] checkeditems;
    ArrayList<Integer> mUserItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etUserName = findViewById(R.id.etUsername);
        final EditText etPassword = findViewById(R.id.etPassword);
        final EditText etFirstName = findViewById(R.id.etFirstName);
        final EditText etLastName = findViewById(R.id.etLastName);
        final Spinner etGender = findViewById(R.id.genderSpinner);
        mDisplayDate = findViewById(R.id.etDate);
        final EditText etEmailAddress = findViewById(R.id.etEmailAddress);
        final Spinner etBloodGroup = findViewById(R.id.bloodSpinner);
        AllergiesCheckbox = findViewById(R.id.etAllergies);
        final EditText etChronicDisease = findViewById(R.id.etChronicDisease);
        final Button bRegister = findViewById(R.id.Register);

        listItems = getResources().getStringArray(R.array.Allergies);
        checkeditems = new boolean[listItems.length];

        AllergiesCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegisterActivity.this);
                mBuilder.setTitle("List of Allergies");
                mBuilder.setMultiChoiceItems(listItems, checkeditems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            } else {
                                mUserItems.remove(position);
                            }
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {

                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ",";
                            }
                        }
                        AllergiesCheckbox.setText(item);
                    }

                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkeditems.length; i++) {
                            checkeditems[i] = false;
                            mUserItems.clear();
                            AllergiesCheckbox.setText("Select Allergies from the list");
                        }
                    }
                });

                AlertDialog mdialog = mBuilder.create();
                mdialog.show();
            }
        });


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String Date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(Date);
                System.out.println(Date);
            }
        };


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUserName.getText().toString();
                final String password = etPassword.getText().toString();
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String gender = etGender.getSelectedItem().toString();
                final String emailAddress = etEmailAddress.getText().toString();
                final String blood = etBloodGroup.getSelectedItem().toString();
                final String dob = mDisplayDate.getText().toString();
                final String chronicDisease = etChronicDisease.getText().toString();
                final String allergies = AllergiesCheckbox.getText().toString();


                String url = "http://134.209.144.24/api/";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("class", "Patient");
                params.put("action", "Register");
                params.put("username", username);
                params.put("password", password);
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("gender", gender);
                params.put("age", "");
                params.put("emailAddress", emailAddress);
                params.put("bloodGroup", blood);
                params.put("dob", dob);
                params.put("allergies", allergies);
                params.put("chronicDisease", chronicDisease);
                client.get(url, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                        String str = new String(responseBody);
                        System.out.println("Results from http" + str);
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

                        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(LoginIntent);

                    }

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                        String str = new String(responseBody);
                        System.out.println("Results from http" + str);
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                    }


                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
            }
        });


    } //OnCreate

}//Main Activity
