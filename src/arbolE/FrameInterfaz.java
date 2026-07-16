/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package arbolE;

import static arbolE.PanelArbol.colorLinea;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Perso
 */
public class FrameInterfaz extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrameInterfaz.class.getName());
    public String nPolaca ="";
    int temp;
    String izquierdo,derecho; // 15 de julio
    String emuLocal="";
    int Contador;
    FrameCuadruplos cuadruplos;
    FrameTripletas frameTripletas;

    /**
     * Creates new form FrameInterfaz
     */
    public FrameInterfaz() {
        initComponents();
        
        nPolaca = "";
        temp=0;
        
        izquierdo="";
        derecho=""; // 15 de julio
        
        
        
        
    }//Constructor
    
    public void generarEmutasm(String emu,int i){
        try{
            FileWriter escritor =new FileWriter("e"+i+".asm");
            escritor.write(emu);
            escritor.close();
            System.out.println("Archivo creado exitosamente");
        }catch (Exception e){
            System.out.println("Ha ocurrido un error al crear el archivo");
        }
    }
    
    public void sonido(){
//sonido
    try {
        File sonido = new File("C:\\Users\\Perso\\OneDrive\\Documentos\\NetBeansProjects\\ArbolExpresiones\\src\\arbolE\\new-notification-022-370046.wav");
            if (sonido.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(sonido);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start(); 
            } else {
                        showMessageDialog(null, "No se encontró el archivo de sonido.");
                    }
    } catch (Exception e) {
                    e.printStackTrace();
                    showMessageDialog(null, "Error al reproducir el sonido.");
                }
        
    }
    
    public void inOrden (Nodo n){
        if (n!=null){
            inOrden(n.getDerecho());
            jTxtInOrden.append(n.getDato()+"\n");
            
            inOrden(n.getIzquierdo());
            
            //15 de julio
            
            switch(n.getDato()){
                case "+":System.out.println("ADD");
                      izquierdo=n.getIzquierdo().getDato();
                      derecho=n.getDerecho().getDato();
                      System.out.println("izq: " + izquierdo);
                      System.out.println("der: " + derecho);
                      emuLocal += "MOV AX, "+n.getIzquierdo().getDato()+"\n";
                      emuLocal += "MOV BX, "+n.getDerecho().getDato()+"\n";
                      emuLocal += "ADD AX,BX"+"\n\n";
                break;
                case "-":System.out.println("SUB");
                      izquierdo=n.getIzquierdo().getDato();
                      derecho=n.getDerecho().getDato();
                break;
                case "/":System.out.println("DIV");
                      izquierdo=n.getIzquierdo().getDato();
                      derecho=n.getDerecho().getDato();
                break;
                case "*":System.out.println("MUL");
                      izquierdo=n.getIzquierdo().getDato();
                      derecho=n.getDerecho().getDato();
                break;
                default: System.out.println("Que ha pasado?");
                break;
            }
        }//if
        
    }//in Order
    
    public void preOrden (Nodo n){
        if (n!=null){
                jTxtPreOrden.append(n.getDato()+"\n");
                nPolaca+=jNotacionPolaca.getText()+n.getDato()+" ";
                jNotacionPolaca.setText(jNotacionPolaca.getText()+
                        n.getDato()+" ");
                preOrden(n.getDerecho());
                preOrden(n.getIzquierdo());
                

        }//if
    }//preOrden
    
    public void postOrden(Nodo n){
        if(n!=null){
            postOrden(n.getDerecho());
            postOrden(n.getIzquierdo());
            jTxtPostOrden.append(n.getDato()+"\n");

            
        }//if
        
    }//postOrden

    public void generarTexto(){    
        String textoAGuardar = jTextArea4.getText();

        try {

            FileWriter archivo = new FileWriter("archivo.txt");
            BufferedWriter escritor = new BufferedWriter(archivo);

            escritor.write(textoAGuardar);

            escritor.close();
            System.out.println("Archivo guardado exitosamente.");
    
            } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }//Generar texto
    
    public void AbrirLink(){
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI("https://youtu.be/TfMEzP34LcA"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//abrirLink
    
    public void intermedio (Nodo n){
        if(n!=null){
            intermedio(n.getIzquierdo());
            intermedio(n.getDerecho());
            if(n.getIzquierdo()==null && n.getDerecho()==null){
                n.setLugar(n.getDato()+" ");
                n.setCodigoIntermedio(" ");
                               
            }else{
                if(n.getDato().equals("+")||n.getDato().equals("*")
                        ||n.getDato().equals("-")||n.getDato().equals("/")){
                    temp++;
                    n.setLugar("T"+temp);
                    Nodo izquierdo = n.getIzquierdo();
                    Nodo derecho = n.getDerecho();
                    String codigoI = "";
                    codigoI = izquierdo.getCodigoIntermedio()+" "
                            +derecho.getCodigoIntermedio()+" "
                            +n.getLugar()+" = "+izquierdo.getLugar()
                            +" "+n.getDato()+" "+derecho.getLugar();
                n.setCodigoIntermedio(codigoI+"\n");
            }else{
                    if(n.getDato().equals("=")){
                        String codigoI="";
                        Nodo izquierdo = n.getIzquierdo();
                        Nodo derecho = n.getDerecho();
                        codigoI = derecho.getDato()+" "+
                                izquierdo.getLugar()+" = T"+temp+"\n";
                        n.setCodigoIntermedio(codigoI);                        
                    }//equals =                
                }//equals +,-,*,/
            }//getDerecho, getIzquierdo
    }//n!=null
}//Intermedio


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtInOrden = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTxtPreOrden = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTxt3Direcciones = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTxtPostOrden = new javax.swing.JTextArea();
        btnAgenteIA = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jNotacionPolaca = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Árbol de expresiones- LyA2");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arbolE/85e9e97c-6a29-421e-ba14-0dcff8046aa6.png"))); // NOI18N

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arbolE/escudo_itt_grande2.png"))); // NOI18N
        jLabel12.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Expresión");

        jTextField1.setBackground(new java.awt.Color(255, 255, 153));

        jButton1.setText("Compila");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jTxtInOrden.setColumns(20);
        jTxtInOrden.setRows(5);
        jScrollPane1.setViewportView(jTxtInOrden);

        jTxtPreOrden.setColumns(20);
        jTxtPreOrden.setRows(5);
        jScrollPane2.setViewportView(jTxtPreOrden);

        jTxt3Direcciones.setColumns(20);
        jTxt3Direcciones.setRows(5);
        jScrollPane3.setViewportView(jTxt3Direcciones);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jTxtPostOrden.setColumns(20);
        jTxtPostOrden.setRows(5);
        jScrollPane5.setViewportView(jTxtPostOrden);

        btnAgenteIA.setText("Agente IA");
        btnAgenteIA.addActionListener(this::btnAgenteIAActionPerformed);

        jButton5.setText("Optimiza Inter...");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton11.setText("Abrir Emu");
        jButton11.addActionListener(this::jButton11ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgenteIA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnAgenteIA)
                    .addComponent(jButton5)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Pre Orden");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("In Orden");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Post Orden");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Reglas Semanticas");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Código 3 direcciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(94, 94, 94)
                .addComponent(jLabel3)
                .addGap(91, 91, 91)
                .addComponent(jLabel4)
                .addGap(80, 80, 80)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jNotacionPolaca.addActionListener(this::jNotacionPolacaActionPerformed);

        jButton2.setText("Código 3 direcciones");

        jButton3.setText("Clean");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Tabla de símbolos");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Notación Polaca");

        jButton6.setText("Cuadruplos");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jButton7.setText("Tripletas");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        jButton8.setText("GAD");
        jButton8.addActionListener(this::jButton8ActionPerformed);

        jButton9.setText("Direcciones");
        jButton9.addActionListener(this::jButton9ActionPerformed);

        jButton10.setText("Asignacion individual");
        jButton10.addActionListener(this::jButton10ActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(34, 34, 34)
                .addComponent(jNotacionPolaca, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton6)
                    .addComponent(jButton9))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton7))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton8)))
                    .addComponent(jButton10))
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jNotacionPolaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String datos="";
        //temp=0;
        Arbol a = new Arbol();
        datos = jTextField1.getText();
        
        Nodo arbolExpresion = a.crear(datos); //enviar los datos al
        jTextArea4.append(a.getReglasEjecutadas());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAgenteIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgenteIAActionPerformed
        // TODO add your handling code here:
String datos="";
jTextArea4.setText ("") ;

ArbolAgenteIA arbol = new ArbolAgenteIA();
datos = jTextField1.getText();
Nodo arbolExpresion = arbol. crear (datos); //Enviar los datos al arbol- expresion
jTextArea4.append (arbol.getReglasEjecutadas () ) ;

JFrame ventana = new JFrame ("Visualizador de Arboles - LyA2");
PanelArbol panel = new PanelArbol (arbolExpresion);

ventana.add (panel) ;
ventana.setSize(600, 400);
ventana.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE) ;
ventana.setLocationRelativeTo (null); // Centrar en pantalla
ventana.setVisible (true) ;

//
        color ventanaColor = new color(panel);
        ventanaColor.setVisible(true);
        
        preOrden(arbolExpresion);
        postOrden(arbolExpresion);
        inOrden(arbolExpresion);
        intermedio(arbolExpresion);
        generarTexto();
        arbol.emu86+=".CODE \n"+
                "MOV AX,@DATA \n"+
                "MOV DS,AX \n"; //15 julio
        String finalEmu=arbol.emu86+this.emuLocal;
        finalEmu+="\n MOV AX,4C00H \n"+
                "INT 21H \n end";
        showMessageDialog(null,finalEmu);
        
        Contador++;
        generarEmutasm(finalEmu,Contador);
        sonido();
        
        jTxt3Direcciones.append(arbolExpresion.getCodigoIntermedio());
        

        
        

    }//GEN-LAST:event_btnAgenteIAActionPerformed

    private void jNotacionPolacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNotacionPolacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNotacionPolacaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        AbrirLink();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String datos="";
        ArbolAgenteIA arbol = new ArbolAgenteIA();
        datos = jTextField1.getText();
        Nodo arbolExpresion = arbol. crear (datos);
        preOrden(arbolExpresion);
        postOrden(arbolExpresion);
        inOrden(arbolExpresion);
        intermedio(arbolExpresion);
        cuadruplos = new FrameCuadruplos(arbolExpresion);
        cuadruplos.setVisible(true);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
                String datos="";
        ArbolAgenteIA arbol = new ArbolAgenteIA();
        datos = jTextField1.getText();
        Nodo arbolExpresion = arbol. crear (datos);
        preOrden(arbolExpresion);
        postOrden(arbolExpresion);
        inOrden(arbolExpresion);
        intermedio(arbolExpresion);
        frameTripletas = new FrameTripletas(arbol.getTripletas());
        frameTripletas.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String datos = jTextField1.getText();

ArbolAgenteIA arbol = new ArbolAgenteIA();
Nodo arbolExpresion = arbol.crear(datos);

JFrame ventana = new JFrame("Visualizador GAD");

PanelGrafo panel = new PanelGrafo(
        arbol.convertirAGAD(arbolExpresion),
        java.awt.Color.CYAN,
        20,
        java.awt.Color.BLACK,
        2f
);

ventana.add(panel);
ventana.setSize(600, 400);
ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
ventana.setLocationRelativeTo(null);
ventana.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
          FrameDescripcion ventanaIntermedio = new FrameDescripcion();
    ventanaIntermedio.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        SSADiagrama ventana = new SSADiagrama();
ventana.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        try {

    File archivo = new File("C:\\Users\\Perso\\OneDrive\\Documentos\\NetBeansProjects\\ArbolExpresiones\\e1.asm"); 

        Desktop.getDesktop().open(archivo);
 
} catch (IOException ex) {
    JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + ex.getMessage());
}
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrameInterfaz().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenteIA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNotacionPolaca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTxt3Direcciones;
    private javax.swing.JTextArea jTxtInOrden;
    private javax.swing.JTextArea jTxtPostOrden;
    private javax.swing.JTextArea jTxtPreOrden;
    // End of variables declaration//GEN-END:variables
}
