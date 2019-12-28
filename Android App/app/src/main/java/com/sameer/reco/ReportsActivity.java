package com.sameer.reco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ReportsActivity extends AppCompatActivity {

   public ArrayList<String> listItems = new ArrayList<String>();
    public ArrayList<String> records = new ArrayList<String>();

    public String recordID;
    public String patientID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        patientID = Splash.prefconfig.readPatientID();
        patientID.replace("\"","");

        final ListView listview = (findViewById(R.id.listView));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        String id = Splash.prefconfig.readPatientID();

        final String url = "http://134.209.144.24/api/";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("class", "Patient");
        params.put("action", "getRecords");
        params.put("patientId", id);
        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                final String str = new String(responseBody);

                final JsonElement json = new JsonParser().parse(str);
                final JsonObject jobject = json.getAsJsonObject();
                final JsonArray jsonArr = jobject.getAsJsonArray("result");

                System.out.println(str);

                if(str.contains("org.reco.health.MedicalRecord"))
                {
                    for (int i = 0; i < jsonArr.size(); i++) {
                        JsonObject jsonObject = jsonArr.get(i).getAsJsonObject();

                        listItems.add("Record #"+ jsonObject.get("recordId").toString().replace("\"",""));
                        records.add( jsonObject.get("recordId").toString().replace("\"",""));
                        adapter.notifyDataSetChanged();

                        listview.setAdapter(adapter);
                    }
                }



                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ReportsActivity.this, ReportText.class);
                        intent.putExtra("response", str);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });

                listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                        Log.v("Long Clicked","pos: "+position);
                      final Dialog d = new Dialog(ReportsActivity.this);
                        d.setContentView(R.layout.dialog_transaction);
                        d.show();

                        final EditText Password = d.findViewById(R.id.Password);
                        final EditText enterDoctorID = d.findViewById(R.id.input);
                        TextView mActionOK = d.findViewById(R.id.action_ok);
                        TextView mActionCancel = d.findViewById(R.id.action_cancel);

                        mActionCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                d.dismiss();
                            }
                        });

                        mActionOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                final String password = Password.getText().toString();
                                final String DoctorId = enterDoctorID.getText().toString();
                                recordID = records.get(position);

                                System.out.println("password is "+ password);
                                System.out.println("Doctor ID is "+ DoctorId);
                                System.out.println("RecordID is "+ recordID);

                                final String url = "http://134.209.144.24/api/";
                                AsyncHttpClient client1 = new AsyncHttpClient();
                                RequestParams params1 = new RequestParams();
                                params1.put("class", "Patient");
                                params1.put("action", "shareRecord");
                                params1.put("patientId", patientID);
                                params1.put("password", password);
                                params1.put("doctorId",DoctorId);
                                params1.put("medicalRecordId", recordID);

                                client1.get(url, params1, new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                        final String str = new String(responseBody);

                                        System.out.println(str);

                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                        final String str = new String(responseBody);

                                        System.out.println(str);
                                    }
                                });

                                d.dismiss();
                            }
                        });

                        return true;
                    }
                });


            }// on success


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String str = new String(responseBody);
                System.out.println("Results from http" + str);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        });




    }//oncreate


}
