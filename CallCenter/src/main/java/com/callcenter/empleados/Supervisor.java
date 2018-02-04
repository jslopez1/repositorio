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
 * Clase que define al supervisor del callcenter
 *
 * @author Jhon Lopez
 */
public class Supervisor implements Dispatcher {

    protected Dispatcher empleado;

    private static Integer cantSupervisores = 2;

    /**
     * Recibe la llamada ,revisa que hayan supervisores disponibles para atender,
     * si hay uno disponible lo ocupa un intervalo de 5 a 10 segundos, si no hay
     * disponible envia la solicitud al Director.
     *
     * @param llamada
     * @return
     */
    @Override
    public String dispatchCall(String llamada) {
        String respuestaSupervisor = "Supervisor " + cantSupervisores + " responde afirmativamente a llamada " + llamada;
        try {
            if (cantSupervisores > 0) {
                cantSupervisores--;
                TimeUnit.SECONDS.sleep((int) (Math.random() * 10 + 5));
                cantSupervisores++;
            } else {
                if (empleado == null) {
                    empleado = new Director();
                }
                return empleado.dispatchCall(llamada);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuestaSupervisor;
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
