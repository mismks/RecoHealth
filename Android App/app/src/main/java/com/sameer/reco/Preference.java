package com.sameer.reco;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.JsonElement;

public class Preference {
    private SharedPreferences sharedPreferences;
    private Context context;

    public Preference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writePatientID(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_id), id);
        editor.commit();
    }

    public String readPatientID() {
        return sharedPreferences.getString(context.getString(R.string.pref_id), "0");
    }


    public void writeProfile(String name) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_profile), name);
        editor.commit();
    }

    public String readProfile() {
        return sharedPreferences.getString(context.getString(R.string.pref_profile), "There should be a profile here");
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();
    }

    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String n) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name), n);
        editor.commit();
    }

    public void writeAge(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_age), n);
        editor.commit();
    }
    public void writeDOB(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_dob), n);
        editor.commit();
    }
    public void writeEmail(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_email), n);
        editor.commit();
    }
    public void writeBlood(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_bloodGroup), n);
        editor.commit();
    }
    public void writeGender(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_gender), n);
        editor.commit();
    }
    public void writeAllergies(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_allergies), n);
        editor.commit();
    }
    public void writeChronic(String n)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_chronicDisease), n);
        editor.commit();
    }

    public String readName() {
        return sharedPreferences.getString(context.getString(R.string.pref_name), "User");
    }

    public String readAge() {
        return sharedPreferences.getString(context.getString(R.string.pref_age), "Age");
    }
    public String readDOB() {
        return sharedPreferences.getString(context.getString(R.string.pref_dob), "DOB");
    }
    public String readEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_email), "Email");
    }
    public String readBlood() {
        return sharedPreferences.getString(context.getString(R.string.pref_bloodGroup), "BloodGroup");
    }
    public String readGender() {
        return sharedPreferences.getString(context.getString(R.string.pref_gender), "Gender");
    }
    public String readAllergies() {
        return sharedPreferences.getString(context.getString(R.string.pref_allergies), "Allergies");
    }
    public String readChronic() {
        return sharedPreferences.getString(context.getString(R.string.pref_chronicDisease), "Chronic Disease");
    }

    public void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
