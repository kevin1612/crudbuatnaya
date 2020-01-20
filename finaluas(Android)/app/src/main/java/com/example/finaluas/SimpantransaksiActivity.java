package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import server.configurl;

public class SimpantransaksiActivity extends AppCompatActivity {
    private BottomNavigationItemView search,list,home,account;
    private RequestQueue mRequestQueue;
    private EditText edtkodemotorcoy,edttgltransaksi,edtusernamecoy;
    private TextView jb;
    private Button simpankuy,kembali;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpantransaksi);
        getSupportActionBar().hide();

        home=(BottomNavigationItemView) findViewById(R.id.btnhome);
        search=(BottomNavigationItemView) findViewById(R.id.btncari);
        account=(BottomNavigationItemView) findViewById(R.id.btnakun);
        getSupportActionBar().hide();
        pDialog=new ProgressDialog(this);
        pDialog.setCancelable(false);
        simpankuy=(Button)findViewById(R.id.btnsimpan);
        kembali=(Button)findViewById(R.id.btnmundur);
        mRequestQueue = Volley.newRequestQueue(this);
        edtusernamecoy=(EditText)findViewById((R.id.edtusername));
        edtkodemotorcoy=(EditText)findViewById((R.id.edtkodemotor));
        jb=(TextView) findViewById(R.id.edtjb);
        jb.setEnabled(false);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SimpantransaksiActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SimpantransaksiActivity.this,KatalogActivity.class);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SimpantransaksiActivity.this,AkunActivity.class);
                startActivity(i);
                finish();
            }
        });


        simpankuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strun=edtusernamecoy.getText().toString();
                String strjb=jb.getText().toString();
                String strkodemotorcoy=edtkodemotorcoy.getText().toString();
                if(strun.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(strjb.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Jumlah Beli Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(strkodemotorcoy.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Alamat Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                {
                    inputpesan(strkodemotorcoy,strun,strjb);
                }

            }
        });





        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SimpantransaksiActivity.this,ListActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private  void inputpesan(String kodemotor,String username,String jbl){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kodemotor", kodemotor);
        params.put("username", username);
        params.put("jumlahbeli", jbl);

        pDialog.setMessage("Please Wait");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(configurl.pesaninput, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {

                            boolean status=response.getBoolean("error");
                            String msg;
                            if (status==false){
                                msg=response.getString("pesan");
                                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                                Intent a=new Intent(SimpantransaksiActivity.this,ListActivity.class);
                                startActivity(a);
                                finish();
                            }
                            else{
                                msg=response.getString("pesan");
                                Toast.makeText(getApplicationContext(),msg,
                                        Toast.LENGTH_LONG).show();
                            }
                            VolleyLog.v("Response:%n %s", response.toString(8));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        mRequestQueue.add(req);
    }

    private void showDialog(){
        if(!pDialog.isShowing())
            pDialog.show();
    }


    private  void hideDialog(){
        if(pDialog.isShowing())
            pDialog.dismiss();
    }
}


