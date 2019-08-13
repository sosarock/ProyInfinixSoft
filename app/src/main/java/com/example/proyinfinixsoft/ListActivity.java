package com.example.proyinfinixsoft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyinfinixsoft.adapters.Adapter;
import com.example.proyinfinixsoft.entities.Empleado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements Adapter.OnClickItemListener {

    public static final String EXTRA_URL = "foto";
    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_APELLIDO = "apellido";
    public static final String EXTRA_DEPARTAMENTO = "departamento";
    public static final String EXTRA_PUESTO = "puesto";
    public static final String EXTRA_TAREA= "tarea";

    private RecyclerView recycler;
    private Adapter adaptor;
    private ArrayList<Empleado> trabajadores;
    private RequestQueue requestQueue;

    private Button item;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recycler = findViewById(R.id.rvList);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        trabajadores = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        item = findViewById(R.id.btnDetallesItem);



        ParseJSON();
    }

    private void ParseJSON() {

        String url = "https://api.myjson.com/bins/1gvno3";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("empleado");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject emp = jsonArray.getJSONObject(i);

                                String foto = emp.getString("foto");
                                String nombre = emp.getString("nombre");
                                String apellido = emp.getString("apellido");
                                String departamento = emp.getString("departamento");
                                String puesto = emp.getString("puesto");
                                String tareaActuales = emp.getString("tarea");

                                trabajadores.add(new Empleado(foto, nombre, apellido, departamento, puesto, tareaActuales));


                            }
                            adaptor = new Adapter(ListActivity.this, trabajadores);
                            recycler.setAdapter(adaptor);
                            adaptor.setOnClickItemListener(ListActivity.this);


                        } catch (JSONException e) {
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



    @Override
    public void onItemClick(int position) {
        Intent detalleIntent = new Intent(ListActivity.this, DetallesActivity.class);
        Empleado clickInEmpleado = trabajadores.get(position);

        detalleIntent.putExtra(EXTRA_URL, clickInEmpleado.getFoto());
        detalleIntent.putExtra(EXTRA_NOMBRE, clickInEmpleado.getNombre());
        detalleIntent.putExtra(EXTRA_APELLIDO, clickInEmpleado.getApellido());
        detalleIntent.putExtra(EXTRA_DEPARTAMENTO, clickInEmpleado.getDepartamento());
        detalleIntent.putExtra(EXTRA_PUESTO, clickInEmpleado.getPuesto());
        detalleIntent.putExtra(EXTRA_TAREA, clickInEmpleado.getTareasActuales());
        startActivity(detalleIntent);
    }
}
