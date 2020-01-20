package com.example.finaluas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import session.SessionManager;

public class AkunActivity extends AppCompatActivity {
    private BottomNavigationItemView home, search, list, account;
    private CircularRevealCardView cardprofil,cardlogout;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        getSupportActionBar().hide();
        session=new SessionManager(this);
        home = (BottomNavigationItemView) findViewById(R.id.btnhome);
        search = (BottomNavigationItemView) findViewById(R.id.btncari);
        list = (BottomNavigationItemView) findViewById(R.id.btnkeranjang);
        account = (BottomNavigationItemView) findViewById(R.id.btnakun);
        cardlogout=(CircularRevealCardView) findViewById(R.id.logout);
        cardprofil=(CircularRevealCardView)findViewById(R.id.profil);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, ListActivity.class);
                startActivity(i);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, KatalogActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent ii=new Intent(AkunActivity.this,ProfilActibity.class);
startActivity(ii);
finish();
            }
        });

        cardlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.setLogin(false);
                session.setSkip(false);
                session.setSkip(false);
                session.setSessid(0);
                Intent a=new Intent(AkunActivity.this,LoginActivity.class);
                startActivity(a);
                finish();
            }
        });



    }
}

