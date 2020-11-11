package Vista;

import java.sql.*;
import org.jdesktop.swingx.autocomplete.*;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.BaseDatos;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ferc
 */
public class VistaControlStock extends javax.swing.JInternalFrame {
    
    public VistaControlStock() {
        
        initComponents();
        cargarColumnas();
        cargarModeloTabla();  
       cargarModeloProv();
        cargarModeloTabla();
        DecimalFormat df = null;               
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        textPromp();
        conexion = new Conexion();
    }
    
    private void textPromp() {

        //TextPrompt precio = new TextPrompt("$/Dia", txtPrecioHabitacion);
        TextPrompt bus = new TextPrompt("Nombre del artículo", txtNombre);
        TextPrompt bus2 = new TextPrompt("Buscador de articulos, seleccione primero la opcion a buscar.", campoBuscarTodo);
        TextPrompt bus3 = new TextPrompt("Código prod.", txtCodigoProducto);
       

        //TextPrompt seña = new TextPrompt("$/Seña", txtSeña);
        bus2.changeAlpha(0.76f);
        bus2.changeStyle(Font.PLAIN);
        bus3.changeAlpha(0.76f);
        bus3.changeStyle(Font.PLAIN);
        bus.changeAlpha(0.75f);
        bus.changeStyle(Font.PLAIN);
        
    }
    
    DefaultTableModel modeloTabla = new DefaultTableModel() {
        @Override
        public final boolean isCellEditable(int row, int column) {
            return false;
            
        }
    };
    
    DefaultComboBoxModel modeloProveedores = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloCategorias = new DefaultComboBoxModel();
    
    BaseDatos base = new BaseDatos();
    
    
    private ArrayList<Proveedor> listaProveedores;
    
    Producto productoSeleccionado = null;    
    Proveedor proveedorSeleccionado = null;
    Connection conn=null;
    Conexion conexion=null;    
    Proveedor proveedor = null;
    
    boolean Seleccion = false;
    boolean estaActualizando;

    //METODO QUE ACTUALIZA EL JCOMBOX CATEGORIA//
  
    private void cargarModeloProv() {
        listaProveedores = new ArrayList<Proveedor>();
        listaProveedores = base.obtenerProveedor();
        
        for (Proveedor proveedor : listaProveedores) {
            modeloProveedores.addElement(proveedor);
        }
        
    }
    
    private void cargarColumnas() {
        
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");
        modeloTabla.addColumn("Proveedor");
        
        TableColumn ColCodigo = tablaProductos.getColumn("Código");
        TableColumn ColNombre = tablaProductos.getColumn("Descripción");
        TableColumn ColProve = tablaProductos.getColumn("Precio");
        TableColumn ColVen = tablaProductos.getColumn("Stock");
        TableColumn ColStock = tablaProductos.getColumn("Proveedor");
        
        
        ColCodigo.setMaxWidth(60);
        ColCodigo.setMinWidth(10);
        
        ColNombre.setMaxWidth(400);
     
        ColProve.setMaxWidth(120);
        ColProve.setMinWidth(60);
        
        ColStock.setMaxWidth(80);
        ColStock.setMinWidth(10);
        
        ColVen.setMaxWidth(80);
        ColVen.setMinWidth(10);

    }
    
    private void addProveedores() {
        
        modeloProveedores.addElement(proveedor);
        comboBoxProveedores.setModel(modeloProveedores);
        modeloProveedores.setSelectedItem(proveedor);
    }
    
    private void cargarModeloTabla() {
        
        ArrayList<Producto> listaProducto = base.obtenerProductosInnerJoin();
        int numeroProducto = listaProducto.size();
        modeloTabla.setNumRows(numeroProducto);
        
        for (int i = 0; i < numeroProducto; i++) {
            
           Producto producto = listaProducto.get(i);
            int idProd = producto.getId_producto();          
            int codigoProd = producto.getCodigo_producto();
            Double pventa = producto.getPrecio_producto();
            int stock = producto.getCantidad_producto();
            String nomProveedor = producto.getNomProveedor();
            
            
            modeloTabla.setValueAt(codigoProd, i, 0);
            modeloTabla.setValueAt(producto, i, 1);           
            modeloTabla.setValueAt(pventa, i, 2);
            modeloTabla.setValueAt(stock, i, 3);
            modeloTabla.setValueAt(nomProveedor, i, 4);
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelProducto = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        txtVenta = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        stock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtIdProducto = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        campoBuscarTodo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        comboBoxProveedores = new javax.swing.JComboBox<>();
        PanelInferiorFoto = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        imgLabel = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setClosable(true);
        setTitle("Inventarios");
        setAutoscrolls(true);
        setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setRequestFocusEnabled(false);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        getContentPane().setLayout(null);

        PanelProducto.setFocusable(false);
        PanelProducto.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N

        jPanel4.setAutoscrolls(true);
        jPanel4.setLayout(new java.awt.GridLayout(2, 2, 2, 2));

        txtNombre.setFont(new java.awt.Font("Comfortaa", 0, 14)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombre.setToolTipText("Nombre de producto");
        txtNombre.setFocusTraversalPolicyProvider(true);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Descripción");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Código ");

        txtCodigoProducto.setFont(new java.awt.Font("Comfortaa", 0, 14)); // NOI18N
        txtCodigoProducto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoProducto.setToolTipText("Codigo Proveedor");
        txtCodigoProducto.setFocusTraversalPolicyProvider(true);
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyPressed(evt);
            }
        });

        btnAñadir.setFont(new java.awt.Font("Comfortaa", 1, 13)); // NOI18N
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/add.png"))); // NOI18N
        btnAñadir.setText("Añadir producto");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        btnAñadir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAñadirKeyPressed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Comfortaa", 1, 13)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/update.png"))); // NOI18N
        btnUpdate.setText("Actualizar producto");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        txtVenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVenta.setText("Precio");

        txtPrecioVenta.setFont(new java.awt.Font("Comfortaa", 0, 14)); // NOI18N
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioVenta.setText("0");
        txtPrecioVenta.setToolTipText("Precio de venta");
        txtPrecioVenta.setFocusTraversalPolicyProvider(true);
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyPressed(evt);
            }
        });

        stock.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stock.setText("Stock");

        txtStock.setFont(new java.awt.Font("Comfortaa", 0, 14)); // NOI18N
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.setText("0");
        txtStock.setToolTipText("Precio de venta");
        txtStock.setFocusTraversalPolicyProvider(true);

        btnEliminar.setFont(new java.awt.Font("Comfortaa", 1, 13)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/close.png"))); // NOI18N
        btnEliminar.setText("Eliminar producto");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtIdProducto.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(stock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoProducto)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtStock))
                        .addGap(53, 53, 53)
                        .addComponent(txtIdProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addGap(99, 99, 99)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(btnAñadir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtNombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnAñadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)))
                .addContainerGap())
        );

        tablaProductos.setFont(new java.awt.Font("Comfortaa Light", 0, 13)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaProductos.setIntercellSpacing(new java.awt.Dimension(4, 4));
        tablaProductos.setRowHeight(25);
        tablaProductos.setModel(modeloTabla);
        tablaProductos.getSelectionModel().addListSelectionListener(

            new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent event){

                    if(!event.getValueIsAdjusting() && (tablaProductos.getSelectedRow()>=0)){

                        txtCodigoProducto.setEditable(false);
                        Producto  producto =(Producto)modeloTabla.getValueAt(tablaProductos.getSelectedRow() , 1);

                        txtIdProducto.setText(String.valueOf(producto.getId_producto()));
                        txtIdProducto.setEnabled(false);
                        txtNombre.setText(producto.getDesc_producto());
                        txtStock.setText(String.valueOf(producto.getCantidad_producto()));
                        txtPrecioVenta.setText(String.valueOf(producto.getPrecio_producto()));
                        txtCodigoProducto.setText(String.valueOf(producto.getCodigo_producto()));
                        modeloProveedores.setSelectedItem(producto.getNomProveedor());
                        //desplegarFoto(producto);
                        productoSeleccionado = producto;

                    }

                }
            }
        );
        tablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProductosKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tablaProductos);

        jPanel3.setFont(new java.awt.Font("Decker", 1, 14)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0, 10, 0));
        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -7, 140, 60));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        campoBuscarTodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campoBuscarTodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoBuscarTodoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Buscar");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 57, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoBuscarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoBuscarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(365, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jButton2.setText("Limpiar Campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jLabel4.setText("Proveedor");

        comboBoxProveedores.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        comboBoxProveedores.setModel(modeloProveedores);

        javax.swing.GroupLayout PanelProductoLayout = new javax.swing.GroupLayout(PanelProducto);
        PanelProducto.setLayout(PanelProductoLayout);
        PanelProductoLayout.setHorizontalGroup(
            PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProductoLayout.createSequentialGroup()
                        .addGap(613, 613, 613)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelProductoLayout.createSequentialGroup()
                        .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(comboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelProductoLayout.setVerticalGroup(
            PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(436, 436, 436))
        );

        getContentPane().add(PanelProducto);
        PanelProducto.setBounds(0, 0, 660, 690);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        imgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/picture_landscape.png"))); // NOI18N
        imgLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imgLabelMousePressed(evt);
            }
        });
        imgLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                imgLabelKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout PanelInferiorFotoLayout = new javax.swing.GroupLayout(PanelInferiorFoto);
        PanelInferiorFoto.setLayout(PanelInferiorFotoLayout);
        PanelInferiorFotoLayout.setHorizontalGroup(
            PanelInferiorFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorFotoLayout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelInferiorFotoLayout.setVerticalGroup(
            PanelInferiorFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInferiorFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        getContentPane().add(PanelInferiorFoto);
        PanelInferiorFoto.setBounds(650, 8, 610, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        
        String nombreProd = txtNombre.getText();
        int codigoProd = Integer.parseInt(txtCodigoProducto.getText());
        int stockProd = Integer.parseInt(txtStock.getText());      
        double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
        int idProveedor =  comboBoxProveedores.getSelectedIndex() + 1 ;
      
        
        Producto p = new Producto(nombreProd, codigoProd, precioVenta, stockProd,idProveedor);
        
        base.insertarProducto(p);     
        
        cargarModeloTabla();
        JOptionPane.showMessageDialog(null, "Se ha insertado un nuevo producto");

    }//GEN-LAST:event_btnAñadirActionPerformed
    
    public void buscarPorDescripcion() {
        
        campoBuscarTodo.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent ke) {
                
                LimpiarLista();
                
                String cadena = campoBuscarTodo.getText();
                
                ArrayList<Producto> listaProductos = base.obtenerProductosInnerJoinPorCriterio(cadena);
                
                int numeroProducto = listaProductos.size();
                modeloTabla.setNumRows(numeroProducto);
                for (int i = 0; i < numeroProducto; i++) {
                    
                    Producto producto = listaProductos.get(i);
                    int clave = producto.getId_producto();
                    String nomBre = producto.getDesc_producto();
                    int codigoProd = producto.getCodigo_producto();
                    Double pventa = producto.getPrecio_producto();
                    int stock = producto.getCantidad_producto();
                    String nomProveedor = producto.getNomProveedor();                    
                    
                    modeloTabla.setValueAt(codigoProd, i, 0);
                    modeloTabla.setValueAt(producto, i, 1);                    
                    modeloTabla.setValueAt(pventa, i, 2);
                    modeloTabla.setValueAt(stock, i, 3);
                    modeloTabla.setValueAt(nomProveedor, i, 4);
                    
                }
            }
        });
        
    }
    
    public void buscarPorCodigo() {
        campoBuscarTodo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    LimpiarLista();
                    
                    int cadena = Integer.parseInt(campoBuscarTodo.getText());
                    
               /*     ArrayList<Producto> listaProductos = base.obtenerProductosPorCodigo(cadena);
                    
                    int numeroProducto = listaProductos.size();
                    modeloTabla.setNumRows(numeroProducto);
                    for (int i = 0; i < numeroProducto; i++) {
                        
                        Producto producto = listaProductos.get(i);
                        int clave = producto.getIdProducto();
                        String nomBre = producto.getNomProducto();
                        String idFabricaProd = producto.getIdProveedorProducto();
                        Double pventa = producto.getPrecioVentaProducto();
                        int exis = producto.getStockProducto();
                        
                        modeloTabla.setValueAt(clave, i, 0);
                        modeloTabla.setValueAt(producto, i, 1);
                        modeloTabla.setValueAt(idFabricaProd, i, 2);
                        modeloTabla.setValueAt(pventa, i, 3);
                        modeloTabla.setValueAt(exis, i, 4);
                        
                    }*/
                }
            }
        });
        
    }
    
    public void buscarPorProveedor() {
        campoBuscarTodo.addKeyListener(new KeyAdapter() {
            
            public void keyReleased(KeyEvent ke) {
                
                String cadena = campoBuscarTodo.getText();
          
                
                LimpiarLista();
                
               /* ArrayList<Producto> listaProductos = base.obtenerProductosPorCodigoProveedor(cadena);
                
                int numeroProducto = listaProductos.size();
                modeloTabla.setNumRows(numeroProducto);
                for (int i = 0; i < numeroProducto; i++) {
                    
                    Producto producto = listaProductos.get(i);
                    int clave = producto.getIdProducto();
                    String nomBre = producto.getNomProducto();
                    String idFabricaProd = producto.getIdProveedorProducto();
                    Double pventa = producto.getPrecioVentaProducto();
                    int exis = producto.getStockProducto();
                    
                    modeloTabla.setValueAt(clave, i, 0);
                    modeloTabla.setValueAt(producto, i, 1);
                    modeloTabla.setValueAt(idFabricaProd, i, 2);
                    modeloTabla.setValueAt(pventa, i, 3);
                    modeloTabla.setValueAt(exis, i, 4);
                    
                }*/
            }
        });
    }
    

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       
        
        actualizarArticulo();

    }//GEN-LAST:event_btnUpdateActionPerformed
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        hacerFoco(evt, txtNombre, txtCodigoProducto);
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtCodigoProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyPressed
        // TODO add your handling code here:
        hacerFoco(evt, txtNombre, txtPrecioVenta);
    }//GEN-LAST:event_txtCodigoProductoKeyPressed

    private void txtClavesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClavesKeyPressed

    }//GEN-LAST:event_txtClavesKeyPressed

    private void btnAñadirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAñadirKeyPressed
        

    }//GEN-LAST:event_btnAñadirKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarCampo();
        txtNombre.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            campoBuscarTodo.requestFocus();
        }
    }//GEN-LAST:event_tablaProductosKeyReleased

    private void imgLabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imgLabelKeyPressed
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo que soporta", "jpg", "png", "gif");
        chooser.setFileFilter(filter);
        
        int returnVal = chooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            imgArticleFile = chooser.getSelectedFile();
            
            ImageIcon icono = new ImageIcon(imgArticleFile.getAbsolutePath());
            
            ImageIcon icono2 = new ImageIcon(icono.getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_DEFAULT));
            
            imgLabel.setIcon(icono2);
    }//GEN-LAST:event_imgLabelKeyPressed
    }
    private void imgLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgLabelMousePressed
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo que soporta", "jpg", "png", "gif");
        chooser.setFileFilter(filter);
        
        int returnVal = chooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            imgArticleFile = chooser.getSelectedFile();
            
            ImageIcon icono = new ImageIcon(imgArticleFile.getAbsolutePath());
            
            ImageIcon icono2 = new ImageIcon(icono.getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_DEFAULT));
            
            imgLabel.setIcon(icono2);
    }//GEN-LAST:event_imgLabelMousePressed
    }
    private void campoBuscarTodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscarTodoKeyReleased
      
            buscarPorDescripcion();      
    
    }//GEN-LAST:event_campoBuscarTodoKeyReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        DeleteProducto();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtPrecioVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyPressed
        hacerFoco(evt, txtStock, btnAñadir);
    }//GEN-LAST:event_txtPrecioVentaKeyPressed

    //CAMBIAR DE COMPONENTE
    public void hacerFoco(KeyEvent e, Component antecesor, Component sucesor) {
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
            
            sucesor.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ALT) {
            antecesor.requestFocus();
            
        }
    }
    


    /* public void añadirArticulo() {
        try {

            String nombre = txtNombre.getText().toUpperCase();
            int stock = Integer.parseInt(campoStock.getText());
            String codigoProveedor = txtClaveProveedor.getText().toUpperCase();
            double pCosto = Double.parseDouble(txtCosto.getText());
            double pVenta = Double.parseDouble(txtPrecioVenta.getText());
           
            double iva = Double.parseDouble(txtIva.getText());
            double dolar = Double.parseDouble(txtDolar.getText());
            double bon1 = Double.parseDouble(txtbon1.getText());
            double bon2 = Double.parseDouble(txtbon2.getText());
            double bon3 = Double.parseDouble(txtbon3.getText());
            double bon4 = Double.parseDouble(txtbon4.getText());
            double flete = Double.parseDouble(txtFlete.getText());
            double ganancia = Double.parseDouble(txtGanancia.getText());

            Proveedor proveedor = (Proveedor) comboBoxProveedores.getSelectedItem();
            Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();

            if (imgArticleFile == null) {

                Producto producto = new Producto(nombre, stock, codigoProveedor, null, pCosto, pVenta, 0.0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, dolar, bon1, bon2, bon3, bon4, flete, ganancia);

                base.insertarProducto(producto);

                cargarModeloTabla();

                JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

            } else {

                Producto producto = new Producto(nombre, stock, codigoProveedor, imgArticleFile, pCosto, pVenta, 0.0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, dolar, bon1, bon2, bon3, bon4, flete, ganancia);

                base.insertarProducto(producto);

                JOptionPane.showMessageDialog(this, "Se ha guardado con exito el articulo " + producto.getNomProducto());

                cargarModeloTabla();

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }*/
   
    public void actualizarArticulo() {
        try {
            
            
            
            int id = Integer.parseInt(txtIdProducto.getText());
            String descripcion = txtNombre.getText();
            int stock = Integer.parseInt(txtStock.getText());
            int codigoProd = Integer.parseInt(txtCodigoProducto.getText());       
            double precioVenta = Double.parseDouble(txtPrecioVenta.getText());                  
             
             
            Producto producto = new Producto(id, descripcion, codigoProd, precioVenta, stock);
            
           base.actualizarProducto(producto);           
            
            cargarModeloTabla();
            JOptionPane.showMessageDialog(null, "Se ha actualizado un producto");
           
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    public void DeleteProducto() {
        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            
            Producto p = new Producto();
            p.setId_producto(id);
            
            base.borrarProducto(p);          
            
            cargarModeloTabla();
             JOptionPane.showMessageDialog(null, "Se ha borrado un producto");
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    File imgArticleFile;
    private String informacion = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInferiorFoto;
    private javax.swing.JPanel PanelProducto;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField campoBuscarTodo;
    private javax.swing.JComboBox<Proveedor> comboBoxProveedores;
    private javax.swing.JLabel imgLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel stock;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStock;
    private javax.swing.JLabel txtVenta;
    // End of variables declaration//GEN-END:variables

    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
///////////////////////////METODO PARA LIMPIAR LA TABLA 

    private void LimpiarLista() {
        int numFilas = modeloTabla.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i < 0; i--) {
                modeloTabla.removeRow(i);
            }
            
        }
    }
    
    private void limpiarCampo() {
        campoBuscarTodo.setText("");      
        txtCodigoProducto.setText("");        
        txtNombre.setText("");      
        txtPrecioVenta.setText("");   
        txtStock.setText("");
        txtCodigoProducto.setEditable(true);
       
    }
}
