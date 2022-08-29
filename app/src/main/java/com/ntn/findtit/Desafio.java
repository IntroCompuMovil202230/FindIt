package com.ntn.findtit;

import android.os.Parcel;
import android.os.Parcelable;

public class Desafio implements Parcelable {
    private String nombre_desafio;
    private String descripcion;
    //private List<String> imagenes;
    private int n_pistas;
    private int n_visitas;
    private int rating;

    public Desafio(String nombre_desafio, String descripcion, int n_pistas, int n_visitas, int rating) {
        this.nombre_desafio = nombre_desafio;
        this.descripcion = descripcion;
        this.n_pistas = n_pistas;
        this.n_visitas = n_visitas;
        this.rating = rating;
    }

    protected Desafio(Parcel in) {
        nombre_desafio = in.readString();
        descripcion = in.readString();
        n_pistas = in.readInt();
        n_visitas = in.readInt();
        rating = in.readInt();
    }

    public static final Creator<Desafio> CREATOR = new Creator<Desafio>() {
        @Override
        public Desafio createFromParcel(Parcel in) {
            return new Desafio(in);
        }

        @Override
        public Desafio[] newArray(int size) {
            return new Desafio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre_desafio);
        parcel.writeString(descripcion);
        parcel.writeInt(n_pistas);
        parcel.writeInt(n_visitas);
        parcel.writeInt(rating);
    }

    public String getNombre_desafio(){
        return this.nombre_desafio;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public int getN_pistas() {
        return n_pistas;
    }

    public int getN_visitas() {
        return n_visitas;
    }

    public int getRating() {
        return rating;
    }

    public void setNombre_desafio(String nombre_desafio) {
        this.nombre_desafio = nombre_desafio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setN_pistas(int n_pistas) {
        this.n_pistas = n_pistas;
    }

    public void setN_visitas(int n_visitas) {
        this.n_visitas = n_visitas;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
