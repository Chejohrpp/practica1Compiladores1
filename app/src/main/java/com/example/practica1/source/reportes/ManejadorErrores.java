/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source.reportes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author sergi
 */
public class ManejadorErrores implements Parcelable {
    private String lexema;
    private int linea;
    private int columna;
    private String tipo;
    private String desc;

    public ManejadorErrores(String lexema, int linea, int columna, String tipo, String desc) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.desc = desc;
    }

    protected ManejadorErrores(Parcel in) {
        lexema = in.readString();
        linea = in.readInt();
        columna = in.readInt();
        tipo = in.readString();
        desc = in.readString();
    }

    public static final Creator<ManejadorErrores> CREATOR = new Creator<ManejadorErrores>() {
        @Override
        public ManejadorErrores createFromParcel(Parcel in) {
            return new ManejadorErrores(in);
        }

        @Override
        public ManejadorErrores[] newArray(int size) {
            return new ManejadorErrores[size];
        }
    };

    public String getLexema() {
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lexema);
        dest.writeInt(linea);
        dest.writeInt(columna);
        dest.writeString(tipo);
        dest.writeString(desc);
    }
}
