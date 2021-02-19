package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.practica1.source.TablaDinamica;
import com.example.practica1.source.reportes.ReportOcurrencias;

import java.util.ArrayList;
import java.util.List;

public class VistaOcurrencias extends AppCompatActivity {

    private TableLayout tableLayout;
    private ArrayList<ReportOcurrencias> report;
    private TableRow fila;
    private TextView txtOperador,txtLinea,txtColum,txtOcurr;
    private TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
    private String[] titulos = {"operador","linea","columna","ocurrencia"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_ocurrencias);
        tableLayout = findViewById(R.id.table);
        report = getIntent().getParcelableArrayListExtra("lista");
        if (report != null) {
            for (int i = -1; i < report.size(); i++) {
                fila = new TableRow(VistaOcurrencias.this);
                fila.setLayoutParams(layoutParams);
                if (i==-1){
                    for (int j = 0; j<titulos.length;j++){
                        txtOperador = new TextView(VistaOcurrencias.this);
                        txtOperador.setText(titulos[j]);
                        txtOperador.setGravity(Gravity.CENTER);
                        txtOperador.setBackgroundColor(Color.BLACK);
                        txtOperador.setTextColor(Color.WHITE);
                        txtOperador.setPadding(10,10,10,10);
                        txtOperador.setLayoutParams(layoutParams);
                        fila.addView(txtOperador);
                    }
                    tableLayout.addView(fila);
                }else{
                        fila.addView(cambiarDatos(report.get(i).getOperador()));
                        fila.addView(cambiarDatos(String.valueOf(report.get(i).getLinea())));
                        fila.addView(cambiarDatos(String.valueOf(report.get(i).getColumna())));
                        fila.addView(cambiarDatos(report.get(i).getOcurrencia()));
                    tableLayout.addView(fila);
                }
            }
        }else{
            fila = new TableRow(VistaOcurrencias.this);
            fila.setLayoutParams(layoutParams);
            fila.addView(cambiarDatos("NO HAY DATOS PARA EL REPORTE"));
            tableLayout.addView(fila);
        }

    }

    private TextView cambiarDatos(String dato){
        TextView txtOperador = new TextView(VistaOcurrencias.this);
        txtOperador.setText(dato);
        txtOperador.setGravity(Gravity.CENTER);
        txtOperador.setPadding(10,10,10,10);
        txtOperador.setLayoutParams(layoutParams);
        return txtOperador;
    }


}