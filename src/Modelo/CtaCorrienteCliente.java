/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author maxid
 */
public class CtaCorrienteCliente {

    public CtaCorrienteCliente(int idCtaCliente, String fecha, String descripcion, double debe, double haber, double saldo, int idCliente) {
        this.idCtaCliente = idCtaCliente;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.debe = debe;
        
        this.haber = haber;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public CtaCorrienteCliente(String fecha, String descripcion, double debe, double haber,double saldo,int idCliente) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }
    
    

    public int getIdCtaCliente() {
        return idCtaCliente;
    }

    public void setIdCtaCliente(int idCtaCliente) {
        this.idCtaCliente = idCtaCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return  descripcion;
    }

  
    
    
    
    private int idCtaCliente;
   private String fecha;
   private String descripcion;
   private double debe;
   private double haber;
   private double saldo;
   private int idCliente;
   
   
    
    
}
