package com.example.proyinfinixsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.proyinfinixsoft.ListActivity.EXTRA_APELLIDO;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_DEPARTAMENTO;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_NOMBRE;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_PUESTO;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_TAREA;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_URL;


public class DetallesActivity extends AppCompatActivity {

    private ImageView foto;
    private TextView nombre;
    private TextView apellido;
    private TextView departamento;
    private TextView puesto;
    private TextView tareas;
    private Button atras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        atras = findViewById(R.id.btnAtrasDetalles);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent i = getIntent();
        String urlFotoEmpleado = i.getStringExtra(EXTRA_URL);
        String nombreEmpleado = i.getStringExtra(EXTRA_NOMBRE);
        String apellidoEmpleado = i.getStringExtra(EXTRA_APELLIDO);
        String departamentoEmpleado = i.getStringExtra(EXTRA_DEPARTAMENTO);
        String puestoEmpleado = i.getStringExtra(EXTRA_PUESTO);
        String tareasEmpleado = i.getStringExtra(EXTRA_TAREA);

        foto = findViewById(R.id.ivFotoDetalle);
        nombre = findViewById(R.id.tvNombreDetalle);
        apellido = findViewById(R.id.tvApellidoDetalle);
        departamento = findViewById(R.id.tvDepartamentoDetalle);
        puesto = findViewById(R.id.tvPuestoDetalle);
        tareas = findViewById(R.id.tvTareasDetalle);

        Picasso.with(this).load(urlFotoEmpleado).fit().centerInside().into(foto);
        nombre.setText("Nombre: " + nombreEmpleado);
        apellido.setText("Apellido: " + apellidoEmpleado);
        departamento.setText("Departamento: " + departamentoEmpleado);
        puesto.setText("Puesto: " + puestoEmpleado);
        tareas.setText("Tarea: " + tareasEmpleado);

    }
}
