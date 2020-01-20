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
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configurl;

public class LihatKatalog extends AppCompatActivity {
    TextView kode,namamotor,jenismotor, warna, stok, harga;
    Button btnmasukkeranjang,btnkembali;
    private BottomNavigationItemView home, search, list, account;
    private RequestQueue mRequestQueue;

    private ProgressDialog pDialog;

    Intent intent;
    String detailorupdate, _id,strkodemotor, strnamamotor, strjenismotor, strwarna, strstok, strharga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_katalog);
        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        home = (BottomNavigationItemView) findViewById(R.id.btnhome);
        search = (BottomNavigationItemView) findViewById(R.id.btncari);
        list = (BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account = (BottomNavigationItemView) findViewById(R.id.btnakun);
        namamotor = (TextView) findViewById(R.id.edtnamamotor);
        kode=(TextView)findViewById(R.id.edtkodemotor);
        jenismotor = (TextView) findViewById(R.id.edtjenismotor);
        warna = (TextView) findViewById(R.id.edtwarna);
        stok = (TextView) findViewById(R.id.edtstok);
        harga = (TextView) findViewById(R.id.edtharga);
        btnkembali=(Button)findViewById(R.id.btnkembali);

        intent = getIntent();
        detailorupdate = intent.getStringExtra("detailorupdate");
        _id         = intent.getStringExtra("_id");
        strkodemotor      = intent.getStringExtra("kodemotor");
        strnamamotor      = intent.getStringExtra("namamotor");
        strjenismotor  = intent.getStringExtra("jenismotor");
        strwarna      = intent.getStringExtra("warna");
        strstok     = intent.getStringExtra("stok");
        strharga  = intent.getStringExtra("harga");
        kode.setText(strkodemotor);
        namamotor.setText(strnamamotor);
        jenismotor.setText(strjenismotor);
        warna.setText(strwarna);
        stok.setText(strstok);
        harga.setText(strharga);




btnkembali.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(LihatKatalog.this,KatalogActivity.class);
        startActivity(i);
        finish();
    }
});

        home = (BottomNavigationItemView) findViewById(R.id.btnhome);
        search = (BottomNavigationItemView) findViewById(R.id.btncari);
        list = (BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account = (BottomNavigationItemView) findViewById(R.id.btnakun);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LihatKatalog.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LihatKatalog.this, ListActivity.class);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LihatKatalog.this, AkunActivity.class);
                startActivity(i);
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LihatKatalog.this, KatalogActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

}
