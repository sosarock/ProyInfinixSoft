package com.example.proyinfinixsoft.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Empleado implements Parcelable {

    private String foto;
    private String nombre;
    private String apellido;

    protected Empleado(Parcel in) {
        foto = in.readString();
        nombre = in.readString();
        apellido = in.readString();
    }

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Empleado(String foto, String nombre, String apellido) {
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public static final Creator<Empleado> CREATOR = new Creator<Empleado>() {
        @Override
        public Empleado createFromParcel(Parcel in) {
            return new Empleado(in);
        }

        @Override
        public Empleado[] newArray(int size) {
            return new Empleado[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foto);
        parcel.writeString(nombre);
        parcel.writeString(apellido);
    }
}
