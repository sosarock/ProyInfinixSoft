package com.example.proyinfinixsoft.adapters;

import android.content.Context;
import android.graphics.Color;
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


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private Context mContext;

    private ArrayList<Empleado> trabajadores;

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

    public LinearLayout getFondo() {
        return fondo;
    }

    public void setFondo(LinearLayout fondo) {
        this.fondo = fondo;
    }

    public Adapter(Context mContext, ArrayList<Empleado> trabajadores, LinearLayout fondo) {
        this.mContext = mContext;
        this.trabajadores = trabajadores;
        this.fondo = fondo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        TextView fecha;

        Empleado datosEmpleado = trabajadores.get(position);

        String fotoUrl = datosEmpleado.getFoto();
        String nombre = datosEmpleado.getNombre();
        String apellido = datosEmpleado.getApellido();


        Picasso.with(mContext).load(fotoUrl).fit().centerInside().into(viewHolder.foto);
        viewHolder.nombre.setText(nombre);
        viewHolder.apellido.setText(apellido);

        try {


            Calendar current = Calendar.getInstance();
            long fech = current.getTimeInMillis() - datosEmpleado.getFechaDeIngreso().getTime();

            int dias = (int) (fech / 86400000);
            System.out.println(dias);

            if (dias <= 1826) {
                fondo.setBackgroundColor(Color.GREEN);
            } else if (dias >= 3652) {
                fondo.setBackgroundColor(Color.argb(255, 255, 152, 0));

            } else if (dias > 1826 && dias < 3652) {
                fondo.setBackgroundColor(Color.WHITE);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

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

            foto = itemView.findViewById(R.id.ivFotoPerfil);
            nombre = itemView.findViewById(R.id.tvNombre);
            apellido = itemView.findViewById(R.id.tvApellido);
            item = itemView.findViewById(R.id.btnDetallesItem);

            verLista = itemView.findViewById(R.id.btnVerLista);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setVisibility(View.INVISIBLE);

                }
            });


            fondo = itemView.findViewById(R.id.fondoItem);


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