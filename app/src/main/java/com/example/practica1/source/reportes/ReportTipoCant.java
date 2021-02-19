package com.example.practica1.source.reportes;

import android.os.Parcel;
import android.os.Parcelable;

public class ReportTipoCant implements Parcelable {
    private String tipo;
    private int cant;

    public ReportTipoCant(String tipo, int cant) {
        this.tipo = tipo;
        this.cant = cant;
    }

    protected ReportTipoCant(Parcel in) {
        tipo = in.readString();
        cant = in.readInt();
    }

    public static final Creator<ReportTipoCant> CREATOR = new Creator<ReportTipoCant>() {
        @Override
        public ReportTipoCant createFromParcel(Parcel in) {
            return new ReportTipoCant(in);
        }

        @Override
        public ReportTipoCant[] newArray(int size) {
            return new ReportTipoCant[size];
        }
    };

    public String getTipo() {
        return tipo;
    }

    public int getCant() {
        return cant;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tipo);
        dest.writeInt(cant);
    }
}
