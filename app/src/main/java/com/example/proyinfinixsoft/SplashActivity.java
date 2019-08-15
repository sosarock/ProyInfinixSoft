package com.example.proyinfinixsoft;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.example.proyinfinixsoft.entities.Usuario;

/**
 * Class: SplashActivity <br>
 * <p>Pantalla de Introduccion a la aplicacion.</p>
 * <p>Ultima Modificacion: 12/08/2019</p>
 *
 * @author Sosa Omar E.
 * @version 1.0.0
 * @see 12/08/2019
 * @since JSockets 1.0.0
 */

public class SplashActivity extends AppCompatActivity {

    public static final String ULTIMO_NOMBRE_INGREASO = "ulitmoNombreIngresado";
    public static final String ULTIMO_APELLIDO_INGREASO = "ultimoApellidoIngresado";
    public static final String ULTIMO_EMAIL_INGREASO = "ulitmoEmailIngresado";
    public static final String ULTIMO_PASS_INGREASO = "ulitmoPassIngresado";
    public static final String USER = "usuario";
    public static final String PREFERNECIAS = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = getSharedPreferences
                (PREFERNECIAS, MODE_PRIVATE);

        String nombreDetails = (String) preferences.getString(ULTIMO_NOMBRE_INGREASO, "");
        String apellidoDetails = (String) preferences.getString(ULTIMO_APELLIDO_INGREASO, "");
        String emailDetails = (String) preferences.getString(ULTIMO_EMAIL_INGREASO, "");
        String passDetails = (String) preferences.getString(ULTIMO_PASS_INGREASO, "");

        if (emailDetails.length() == 0 || passDetails.length() == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 4000);
        } else {

            Usuario usuario = new Usuario(nombreDetails, apellidoDetails, emailDetails, passDetails);
            Intent i = new Intent(SplashActivity.this, ListActivity.class);
            i.putExtra(USER, usuario);
            startActivity(i);
            finish();
        }
    }
}