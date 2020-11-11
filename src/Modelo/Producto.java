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
public class Producto {
    
    private int id_producto;
    private String desc_producto;
    private int codigo_producto;
    private double precio_producto;
    private int cantidad_producto;
    private int id_proveedor;
    private String nomProveedor;
    
    public Producto(){
        
    }

    public Producto(int id_producto, String desc_producto, int codigo_producto, double precio_producto, int cantidad_producto, int id_proveedor) {
        this.id_producto = id_producto;
        this.desc_producto = desc_producto;
        this.codigo_producto = codigo_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
        this.id_proveedor = id_proveedor;
    }

    public Producto(int id_producto, String desc_producto, int codigo_producto, double precio_producto, int cantidad_producto) {
        this.id_producto = id_producto;
        this.desc_producto = desc_producto;
        this.codigo_producto = codigo_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
        
    }
    
    public Producto(String desc_producto, int codigo_producto, double precio_producto, int cantidad_producto, int id_proveedor) {
        this.desc_producto = desc_producto;
        this.codigo_producto = codigo_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
        this.id_proveedor = id_proveedor;
    }
    
       public Producto(String desc_producto, int codigo_producto, double precio_producto, int cantidad_producto) {
        this.desc_producto = desc_producto;
        this.codigo_producto = codigo_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
       
    }

    public Producto(int id_producto, String desc_producto, int codigo_producto, double precio_producto, int cantidad_producto,  String nomProveedor) {
        this.id_producto = id_producto;
        this.desc_producto = desc_producto;
        this.codigo_producto = codigo_producto;
        this.precio_producto = precio_producto;
        this.cantidad_producto = cantidad_producto;
       
        this.nomProveedor = nomProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }
       
       

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDesc_producto() {
        return desc_producto;
    }

    public void setDesc_producto(String desc_producto) {
        this.desc_producto = desc_producto;
    }

    public int getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    @Override
    public String toString() {
        return desc_producto;
    }
    
    
    
    
}
