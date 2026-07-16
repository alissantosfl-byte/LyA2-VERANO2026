/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Perso
 */
public class ArbolAgenteIA {
    //Atributos
    Stack<Nodo> arbolNodo;
    Stack<String> caracter;
        //Identificar entre OPERADORES Y OPERANDOS
    final String espacios="\t";
    final String aritmeticos = "+-*()^=/";
    final String variables="abcdefghijklmnopqrstuvwxyz";
    final String opMultiplica="*";
    private Nodo raiz;
    public String valorToken;
    public int valor;
    private ArrayList<String[]> tripletas = new ArrayList<>();
    
    //30 de junio 2026
    
    String[] temporales={"T1","T2","T3","T4","T5"};
    public static HashMap<String,String> tablaSimbolos;
    //HashMap<String,String> tablaSimbolos;
    HashMap<String,String> erroresSemanticos;
    HashMap<String,String> producciones;
    int paso;
    String r;
    String reglaSemantica;
    
    ArrayList<String>reglasEjecutadas;
    
    public String emu86;// 15 de julio
    
    //costructor
    public ArbolAgenteIA(){
                emu86 = ";SANTOS FLETES ALAM ISRAEL \n"+
                ".MODES SMALL\n"+
                ".STACK\n"+
                ".DATA\n";
                //".CODE\n"
        reglasEjecutadas = new ArrayList<String>();
        tablaSimbolos = new HashMap();
        erroresSemanticos = new HashMap();
        producciones = new HashMap();
        
        arbolNodo = new Stack<Nodo>();
        caracter = new Stack<String>();
        paso=0;
        reglaSemantica=" ";
        r="";
    }//constructor
    
    public String getReglasEjecutadas(){
        String reglasE="";
    for (int i=0; i<reglasEjecutadas.size();i++){
    System.out.println("Reglas ejecutadas"+
            reglasEjecutadas.get(i));
    reglasE+=reglasEjecutadas.get(i)+"\n";
   
    }//for
    System.out.println("Tabla de simbolos:");
    System.out.println(tablaSimbolos);
    return reglasE;
    }//get reglasEjecutadas
    
    public ArrayList<String[]> getTripletas() {
    return tripletas;
}//get tripletas
    
    public void agregaValex(String lexema, String valor){
        
    }//agregaValez- Análisis semántico}
    
    public String regresaValex(String lexema){
        return this.tablaSimbolos.get(lexema);
    }//regresaValex
    
    public void guardar(){//permite construir el arbol
        if (arbolNodo.size() < 2 || caracter.empty ()) return;
        
        paso++;
        r = "r" + paso;
        
        
        Nodo izquierdo = arbolNodo.pop();
        Nodo derecho = arbolNodo.pop();
        String operador = caracter.pop();
        //Investigar que hace peek
        //Permite observar un elemento sin extraerlo o modificarlo
        tripletas.add(new String[]{
            operador,
            derecho.getDato(),
            izquierdo.getDato()
        });
        
        arbolNodo.push(new Nodo(derecho,operador,izquierdo));
        // Optimización: En una sola línea dinámica
            String reglaE = "E.nodo = new Nodo("+operador+",El.nodo,T.nodo)";
            reglasEjecutadas.add("p"+paso+" "+reglaE);            
        
    }//guardar
    
    //METODOS DEL ARBOL
    
    public Nodo crear(String expresion){
        //1.Considerar la expresion como un conjunto de tokens
        StringTokenizer tokenizer;
        String token;
        //0.Inicializar valores para varias ejecuciones
        paso=0;
        reglaSemantica = ""; r = "";
        //Investiga clase StringTokenizer
                //divide una cadena de texto en tokens
        //2.Separacion de tokens de la expresion
        tokenizer = new StringTokenizer(expresion,espacios+aritmeticos+"/",true);
        
        //3.Mientras existan tokens
        while(tokenizer.hasMoreTokens()){            
            //4.Omitir espacios en blanco
            token = tokenizer.nextToken();
            if(espacios.contains(token)) continue;     
            if(!aritmeticos.contains(token)){
                //6.Extraer de la pila los terminos d¿que estaban en parentesis
                arbolNodo.push(new Nodo(token));
                valorToken = JOptionPane.showInputDialog(null, 
                "¿Cual es el valor del token "+token+"?", JOptionPane.QUESTION_MESSAGE);
                
                tablaSimbolos.put(token, valorToken);
                valor = Integer.parseInt(valorToken);
                emu86+=token+" dw "+valor+"\n";//15 julio
                paso++;
                String regla= "T.nodo = new Hoja(id<"+token+">,id.entrada_"+token+")";
                reglasEjecutadas.add("p"+paso+" "+regla);
                
                //preguntar valor del token 
                //y insertar en tabla de simbolos
                //mostrar en consola al finalizar
       
                
                

                
                //insertarSimbolos(token);
            }else if(token.equals("(")) caracter.push(token);
           
            else if(token.equals(")")){
                //7 Tratat tokens que no son parentesis
               // if(token.equals(")")){
                    while(!caracter.empty()&& !caracter.peek().equals("("))
                        guardar();
                       //     if(!caracter.empty())
                       //         System.out.println("");
            if (!caracter.empty())
                    caracter.pop();
                //}//if
            }else{
                    //no puedo cambiar a contains porque es una comparacion y regresa booleano
                    while(!caracter.empty() && !caracter.peek().equals("(")){
                        // Si el operador en el tope tiene mayor o igual prioridad, se procesa primero
                        if (obtenerPrioridad (caracter.peek () ) >= obtenerPrioridad (token))
                            guardar ();
                            
                        else break;
                    }//WHILE
                caracter.push(token);//guardar el token
            }//if else
            //8.Guardar el token
        }//while-tokenizer- hasMoreTokens
        while(!caracter.empty()){
            if(caracter.peek().equals("("))//El caracter tiene simbolo de apertura
                caracter.pop();
            else{
                guardar();//aqui se insertan los operadores
                raiz= (Nodo) arbolNodo.peek();
            }//if
        }//while !caracter.empty
        sintetizar(raiz);
        return raiz;
    }//crear
    
    private int obtenerPrioridad (String operador) {
        switch (operador) {
                case "^":
                    return 3;
                case "*": case "/":
                    return 2;
                case "+": case "-":
                    return 1;
                case "=":
                    return 0;
                default:
                    return -1; // Para parentesis u otros caracteres
                    
        }//switch
    }//Obtener prioridad
    
    public int sintetizar(Nodo nodo) {

    // Si es hoja
    if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {

        int valor = Integer.parseInt(tablaSimbolos.get(nodo.getDato()));
        nodo.setResultado(String.valueOf(valor));
        return valor;
    }

    int izq = sintetizar(nodo.getIzquierdo());
    int der = sintetizar(nodo.getDerecho());

    int resultado = 0;

    switch(nodo.getDato()){

        case "+":
            resultado = izq + der;
            break;

        case "-":
            resultado = izq - der;
            break;

        case "*":
            resultado = izq * der;
            break;

        case "/":
            resultado = izq / der;
            break;
    }

    nodo.setResultado(String.valueOf(resultado));

    return resultado;
}
    
    public Nodo convertirAGAD(Nodo raizAST) {
        HashMap<String, Nodo> tabla = new HashMap<>();
        return convertir(raizAST, tabla);
    }

    private Nodo convertir(Nodo n, HashMap<String, Nodo> tabla) {
        if (n == null) return null;

        if (n.getIzquierdo() == null && n.getDerecho() == null) {
            String clave = "HOJA#" + n.getDato();
            Nodo existente = tabla.get(clave);
            if (existente != null) return existente; // reutiliza
            tabla.put(clave, n);
            return n;
        }

        // Procesar hijos primero (post-orden): así al llegar al padre
        // ya sabemos si los hijos son nodos compartidos o no.
        Nodo izqNuevo = convertir(n.getIzquierdo(), tabla);
        Nodo derNuevo = convertir(n.getDerecho(), tabla);

        // Reasignar hijos (puede que ahora apunten a nodos ya existentes)
        n.setIzquierdo(izqNuevo);
        n.setDerecho(derNuevo);

        String clave = n.getDato() + "#" 
                     + System.identityHashCode(izqNuevo) + "#" 
                     + System.identityHashCode(derNuevo);

        Nodo existente = tabla.get(clave);
        if (existente != null) return existente; 

        tabla.put(clave, n);
        return n;
    }

    
}//fin clase
