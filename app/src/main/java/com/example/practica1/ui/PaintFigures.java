package com.example.practica1.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

import com.example.practica1.source.ManejadorFigura;
import com.example.practica1.source.MoveAnimaciones;
import com.example.practica1.source.objetos.Animacion;
import com.example.practica1.source.reportes.ReportTipoCant;

import java.util.ArrayList;
import java.util.List;

public class PaintFigures extends View {

    private List<ManejadorFigura> manejadorFiguras;
    private ArrayList<ReportTipoCant> reportFiguras;
    private  ArrayList<ReportTipoCant> reportColores;
    private Context context;
    private int indexFigura=0;

    private int[] figuras = new int[5];
    private  int[] colores = new int[8];

    private int indexAnimacion;

    private boolean add=true;

    private ArrayList<MoveAnimaciones> posXY = new ArrayList<>();


    private float id = 0;

    public PaintFigures(Context context,List<ManejadorFigura> manejadorFiguras) {
        super(context);
        this.context = context;
        this.manejadorFiguras = manejadorFiguras;
        this.reportColores = new ArrayList<>();
        this.reportFiguras = new ArrayList<>();
    }
    protected void onDraw(Canvas canvas){
        //reiniciamos las variables
        Paint paint = new Paint();
        indexFigura = 0;
        int indexAnimacion = 0;
        figuras = new int[5];
         colores = new int[8];

         //evaluamos cada objeto de la lista
        for (ManejadorFigura figura: manejadorFiguras) {
            if (figura.getCirculo() != null){
                //dibujamos un circulo
                paint.setColor(theColor(figura.getCirculo().getColor()));

                if (manejadorFiguras.size() > indexFigura+1 && manejadorFiguras.get(indexFigura+1).getAnimacion() != null){
                    if (add == true){
                        posXY.add(new MoveAnimaciones((float) figura.getCirculo().getPosX(),(float) figura.getCirculo().getPosY()));
                    }
                    float radio = (float) figura.getCirculo().getRadio();
                    canvas.drawCircle(posXY.get(indexAnimacion).getPosX(), posXY.get(indexAnimacion).getPosY(), radio, paint);
                    indexAnimacion++;
                }else{
                    canvas.drawCircle((float) figura.getCirculo().getPosX(), (float) figura.getCirculo().getPosY(),
                            (float) figura.getCirculo().getRadio(), paint);
                }
               indexFigura++;
               figuras[0]++;
            }else if (figura.getRectangulo() != null){
                //dibuajmos un rectangulo
                paint.setColor(theColor(figura.getRectangulo().getColor()));
                float posx = (float)figura.getRectangulo().getPosX();
                float posy = (float)figura.getRectangulo().getPosY();
                float alto = posy + (float)figura.getRectangulo().getAlto();
                float ancho = posx + (float)figura.getRectangulo().getAncho();
                if (manejadorFiguras.size() > indexFigura+1 && manejadorFiguras.get(indexFigura+1).getAnimacion() != null){
                    if (add == true){
                        posXY.add(new MoveAnimaciones(posx,posy));
                    }
                    alto = posXY.get(indexAnimacion).getPosY() + (float)figura.getRectangulo().getAlto();
                    ancho = posXY.get(indexAnimacion).getPosX()  + (float)figura.getRectangulo().getAncho();
                    canvas.drawRect(posXY.get(indexAnimacion).getPosX(),posXY.get(indexAnimacion).getPosY() ,ancho,alto,paint);
                    indexAnimacion++;
                }else{
                    canvas.drawRect(posx,posy,ancho,alto,paint);
                }
                indexFigura++;
                figuras[1]++;

            }else if (figura.getLinea() != null){
                //dibujamos una linea
                paint.setColor(theColor(figura.getLinea().getColor()));
                if (manejadorFiguras.size() > indexFigura+1 && manejadorFiguras.get(indexFigura+1).getAnimacion() != null){
                    float posx = (float) figura.getLinea().getPosX();
                    float posy = (float) figura.getLinea().getPosX();
                    if (add == true){
                        posXY.add(new MoveAnimaciones(posx,posy));
                    }
                    posx = posXY.get(indexAnimacion).getPosX();
                    posy = posXY.get(indexAnimacion).getPosY();
                    float ancho = (float) figura.getLinea().getFinX() - (float)figura.getLinea().getPosX();
                    float alto = (float) figura.getLinea().getFinY() - (float)figura.getLinea().getPosY();
                    float finX = posx + ancho;
                    float finY = posy + alto;
                    canvas.drawLine( posx,posy,finX,finY,paint);
                    indexAnimacion++;
                }else{
                    canvas.drawLine( (float)figura.getLinea().getPosX(),(float)figura.getLinea().getPosY(),
                            (float)figura.getLinea().getFinX(),(float)figura.getLinea().getFinY(),paint);
                }
                indexFigura++;
                figuras[2]++;

            }else if (figura.getPoligono() != null){
                //dibujamos un poligono
                paint.setColor(theColor(figura.getPoligono().getColor()));
                try {
                    if (manejadorFiguras.size() > indexFigura+1 && manejadorFiguras.get(indexFigura+1).getAnimacion() != null){
                        float posx = (float) figura.getPoligono().getPosX();
                        float posy = (float) figura.getPoligono().getPosX();
                        if (add == true){
                            posXY.add(new MoveAnimaciones(posx,posy));
                        }
                        posx = posXY.get(indexAnimacion).getPosX();
                        posy = posXY.get(indexAnimacion).getPosY();
                        Path path = dibujarPoligono(posx,posy,figura.getPoligono().getAlto(),
                                figura.getPoligono().getAncho(),figura.getPoligono().getCantLados());
                        canvas.drawPath(path,paint);
                        indexAnimacion++;
                    }else{
                        Path path = dibujarPoligono(figura.getPoligono().getPosX(),figura.getPoligono().getPosY(),figura.getPoligono().getAlto(),
                                figura.getPoligono().getAncho(),figura.getPoligono().getCantLados());
                        canvas.drawPath(path,paint);
                    }
                }catch (Exception e){

                }
                indexFigura++;
                figuras[3]++;

            }else if (figura.getCuadrado() != null){
                //dibujamos un cuadrado
                paint.setColor(theColor(figura.getCuadrado().getColor()));
                float posx = (float)figura.getCuadrado().getPosX();
                float posy = (float)figura.getCuadrado().getPosY();
                float tamLadoX = posx +  (float)figura.getCuadrado().getTamLado();
                float tamLadoY = posy +  (float)figura.getCuadrado().getTamLado();
                if (manejadorFiguras.size() > indexFigura+1 && manejadorFiguras.get(indexFigura+1).getAnimacion() != null){
                    if (add == true){
                        posXY.add(new MoveAnimaciones(posx,posy));
                    }
                     posx = posXY.get(indexAnimacion).getPosX();
                     posy = posXY.get(indexAnimacion).getPosY();
                     tamLadoX = posx +  (float)figura.getCuadrado().getTamLado();
                     tamLadoY = posy +  (float)figura.getCuadrado().getTamLado();
                    canvas.drawRect(posx,posy,tamLadoX,tamLadoY,paint);
                    indexAnimacion++;
                }else{
                    canvas.drawRect(posx,posy,tamLadoX,tamLadoY,paint);
                }
                indexFigura++;
                figuras[4]++;
            }else  if (figura.getAnimacion() != null){
                indexFigura++;
            }
        }


        add =false;
        //agregamos los listados
        llenarListFiguras();
        llenarListColores();
    }
    public float getPosX(int index) {
        return posXY.get(index).getPosX();
    }

    public void setPosX(float posX, int index) {
        posXY.get(index).setPosX(posX);
    }

    public float getPosY(int index) {
        return posXY.get(index).getPosY();
    }

    public void setPosY(float posY, int index) {
        posXY.get(index).setPosY(posY);
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
