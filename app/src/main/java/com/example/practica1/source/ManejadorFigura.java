/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source;
import com.example.practica1.source.objetos.*;

import java.io.Serializable;

public class ManejadorFigura implements Serializable {
    private int id;
    private Circulo circulo;
    private Rectangulo rectangulo;
    private Poligono poligono;
    private Linea linea;
    private Animacion animacion;
    private Cuadrado cuadrado;

    public ManejadorFigura(int id, Circulo circulo) {
        this.id = id;
        this.circulo = circulo;
    }

    public ManejadorFigura(int id, Rectangulo rectangulo) {
        this.id = id;
        this.rectangulo = rectangulo;
    }

    public ManejadorFigura(int id, Poligono poligono) {
        this.id = id;
        this.poligono = poligono;
    }

    public ManejadorFigura(int id, Linea linea) {
        this.id = id;
        this.linea = linea;
    }

    public ManejadorFigura(int id, Animacion animacion) {
        this.id = id;
        this.animacion = animacion;
    }

    public ManejadorFigura(int id, Cuadrado cuadrado) {
        this.id = id;
        this.cuadrado = cuadrado;
    }

    public Animacion getAnimacion() {
        return animacion;
    }    

    public int getId() {
        return id;
    }

    public Circulo getCirculo() {
        return circulo;
    }

    public Rectangulo getRectangulo() {
        return rectangulo;
    }

    public Poligono getPoligono() {
        return poligono;
    }

    public Linea getLinea() {
        return linea;
    }

    public Cuadrado getCuadrado() {
        return cuadrado;
    }
}
