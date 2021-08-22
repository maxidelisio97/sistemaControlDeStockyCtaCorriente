/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
/**
 *
 * @author maxid
 */
public class CtaCorrienteProveedor {
    
    private int idCtaProveedor;
    private Date fecha;
    private String descripcion;
    private double debe;
    private double haber;
    private double saldo;
    private int idProveedor;
   
 public CtaCorrienteProveedor(int idCtaProveedor, Date fecha, String descripcion, double debe, double haber, double saldo, int idProveedor) {
        this.idCtaProveedor = idCtaProveedor;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.idProveedor = idProveedor;
    }

    public CtaCorrienteProveedor(Date fecha, String descripcion, double debe, double haber, double saldo, int idProveedor) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.idProveedor = idProveedor;
    }
    
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
   

    

    public int getIdCtaProveedor() {
        return idCtaProveedor;
    }

    public void setIdCtaProveedor(int idCtaProveedor) {
        this.idCtaProveedor = idCtaProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return  descripcion ;
    }
    
    
}
