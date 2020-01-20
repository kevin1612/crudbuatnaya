package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configurl;
import session.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private Button btnlinkregister;
    private RequestQueue mRequestQueue;
    private EditText edtusername,edtpasswor;
    private Button btnlogin;
    private CheckBox show;
    private ProgressDialog pDialog;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        session=new SessionManager(this);
        pDialog=new ProgressDialog(this);
        pDialog.setCancelable(false);
        mRequestQueue = Volley.newRequestQueue(this);
        show=(CheckBox)findViewById(R.id.show);
        session=new SessionManager(this);
        edtpasswor=(EditText)findViewById(R.id.edtpassword);
        edtusername=(EditText)findViewById(R.id.edtusername);
        btnlogin=(Button)findViewById(R.id.btnlogin);


        if(session.isLoggedIn()){
            Intent a=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(a);finish();
        }

        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtpasswor.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edtpasswor.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnlinkregister=(Button)findViewById(R.id.btnlinkregis);
        btnlinkregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(LoginActivity.this,DaftarActivity.class);
                startActivity(b);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername=edtusername.getText().toString();
                String strpassword=edtpasswor.getText().toString();
                if(strusername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Silahkan isi npm anda",
                            Toast.LENGTH_LONG).show();
                }else
                if(strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Silahkan isi password Anda",
                            Toast.LENGTH_LONG).show();
                }
                else{

                    login(strusername,strpassword);
                }

            }
        });
    }

    private  void login(String un,String pass){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", un);
        params.put("password", pass);

        pDialog.setMessage("Please Wait");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(configurl.logincustomer, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status=response.getBoolean("error");
                            String msg;
                            if (status==true){
                                msg=response.getString("pesan");
                                edtusername.setText(null);
                                edtpasswor.setText(null);
                                edtusername.requestFocus();
                                Toast.makeText(getApplicationContext(),msg,
                                        Toast.LENGTH_LONG).show();

                            }
                            else{
                                session.setLogin(true);
                                msg=response.getString("pesan");
                                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                                Intent a=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(a);
                                finish();

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
