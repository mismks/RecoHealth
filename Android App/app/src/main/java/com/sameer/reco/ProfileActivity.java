package com.sameer.reco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.Year;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String str = Splash.prefconfig.readProfile();


        TextView Name = findViewById(R.id.patientname);
        TextView PatientID = findViewById(R.id.patientID);
        TextView Gender = findViewById(R.id.Gender);
        TextView Age = findViewById(R.id.PatientAge);
        TextView EmailAddress = findViewById(R.id.patientemail);
        TextView BloodGroup = findViewById(R.id.blodgroup);
        TextView DOB = findViewById(R.id.date);
        TextView Allergies = findViewById(R.id.alergies);
        TextView ChronicDisease = findViewById(R.id.chronicdesise);


        JsonElement json = new JsonParser().parse(str);
        JsonObject jobject = json.getAsJsonObject();


        String result = "" + jobject.get("result").getAsJsonObject().get("firstName");
        result = result.replace("\"", "");

        result += " " + jobject.get("result").getAsJsonObject().get("lastName");

        result = result.replace("\"", "");
        Name.setText(result);

        String patientID = Splash.prefconfig.readPatientID();
        PatientID.setText("Patient ID:" + patientID);

        result = "" + jobject.get("result").getAsJsonObject().get("gender");
        result = result.replace("\"", "");
        Gender.setText(result);

        result = "" + jobject.get("result").getAsJsonObject().get("dob");
        result = result.replace("\"", "");
        DOB.setText(result);

        result = result.substring(6);
        int year = Year.now().getValue();
        int age = Integer.parseInt(result);
        age = year - age;
        result = String.valueOf(age);
        Age.setText(result);

        result = result.replace("\"", "");
        Age.setText(result);

        result = "" + jobject.get("result").getAsJsonObject().get("emailAddress");
        result = result.replace("\"", "");
        EmailAddress.setText(result);

        result = "" + jobject.get("result").getAsJsonObject().get("bloodGroup");
        result = result.replace("\"", "");
        BloodGroup.setText(result);


        result = "" + jobject.get("result").getAsJsonObject().get("allergies");
        result = result.replace("\"", "");
        Allergies.setText(result);

        result = "" + jobject.get("result").getAsJsonObject().get("chronicDisease");
        result = result.replace("\"", "");
        ChronicDisease.setText(result);


        System.out.println(str);

    }
}
