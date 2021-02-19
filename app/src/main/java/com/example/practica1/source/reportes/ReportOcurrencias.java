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
public class ReportOcurrencias implements Parcelable {
    private String operador;
    private int linea;
    private int columna;
    private String ocurrencia;

    public ReportOcurrencias(String operador, int linea, int columna, String ocurrencia) {
        this.operador = operador;
        this.linea = linea;
        this.columna = columna;
        this.ocurrencia = ocurrencia;
    }

    protected ReportOcurrencias(Parcel in) {
        operador = in.readString();
        linea = in.readInt();
        columna = in.readInt();
        ocurrencia = in.readString();
    }

    public static final Creator<ReportOcurrencias> CREATOR = new Creator<ReportOcurrencias>() {
        @Override
        public ReportOcurrencias createFromParcel(Parcel in) {
            return new ReportOcurrencias(in);
        }

        @Override
        public ReportOcurrencias[] newArray(int size) {
            return new ReportOcurrencias[size];
        }
    };

    public String getOperador() {
        return operador;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getOcurrencia() {
        return ocurrencia;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operador);
        dest.writeInt(linea);
        dest.writeInt(columna);
        dest.writeString(ocurrencia);
    }
}
