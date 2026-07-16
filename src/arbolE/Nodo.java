/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

/**
 *
 * @author ALAM
 * CLASE PARA ARMAR EL ARBOL
 * nombre
 * parte 1. analisis sintactico
 * 2. analisis semantico
 * 3.codigo intermedio
 * 4.codigo objeto
 */
public class Nodo {
    //atributos
    private String dato;
    private Nodo padre;
    private Nodo izquierdo;
    private Nodo derecho;
    private String codigoIntermedio;
    private String lugar; //Para los temporales
    private String lineaColor;
    private String nodoColor;
    private String resultado;
    
    
    public Nodo(String dato){//Informacion
        this.dato=dato;
    }//constructor

    public Nodo(Nodo derecho,String dato,Nodo izquierdo) {
        this.dato = dato;
        this.padre = null;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.codigoIntermedio = "";
        this.lugar = "";
    }
    
    

    public String getDato() {
        return dato;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public String getCodigoIntermedio() {
        return codigoIntermedio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public void setCodigoIntermedio(String codigoIntermedio) {
        this.codigoIntermedio = codigoIntermedio;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getLineaColor() {
        return lineaColor;
    }

    public void setLineaColor(String lineaColor) {
        this.lineaColor = lineaColor;
    }

    public String getNodoColor() {
        return nodoColor;
    }

    public void setNodoColor(String nodoColor) {
        this.nodoColor = nodoColor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    
    
    
    
    
}
