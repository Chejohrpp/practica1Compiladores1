package com.example.practica1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VistaAnimaciones extends AppCompatActivity {
    private TableRow fila;
    private TableLayout tableLayout;
    private TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
    private String[] titulos = {"TIPO DE ANIMACION","CANTIDAD"};
    private int animLineas;
    private int animCurvas;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_ocurrencias);
        recibirData();
        tableLayout = findViewById(R.id.table);
        newFila();
        for (int j =0; j<titulos.length;j++){
            TextView textView = cambiarDatos(titulos[j]);
            textView.setBackgroundColor(Color.BLACK);
            textView.setTextColor(Color.WHITE);
            fila.addView(textView);
        }
        tableLayout.addView(fila);

        if (animLineas!=0){
            newFila();
            fila.addView(cambiarDatos("LINEA"));
            fila.addView(cambiarDatos(String.valueOf(animLineas)));
            tableLayout.addView(fila);
        }
        if (animCurvas!=0){
            newFila();
            fila.addView(cambiarDatos("CURVA"));
            fila.addView(cambiarDatos(String.valueOf(animCurvas)));
            tableLayout.addView(fila);
        }
    }
    private void newFila(){
        fila = new TableRow(this);
        fila.setLayoutParams(layoutParams);
    }

    private TextView cambiarDatos(String dato){
        TextView txtOperador = new TextView(this);
        txtOperador.setText(dato);
        txtOperador.setGravity(Gravity.CENTER);
        txtOperador.setPadding(10,10,10,10);
        txtOperador.setLayoutParams(layoutParams);
        return txtOperador;
    }

    private void recibirData(){
        Bundle bundle = getIntent().getExtras();
        animCurvas = Integer.parseInt(bundle.getString("curva"));
        animLineas = Integer.parseInt(bundle.getString("linea"));
    }
}
