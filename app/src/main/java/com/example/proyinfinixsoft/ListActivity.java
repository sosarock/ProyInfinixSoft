package com.example.proyinfinixsoft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyinfinixsoft.entities.Usuario;

public class ListActivity extends AppCompatActivity {

    private TextView tvSaludoUsuario;
    private Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tvSaludoUsuario = findViewById(R.id.tvBienvenida);
        btnSalir = findViewById(R.id.btnSalir);

        Usuario usuario = getIntent().getParcelableExtra("usuario");

        tvSaludoUsuario.setText("Bienvenido " + usuario.getNombre());


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferneces = getSharedPreferences
                        ("pref", Context.MODE_PRIVATE);


                //METODO 1
                SharedPreferences.Editor editor = preferneces.edit();
                editor.putString("ulitmoUsuarioIngresado", "");
                editor.putString("ulitmoPassIngresado", "");
                editor.putString("ulitmoemailIngresado", "");

                editor.commit();
                Intent i = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

}

