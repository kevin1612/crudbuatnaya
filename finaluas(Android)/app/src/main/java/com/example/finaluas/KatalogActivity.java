package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.AdapterMotor;
import model.MotorModel;
import server.configurl;

public class KatalogActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    private BottomNavigationItemView home,search,list,account;
    AdapterMotor adapter;
    ListView listku;
    Intent intent;
    String detailorupdate,usernamen;
    ArrayList<MotorModel> newsList = new ArrayList<MotorModel>();

    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        home=(BottomNavigationItemView) findViewById(R.id.btnhome);
        list=(BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account=(BottomNavigationItemView) findViewById(R.id.btnakun);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(KatalogActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(KatalogActivity.this,ListActivity.class);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(KatalogActivity.this,AkunActivity.class);
                startActivity(i);
                finish();
            }
        });

        listku = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterMotor(KatalogActivity.this, newsList, mRequestQueue, pDialog);
        listku.setAdapter(adapter);
        getAllData();

    }

    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, configurl.getmotor, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    MotorModel matkul = new MotorModel();
                                    matkul.setId(jsonObject.getString("_id"));
                                    matkul.setKodemotor(jsonObject.getString("kodemotor"));
                                    matkul.setNamamotor(jsonObject.getString("namamotor"));
                                    matkul.setJenismotor(jsonObject.getString("jenismotor"));
                                    matkul.setWarna(jsonObject.getString("warna"));
                                    matkul.setStok(jsonObject.getString("stok"));
                                    matkul.setHarga(jsonObject.getString("harga"));

                                    newsList.add(matkul);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onBackPressed(){
        Intent i = new Intent(KatalogActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

