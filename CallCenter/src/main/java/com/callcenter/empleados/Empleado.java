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
 * Clase encargada de manejar el envio de llamadas a los empleados
 * correspondientes.
 *
 * @author Jhon Lopez
 */
public class Empleado implements Dispatcher {

    protected Dispatcher empleado;

    @Override
    public void setEmpleado(Dispatcher dispatcher) {
        this.empleado = dispatcher;
    }

    @Override
    public Dispatcher getEmpleado() {
        return this.empleado;
    }
    /**
     * Inicializa la cadena de responsabilidad
     * @param llamada
     * @return 
     */
    @Override
    public String dispatchCall(String llamada) {
        String respuesta="Lo sentimos los operadores se encuentran ocupados y no pueden atender la llamada "+llamada;
        try {
            Operador operador = new Operador();
            this.setEmpleado(operador);
            Supervisor supervisor = new Supervisor();
            operador.setEmpleado(supervisor);
            Director director = new Director();
            supervisor.setEmpleado(director);
            /*En este codigo trato de solventar el problema de que hayan mas 
              llamadas que empleados que los puedan atender.
            
              En caso de que la llamada no haya sido respondida doy un tiempo de 
              espera y vuelvo a intentar enviarlo a un operador lo que reinicia
              la cadena de responsabilidad.
                
               Si definitivamente no hay quien pueda atender envio el mensaje:
                "No fue posible atender la llamada".
             */
            respuesta = empleado.dispatchCall(llamada);
            if (respuesta == null || respuesta.isEmpty()) {

                TimeUnit.SECONDS.sleep(15);
                
                this.setEmpleado(operador);
                respuesta=empleado.dispatchCall(llamada);
                if(respuesta==null){
                   
                    respuesta="No fue posible atender la llamada "+llamada;
                }

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

}
