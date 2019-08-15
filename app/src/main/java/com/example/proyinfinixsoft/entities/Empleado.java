package com.example.proyinfinixsoft.entities;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

/**
 * Class: Empleado <br>
 * <p>Clase Emplado con su respectivos datos y constructor.</p>
 * <p>Ultima Modificacion: 12/08/2019</p>
 *
 * @author Sosa Omar E.
 * @version 1.0.0
 * @see 12/08/2019
 * @since JSockets 1.0.0
 */
public class Empleado implements Parcelable {

    private String foto;
    private String nombre;
    private String apellido;
    private int edad;
    private Date fechaDeIngreso;
    private String departamento;
    private String puesto;
    private String tareasActuales;

    protected Empleado(Parcel in) {
        foto = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        edad = in.readInt();
        fechaDeIngreso = new Date(in.readLong());
        departamento = in.readString();
        puesto = in.readString();
        tareasActuales = in.readString();

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

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
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

    public Empleado(String foto, String nombre, String apellido, int edad, Date fechaDeIngreso, String departamento, String puesto, String tareasActuales) {
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaDeIngreso = fechaDeIngreso;
        this.departamento = departamento;
        this.puesto = puesto;
        this.tareasActuales = tareasActuales;

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
        parcel.writeInt(edad);
        parcel.writeLong((fechaDeIngreso).getTime());
        parcel.writeString(departamento);
        parcel.writeString(puesto);
        parcel.writeString(tareasActuales);
    }
}
