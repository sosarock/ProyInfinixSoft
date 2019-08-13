package com.example.proyinfinixsoft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyinfinixsoft.entities.Usuario;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {
    public static final Pattern EMAIL_ADDRESS =
            Pattern.compile(
                    "[a-z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                            "\\@" +
                            "[a-z0-9][a-z0-9\\-]{1,64}" +
                            "(" +
                            "\\." +
                            "[a-z0-9][a-z0-9\\-]{1,25}" +
                            ")+");

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "(?=.*[0-9])" +  //almenos 1 digito
                            "(?=.*[a-z])" + //almenos 1 letra minuscula
                            "(?=.*[A-Z])" + //almenos 1 letra mayuscula
                            //"(?=.*[@#$%^&+=])" + //almenos un caracter especial
                            "(?=\\S+$)" + //no se acepta espacios
                            ".{6,}" + //minimo 6 caracteres
                            "$");

    public static Pattern USERPattern =
            Pattern.compile(
                    "(?=.*[0-9])" +  //almenos 1 digito
                            "(?=.*[a-z])" + //almenos 1 letra minuscula
                            "(?=.*[A-Z])" + //almenos 1 letra mayuscula
                            //"(?=.*[@#$%^&+=])" + //almenos un caracter especial
                            "(?=\\S+$)" + //no se acepta espacios
                            ".{6,}" + //minimo 6 caracteres
                            "$");

    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText password;
    private EditText password2;
    private CheckBox terminos;

    private Button btnRegistrar;
    private Button btnVoler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.etNombreRegister);
        apellido = findViewById(R.id.etApellidoRegister);
        email = findViewById(R.id.etEmailRegister);
        password = findViewById(R.id.etPassRegister);
        password2 = findViewById(R.id.etPassRegister2);
        terminos = findViewById(R.id.cbTerminos);

        btnRegistrar = findViewById(R.id.btnResgistrar);
        btnVoler = findViewById(R.id.btnVolerResgister);
    }


    @Override
    protected void onResume() {
        super.onResume();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistarUsuario();
            }
        });
        btnVoler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void RegistarUsuario() {

        if (ValidarNombre() && ValidarApellido() && ValidarEmail() && ValidarPassword1() && ValidarPassword2()) {

            if (password.getText().toString().equals(password2.getText().toString())) {
                if (terminos.isChecked()) {

                    String newNombre = nombre.getText().toString();
                    String newApellido = apellido.getText().toString();
                    String newEmail = email.getText().toString();
                    String newPassword = password.getText().toString();


                    SharedPreferences preferneces = getSharedPreferences
                            ("credencial", Context.MODE_PRIVATE);


                    //METODO 1
                    SharedPreferences.Editor editor = preferneces.edit();
                    editor.putString(newEmail + "dataNombre", newNombre);
                    editor.putString(newEmail + "dataApellido", newApellido);
                    editor.putString(newEmail + "dataPass", newPassword);
                    editor.putString(newEmail + "dataEmail", newEmail);

                    editor.commit();

                    Usuario usuario = new Usuario(newNombre, newApellido, newEmail, newPassword);

                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                    i.putExtra("newUsuario", usuario);
                    startActivity(i);
                    finish();


                } else {
                    Toast.makeText(this, "Acepte los terminos y condiciones", Toast.LENGTH_SHORT).show();
                }
            } else {
                password2.setError("Los password no coinsiden!");
            }
        }


    }


    private boolean ValidarEmail() {

        String inputEmail = email.getText().toString().trim();

        SharedPreferences preferences = getSharedPreferences
                ("credencial", MODE_PRIVATE);
        String verificarEmail = (String) preferences.getString(inputEmail + "dataEmail", "");

        if (inputEmail.isEmpty()) {
            email.setError("El campo se encuentra vacio!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            email.setError("Ingrese un email valido!");
            return false;
        } else if (verificarEmail.equals(email.getText().toString())) {
            email.setError("Este Email ya se encuentra registrado");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

    private boolean ValidarPassword1() {
        String inputPass = password.getText().toString().trim();
        if (inputPass.isEmpty()) {
            password.setError("El campo se encuentra Vacio!");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(inputPass).matches()) {
            password.setError("Ingrese un password valido!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean ValidarPassword2() {
        String inputPass2 = password2.getText().toString().trim();
        if (inputPass2.isEmpty()) {
            password2.setError("El campo se encuentra Vacio!");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(inputPass2).matches()) {
            password2.setError("Ingrese un password valido!");
            return false;
        } else {
            password2.setError(null);
            return true;
        }
    }

    private Boolean ValidarNombre() {
        String inputUser = nombre.getText().toString().trim();
        if (inputUser.isEmpty()) {
            nombre.setError("El campo se encuentra vacio");
            return false;
        } else {
            nombre.setError(null);
            return true;
        }
    }

    private Boolean ValidarApellido() {
        String inputUser = apellido.getText().toString().trim();
        if (inputUser.isEmpty()) {
            apellido.setError("El campo se encuentra vacio");
            return false;
        } else {
            apellido.setError(null);
            return true;
        }
    }

}