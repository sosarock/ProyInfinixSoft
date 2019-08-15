package com.example.proyinfinixsoft.adapters;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyinfinixsoft.entities.Empleado;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.proyinfinixsoft.R.drawable.background_blanco;
import static com.example.proyinfinixsoft.R.drawable.background_narajan;
import static com.example.proyinfinixsoft.R.drawable.background_verde;
import static com.example.proyinfinixsoft.R.id;
import static com.example.proyinfinixsoft.R.layout;


/**
 * Class: Adapter <br>
 * <p>Clase Adapter con su respectivos datos y constructor.</p>
 * <p>Ultima Modificacion: 14/08/2019</p>
 *
 * @author Sosa Omar E.
 * @version 1.0.0
 * @see 12/08/2019
 * @since JSockets 1.0.0
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private Context mContext;
    private List<Empleado> trabajadores;
    private OnClickItemListener mListener;
    private Button item;
    private LinearLayout fondo;
    private Button verLista;
    public boolean iniciado;

    protected static ActivityManager activityManager;


    protected static int getNumberOfTasks() {
        return activityManager.getAppTasks().size();
    }


    public interface OnClickItemListener {
        void onItemClick(int position);

    }

    public void setOnClickItemListener(OnClickItemListener listener) {
        mListener = listener;
    }


    public Adapter(Context mContext, List<Empleado> trabajadores, boolean iniciado) {
        this.mContext = mContext;
        this.trabajadores = trabajadores;
        this.iniciado = iniciado;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(mContext).inflate(layout.item_layout, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Empleado datosEmpleado = trabajadores.get(position);


        String fotoUrl = datosEmpleado.getFoto();
        String nombre = datosEmpleado.getNombre();
        String apellido = datosEmpleado.getApellido();


        Picasso.with(mContext).load(fotoUrl).fit().centerInside().into(viewHolder.foto);
        viewHolder.nombre.setText(nombre);
        viewHolder.apellido.setText(apellido);

        Date fechaReg = datosEmpleado.getFechaDeIngreso();
        Calendar current = Calendar.getInstance();
        long fech = current.getTimeInMillis() - fechaReg.getTime();


        int dias = (int) (fech / 86400000);

        if (dias <= 1826) {
            fondo.setBackgroundResource(background_verde);

        } else if (dias >= 3652) {
            fondo.setBackgroundResource(background_narajan);
        } else {
            fondo.setBackgroundResource(background_blanco);
        }


        if (iniciado == false) {
            item.setVisibility(View.GONE);
        } else if (iniciado == true) {
            item.setVisibility(View.VISIBLE);

        }

       /* StringBuilder stringBuilder = new StringBuilder();
        int totalNumberOfTasks = activityManager.getRunningTasks(10).size();//devuelve total numero de tasks - stacks

        int flags = getWindow().getAttributes().flags;
        stringBuilder.append("\nTotal Number of Tasks: " + totalNumberOfTasks + "\n\n");


        if (totalNumberOfTasks == 2) {
            item.setVisibility(View.GONE);

        }*/


    }


    @Override
    public int getItemCount() {
        return trabajadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nombre;
        TextView apellido;

        //CONSTRUCTOR
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(id.ivFotoPerfil);
            nombre = itemView.findViewById(id.tvNombre);
            apellido = itemView.findViewById(id.tvApellido);

            item = itemView.findViewById(id.btnDetallesItem);
            verLista = itemView.findViewById(id.btnVerLista);
            fondo = itemView.findViewById(id.fondoItem);


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}