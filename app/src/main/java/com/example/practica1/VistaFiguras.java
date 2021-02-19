package com.example.practica1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica1.source.reportes.ReportTipoCant;

import java.util.ArrayList;

public class VistaFiguras extends AppCompatActivity {
    private TableLayout tableLayout;
    private TableRow fila;
    private String[] titulos = {"OBJETO", "CANTIDAD"};
    private TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);

    private ArrayList<ReportTipoCant> figuras;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_ocurrencias);
        tableLayout = findViewById(R.id.table);
        figuras = getIntent().getParcelableArrayListExtra("lista");
        if (figuras != null){
            addTabla();
        }else{
            newFila();
            fila.addView(cambiarDatos("NO HAY DATOS"));
            tableLayout.addView(fila);
        }
    }
    private TextView cambiarDatos(String dato){
        TextView txtOperador = new TextView(this);
        txtOperador.setText(dato);
        txtOperador.setGravity(Gravity.CENTER);
        txtOperador.setPadding(10,10,10,10);
        txtOperador.setLayoutParams(layoutParams);
        return txtOperador;
    }
    private void newFila(){
        fila = new TableRow(this);
        fila.setLayoutParams(layoutParams);
    }
    private void addTabla(){
        for (int i=-1; i< figuras.size();i++){
            newFila();
            if (i==-1){
                for (String titulo: titulos){
                    TextView textView = cambiarDatos(titulo);
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                    fila.addView(textView);
                }
                tableLayout.addView(fila);
            }else{
                if (figuras.get(i).getCant() != 0){
                    fila.addView(cambiarDatos(figuras.get(i).getTipo()));
                    fila.addView(cambiarDatos(String.valueOf(figuras.get(i).getCant())));
                    tableLayout.addView(fila);
                }
            }
        }
    }
}
