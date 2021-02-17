package com.example.practica1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.practica1.source.LexicoGraph;
import com.example.practica1.source.ManejadorFigura;
import com.example.practica1.source.parser;
import com.example.practica1.ui.PaintFigures;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    private List<ManejadorFigura> manejadorFiguras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("mensaje");

        //Analizamos el contenido de la ventana anterior
        try {
            StringReader reader = new StringReader(message);
            LexicoGraph lexico = new LexicoGraph(reader);
            parser parser = new parser(lexico);
            parser.parse();
            //obtenemos la lista
            manejadorFiguras = parser.getManejadorFigura();

        }catch(Exception e){
            System.out.println("error : " + e.getMessage());
        }


        //empezamos a dibujar las figuras
        ConstraintLayout layout1 = (ConstraintLayout) findViewById(R.id.voidActivityDisplay);
        PaintFigures fondo = new PaintFigures(this, manejadorFiguras);
        layout1.addView(fondo);
    }


}