package com.example.practica1.source.objetos;

public class Cuadrado {
    private double posX;
    private double posY;
    private double tamLado;
    private String color;

    public Cuadrado(double posX, double posY, double tamLado, String color) {
        this.posX = posX;
        this.posY = posY;
        this.tamLado = tamLado;
        this.color = color;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getTamLado() {
        return tamLado;
    }

    public String getColor() {
        return color;
    }
}
