package com.example.proyinfinixsoft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyinfinixsoft.adapters.Adapter;
import com.example.proyinfinixsoft.entities.Empleado;
import com.example.proyinfinixsoft.entities.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class: ListActivity <br>
 * <p>Este metodo mostrara una lista de empleado en el cual esta restringido a los detalles
 * sino el usuario no ha iniciado sesion con una cuenta registrada previamente.</p>
 * <p>Ultima Modificacion: 15/08/2019</p>
 *
 * @author Sosa Omar E.
 * @version 1.0.0
 * @see 12/08/2019
 * @since JSockets 1.0.0
 */

public class ListActivity extends AppCompatActivity implements Adapter.OnClickItemListener {

    public static final String ULTIMO_NOMBRE_INGREASO = "ulitmoNombreIngresado";
    public static final String ULTIMO_APELLIDO_INGREASO = "ultimoApellidoIngresado";
    public static final String ULTIMO_EMAIL_INGREASO = "ulitmoEmailIngresado";
    public static final String ULTIMO_PASS_INGREASO = "ulitmoPassIngresado";
    public static final String USER = "usuario";
    public static final String PREFERNECIAS = "pref";
    public static final String TAG_ARRAY_JSON = "empleado";
    public static final String TAG_FOTO_USER = "foto";
    public static final String TAG_NOMBRE_USER = "nombre";
    public static final String TAG_APELLIDO_USER = "apellido";
    public static final String TAG_EDAD_USER = "edad";
    public static final String TAG_FECHA_INGRESO_USER = "fecha";
    public static final String EXTRA_FECHAFORMATO = "dd/MM/yyyy";
    public static final String TAG_DEPARTAMENTO_USER = "departamento";
    public static final String TAG_PUESTO_USER = "puesto";
    public static final String TAG_TAREAS_USER = "tarea";
    public static final String URL_JSON = "https://api.myjson.com/bins/kdmlr";


    private RecyclerView recycler;
    private Adapter adaptor;
    private ArrayList<Empleado> trabajadores;
    private RequestQueue requestQueue;
    private TextView bienvenida;
    private Button cerrarSesion;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recycler = findViewById(R.id.rvList);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        bienvenida = findViewById(R.id.tvBienvenida);
        cerrarSesion = findViewById(R.id.btnSalir);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferneces = getSharedPreferences
                        (PREFERNECIAS, Context.MODE_PRIVATE);


                SharedPreferences.Editor editor = preferneces.edit();
                editor.putString(ULTIMO_NOMBRE_INGREASO, "");
                editor.putString(ULTIMO_APELLIDO_INGREASO, "");
                editor.putString(ULTIMO_EMAIL_INGREASO, "");
                editor.putString(ULTIMO_PASS_INGREASO, "");

                editor.commit();
                Intent i = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        trabajadores = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        Usuario usuario = getIntent().getParcelableExtra(USER);
        if (!usuario.getEmail().equals("") && !usuario.getPassword().equals("")) {
            bienvenida.setText("Bienvenido " + usuario.getNombre() + "!");
        } else {
            bienvenida.setVisibility(View.GONE);
            cerrarSesion.setVisibility(View.GONE);
        }

        ParseJSON();
    }

    /**
     * Metodo: ParseJSON
     *
     * <p>Este metodo hara conexion con un servidor y recibira una lista de empleados</p>
     *
     * @since 1.0.0
     */
    private void ParseJSON() {

        String url = URL_JSON;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray(TAG_ARRAY_JSON);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject emp = jsonArray.getJSONObject(i);

                                String foto = emp.getString(TAG_FOTO_USER);
                                String nombre = emp.getString(TAG_NOMBRE_USER);
                                String apellido = emp.getString(TAG_APELLIDO_USER);
                                int edad = emp.getInt(TAG_EDAD_USER);
                                SimpleDateFormat formatter = new SimpleDateFormat(EXTRA_FECHAFORMATO);
                                String dateInString = emp.getString(TAG_FECHA_INGRESO_USER);
                                Date fechaIngreso = formatter.parse(dateInString);
                                String departamento = emp.getString(TAG_DEPARTAMENTO_USER);
                                String puesto = emp.getString(TAG_PUESTO_USER);
                                String tareaActuales = emp.getString(TAG_TAREAS_USER);

                                trabajadores.add(new Empleado(foto, nombre, apellido, edad, fechaIngreso, departamento, puesto, tareaActuales));

                            }

                            Usuario usuario = getIntent().getParcelableExtra(USER);
                            if (!usuario.getEmail().equals("") && !usuario.getPassword().equals("")) {
                                adaptor = new Adapter(ListActivity.this, trabajadores, true);
                                recycler.setAdapter(adaptor);
                                adaptor.setOnClickItemListener(ListActivity.this);
                            } else {
                                adaptor = new Adapter(ListActivity.this, trabajadores, false);
                                recycler.setAdapter(adaptor);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    /**
     * Metodo: onItemClick
     *
     * <p>Este metodo le envia los datos del empleado seleccionado a la pantalla detalles
     * para ser visualizada</p>
     *
     * @since 1.0.0
     */
    @Override
    public void onItemClick(int position) {

        Intent detalleIntent = new Intent(ListActivity.this, DetallesActivity.class);
        Empleado clickInEmpleado = trabajadores.get(position);
        SimpleDateFormat formatter = new SimpleDateFormat(EXTRA_FECHAFORMATO);
        detalleIntent.putExtra(TAG_FECHA_INGRESO_USER, "" + formatter.format(clickInEmpleado.getFechaDeIngreso()));
        long fechaEmpleado = clickInEmpleado.getFechaDeIngreso().getTime();

        Empleado emp = new Empleado(
                clickInEmpleado.getFoto(),
                clickInEmpleado.getNombre(),
                clickInEmpleado.getApellido(),
                clickInEmpleado.getEdad(),
                new Date(fechaEmpleado),
                clickInEmpleado.getDepartamento(),
                clickInEmpleado.getPuesto(),
                clickInEmpleado.getTareasActuales());

        detalleIntent.putExtra(TAG_ARRAY_JSON, emp);
        startActivity(detalleIntent);
    }
}
