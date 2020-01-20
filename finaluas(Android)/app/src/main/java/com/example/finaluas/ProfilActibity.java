package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;

import model.cusModel;
import server.configurl;

public class ProfilActibity extends AppCompatActivity {
    private BottomNavigationItemView home, search, list, account;
    private EditText us, password;
    private TextView txtdatas;
    private RequestQueue mRequestQueue;
    private Button lihat,kembali;
    private ProgressDialog pDialog;
    ListView listku;

    ArrayList<cusModel> newsList = new ArrayList<cusModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_actibity);
        getSupportActionBar().hide();
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        mRequestQueue = Volley.newRequestQueue(this);
        home = (BottomNavigationItemView) findViewById(R.id.btnhome);
        search = (BottomNavigationItemView) findViewById(R.id.btncari);
        list = (BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account = (BottomNavigationItemView) findViewById(R.id.btnakun);
        lihat = (Button) findViewById(R.id.btnlihat);
        us = (EditText) findViewById(R.id.u);
        txtdatas=(TextView)findViewById(R.id.txtdata);
        password=(EditText)findViewById(R.id.pw);
        kembali=(Button)findViewById(R.id.btnkembali);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilActibity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilActibity.this, ListActivity.class);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilActibity.this, AkunActivity.class);
                startActivity(i);
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilActibity.this, KatalogActivity.class);
                startActivity(i);
                finish();
            }
        });

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strun=us.getText().toString();
                String paw=password.getText().toString();
                if(strun.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Silahkan isi Username anda dulu",
                            Toast.LENGTH_LONG).show();
                }else
                if(paw.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Silahkan isi Username anda dulu",
                            Toast.LENGTH_LONG).show();
                }else{
                    logindulu(strun,paw);}
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ProfilActibity.this,AkunActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private  void logindulu(String un,String pass){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", un);
        params.put("password", pass);

        pDialog.setMessage("Please Wait");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(configurl.logindulu, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status=response.getBoolean("error");
                            String msg;
                            if (status==true){
                                msg=response.getString("pesan");
                                Toast.makeText(getApplicationContext(),msg,
                                        Toast.LENGTH_LONG).show();
                            }
                            else{
                                String day;
                                msg=response.getString("pesan");
                                day=response.getString("data");
                                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                                txtdatas.setText(day);
                            }
                            VolleyLog.v("Response:%n %s", response.toString(2));
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