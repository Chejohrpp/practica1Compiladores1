package com.example.practica1.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

import com.example.practica1.source.ManejadorFigura;
import com.example.practica1.source.reportes.ReportTipoCant;

import java.util.ArrayList;
import java.util.List;

public class PaintFigures extends View {

    private List<ManejadorFigura> manejadorFiguras;
    private ArrayList<ReportTipoCant> reportFiguras;
    private  ArrayList<ReportTipoCant> reportColores;

    private int[] figuras = new int[5];
    private  int[] colores = new int[8];

    public PaintFigures(Context context,List<ManejadorFigura> manejadorFiguras) {
        super(context);
        this.manejadorFiguras = manejadorFiguras;
        this.reportColores = new ArrayList<>();
        this.reportFiguras = new ArrayList<>();
    }

    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        figuras = new int[5];
         colores = new int[8];
        for (ManejadorFigura figura: manejadorFiguras) {
            if (figura.getCirculo() != null){
                //dibujamos un circulo
                paint.setColor(theColor(figura.getCirculo().getColor()));
                canvas.drawCircle((float)figura.getCirculo().getPosX(),(float)figura.getCirculo().getPosY(),
                        (float) figura.getCirculo().getRadio(),paint);
                figuras[0]++;

            }else if (figura.getRectangulo() != null){
                //dibuajmos un rectangulo
                paint.setColor(theColor(figura.getRectangulo().getColor()));
                float posx = (float)figura.getRectangulo().getPosX();
                float posy = (float)figura.getRectangulo().getPosY();
                float alto = posy + (float)figura.getRectangulo().getAlto();
                float ancho = posx + (float)figura.getRectangulo().getAncho();
                canvas.drawRect(posx,posy,ancho,alto,paint);
                figuras[1]++;
            }else if (figura.getLinea() != null){
                //dibujamos una linea
                paint.setColor(theColor(figura.getLinea().getColor()));
                canvas.drawLine( (float)figura.getLinea().getPosX(),(float)figura.getLinea().getPosY(),
                        (float)figura.getLinea().getFinX(),(float)figura.getLinea().getFinY(),paint);
                figuras[2]++;

            }else if (figura.getPoligono() != null){
                //dibujamos un poligono
                paint.setColor(theColor(figura.getPoligono().getColor()));
                try {
                    Path path = dibujarPoligono(figura.getPoligono().getPosX(),figura.getPoligono().getPosY(),figura.getPoligono().getAlto(),
                            figura.getPoligono().getAncho(),figura.getPoligono().getCantLados());
                    canvas.drawPath(path,paint);
                }catch (Exception e){

                }
                figuras[3]++;
            }else if (figura.getAnimacion() != null){


            }else if (figura.getCuadrado() != null){
                //dibujamos un cuadrado
                paint.setColor(theColor(figura.getCuadrado().getColor()));
                float posx = (float)figura.getCuadrado().getPosX();
                float posy = (float)figura.getCuadrado().getPosY();
                float tamLadoX = posx +  (float)figura.getCuadrado().getTamLado();
                float tamLadoY = posy +  (float)figura.getCuadrado().getTamLado();
                canvas.drawRect(posx,posy,tamLadoX,tamLadoY,paint);
                figuras[4]++;
            }
        }
        llenarListFiguras();
        llenarListColores();
    }
    //evaluamos el color
    private int theColor(String color){
        switch (color){
            case "rojo":
                colores[0]++;
                return Color.RED;
            case "azul":
                colores[1]++;
                return  Color.BLUE;
            case "verde":
                colores[2]++;
                return  Color.GREEN;
            case "amarillo":
                colores[3]++;
                return Color.YELLOW;
            case "naranja":
                colores[4]++;
                return  Color.rgb(255,165,0);
            case "morado":
                colores[5]++;
                return  Color.rgb(128,0,128);
            case "cafe":
                colores[6]++;
                return Color.rgb(128,0,0);
            case "negro":
                colores[7]++;
                return  Color.BLACK;
        }
        return Color.TRANSPARENT;
    }

    //creamos el poligono
    private Path dibujarPoligono(double posX, double posY, double alto, double ancho, double numLado){
        double angulo =  ((2*Math.PI)/numLado);
        double centerX =  ((posX+(posX+ancho))/2);
        double radioX = ancho/2;
        double radioY = alto/2;
        double centerY =  ((posY+(posY+alto))/2);
        Path path = new Path();
        for (int i=0;i<numLado;i++){
            double x = radioX * Math.cos(angulo + (i*angulo)) + centerX;
            double y = radioY * Math.sin(angulo + (i*angulo)) + centerY;
            if (i==0){
                path.moveTo((float)x,(float)y);
            }else{
                path.lineTo((float) x, (float) y);
            }
        }
        return path;
    }
    private void llenarListFiguras(){
        if (reportFiguras.size() != 0){
            reportFiguras.clear();
        }
        reportFiguras.add(new ReportTipoCant("CIRCULO",figuras[0]));
        reportFiguras.add(new ReportTipoCant("RECTANGULO",figuras[1]));
        reportFiguras.add(new ReportTipoCant("LINEA",figuras[2]));
        reportFiguras.add(new ReportTipoCant("POLIGONO",figuras[3]));
        reportFiguras.add(new ReportTipoCant("CUADRADO",figuras[4]));
    }
    private void llenarListColores(){
        if (reportColores.size() != 0){
            reportColores.clear();
        }
        reportColores.add(new ReportTipoCant("ROJO",colores[0]));
        reportColores.add(new ReportTipoCant("AZUL",colores[1]));
        reportColores.add(new ReportTipoCant("VERDE",colores[2]));
        reportColores.add(new ReportTipoCant("AMARILLO",colores[3]));
        reportColores.add(new ReportTipoCant("NARANJA",colores[4]));
        reportColores.add(new ReportTipoCant("MORADO",colores[5]));
        reportColores.add(new ReportTipoCant("CAFE",colores[6]));
        reportColores.add(new ReportTipoCant("NEGRO",colores[7]));
    }

    public ArrayList<ReportTipoCant> getReportFiguras() {
        return reportFiguras;
    }

    public ArrayList<ReportTipoCant> getReportColores() {
        return reportColores;
    }
}
