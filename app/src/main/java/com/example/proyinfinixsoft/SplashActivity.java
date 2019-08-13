package com.example.proyinfinixsoft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.proyinfinixsoft.entities.Usuario;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = getSharedPreferences
                ("pref", MODE_PRIVATE);

        String nombreDetails = (String) preferences.getString("ulitmoNombreIngresado", "");
        String apellidoDetails = (String) preferences.getString("ultimoApellidoIngresado", "");
        String emailDetails = (String) preferences.getString("ulitmoEmailIngresado", "");
        String passDetails = (String) preferences.getString("ulitmoPassIngresado", "");

        if (emailDetails.length() == 0 || passDetails.length() == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        } else {

            Usuario usuario = new Usuario(nombreDetails, apellidoDetails, emailDetails, passDetails);

            Intent i = new Intent(SplashActivity.this, ListActivity.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
            finish();

        }

    }

}