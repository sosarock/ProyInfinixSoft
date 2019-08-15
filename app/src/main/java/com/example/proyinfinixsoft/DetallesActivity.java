package com.example.proyinfinixsoft;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.proyinfinixsoft.entities.Empleado;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static final String TAG_ARRAY_JSON = "empleado";
    public static final String EXTRA_FECHAFORMATO = "dd/MM/yyyy";

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

        foto = findViewById(R.id.ivFotoDetalle);
        nombre = findViewById(R.id.tvNombreDetalle);
        apellido = findViewById(R.id.tvApellidoDetalle);
        edad = findViewById(R.id.tvEdadDetalle);
        fecha = findViewById(R.id.tvFechaIngresoDetalle);
        departamento = findViewById(R.id.tvDepartamentoDetalle);
        puesto = findViewById(R.id.tvPuestoDetalle);
        tareas = findViewById(R.id.tvTareasDetalle);

        circuloEdad = findViewById(R.id.circuloEdad);

        Empleado emp = getIntent().getParcelableExtra(TAG_ARRAY_JSON);
        SimpleDateFormat formatter = new SimpleDateFormat(EXTRA_FECHAFORMATO);

        Picasso.with(this).load(emp.getFoto()).fit().centerInside().into(foto);
        nombre.setText("Nombre: " + emp.getNombre());
        apellido.setText("Apellido: " + emp.getApellido());
        departamento.setText("Departamento: " + emp.getDepartamento());
        edad.setText("Edad: " + emp.getEdad());
        fecha.setText("Fecha: " + formatter.format(emp.getFechaDeIngreso()));
        puesto.setText("Puesto: " + emp.getPuesto());
        tareas.setText("Tarea: " + "\n" + emp.getTareasActuales());
        circuloEdad.setText("" + emp.getEdad());

        //CAMBIA DE COLOR LA FECHA DE ANTIGUEDAD

        try {

            Calendar current = Calendar.getInstance();

            String dateInString = formatter.format(emp.getFechaDeIngreso());

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
