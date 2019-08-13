package com.example.proyinfinixsoft.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Empleado implements Parcelable {

    private String foto;
    private String nombre;
    private String apellido;
    private int edad;
    //  private Date fechaDeIngreso;
    private String departamento;
    private String puesto;
    private String tareasActuales;

    protected Empleado(Parcel in) {
        foto = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        edad = in.readInt();
        departamento = in.readString();
        puesto = in.readString();
        tareasActuales = in.readString();
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

    public Empleado(String foto, String nombre, String apellido, int edad, String departamento, String puesto, String tareasActuales) {
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.departamento = departamento;
        this.puesto = puesto;
        this.tareasActuales = tareasActuales;
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getEdad() {
        return edad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getTareasActuales() {
        return tareasActuales;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foto);
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeInt(edad);
        parcel.writeString(departamento);
        parcel.writeString(puesto);
        parcel.writeString(tareasActuales);
    }
}
