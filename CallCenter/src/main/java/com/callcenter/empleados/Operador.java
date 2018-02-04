/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.empleados;

import com.callcenter.atencion.Dispatcher;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calse que define al operador del call center.
 * @author Jhon Lopez
 */
public class Operador implements Dispatcher {

    private static Integer cantOperadores = 6;
    protected Dispatcher empleado;

    /**
      * Recibe la llamada ,revisa que hayan operadores disponibles para atender, 
      * si hay uno disponible lo ocupa un intervalo de 5 a 10 segundos, si no 
      * hay disponible envia la solicitud al supervisor.
      * @param llamada
      * @return 
      */
    @Override
    public  String  dispatchCall(String llamada) {
        String respuestaOperador = "Operador "+cantOperadores+" responde afirmativamente a llamada "+ llamada;
        try {
            if (cantOperadores > 0) {
                cantOperadores--;
                TimeUnit.SECONDS.sleep((int)(Math.random()*10+5));
                cantOperadores++;
            } else {
                if(empleado==null){
                   empleado=new Supervisor();
               }
                return empleado.dispatchCall(llamada);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestaOperador;
    }

    @Override
    public void setEmpleado(Dispatcher dispatcher) {
        this.empleado = dispatcher;
    }

    @Override
    public Dispatcher getEmpleado() {
        return this.empleado;
    }

}
