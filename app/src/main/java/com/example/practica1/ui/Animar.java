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

        }
    }

}
