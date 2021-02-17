package com.example.practica1.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

import com.example.practica1.source.ManejadorFigura;

import java.util.List;

public class PaintFigures extends View {

    private List<ManejadorFigura> manejadorFiguras;

    public PaintFigures(Context context,List<ManejadorFigura> manejadorFiguras) {
        super(context);
        this.manejadorFiguras = manejadorFiguras;

    }

    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();

        for (ManejadorFigura figura: manejadorFiguras) {
            if (figura.getCirculo() != null){
                //dibujamos un circulo
                paint.setColor(theColor(figura.getCirculo().getColor()));
                canvas.drawCircle((float)figura.getCirculo().getPosX(),(float)figura.getCirculo().getPosY(),
                        (float) figura.getCirculo().getRadio(),paint);

            }else if (figura.getRectangulo() != null){
                //dibuajmos un rectangulo
                paint.setColor(theColor(figura.getRectangulo().getColor()));
                float posx = (float)figura.getRectangulo().getPosX();
                float posy = (float)figura.getRectangulo().getPosY();
                float alto = posy + (float)figura.getRectangulo().getAlto();
                float ancho = posx + (float)figura.getRectangulo().getAncho();
                canvas.drawRect(posx,posy,ancho,alto,paint);
            }else if (figura.getLinea() != null){
                //dibujamos una linea
                paint.setColor(theColor(figura.getLinea().getColor()));
                canvas.drawLine( (float)figura.getLinea().getPosX(),(float)figura.getLinea().getPosY(),
                        (float)figura.getLinea().getFinX(),(float)figura.getLinea().getFinY(),paint);

            }else if (figura.getPoligono() != null){
                //dibujamos un poligono
                paint.setColor(theColor(figura.getPoligono().getColor()));
                try {
                    Path path = dibujarPoligono(figura.getPoligono().getPosX(),figura.getPoligono().getPosY(),figura.getPoligono().getAlto(),
                            figura.getPoligono().getAncho(),figura.getPoligono().getCantLados());
                    canvas.drawPath(path,paint);
                }catch (Exception e){

                }
            }else if (figura.getAnimacion() != null){


            }else if (figura.getCuadrado() != null){
                //dibujamos un cuadrado
                paint.setColor(theColor(figura.getCuadrado().getColor()));
                float posx = (float)figura.getCuadrado().getPosX();
                float posy = (float)figura.getCuadrado().getPosY();
                float tamLadoX = posx +  (float)figura.getCuadrado().getTamLado();
                float tamLadoY = posy +  (float)figura.getCuadrado().getTamLado();
                canvas.drawRect(posx,posy,tamLadoX,tamLadoY,paint);
            }
        }
    }
    //evaluamos el color
    private int theColor(String color){
        switch (color){
            case "rojo":
                return Color.RED;
            case "azul":
                return  Color.BLUE;
            case "verde":
                return  Color.GREEN;
            case "amarillo":
                return Color.YELLOW;
            case "naranja":
                return  Color.rgb(255,165,0);
            case "morado":
                return  Color.rgb(128,0,128);
            case "cafe":
                return Color.rgb(128,0,0);
            case "negro":
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

}
