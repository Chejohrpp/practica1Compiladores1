/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source.objetos;


public class Rectangulo {
    private double posX;
    private double posY;
    private double alto;
    private double ancho;
    private String color;

    public Rectangulo(double posX, double posY, double alto, double ancho, String color) {
        this.posX = posX;
        this.posY = posY;
        this.alto = alto;
        this.ancho = ancho;
        this.color = color;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getAlto() {
        return alto;
    }

    public double getAncho() {
        return ancho;
    }

    public String getColor() {
        return color;
    }
    
    
}
