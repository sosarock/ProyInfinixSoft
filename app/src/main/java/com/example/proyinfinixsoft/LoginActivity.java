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

import com.example.proyinfinixsoft.entities.Usuario;

import java.util.regex.Pattern;

/**
 * Class: LoginActivity <br>
 * <p>Este metodo permite logearse al usuario con Email y password, o registrar un nuevo usuario,
 * y ver las listas sin necesidad de logearse.</p>
 * <p>Ultima Modificacion: 12/08/2019</p>
 *
 * @author Sosa Omar E.
 * @version 1.0.0
 * @see 12/08/2019
 * @since JSockets 1.0.0
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String USER_NOMBRE = "dataNombre";
    public static final String USER_APELLIDO = "dataApellido";
    public static final String USER_EMAIL = "dataEmail";
    public static final String USER_PASS = "dataPass";
    public static final String ULTIMO_NOMBRE_INGREASO = "ulitmoNombreIngresado";
    public static final String ULTIMO_APELLIDO_INGREASO = "ultimoApellidoIngresado";
    public static final String ULTIMO_EMAIL_INGREASO = "ulitmoEmailIngresado";
    public static final String ULTIMO_PASS_INGREASO = "ulitmoPassIngresado";
    public static final String USER = "usuario";
    public static final String PREFERNECIAS = "pref";
    //  public static final String PREFERNECIAS_VISIBLE="prefVisible";
    public static final String CREDENCIAL = "credencial";


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

    /**
     * Metodo: onClick
     *
     * <p>Este metodo contiene 3 botones con diferentes acciones</p>
     * <p>- Loguear el usuario con email y contraseña</p>
     * </p>- Ver la lista sin necesidad de loguearse</p>
     * </p>- Registrar un nuevo usuario</p>
     *
     * @param view permite mostrar vistas de datos previamente informados en la aplicación
     * @since 1.0.0
     */
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:


                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString();
                SharedPreferences preferences = getSharedPreferences
                        (CREDENCIAL, MODE_PRIVATE);

                String verificarEmail = (String) preferences.getString(email + USER_EMAIL, "");

                String nombreDetails = (String) preferences.getString(email + USER_NOMBRE, "");
                String apellidoDetails = (String) preferences.getString(email + USER_APELLIDO, "");
                String emailDetails = (String) preferences.getString(email + USER_EMAIL, "");
                String passDetails = (String) preferences.getString(email + USER_PASS, "");


                if (email.length() == 0) {
                    etEmail.setError("El campo se encuentra vacio");
                } else if (pass.length() == 0) {
                    etPass.setError("El campo se encuentra vacio");

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Ingrese un email valido!");

                } else if (!verificarEmail.equals(etEmail.getText().toString())) {
                    etEmail.setError("Este Email no se encuentra registrado");
                } else if (!emailDetails.equals(etEmail.getText().toString()) ||
                        !passDetails.equals(etPass.getText().toString())) {
                    etEmail.setError("El Email o Password es incorrecto");
                    etPass.setError("El Email o Password es incorrecto");

                } else {
                    if (emailDetails.equals(etEmail.getText().toString()) &&
                            passDetails.equals(etPass.getText().toString())) {


                        SharedPreferences preferneces = getSharedPreferences
                                (PREFERNECIAS, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = preferneces.edit();
                        editor.putString(ULTIMO_NOMBRE_INGREASO, nombreDetails);
                        editor.putString(ULTIMO_APELLIDO_INGREASO, apellidoDetails);
                        editor.putString(ULTIMO_EMAIL_INGREASO, emailDetails);
                        editor.putString(ULTIMO_PASS_INGREASO, passDetails);

                        editor.commit();

                        Usuario usuario = new Usuario(nombreDetails, apellidoDetails, emailDetails, passDetails);
                        Intent i = new Intent(LoginActivity.this, ListActivity.class);
                        i.putExtra(USER, usuario);
                        startActivity(i);
                        finish();

                    }
                }
                break;

            case R.id.tvRegistrar:
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
                break;

            case R.id.btnVerLista:


                SharedPreferences preferneces = getSharedPreferences
                        (PREFERNECIAS, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferneces.edit();
                editor.putString(ULTIMO_NOMBRE_INGREASO, "");
                editor.putString(ULTIMO_APELLIDO_INGREASO, "");
                editor.putString(ULTIMO_EMAIL_INGREASO, "");
                editor.putString(ULTIMO_PASS_INGREASO, "");
                //  editor.putString(PREFERNECIAS_VISIBLE, "");

                editor.commit();

                Usuario usuario = new Usuario("", "", "", "");
                Intent i2 = new Intent(LoginActivity.this, ListActivity.class);
                i2.putExtra(USER, usuario);
                startActivity(i2);
                break;
        }
    }
}
