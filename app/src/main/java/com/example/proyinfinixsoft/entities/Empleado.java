package com.example.proyinfinixsoft.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Empleado implements Parcelable {

    private String imagen;
    private String nombre;
    private String apellido;
    private int edad;
    private Date fechaDeIngreso;
    private String departamento;
    private String puesto;
    private String tareasActuales;

    protected Empleado(Parcel in) {
        imagen = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        edad = in.readInt();
        departamento = in.readString();
        puesto = in.readString();
        tareasActuales = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagen);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeInt(edad);
        dest.writeString(departamento);
        dest.writeString(puesto);
        dest.writeString(tareasActuales);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
