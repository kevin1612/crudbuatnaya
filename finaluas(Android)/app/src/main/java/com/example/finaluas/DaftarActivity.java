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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import server.configurl;

public class DaftarActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private EditText edtusername,edtpassword,edtnama,edtlahir,edtalamat,edtnohp,edtemail;
    private Button btnlinklogin,btnregister,btncal;
    private ProgressDialog pDialog;
    private Spinner spinkel;
    private Calendar mycal;
    private DatePickerDialog.OnDateSetListener date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        getSupportActionBar().hide();
        pDialog=new ProgressDialog(this);
        pDialog.setCancelable(false);
        btnlinklogin=(Button)findViewById(R.id.btnlinklogin);
        btnregister=(Button)findViewById(R.id.btnregis);
        mRequestQueue = Volley.newRequestQueue(this);
        edtusername=(EditText)findViewById((R.id.edtusername));
        edtnama=(EditText)findViewById((R.id.edtnama));
        edtlahir=(EditText)findViewById((R.id.edtlahir));
        edtalamat=(EditText)findViewById((R.id.edtalamat));
        edtnohp=(EditText)findViewById((R.id.edtnohp));
        edtemail=(EditText)findViewById((R.id.edtemail));
        edtpassword=(EditText)findViewById((R.id.edtpassword));
        spinkel=(Spinner)findViewById(R.id.spinkel);
        btncal=(Button)findViewById(R.id.btnkalender);

        mycal=Calendar.getInstance();
        date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfyear, int dayOfMonth) {
                mycal.set(Calendar.YEAR,year);
                mycal.set(Calendar.MONTH,monthOfyear);
                mycal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DaftarActivity.this,date,mycal
                        .get(Calendar.YEAR),mycal.get(Calendar.MONTH),mycal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strun=edtusername.getText().toString();
                String strnama=edtnama.getText().toString();
                String strtglahir=edtlahir.getText().toString();
                String stralamat=edtalamat.getText().toString();
                String strjk=String.valueOf(spinkel.getSelectedItem().toString());
                String strnohp=edtnohp.getText().toString();
                String stremail=edtemail.getText().toString();
                String strpassword=edtpassword.getText().toString();
                if(strun.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(strnama.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nama Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(strtglahir.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tanggal Lahir Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(stralamat.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Alamat Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(strnohp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"No Hp Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(stremail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"E-Mail Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                if(strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Password tidak boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else
                {
                    inputdata(strun,strnama,strtglahir,strjk,stralamat,strnohp,stremail,strpassword);
                }

            }
        });





        btnlinklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DaftarActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private  void inputdata(String username,String nama,String tglahir,String jk,String alamat,
                            String nohp,String email,String pass){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("nama", nama);
        params.put("tglahir", tglahir);
        params.put("jeniskelamin", jk);
        params.put("alamatskrng", alamat);
        params.put("nohp", nohp);
        params.put("email", email);
        params.put("password", pass);

        pDialog.setMessage("Please Wait");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(configurl.inputcustomer, new JSONObject(params),
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
                                Intent a=new Intent(DaftarActivity.this,LoginActivity.class);
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
    private void updateLabel(){
        String myformat="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(myformat, Locale.US);
        edtlahir.setText(sdf.format(mycal.getTime()));
    }
}
