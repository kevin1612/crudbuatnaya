package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

import server.configurl;

public class UbahpesanActivity extends AppCompatActivity {
    EditText kodemotorq,usernameq;
    TextView jbq;
    Button btneditpesan,bek;

    private RequestQueue mRequestQueue;

    private ProgressDialog pDialog;

    Intent intent;
    String detailorupdate, _id, kodem, usern, jumlahb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahpesan);
        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        kodemotorq=(EditText) findViewById(R.id.edtkodemotor);
        usernameq=(EditText) findViewById(R.id.edtusername);
        jbq=(TextView)findViewById(R.id.edtjb);
        btneditpesan = (Button) findViewById(R.id.btneditskuy);
        bek = (Button) findViewById(R.id.btnkembalidunk);
        jbq.setEnabled(false);
        intent = getIntent();
        detailorupdate = intent.getStringExtra("detailorupdate");
        _id         = intent.getStringExtra("_id");
        kodem      = intent.getStringExtra("kodemotor");
        usern   = intent.getStringExtra("username");
        jumlahb      = intent.getStringExtra("jumlahbeli");

        kodemotorq.setText(kodem);
        usernameq.setText(usern);
        jbq.setText(jumlahb);


        if(detailorupdate.equals("detail")){
            kodemotorq.setEnabled(false);
            usernameq.setEnabled(false);
            jbq.setEnabled(false);
            btneditpesan.setVisibility(View.GONE);
        }
        btneditpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strkm = kodemotorq.getText().toString();
                String strun = usernameq.getText().toString();
                String strjb = jbq.getText().toString();
                if (strkm.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kode Motor Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strun.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strjb.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Jumlah Beli Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
                ubahMatakuliah(strkm, strun, strjb);
            }
        });

        bek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(UbahpesanActivity.this,CaripesanActivity.class);
                startActivity(d);
                finish();
            }
        });

    }
    private void ubahMatakuliah(String kodem, String username, String jb){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kodemotor", kodem);
        params.put("username", username);
        params.put("jumlahbeli", jb);

        pDialog.setMessage("Mohon tunggu");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, configurl.updatepesan + _id, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if(status == true){
                                msg = response.getString("pesan");
                            }else {
                                msg = response.getString("pesan");
                                Intent i = new Intent(UbahpesanActivity.this,
                                        CaripesanActivity.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg,
                                    Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        // add the request object to the queue to be executed
        // ApplicationController.getInstance().addToRequestQueue(req);
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
    public void onBackPressed() {
        Intent i = new Intent(UbahpesanActivity.this, CaripesanActivity.class);
        startActivity(i);
        finish();
    }
}

