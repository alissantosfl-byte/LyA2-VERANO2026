/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolAlam;

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
    
}
