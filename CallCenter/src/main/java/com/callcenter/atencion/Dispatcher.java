/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.atencion;

/**
 *
 * @author hernandez
 */
public  interface Dispatcher {
   public void setEmpleado(Dispatcher dispatcher);
   public Dispatcher getEmpleado();
    
    public  String dispatchCall(String llamada);
}
