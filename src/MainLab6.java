
import java.text.DecimalFormat;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
/*
 * Universdidad del Valle de Guatemala
 * Diseño de lenguajes de programacion
 * Compiladores
 *
 * @author Sebastian Galindo, Carnet: 15452
 */
public class
MainLab6{

    public static void main(String[] args) {
        Operaciones operacion = new Operaciones();
        RegExConverter sC = new RegExConverter();
        CocolStructure structure = new CocolStructure();
        String regexp="";
        String regexpPF;
        String cadenaExtendida;
        String regexPFestendida;

/* ***********************************Construccion del automata de ident************************************************/

        //Creando la cadena que se ingresara para crear el automata de ident
        regexp = structure.getIdent();

        //Creando cadena Extendida para la generación directa de AFD's y convirtiendola a formato Postfix
        regexpPF = sC.infixToPostfix(regexp);
        cadenaExtendida="("+regexp+")#";
        regexPFestendida=sC.infixToPostfix(cadenaExtendida);

        //Obteniedno la hoja final del arbol sintactico
        Hoja n = operacion.generarArbolSintactico(regexPFestendida);

        //Creando el automata
        AutomataDFA ident = new AutomataDFA();
        ArrayList<String> alfabetoIdent = operacion.generateAlphabet(regexpPF);
        operacion.construccionDirecta(ident, n, alfabetoIdent);
        operacion.nombrarNodos(ident);
        operacion.estadoFinal(ident);

/* ***********************************Construccion del automata de Set***********************************************/

        //Creando la cadena que se ingresara para crear el automata de number
        regexp = structure.getSet();


        //Creando cadena Extendida para la generación directa de AFD's y convirtiendola a formato Postfix
        regexpPF = sC.infixToPostfix(regexp);

        cadenaExtendida="("+regexp+")#";
        regexPFestendida=sC.infixToPostfix(cadenaExtendida);
        //Obteniedno la hoja final del arbol sintactico
        n = operacion.generarArbolSintactico(regexPFestendida);

        //Creando el automata
        AutomataDFA set = new AutomataDFA();
        ArrayList<String> alfabetoSet = operacion.generateAlphabet(regexpPF);
        operacion.construccionDirecta(set, n, alfabetoSet);
        operacion.nombrarNodos(set);
        operacion.estadoFinal(set);


/* ****************************************Construccion del automata de String******************************************/

        //Creando la cadena que se ingresara para crear el automata de number
        regexp = structure.getString();

        //Creando cadena Extendida para la generación directa de AFD's y convirtiendola a formato Postfix
        regexpPF = sC.infixToPostfix(regexp);
        cadenaExtendida="("+regexp+")#";
        regexPFestendida=sC.infixToPostfix(cadenaExtendida);

        //Obteniedno la hoja final del arbol sintactico
        n = operacion.generarArbolSintactico(regexPFestendida);

        //Creando el automata
        AutomataDFA string = new AutomataDFA();
        ArrayList<String> alfabetoString = operacion.generateAlphabet(regexpPF);
        operacion.construccionDirecta(string, n, alfabetoString);
        operacion.nombrarNodos(string);
        operacion.estadoFinal(string);

/* ******************************************LEYENDO EL ARCHIVO TXT*****************************************************/
/*
        String path = System.getProperty("user.dir") + "\\cocol.txt";
        ArrayList<String> fileContent = operacion.fileReader(path);

/* ******************************************RECORRIENDO EL ARCHIVO*****************************************************/
/*

        operacion.Errors(operacion.recorrido(fileContent, ident, set,string));
        //operacion.Errors(operacion.recorrido(fileContent, ident, number, string, charr));

/* ******************************************Ingresando el archivo**************************************************************/
        //Solicitando al usuario que ingrese la expresion regular
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo con la especificación léxica en Cocol: ");
        String nombreDelArchivo=scanner.nextLine();

        String path1 = System.getProperty("user.dir") + "\\"+nombreDelArchivo+"";
        ArrayList<String> fileContent2 = operacion.fileReader(path1);
        int errorNumber = operacion.recorrido(fileContent2, ident, set, string);
        operacion.Errors(errorNumber);
        if(errorNumber==0){
            operacion.lexerGenerator(fileContent2);

        }
    }
}
