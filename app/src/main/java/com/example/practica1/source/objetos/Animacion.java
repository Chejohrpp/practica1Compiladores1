/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source.objetos;

/**
 *
 * @author sergi
 */
public class Animacion {
    private double desX;
    private double desY;
    private String tipo;

    public Animacion(double desX, double desY, String tipo) {
        this.desX = desX;
        this.desY = desY;
        this.tipo = tipo;
    }
    
    public double getDesX() {
        return desX;
    }

    public double getDesY() {
        return desY;
    }

    public String getTipo() {
        return tipo;
    }
    
    
}
