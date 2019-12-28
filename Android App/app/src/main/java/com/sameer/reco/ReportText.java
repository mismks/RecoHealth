package com.sameer.reco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReportText extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_text);

        Intent mIntent = getIntent();

        TextView DoctorID = findViewById(R.id.doctoridGet);
        TextView DoctorName = findViewById(R.id.doctornameGet);
        TextView ReportCreated = findViewById(R.id.reportcreatedGet);
        TextView SitePain = findViewById(R.id.sitepainGet);
        TextView PainSeverity = findViewById(R.id.painseverityGet);
        TextView PainCharacter = findViewById(R.id.paincharacterGet);
        TextView PainPattern = findViewById(R.id.painpatternGet);
        TextView SiteBlood = findViewById(R.id.sitebleedGet);
        TextView BleedSeverity = findViewById(R.id.bleedseverityGet);
        TextView Symptoms = findViewById(R.id.symptomsGet);
        TextView Diagnosis = findViewById(R.id.diagnosisGet);
        TextView Scans = findViewById(R.id.pScansGet);
        TextView Transfer = findViewById(R.id.deprtTransferGet);
        TextView Medication = findViewById(R.id.pMedicationGet);
        TextView RecordID = findViewById(R.id.RecordID);
        TextView BloodTest = findViewById(R.id.bloodtestGet);


        String str = mIntent.getStringExtra("response");
        int position = mIntent.getIntExtra("position", 0);

        final JsonElement json = new JsonParser().parse(str);
        final JsonObject jobject = json.getAsJsonObject();
        final JsonArray jsonArr = jobject.getAsJsonArray("result");

        System.out.println(str);


        for (int i = position; i < position + 1; i++) {
            JsonObject jsonObject = jsonArr.get(i).getAsJsonObject();

            String result = jsonObject.get("recordId").toString().replace("\"", "");
            RecordID.setText(result);

            result = "" + jsonObject.get("doctor").getAsJsonObject().get("doctorId");
            result = result.replace("\"","");
            DoctorID.setText(result);

            result = "" + jsonObject.get("doctor").getAsJsonObject().get("firstName");
            result +=" " + jsonObject.get("doctor").getAsJsonObject().get("lastName");
            result = result.replace("\"","");
            DoctorName.setText(result);


            result = jsonObject.get("encounterTime").toString().replace("\"", "");
            ReportCreated.setText(result);

            result = jsonObject.get("eventToIllness").toString().replace("\"", "");

            result = jsonObject.get("sitePain").toString().replace("\"", "");
            SitePain.setText(result);

            result = jsonObject.get("severityPain").toString().replace("\"", "");
            PainSeverity.setText(result);

            result = jsonObject.get("painCharacter").toString().replace("\"", "");
            PainCharacter.setText(result);

            result = jsonObject.get("patternPain").toString().replace("\"", "");
            PainPattern.setText(result);

            result = jsonObject.get("siteBleed").toString().replace("\"", "");
            SiteBlood.setText(result);

            result = jsonObject.get("severityBleed").toString().replace("\"", "");
            BleedSeverity.setText(result);

            result = jsonObject.get("symptoms").toString().replace("\"", "");
            Symptoms.setText(result);

            result = jsonObject.get("diagnosis").toString().replace("\"", "");
            Diagnosis.setText(result);

            result = jsonObject.get("bloodTest").toString().replace("\"", "");
            BloodTest.setText(result);

            result = jsonObject.get("patientScans").toString().replace("\"", "");
            Scans.setText(result);

            result = jsonObject.get("departmentTransfer").toString().replace("\"", "");
            Transfer.setText(result);

            result = jsonObject.get("patientMedication").toString().replace("\"", "");
            Medication.setText(result);




        }


    }
}
