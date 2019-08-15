package com.example.proyinfinixsoft;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.proyinfinixsoft.ListActivity.EXTRA_APELLIDO;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_DEPARTAMENTO;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_EDAD;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_FECHA;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_NOMBRE;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_PUESTO;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_TAREA;
import static com.example.proyinfinixsoft.ListActivity.EXTRA_URL;

/**
 * Class: DetallesActivity <br>
 * <p>Este metodo mostrara con mas detalles la informacion de un emepleado
 * previamente seleccionado.</p>
 * <p>Ultima Modificacion: 14/08/2019</p>
 *
 * @author Sosa Omar E.
 * @version 1.0.0
 * @see 13/08/2019
 * @since JSockets 1.0.0
 */

public class DetallesActivity extends AppCompatActivity {

    private ImageView foto;
    private TextView nombre;
    private TextView apellido;
    private TextView edad;
    private TextView fecha;
    private TextView departamento;
    private TextView puesto;
    private TextView tareas;
    private Button atras;
    private TextView circuloEdad;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Intent i = getIntent();
        String urlFotoEmpleado = i.getStringExtra(EXTRA_URL);
        String nombreEmpleado = i.getStringExtra(EXTRA_NOMBRE);
        String apellidoEmpleado = i.getStringExtra(EXTRA_APELLIDO);

        int edadEmpleado = i.getIntExtra(EXTRA_EDAD, 0);

        String fechaIngreso = i.getStringExtra(EXTRA_FECHA);

        String departamentoEmpleado = i.getStringExtra(EXTRA_DEPARTAMENTO);
        String puestoEmpleado = i.getStringExtra(EXTRA_PUESTO);
        String tareasEmpleado = i.getStringExtra(EXTRA_TAREA);
        String edadCir = "" + edadEmpleado;

        foto = findViewById(R.id.ivFotoDetalle);
        nombre = findViewById(R.id.tvNombreDetalle);
        apellido = findViewById(R.id.tvApellidoDetalle);
        edad = findViewById(R.id.tvEdadDetalle);
        fecha = findViewById(R.id.tvFechaIngresoDetalle);
        departamento = findViewById(R.id.tvDepartamentoDetalle);
        puesto = findViewById(R.id.tvPuestoDetalle);
        tareas = findViewById(R.id.tvTareasDetalle);

        circuloEdad = findViewById(R.id.circuloEdad);

        Picasso.with(this).load(urlFotoEmpleado).fit().centerInside().into(foto);
        nombre.setText("Nombre: " + nombreEmpleado);
        apellido.setText("Apellido: " + apellidoEmpleado);
        departamento.setText("Departamento: " + departamentoEmpleado);
        edad.setText("Edad: " + edadEmpleado);
        fecha.setText("Fecha: " + fechaIngreso);
        puesto.setText("Puesto: " + puestoEmpleado);
        tareas.setText("Tarea: " + tareasEmpleado);
        circuloEdad.setText(edadCir);


        //CAMBIA DE COLOR LA FECHA DE ANTIGUEDAD

        try {

            Calendar current = Calendar.getInstance();
            String dateInString = fechaIngreso;
            Date fechaIngr = null;
            fechaIngr = formatter.parse(dateInString);
            long fech = current.getTimeInMillis() - fechaIngr.getTime();
            int dias = (int) (fech / 86400000);
            //System.out.println(dias);

            if (dias <= 1826) {
                fecha.setTextColor(Color.GREEN);
            } else if (dias >= 3652) {
                fecha.setTextColor(Color.argb(255, 255, 152, 0));

            } else if (dias > 1826 && dias < 3652) {
                fecha.setTextColor(Color.BLACK);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
