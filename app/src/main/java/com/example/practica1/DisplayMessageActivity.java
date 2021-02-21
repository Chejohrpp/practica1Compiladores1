package com.example.practica1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.practica1.source.LexicoGraph;
import com.example.practica1.source.ManejadorFigura;
import com.example.practica1.source.objetos.Animacion;
import com.example.practica1.source.parser;
import com.example.practica1.source.reportes.ManejadorErrores;
import com.example.practica1.source.reportes.ReportOcurrencias;
import com.example.practica1.source.reportes.ReportTipoCant;
import com.example.practica1.ui.Animar;
import com.example.practica1.ui.PaintFigures;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class DisplayMessageActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private List<ManejadorFigura> manejadorFiguras;
    private ArrayList<ManejadorErrores> manejadorErrores = new ArrayList<>();
    private ArrayList<ReportOcurrencias> reportOcurrencias=new ArrayList<>();
    private ArrayList<ReportTipoCant> reportFiguras = new ArrayList<>();
    private ArrayList<ReportTipoCant> reportColores = new ArrayList<>();

    private FragmentTransaction fragmentTransaction;
    private Bundle parametro;
    private DrawerLayout drawer;

    //variables listados
    int animLinea = 0;
    int animCurva= 0;

    int cantAnimacion=0;
    ArrayList<Animacion> animacions = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("mensaje");

        //Analizamos el contenido de la ventana anterior
        try {
            StringReader reader = new StringReader(message);
            LexicoGraph lexico = new LexicoGraph(reader);
            parser parser = new parser(lexico);
            try {
                parser.parse();
            }catch (Exception e){
                System.out.println("error en el parser " + e.getMessage());
            }
            //obtenemos la lista

            manejadorFiguras = parser.getManejadorFigura();
            manejadorErrores = (ArrayList<ManejadorErrores>) parser.getManejadorErrores();
            //evaluamos que no hay errores para hacer los reportes
            if (manejadorErrores.size()==0 && manejadorErrores != null){
                reportOcurrencias= (ArrayList<ReportOcurrencias>) parser.getReportOcurrencia();
                extraerListas();
            }
            //System.out.println("hola " + manejadorErrores.size());
        }catch(Exception e){
            System.out.println("error : " + e.getMessage());
        }

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()){
                    item.setChecked(false);
                }else{
                    item.setChecked(true);
                }
                drawer.closeDrawers();
                parametro = new Bundle();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.report_ocurrencias: {
                        Intent intent = new Intent(DisplayMessageActivity.this, VistaOcurrencias.class);
                        intent.putExtra("lista", reportOcurrencias);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.report_animaciones: {
                        Intent intent = new Intent(DisplayMessageActivity.this, VistaAnimaciones.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("linea", String.valueOf(animLinea));
                        bundle.putString("curva",String.valueOf(animCurva));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.nav_Errores:{
                        Intent intent = new Intent(DisplayMessageActivity.this, VistaErrores.class);
                        intent.putExtra("lista",manejadorErrores);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.nav_colores:{
                        Intent intent = new Intent(DisplayMessageActivity.this, VistaColores.class);
                        intent.putExtra("lista",reportColores);
                        startActivity(intent);
                        return true;
                    }
                    case R.id.nav_figuras:{
                        Intent intent = new Intent(DisplayMessageActivity.this, VistaFiguras.class);
                        intent.putExtra("lista",reportFiguras);
                        startActivity(intent);
                        return true;
                    }
                    default: {
                        return true;
                    }
                }
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.abrirDrawe,R.string.cerrarDrawer){
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }
        };
        //drawer.setDrawerListener(actionBarDrawerToggle);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (manejadorErrores.size() == 0 && manejadorErrores != null) {
            //empezamos a dibujar las figuras
            ConstraintLayout layout1 = (ConstraintLayout) findViewById(R.id.voidActivityDisplay);
            PaintFigures fondo = new PaintFigures(this, manejadorFiguras);
            fondo.setId(R.id.fondo);
            layout1.addView(fondo);
            reportColores = fondo.getReportColores();
            reportFiguras = fondo.getReportFiguras();
            if (cantAnimacion>0){
                activarAnimacion();
            }
        }else{
            ConstraintLayout layout1 = (ConstraintLayout) findViewById(R.id.voidActivityDisplay);
            layout1.addView(cambiarDatos("ERROR DE COMPILACION:\nREVISE EL APARTADO DE REPORTES DE ERRORES"));
        }
    }

    public void extraerListas(){
        int id = -1;
        for (ManejadorFigura manejadorFigura: manejadorFiguras){
            id++;
            if (manejadorFigura.getAnimacion() != null){
                if (manejadorFigura.getAnimacion().getTipo().equalsIgnoreCase("linea")){
                    animLinea++;
                }else{
                    animCurva++;
                }
                if (id > 0){
                    if (manejadorFiguras.get(id-1).getAnimacion() == null ){
                        cantAnimacion++;
                        animacions.add(new Animacion(manejadorFigura.getAnimacion().getDesX(),manejadorFigura.getAnimacion().getDesY(),manejadorFigura.getAnimacion().getTipo()));
                    }
                }
            }
        }
    }

    private TextView cambiarDatos(String dato){
        TextView txtOperador = new TextView(this);
        txtOperador.setText(dato);
        txtOperador.setGravity(Gravity.CENTER);
        txtOperador.setPadding(10,10,10,10);
        txtOperador.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        return txtOperador;
    }

    private void activarAnimacion(){
        LinearLayout linearLayout = findViewById(R.id.linearAnim);
        linearLayout.addView(cambiarDatos("Cantidad de animaciones Posibles : "+ cantAnimacion));
        Button button = new Button(this);
        button.setText("Animar");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    int id =0;
                    public void run() {
                        while (id < cantAnimacion) {
                            try {
                                //System.out.println("id: " + id);
                                PaintFigures paintFigures = findViewById(R.id.fondo);
                                String tipo = animacions.get(id).getTipo();
                                float desX = (float) animacions.get(id).getDesX();
                                float desY = (float) animacions.get(id).getDesY();
                                Animar animar = new Animar(paintFigures,desX,desY,tipo,id);
                                animar.setDuration(1000);
                                paintFigures.startAnimation(animar);
                                id++;
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
        button.setPadding(10,10,10,10);
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setGravity(Gravity.CENTER);
        linearLayout.addView(button);
    }
}