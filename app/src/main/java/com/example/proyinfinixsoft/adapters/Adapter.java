package com.example.proyinfinixsoft.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyinfinixsoft.R;
import com.example.proyinfinixsoft.entities.Empleado;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.proyinfinixsoft.R.*;
import static com.example.proyinfinixsoft.R.drawable.*;
import static com.example.proyinfinixsoft.R.drawable.background_narajan;


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


    public interface OnClickItemListener {
        void onItemClick(int position);

    }

    public void setOnClickItemListener(OnClickItemListener listener) {
        mListener = listener;
    }




    public Adapter(Context mContext, List<Empleado> trabajadores) {
        this.mContext = mContext;
        this.trabajadores = trabajadores;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(layout.item_layout, viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Empleado datosEmpleado = trabajadores.get(position);
        String fotoUrl = datosEmpleado.getFoto();
        String nombre = datosEmpleado.getNombre();
        String apellido = datosEmpleado.getApellido();
        Date fechaReg = datosEmpleado.getFechaDeIngreso();



        Picasso.with(mContext).load(fotoUrl).fit().centerInside().into(viewHolder.foto);
        viewHolder.nombre.setText(nombre);
        viewHolder.apellido.setText(apellido);


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

            //    item.setVisibility(View.GONE);


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