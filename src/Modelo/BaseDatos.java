/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maxid
 */
public class BaseDatos {
    
    Conexion conn = null;
    Connection Conexion = null;
    PreparedStatement prep =null;
    ResultSet rs= null;
    Statement st = null;
    
    public BaseDatos(){
        
        conn = new Conexion();
        Conexion = conn.getConexion();
        
    }

 

    public ArrayList<Proveedor> obtenerProveedor() {
           ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT * FROM  proveedor");

            while (rs.next()) {
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
           
                Proveedor proveedor1 = new Proveedor(idProveedor, nomProveedor);

                listaProveedores.add(proveedor1);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        

        }
        return listaProveedores;

    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();

        try {
            
            prep = Conexion.prepareStatement("SELECT * FROM productos ORDER BY CANTIDAD_PROD ASC");

            rs = prep.executeQuery();

            while (rs.next()) {
                //  ( clave, nombre, descripcion, stock, codigoProveedor
                int idprod = rs.getInt("ID_PROD");
                String descripcionProd = rs.getString("DESCRIPCION_PROD");
                int codigoProd = rs.getInt("CODIGO_PROD");
                double precioProd = rs.getInt("PRECIO_PROD");
                int stock = rs.getInt("CANTIDAD_PROD");
                int idProveedor = rs.getInt("ID_PROVEEDOR");
            

                Producto producto = new Producto(idprod,descripcionProd,codigoProd,precioProd,stock,idProveedor);
               
                listaProductos.add(producto);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaProductos;
    
    }

    public void insertarProducto(Producto p) {
         try {            
      
            String sql = "INSERT INTO productos ( DESCRIPCION_PROD,CODIGO_PROD, PRECIO_PROD, CANTIDAD_PROD,ID_PROVEEDOR) "
                    + "VALUES(?,?, ?, ?, ?)";

            prep = Conexion.prepareStatement(sql);            
            prep.setString(1, p.getDesc_producto());
            prep.setInt(2, p.getCodigo_producto());
            prep.setDouble(3, p.getPrecio_producto());
            prep.setInt(4,p.getCantidad_producto());
            prep.setInt(5, p.getId_proveedor());           

            prep.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarStock(Producto producto) {
        
        try {                   
            
                prep = Conexion.prepareStatement("UPDATE productos SET CANTIDAD_PROD= ? WHERE ID_PROD= ?");

                prep.setInt(1, producto.getCantidad_producto());
                prep.setInt(2, producto.getId_producto());
               

                prep.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();   
    }   
       
    }

    public ArrayList<Cliente> getClientes() {
         ArrayList<Cliente> listarCliente = new ArrayList<Cliente>();

        try {
            st = Conexion.createStatement();
            rs = st.executeQuery(" SELECT * FROM clientes");           

            while (rs.next()) {
                //  ( clave, nombre, descripcion, stock, codigoProveedor
                int idCliente = rs.getInt("ID_CLIENTE");
                String nombre = rs.getString("NOM_CLIENTE");
                String direccion = rs.getString("DIR_CLIENTE");
                String cuit = rs.getString("CUIT_CLIENTE");
              

                Cliente cliente = new Cliente(idCliente, nombre, direccion, cuit);

                listarCliente.add(cliente);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } 
        return listarCliente;
    }

    public ArrayList<Producto> obtenerProductosPorCriterio(int criterio) {
          ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {

            String sql = "SELECT * FROM productos WHERE CODIGO_PROD =" + criterio;
            st = Conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                int idprod = rs.getInt("ID_PROD");
                String nomprod = rs.getString("DESCRIPCION_PROD");
                int codigoProd = rs.getInt("CODIGO_PROD");
                double precio = rs.getDouble("PRECIO_PROD");
                int cantidad = rs.getInt("CANTIDAD_PROD");
            

                Producto producto = new Producto(idprod,nomprod, codigoProd, precio, cantidad);
                listaProductos.add(producto);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listaProductos;
    }

    public ArrayList<Producto> obtenerProductosPorCadenaTexto(String cadena) {
             ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {

            String sql = "SELECT * FROM productos WHERE DESCRIPCION_PROD LIKE '%" + cadena + "%'  ORDER BY CANTIDAD_PROD ASC";
            st = Conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

             
                 int idprod = rs.getInt("ID_PROD");
                String nomprod = rs.getString("DESCRIPCION_PROD");
                int codigoProd = rs.getInt("CODIGO_PROD");
                double precio = rs.getDouble("PRECIO_PROD");
                int cantidad = rs.getInt("CANTIDAD_PROD");
            

                Producto producto = new Producto(idprod,nomprod, codigoProd, precio, cantidad);
                listaProductos.add(producto);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listaProductos;

    }

    public void insertarCliente(Cliente c) {
        try {
           

            String sql = "INSERT INTO CLIENTES (NOM_CLIENTE,DIR_CLIENTE,CUIT_CLIENTE) VALUES (?,?,?)";

            prep = Conexion.prepareStatement(sql);
            prep.setString(1, c.getNom_cliente());
            prep.setString(2, c.getDir_cliente());
            prep.setString(3, c.getCuit_cliente());
         
            prep.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
       }
    }
    
    public void actualizarProducto(Producto producto){
        try{
            
            prep = Conexion.prepareStatement("UPDATE  productos SET DESCRIPCION_PROD=? , CODIGO_PROD=?, PRECIO_PROD=?, CANTIDAD_PROD=? WHERE ID_PROD=?");
            
            prep.setString(1, producto.getDesc_producto());
            prep.setInt(2, producto.getCodigo_producto());
            prep.setDouble(3, producto.getPrecio_producto());
            prep.setInt(4, producto.getCantidad_producto());
            
            prep.setInt(5, producto.getId_producto());
            
             prep.execute();
            
        }catch(Exception e){
            
        }
    }

    public void borrarProducto(Producto p) {
        try{
            
            prep = Conexion.prepareStatement("DELETE FROM productos WHERE ID_PROD=?");
            
            prep.setInt(1, p.getId_producto());
            
             prep.execute();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
          }
    
      public ArrayList<Producto> obtenerProductosInnerJoin() {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();

        try {
            
            prep = Conexion.prepareStatement("SELECT ID_PROD,DESCRIPCION_PROD,CODIGO_PROD,PRECIO_PROD,CANTIDAD_PROD,NOM_PROVEEDOR FROM productos INNER JOIN proveedor ON productos.ID_PROVEEDOR=proveedor.ID_PROVEEDOR ORDER BY CANTIDAD_PROD ASC");

            rs = prep.executeQuery();

            while (rs.next()) {
                //  ( clave, nombre, descripcion, stock, codigoProveedor
                int idprod = rs.getInt("ID_PROD");
                String descripcionProd = rs.getString("DESCRIPCION_PROD");
                int codigoProd = rs.getInt("CODIGO_PROD");
                double precioProd = rs.getInt("PRECIO_PROD");
                int stock = rs.getInt("CANTIDAD_PROD");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
              
            

                Producto producto = new Producto(idprod,descripcionProd,codigoProd,precioProd,stock,nomProveedor);
               
                listaProductos.add(producto);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaProductos;
    
    }
      
         public ArrayList<Producto> obtenerProductosInnerJoinPorCriterio(String cadena) {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();

        try {
            
            prep = Conexion.prepareStatement("SELECT ID_PROD,DESCRIPCION_PROD,CODIGO_PROD,PRECIO_PROD,CANTIDAD_PROD,NOM_PROVEEDOR FROM productos INNER JOIN proveedor ON productos.ID_PROVEEDOR=proveedor.ID_PROVEEDOR WHERE DESCRIPCION_PROD LIKE '%" + cadena + "%'  ORDER BY CANTIDAD_PROD ASC");

            rs = prep.executeQuery();

            while (rs.next()) {
                //  ( clave, nombre, descripcion, stock, codigoProveedor
                int idprod = rs.getInt("ID_PROD");
                String descripcionProd = rs.getString("DESCRIPCION_PROD");
                int codigoProd = rs.getInt("CODIGO_PROD");
                double precioProd = rs.getInt("PRECIO_PROD");
                int stock = rs.getInt("CANTIDAD_PROD");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
              
            

                Producto producto = new Producto(idprod,descripcionProd,codigoProd,precioProd,stock,nomProveedor);
               
                listaProductos.add(producto);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaProductos;
    
    }

    public void insertarCtaCorrienteCliente(CtaCorrienteCliente cta) {
          try {
           

            String sql = "INSERT INTO ctaclientes (FECHA,DESCRIPCION,DEBE,HABER,SALDO,ID_CLIENTE) VALUES (?,?,?,?,?,?)";

            prep = Conexion.prepareStatement(sql);
            prep.setString(1, cta.getFecha());
            prep.setString(2, cta.getDescripcion());
            prep.setDouble(3, cta.getDebe());
            prep.setDouble(4, cta.getHaber());
            prep.setDouble(5, cta.getSaldo());
            prep.setInt(6, cta.getIdCliente());
         
            prep.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
       }
    }

    public ArrayList<CtaCorrienteCliente> getCtaCorrientePorId(int idCliente) {
        
         ArrayList<CtaCorrienteCliente> listaCta = new ArrayList<CtaCorrienteCliente>();

        try {
            
            prep = Conexion.prepareStatement("SELECT * FROM ctaclientes WHERE ID_CLIENTE = " + idCliente);

            rs = prep.executeQuery();
            
         

            while (rs.next()) {
               
                int idCta = rs.getInt(1);
                String fecha= rs.getString(2);
                String descripcion = rs.getString(3);
                double debe = rs.getDouble(4);
                double haber = rs.getDouble(5);
                double saldo = rs.getDouble(6);
                int id_Cliente = rs.getInt(7);
            

                CtaCorrienteCliente cta = new CtaCorrienteCliente(idCta,fecha,descripcion,debe,haber,saldo,id_Cliente);
               
                listaCta.add(cta);
            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaCta;
    
        
    }

    public void eliminarFilaCtaCorrienteCliente(CtaCorrienteCliente cta) {
        try{
            
            prep = Conexion.prepareStatement("DELETE FROM ctaclientes WHERE ID_CTA_CLIENTE=?");
            
            prep.setInt(1, cta.getIdCtaCliente());
            
             prep.execute();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
     
    
}
