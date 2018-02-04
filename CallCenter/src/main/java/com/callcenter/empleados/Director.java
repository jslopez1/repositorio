/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.empleados;

import com.callcenter.atencion.Dispatcher;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que define al director del callcenter
 * @author Jhon lopez
 */
public class Director implements Dispatcher{

    protected Dispatcher empleado;
     private static Integer cantDirectivos = 2;
  
     /**
      * Recibe la llamada ,revisa que hayan directivos disponibles para atender, 
      * si hay uno disponible lo ocupa un intervalo de 5 a 10 segundos, si no 
      * hay disponible retorna una respuesta nula.
      * @param llamada
      * @return 
      */
    @Override
    public   String dispatchCall(String llamada) {
        
        String respuestaDirector = null;
        try {
            if (cantDirectivos > 0 ) {
                respuestaDirector="Director "+cantDirectivos+" responde afirmativamente a llamada "+ llamada;
                cantDirectivos--;
                TimeUnit.SECONDS.sleep((int)(Math.random()*10+5));
                cantDirectivos++;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestaDirector;
    }
    @Override
    public void setEmpleado(Dispatcher dispatcher) {
        this.empleado=dispatcher;
    }

    @Override
    public Dispatcher getEmpleado() {
        return this.empleado;
    }
    
}
