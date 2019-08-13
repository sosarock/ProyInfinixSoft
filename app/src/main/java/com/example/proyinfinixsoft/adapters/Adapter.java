package com.example.proyinfinixsoft.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.proyinfinixsoft.R;
import com.example.proyinfinixsoft.entities.Empleado;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private Context mContext;
    private ArrayList<Empleado> trabajadores;
    private OnClickItemListener mListener;

    public interface OnClickItemListener {
        void onItemClick(int position);
    }

    public void setOnClickItemListener(OnClickItemListener listener) {
        mListener = listener;
    }


    public Adapter(Context mContext, ArrayList<Empleado> games) {
        this.mContext = mContext;
        this.trabajadores = games;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Empleado datosEmpleado = trabajadores.get(position);

        String fotoUrl = datosEmpleado.getFoto();
        String nombre = datosEmpleado.getNombre();
        String apellido = datosEmpleado.getApellido();


        Picasso.with(mContext).load(fotoUrl).fit().centerInside().into(viewHolder.foto); //cargar la foto del juego
        viewHolder.nombre.setText(nombre); // nombre del juego
        viewHolder.apellido.setText(apellido); // nombre del juego


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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener !=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
