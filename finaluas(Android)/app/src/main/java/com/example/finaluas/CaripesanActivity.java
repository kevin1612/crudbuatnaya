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
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.AdapterPesan;
import model.PesanModel;
import server.configurl;
import session.SessionManager;

public class CaripesanActivity extends AppCompatActivity {
    private BottomNavigationItemView us,home, search, list, account;
    private CircularRevealCardView cardinputin,cardfind;
    private SessionManager session;
    ProgressDialog pDialog;

    AdapterPesan adapter;
    ListView listk;

    ArrayList<PesanModel> newsList = new ArrayList<PesanModel>();

    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caripesan);
        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        us = (BottomNavigationItemView) findViewById(R.id.btnhome);
        search = (BottomNavigationItemView) findViewById(R.id.btncari);
        list = (BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account = (BottomNavigationItemView) findViewById(R.id.btnakun);
        listk = (ListView) findViewById(R.id.array_list1);
        newsList.clear();
        adapter = new AdapterPesan(CaripesanActivity.this, newsList, mRequestQueue, pDialog);
        listk.setAdapter(adapter);
        getAllData();
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CaripesanActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CaripesanActivity.this, KatalogActivity.class);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CaripesanActivity.this, AkunActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, configurl.ambiilpesan, null,
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
                                    PesanModel matkul = new PesanModel();
                                    matkul.setId(jsonObject.getString("_id"));
                                    matkul.setKodemotor(jsonObject.getString("kodemotor"));
                                    matkul.setUsername(jsonObject.getString("username"));
                                    matkul.setJumlahbeli(jsonObject.getString("jumlahbeli"));
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
        Intent i = new Intent(CaripesanActivity.this, ListActivity.class);
        startActivity(i);
        finish();
    }
}
