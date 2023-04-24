/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.Practica08.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carte
 */
@RestController
@RequestMapping("/api")
public class EmpleadoController {
    private static List<DTOEmpleado> listemp = new ArrayList<DTOEmpleado>();
    @GetMapping("/msg")
    public String holaMundo() {
        return "hola mundo";
    }
    
   @GetMapping("/empleado/{id}")
    public DTOEmpleado obtenerEmpleado(@PathVariable("id") long id){
        DTOEmpleado emp = null;
        for (DTOEmpleado emp1:listemp){
            if (emp1.getClave()==id){
                emp=emp1;
            }
        }
       return emp;
    }
    
     @GetMapping("/empleado")
     public List<DTOEmpleado> obtenerTodosLosEmpleados(){
        return listemp;
     }
     
    @PostMapping
    public DTOEmpleado saveEmpleado(@Validated @RequestBody DTOEmpleado emp) {//se crean los empelados
        DTOEmpleado emp1=new DTOEmpleado();
        emp1.setClave(emp.getClave());
        emp1.setNombre(emp.getNombre());
        emp1.setDireccion(emp.getDireccion());
        emp1.setTelefono(emp.getTelefono());
        
        listemp.add(emp1);
        
        return emp1;
    }
    
    @PutMapping("/empleado/actualizar/{id}")
    public DTOEmpleado updateEmpleado(@PathVariable ("id") long id, @RequestBody DTOEmpleado emp) {
        DTOEmpleado emp2=new DTOEmpleado();
        for (DTOEmpleado emp1:listemp){
            if (emp1.getClave()==id){
                emp1.setClave(emp.getClave());
                emp1.setNombre(emp.getNombre());
                emp1.setDireccion(emp.getDireccion());
                emp1.setTelefono(emp.getTelefono());
                emp2=emp1;
            }
        }
       return emp2;
    }
    
    @DeleteMapping("/empleados/borrar/{id}")
    public boolean deleteEmpleado(@PathVariable ("id") long id){
        boolean bandera = false;
        Iterator <DTOEmpleado> emp=listemp.iterator();
        while (emp.hasNext()){
            if(emp.next().getClave()==id){
                emp.remove();
                bandera=true;
            }
        }
       return bandera;
    }
    
   
}
