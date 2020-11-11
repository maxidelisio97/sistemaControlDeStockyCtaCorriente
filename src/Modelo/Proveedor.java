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
public class Proveedor {
    
    private int id_proveedor;
    private String nom_proveedor;
    
    public Proveedor(){
        
    }

    public Proveedor(int id_proveedor, String nom_proveedor) {
        this.id_proveedor = id_proveedor;
        this.nom_proveedor = nom_proveedor;
    }

    public Proveedor(String nom_proveedor) {
        this.nom_proveedor = nom_proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNom_proveedor() {
        return nom_proveedor;
    }

    public void setNom_proveedor(String nom_proveedor) {
        this.nom_proveedor = nom_proveedor;
    }

    @Override
    public String toString() {
        return nom_proveedor ;
    }
    
    
}
