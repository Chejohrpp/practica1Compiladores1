/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source.objetos;



public class Circulo {
    private double posX;
    private double posY;
    private double radio;
    private String color;

    public Circulo(double posX, double posY, double radio, String color) {
        this.posX = posX;
        this.posY = posY;
        this.radio = radio;
        this.color = color;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRadio() {
        return radio;
    }

    public String getColor() {
        return color;
    }
    
    
    
}
