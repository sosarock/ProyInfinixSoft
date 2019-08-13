package com.example.proyinfinixsoft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyinfinixsoft.entities.Usuario;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //PATRON DE VALIDACION DE EMAIL
    public static final Pattern EMAIL_ADDRESS =
            Pattern.compile(
                    "[a-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                            "\\@" +
                            "[a-z0-9][a-z0-9\\-]{1,64}" +
                            "(" +
                            "\\." +
                            "[a-z0-9][a-z0-9\\-]{1,25}" +
                            ")+");

    //PATRON DE VALIDACION DE PASSWORD
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "(?=.*[0-9])" +  //almenos 1 digito
                            "(?=.*[a-z])" + //almenos 1 letra minuscula
                            "(?=.*[A-Z])" + //almenos 1 letra mayuscula
                            //"(?=.*[@#$%^&+=])" + //almenos un caracter especial
                            "(?=\\S+$)" + //no se acepta espacios
                            ".{8,}" + //minimo 8 caracteres
                            "$");


    private EditText etEmail;
    private EditText etPass;
    private TextView tvRegistrar;
    private Button btnEntrar;
    private Button btnVerLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        tvRegistrar = findViewById(R.id.tvRegistrar);
        tvRegistrar.setOnClickListener(this);

        btnEntrar = findViewById(R.id.btnLogin);
        btnEntrar.setOnClickListener(this);
          btnVerLista = findViewById(R.id.btnVerLista);
          btnVerLista.setOnClickListener(this);


    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                String email = etEmail.getText().toString();

                SharedPreferences preferences = getSharedPreferences
                        ("credencial", MODE_PRIVATE);
                String nombreDetails = (String) preferences.getString(email + "dataNombre", "");
                String apellidoDetails = (String) preferences.getString(email + "dataApellido", "");
                String emailDetails = (String) preferences.getString(email + "dataEmail", "");
                String passDetails = (String) preferences.getString(email + "dataPass", "");


                if (emailDetails.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Email o Password es incorrecto! ", Toast.LENGTH_SHORT).show();

                } else if (passDetails.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Email o Password es incorrecto! ", Toast.LENGTH_SHORT).show();

                } else {
                    if (emailDetails.equals(etEmail.getText().toString()) &&
                            passDetails.equals(etPass.getText().toString())) {

                        SharedPreferences preferneces = getSharedPreferences
                                ("pref", Context.MODE_PRIVATE);


                        SharedPreferences.Editor editor = preferneces.edit();
                        editor.putString("ulitmoNombreIngresado", nombreDetails);
                        editor.putString("ulitmoApellidoIngresado", apellidoDetails);
                        editor.putString("ulitmoEmailIngresado", emailDetails);
                        editor.putString("ulitmoPassIngresado", passDetails);


                        editor.commit();

                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                        Usuario usuario = new Usuario(nombreDetails, apellidoDetails, emailDetails, passDetails);

                        Intent i = new Intent(LoginActivity.this, ListActivity.class);
                        i.putExtra("usuario", usuario);
                        startActivity(i);
                        finish();

                    }

                }

                break;
            case R.id.tvRegistrar:
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
               // finish();
                break;
            case R.id.btnVerLista:
                Intent i2 = new Intent(LoginActivity.this, ListActivity.class);
                startActivity(i2);
                //finish();
                break;
        }
    }


}
