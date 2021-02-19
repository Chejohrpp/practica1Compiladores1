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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.practica1.source.LexicoGraph;
import com.example.practica1.source.ManejadorFigura;
import com.example.practica1.source.parser;
import com.example.practica1.source.reportes.ManejadorErrores;
import com.example.practica1.source.reportes.ReportOcurrencias;
import com.example.practica1.ui.PaintFigures;
import com.example.practica1.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private List<ManejadorFigura> manejadorFiguras;
    private ArrayList<ManejadorErrores> manejadorErrores;
    private ArrayList<ReportOcurrencias> reportOcurrencias;

    private HomeFragment fragment;
    private FragmentTransaction fragmentTransaction;
    private Bundle parametro;
    private DrawerLayout drawer;
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
            parser.parse();
            //obtenemos la lista
            manejadorFiguras = parser.getManejadorFigura();
            reportOcurrencias= (ArrayList<ReportOcurrencias>) parser.getReportOcurrencia();
            manejadorErrores = (ArrayList<ManejadorErrores>) parser.getManejadorErrores();
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
                fragment = new HomeFragment();
                parametro = new Bundle();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.report_ocurrencias:
                        Intent intent = new Intent(DisplayMessageActivity.this, VistaOcurrencias.class);
                        intent.putExtra("lista",reportOcurrencias);
                        startActivity(intent);
                        return true;
                    case R.id.report_animaciones:

                        return true;
                    default:
                        parametro.putString("defecto", "opcion por defecto");
                        fragment.setArguments(parametro);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.voidActivityDisplay,fragment);
                        fragmentTransaction.commit();
                        return true;
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




        //empezamos a dibujar las figuras
        ConstraintLayout layout1 = (ConstraintLayout) findViewById(R.id.voidActivityDisplay);
        PaintFigures fondo = new PaintFigures(this, manejadorFiguras);
        layout1.addView(fondo);
    }


    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment1);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}