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
public class Cliente {
    
    private int id_cliente;
    private String nom_cliente;
    private String dir_cliente;
    private String cuit_cliente;
    
    public Cliente(String nom_cliente){
        this.nom_cliente= nom_cliente;
        
    }

    public Cliente(String nom_cliente, String dir_cliente, String cuit_cliente) {
        this.nom_cliente = nom_cliente;
        this.dir_cliente = dir_cliente;
        this.cuit_cliente = cuit_cliente;
    }

    public Cliente(int id_cliente, String nom_cliente, String dir_cliente, String cuit_cliente) {
        this.id_cliente = id_cliente;
        this.nom_cliente = nom_cliente;
        this.dir_cliente = dir_cliente;
        this.cuit_cliente = cuit_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }

    public String getDir_cliente() {
        return dir_cliente;
    }

    public void setDir_cliente(String dir_cliente) {
        this.dir_cliente = dir_cliente;
    }

    public String getCuit_cliente() {
        return cuit_cliente;
    }

    public void setCuit_cliente(String cuit_cliente) {
        this.cuit_cliente = cuit_cliente;
    }

    @Override
    public String toString() {
        return nom_cliente ;
    }
    
    
}
