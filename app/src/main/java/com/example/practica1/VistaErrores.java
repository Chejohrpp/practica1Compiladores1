package com.example.practica1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica1.source.reportes.ManejadorErrores;

import java.util.ArrayList;

public class VistaErrores extends AppCompatActivity {
    private TableLayout tableLayout;
    private TableRow fila;
    private String[] titulos = {"LEXEMA","LINEA","COLUMNA","TIPO","DESCRIPCION"};
    private TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
    private ArrayList<ManejadorErrores> manejadorErrores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_ocurrencias);
        tableLayout = findViewById(R.id.table);
        manejadorErrores = getIntent().getParcelableArrayListExtra("lista");
        if (manejadorErrores != null){
            addTabla();
        }else {
            newFila();
            fila.addView(cambiarDatos("NO HAY DATOS"));
            tableLayout.addView(fila);
        }


    }
    private TextView cambiarDatos(String dato){
        TextView txtOperador = new TextView(VistaErrores.this);
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
        for (int i=-1; i< manejadorErrores.size();i++){
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
                fila.addView(cambiarDatos(manejadorErrores.get(i).getLexema()));
                fila.addView(cambiarDatos(String.valueOf(manejadorErrores.get(i).getLinea())));
                fila.addView(cambiarDatos(String.valueOf(manejadorErrores.get(i).getColumna())));
                fila.addView(cambiarDatos(manejadorErrores.get(i).getTipo()));
                fila.addView(cambiarDatos(manejadorErrores.get(i).getDesc()));
                tableLayout.addView(fila);
            }
        }
    }
}
