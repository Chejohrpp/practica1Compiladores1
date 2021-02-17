/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source.objetos;


public class Linea {
    private double posX;
    private double posY;
    private double finX;
    private double finY;
    private String color;

    public Linea(double posX, double posY, double finX, double finY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.finX = finX;
        this.finY = finY;
        this.color = color;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getFinX() {
        return finX;
    }

    public double getFinY() {
        return finY;
    }

    public String getColor() {
        return color;
    }
    
}
