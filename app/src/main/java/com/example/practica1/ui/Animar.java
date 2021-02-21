package com.example.practica1.ui;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.practica1.source.objetos.Animacion;

public class Animar extends Animation {
    private PaintFigures paintFigures;
    private float oldPosicionX;
    private float newPosicionX;

    private float newPosicionY;
    private float oldPosicionY;


    private String tipo;
    private int index;


    public Animar(PaintFigures paintFigures,float newPosicionX,float newPosicionY, String tipo, int index) {
        this.paintFigures = paintFigures;
        this.oldPosicionX = paintFigures.getPosX(index);
        this.newPosicionX = newPosicionX;
        this.oldPosicionY= paintFigures.getPosY(index);
        this.newPosicionY = newPosicionY;
        this.tipo = tipo;
        this.index = index;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (tipo.equalsIgnoreCase("linea")){
            //posicion X
            float positionX = oldPosicionX + ((newPosicionX -oldPosicionX) * interpolatedTime);
            paintFigures.setPosX(positionX,index);
            //posicion Y
            float positionY = oldPosicionY +  ((newPosicionY-oldPosicionY)*interpolatedTime);
            paintFigures.setPosY(positionY,index);
            //actualizamos
            paintFigures.requestLayout();
        }else{
            float desfase=0;
            float angulo = (((float)(Math.PI) * interpolatedTime));
            float radioX = ((newPosicionX - oldPosicionX)/2);
            float radioY = ((newPosicionY - oldPosicionY)/2);
            float radio = ((float)Math.hypot(radioX*2,radioY*2))/2;
            float centroX = ((newPosicionX+oldPosicionX)/2);
            float centroY = ((newPosicionY+oldPosicionY)/2);
            double x = -radioX * Math.cos(angulo) + centroX;
            double y = radioY * Math.sin(angulo) + centroY;

            if (Math.acos((centroX-oldPosicionX)/radio) <= Math.acos((centroY-oldPosicionY)/radio) ){

                if (radio != 0){
                    desfase =(float) Math.acos((centroX-oldPosicionX)/radio);
                    //System.out.println("hola");
                    //System.out.println("radio: " + radio + "Y: "+ (centroY-oldPosicionY) );
                }
                if (radioX > 0){
                    x = -radio * Math.cos(angulo+desfase) + centroX;
                    y = -radio * Math.sin(angulo+desfase) + centroY;
                }else{
                    x = -radio * Math.cos(desfase + angulo) + centroX;
                    y = radio * Math.sin( desfase +angulo) + centroY;
                }
            }
            if(Math.abs((centroX-oldPosicionX)/radio) > Math.abs((centroY-oldPosicionY)/radio)  ){
                if (radio != 0){
                    desfase =(float) Math.acos((centroY-oldPosicionY)/radio);
                    //System.out.println("radio: " + radio + "Y: "+ (centroY-oldPosicionY) );
                }
                 if (radioY>0){
                     x = radio * Math.sin(angulo +desfase) + centroX;
                     y = -radio * Math.cos(angulo +desfase) + centroY;
                 }else{
                     x = -radio * Math.sin(desfase+ angulo ) + centroX;
                     y = -radio * Math.cos(desfase + angulo) + centroY;
                 }
            }
            //cuando solo hay que moverse en el eje y
            if (radioX==0){
                if (radio != 0){
                    desfase =(float) Math.acos((centroY-oldPosicionY)/radio);
                }
                if (radioY>0){
                    x = radio * Math.sin(angulo +desfase) + centroX;
                    y = -radio * Math.cos(angulo +desfase) + centroY;
                }else{
                    x = -radio * Math.sin(desfase+ angulo ) + centroX;
                    y = -radio * Math.cos(desfase + angulo) + centroY;
                }
            }
            //cuando solo hay que moverse en el eje X
            if (radioY==0){
                if (radio != 0){
                    desfase =(float) Math.acos((centroX-oldPosicionX)/radio);
                }
                if (radioX > 0){
                    x = -radio * Math.cos(angulo+desfase) + centroX;
                    y = radio * Math.sin(angulo+desfase) + centroY;
                }else{
                    x = -radio * Math.cos(desfase + angulo) + centroX;
                    y = -radio * Math.sin( desfase +angulo) + centroY;
                }
            }
            /*System.out.println("posicion x:" + x + "posicion y:" + y);
            System.out.println("desfase: " + desfase);
            System.out.println("desfase en x: " + (centroY-oldPosicionY)/radio);
            System.out.println("desfase en y: " + (centroX-oldPosicionX)/radio);*/
            paintFigures.setPosX((float)x,index);
            paintFigures.setPosY((float)y,index);
            paintFigures.requestLayout();

        }
    }

}
