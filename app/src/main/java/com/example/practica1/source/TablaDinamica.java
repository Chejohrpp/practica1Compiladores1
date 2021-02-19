package com.example.practica1.source;


import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TablaDinamica {
    private TableLayout tableLayout;
    private Context context;
    private String[] titulos;
    private ArrayList<String[]> datos;
    private TableRow tableRow;
    private TextView txtcelda;
    private int indexC;
    private int indexF;
    public TablaDinamica(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }
    public void addTitulos(String[] titulos){
        this.titulos = titulos;
        crearEncabezado();
    }
    public void addDatos(ArrayList<String[]> datos){
        this.datos = datos;
        //createdDataTable();
    }

    private void newFila(){
        tableRow = new TableRow(context);
    }
    private void newCelda(){
        txtcelda = new TextView(context);
        txtcelda.setGravity(Gravity.CENTER);
        txtcelda.setTextSize(26);
    }
    private void crearEncabezado(){
        indexC = 0;
        newFila();
        while (indexC<titulos.length){
            newCelda();
            txtcelda.setText(titulos[indexC++]);
            tableRow.addView(txtcelda,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void createdDataTable(){
        String info;
        for (indexF=1; indexF<=datos.size();indexF++){
            newFila();
            for (indexC= 0; indexC<titulos.length;indexC++){
                newCelda();
                String[] colums = datos.get(indexF-1);
                info=(indexC<colums.length)?colums[indexC]:"";
                txtcelda.setText(info);
                tableRow.addView(txtcelda,newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }
    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.width = 1;
        return params;
    }

}
