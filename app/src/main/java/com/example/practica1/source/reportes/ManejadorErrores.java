/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.practica1.source.reportes;

/**
 *
 * @author sergi
 */
public class ManejadorErrores {
    private String lexema;
    private int linea;
    private int columna;
    private String tipo;
    private String desc;

    public ManejadorErrores(String lexema, int linea, int columna, String tipo, String desc) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.desc = desc;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDesc() {
        return desc;
    }
    
    
}
