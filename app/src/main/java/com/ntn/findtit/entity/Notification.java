package com.ntn.findtit.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Notification implements Parcelable {

    private String titulo;
    private String descripcion;

    public Notification(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }


    protected Notification(Parcel in) {
        titulo = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(descripcion);
    }
}
