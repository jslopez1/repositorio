/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.atencion;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.callcenter.empleados.Empleado;
import com.callcenter.empleados.Operador;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 * Clase para probar la funcionalidad de call center.
 * @author Jhon Lopez
 */
@RunWith(ConcurrentTestRunner.class)
public class DispatcherTest {

    /*
        Mantiene la instancia de la clase que se encarga de administrar la 
        cadena de responsabilidad.
    */
    private final static Dispatcher INSTANCE = new Empleado();
    private final static int THREAD_COUNT = 11;
    private static List<String> llamadas;
    private static List<String> respuestas= new ArrayList<>();

    public DispatcherTest() {
    }
    /*
        Se llena el list de llamadas para comprobar que si se de atencion
        a todas las llamadas.
    */
    @BeforeClass
    public static void setUpClass() {
        llamadas = new ArrayList<>();
        llamadas.add("llamada 1");
        llamadas.add("llamada 2");
        llamadas.add("llamada 3");
        llamadas.add("llamada 4");
        llamadas.add("llamada 5");
        llamadas.add("llamada 6");
        llamadas.add("llamada 7");
        llamadas.add("llamada 8");
        llamadas.add("llamada 9");
        llamadas.add("llamada 10");
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }
    /*
        Valida que todas las llamadas hayan sido atendidas.
    */
    @After
    public void tearDown() {
        Integer contador=0;
       
        for (String llamada : llamadas) {
            for (String respuesta : respuestas) {
                if(isContain(respuesta,llamada)){
                    contador++;
                }
            }
        }
           assertEquals(new Long(llamadas.size()),new Long(contador));
     
             
        
       
    }

    /**
     * Genera varios thread para probar concurrentemente la clase que recibe 
     * llamadas.
     */
    @Test
    @ThreadCount(THREAD_COUNT)
    public  void  testLlamadas() {
        
            Thread thread=Thread.currentThread();

            if(thread.getName().equals("Thread-1")){
                String result = INSTANCE.dispatchCall("llamada 1");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-2")){
                String result = INSTANCE.dispatchCall("llamada 2");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-3")){
                String result = INSTANCE.dispatchCall("llamada 3");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-4")){
                String result = INSTANCE.dispatchCall("llamada 4");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-5")){
                String result = INSTANCE.dispatchCall("llamada 5");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-6")){
                String result = INSTANCE.dispatchCall("llamada 6");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-7")){
                String result = INSTANCE.dispatchCall("llamada 7");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-8")){
                String result = INSTANCE.dispatchCall("llamada 8");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-9")){
                String result = INSTANCE.dispatchCall("llamada 9");
                respuestas.add(result);
                System.out.println(result);
            }
            if(thread.getName().equals("Thread-10")){
                String result = INSTANCE.dispatchCall("llamada 10");
                respuestas.add(result);
                System.out.println(result);
            }
    }
    /**
     * Metodo que comprueba que un texto fuente contenga el texto completo de 
     * valor.
     * @param fuente
     * @param valor
     * @return 
     */
    private static boolean isContain(String fuente, String valor){
         String pattern = "\\b"+valor+"\\b";
         Pattern p=Pattern.compile(pattern);
         Matcher m=p.matcher(fuente);
         return m.find();
    }


}
