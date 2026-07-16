/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Perso
 */
public class PanelArbol extends JPanel {
    private final Nodo raiz;
    private final int RADIO = 20;
    //private final int 
    private final int ESPACIO_VERTICAL = 60;
    public static String colorLinea="";
    public static String colorContorno="";
    private Color colorNodo = new Color(173,216,230);
    private float grosorLinea = 2f;
    private HashMap<String, String> tablaSimbolos=ArbolAgenteIA.tablaSimbolos;
    
    
    private static PanelArbol panelArbol;
public class PedirValor {
    public static void main(String[] args) {


    }
    
}



private Color obtenerColor(String color) {
 
    switch (color.toLowerCase()) {
        case "rojo":
            return Color.RED;
        case "azul":
            return Color.BLUE;
        case "verde":
            return Color.GREEN;
        case "negro":
            return Color.BLACK;
        case "gris":
            return Color.GRAY;
        case "amarillo":
            return Color.YELLOW;
        default:
            return Color.BLACK;
    }
}  

    public void setColorLinea(String colorLinea) {
        if (colorLinea != null && !colorLinea.isBlank()) {
            this.colorLinea = colorLinea;
            //obtenerColor(colorLinea);
            repaint(); 
        }
    }

    public void setColorContorno(String colorContorno) {
        if (colorContorno != null && !colorContorno.isBlank()) {
            this.colorContorno = colorContorno;
            //obtenerColor(colorNodo);
            repaint();
        }
    }
    
    public void setColorNodo(int r, int g, int b) {
    colorNodo = new Color(r, g, b);
    repaint();
    
    }
    
    public void setGrosorLinea(float grosor) {
    grosorLinea = grosor;
    repaint();
}
    
    
 
    /*
    public static void actualizarColorLinea(String colorLinea) {
        if (panelArbol != null) {
            panelArbol.setColorLinea(colorLinea);
        }
    }
*/


    public PanelArbol(Nodo raiz) {
        this.raiz = raiz;
        setBackground(Color.WHITE);
    }
   
    /*
    public PanelArbol(Nodo raiz, HashMap<String, String> tablaSimbolos) {
        this.raiz = raiz;
        this.tablaSimbolos = tablaSimbolos;
        setBackground(Color.WHITE);
    }
*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            // LINEAS...MODIFICAR
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // INICIA DESDE EL CENTRO SUPERIOR
            dibujarNodo(g2, raiz, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics2D g, Nodo nodo, int x, int y, int espacioHorizontal) {
       
        if (nodo == null) return;
        
        
        // Dibujar NODOS IZQUIERDO Y DERECHO 
        //g.setColor(Color.BLACK);
        g.setColor(obtenerColor(colorLinea));
        g.setStroke(new BasicStroke(grosorLinea)); 
        if (nodo.getDerecho() != null) {
            g.drawLine(x, y, x - espacioHorizontal, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.getDerecho(), x - espacioHorizontal,
                    y + ESPACIO_VERTICAL, espacioHorizontal / 2);
        }
        if (nodo.getIzquierdo() != null) {
            g.drawLine(x, y, x + espacioHorizontal, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.getIzquierdo(), x 
                    + espacioHorizontal, y + ESPACIO_VERTICAL, espacioHorizontal / 2);
        }
        

        // FORMATO DEL NODO
        
        //g.setColor(new Color(173, 216, 230));
        g.setColor(colorNodo);
       // g.fillOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);
        g.fill3DRect(x -20, y - 20, 50+WIDTH,50+HEIGHT, true);
        // g.setColor(Color.black);
        g.setColor(obtenerColor(colorContorno));
        //g.drawOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);
        g.draw3DRect(x - 20, y - 20, 100+WIDTH ,50+HEIGHT, true);
       

        //TEXTO CENTRADO DEL NODO
        g.setColor(obtenerColor(colorLinea));
        //g.setColor(Color.black);
        FontMetrics fm = g.getFontMetrics();//obtiene ancho y alto
        int anchoTexto = fm.stringWidth(nodo.getDato());//mide ancho del texto
        int altoTexto = fm.getAscent();//alto texto
        g.drawString(nodo.getDato(), x - (anchoTexto / 2), y + (altoTexto / 4));

        String valor = tablaSimbolos.get(nodo.getDato());

        if (valor != null) {
        g.drawString(valor, x + 40, y + (altoTexto / 4));
        }
        
        if (nodo.getResultado() != null) {
        g.drawString(nodo.getResultado(), x + 40, y + (altoTexto / 4));
        }

        //g.drawString(String.valueOf(nodo.token), x + 25, y + 5);
    }//dibujarNodo
    
}//FIN CLASE es e
