package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import session.SessionManager;

public class ListActivity extends AppCompatActivity {
    private BottomNavigationItemView home, search, list, account;
    private CircularRevealCardView cardinputin,cardfind;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();
        home = (BottomNavigationItemView) findViewById(R.id.btnhome);
        search = (BottomNavigationItemView) findViewById(R.id.btncari);
        list = (BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account = (BottomNavigationItemView) findViewById(R.id.btnakun);
        cardinputin=(CircularRevealCardView) findViewById(R.id.masuk);
        cardfind=(CircularRevealCardView)findViewById(R.id.find);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, KatalogActivity.class);
                startActivity(i);
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, AkunActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardinputin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(ListActivity.this,SimpantransaksiActivity.class);
                startActivity(ii);
                finish();
            }
        });

        cardfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ListActivity.this,CaripesanActivity.class);
                startActivity(a);
                finish();
            }
        });

    }
}
