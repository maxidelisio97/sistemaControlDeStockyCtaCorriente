package Vista;

import java.sql.*;
import Modelo.Producto;
import Modelo.Proveedor;

import Modelo.BaseDatos;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.CtaCorrienteCliente;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class vistaVentas extends javax.swing.JInternalFrame {

    DefaultTableModel modeloTablaProductosArriba = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;

        }
    };

    DefaultTableModel modeloTablaProductosAbajo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;

        }
    };

    Producto productoSeleccionado = null;   
    Proveedor proveedorSeleccionado = null;
    String categoria = "";
    String proveedor = "";
    Connection conn = null;
    Conexion Conexion = null;
    private final double IVA = 0.21;
    
    DefaultComboBoxModel modeloComboProveedores = new DefaultComboBoxModel();
    DefaultListModel<Producto> modeloListaProductos = new DefaultListModel<Producto>();
    BaseDatos base = new BaseDatos();
    int filaSeleccionada;
    boolean Seleccion = false;
    private double sumatoria = 0;
    private double importeTotal = 0;
    DecimalFormat df = new DecimalFormat("0.00");

    public vistaVentas() {

        initComponents();
        cargarColumnasTablaAbajo();
        cargarColumnasTablaArriba();
        cargarModeloTablaArriba();      
        cargarProveedoresEnComboBox();
       
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Conexion = new Conexion();
    }

    private double getImporteTotal() {

        int numFilas = modeloTablaProductosAbajo.getRowCount();
        double sumatoria = 0;
        for (int i = 0; i < numFilas; i++) {
            double importe = (double) modeloTablaProductosAbajo.getValueAt(i, 7);
            sumatoria += (importe);

        }

        return sumatoria;

    }

    //CARGAR PRESUPUESTO EN TABLA DE ABAJO    
    

    private void cargarModeloTablaArriba() {

        ArrayList<Producto> listaProducto = base.obtenerProductos();
        int numeroProducto = listaProducto.size();
        modeloTablaProductosArriba.setNumRows(numeroProducto);
        
        for (int i = 0; i < numeroProducto; i++) {
            
           Producto producto = listaProducto.get(i);
            int idProd = producto.getId_producto();          
            int codigoProd = producto.getCodigo_producto();
            Double pventa = producto.getPrecio_producto();
            int stock = producto.getCantidad_producto();
              modeloTablaProductosArriba.setValueAt(idProd, i, 0);
            modeloTablaProductosArriba.setValueAt(codigoProd, i, 1);
            modeloTablaProductosArriba.setValueAt(producto, i, 2);
            modeloTablaProductosArriba.setValueAt(pventa, i, 3);
            modeloTablaProductosArriba.setValueAt(stock, i, 4);
            

        }

    }

    private void cargarColumnasTablaAbajo() {
        modeloTablaProductosAbajo.addColumn("ID");
        modeloTablaProductosAbajo.addColumn("Codigo");
         modeloTablaProductosAbajo.addColumn("Cant");
        modeloTablaProductosAbajo.addColumn("Descripcion");
         modeloTablaProductosAbajo.addColumn("Stock");
        modeloTablaProductosAbajo.addColumn("Desc%");       
        modeloTablaProductosAbajo.addColumn("P.Venta");
        modeloTablaProductosAbajo.addColumn("Importe");

        TableColumn ColId = tablaAbajo.getColumn("ID");
        TableColumn ColCodigo = tablaAbajo.getColumn("Codigo");
        TableColumn Colcant = tablaAbajo.getColumn("Cant");
        TableColumn ColNombre = tablaAbajo.getColumn("Descripcion");
         TableColumn ColStock = tablaAbajo.getColumn("Stock");
        TableColumn ColProve = tablaAbajo.getColumn("Desc%");        
        TableColumn ColVen = tablaAbajo.getColumn("P.Venta");
        TableColumn Colimport = tablaAbajo.getColumn("Importe");

        //ColCodigo.setPreferredWidth(1);
        ColId.setMaxWidth(40);
        ColId.setMinWidth(10);
        
        ColCodigo.setMaxWidth(80);
        ColCodigo.setMinWidth(10);

        Colcant.setMaxWidth(60);
        Colcant.setMinWidth(10);
        ColNombre.setMaxWidth(700);
        ColNombre.setMinWidth(500);     
        
        ColStock.setMaxWidth(50);
        ColStock.setMinWidth(10);

        ColProve.setMaxWidth(90);
        ColProve.setMinWidth(90);

        ColVen.setMaxWidth(120);
        ColVen.setMinWidth(10);

        Colimport.setMaxWidth(120);
        Colimport.setMinWidth(10);

    }
    
    private void cargarProveedoresEnComboBox(){
     
      ArrayList<Proveedor>  listaProveedores = new ArrayList<Proveedor>();
        listaProveedores = base.obtenerProveedor();
        
        for (Proveedor proveedor : listaProveedores) {
            modeloComboProveedores.addElement(proveedor);
        }
        
    
    }

  

    private void cargarColumnasTablaArriba() {
        modeloTablaProductosArriba.addColumn("ID");
        modeloTablaProductosArriba.addColumn("Codigo");
        modeloTablaProductosArriba.addColumn("Nombre");
        modeloTablaProductosArriba.addColumn("P.Venta");
        modeloTablaProductosArriba.addColumn("Stock");
        
         TableColumn ColId = tablaArriba.getColumn("ID");
        TableColumn ColCodigo = tablaArriba.getColumn("Codigo");
        TableColumn ColNombre = tablaArriba.getColumn("Nombre");
        TableColumn ColProve = tablaArriba.getColumn("P.Venta");

        TableColumn ColVen = tablaArriba.getColumn("Stock");
        
        ColId.setMaxWidth(40);
        ColId.setMinWidth(10);
        
        ColCodigo.setMaxWidth(80);
        ColCodigo.setMinWidth(10);

        ColNombre.setMinWidth(100);
        ColNombre.setMaxWidth(770);

        ColProve.setMaxWidth(100);
        ColProve.setMinWidth(50);

        ColVen.setMaxWidth(80);
        ColVen.setMinWidth(10);


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBusquedaCriterio = new javax.swing.ButtonGroup();
        grupo_boletas = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAbajo = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnQuitarProd = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaArriba = new javax.swing.JTable();
        txtDesc = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblSumatoria = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setTitle("Ventas");
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        getContentPane().setLayout(null);

        tablaAbajo.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        tablaAbajo.setModel(modeloTablaProductosAbajo);
        tablaAbajo.setRowHeight(25);
        /*tablaAbajo.getSelectionModel().addListSelectionListener(

            new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent event){

                    if(!event.getValueIsAdjusting() && (tablaAbajo.getSelectedRow()>=0)){

                        modeloTablaProductosAbajo.setValueAt(0,tablaAbajo.getSelectedRow() , 4);

                    }
                }

            });*/
            jScrollPane1.setViewportView(tablaAbajo);

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 649, Short.MAX_VALUE)
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 58, Short.MAX_VALUE)
            );

            jPanel7.setLayout(new java.awt.BorderLayout());

            jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton2.setForeground(new java.awt.Color(255, 0, 0));
            jButton2.setText("Salir");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });
            jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 698, -1, -1));

            btnQuitarProd.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
            btnQuitarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/close1.png"))); // NOI18N
            btnQuitarProd.setText("Quitar producto");
            btnQuitarProd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnQuitarProdActionPerformed(evt);
                }
            });

            btnCancelar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
            btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/close1.png"))); // NOI18N
            btnCancelar.setText("Quitar todo");
            btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelarActionPerformed(evt);
                }
            });

            btnAceptar.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
            btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/import.png"))); // NOI18N
            btnAceptar.setText("Actualizar stock");
            btnAceptar.setBorder(null);
            btnAceptar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            btnAceptar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAceptarActionPerformed(evt);
                }
            });

            tablaArriba.setModel(modeloTablaProductosArriba);
            tablaArriba.getSelectionModel().addListSelectionListener(

                new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent event){

                        if(!event.getValueIsAdjusting() &&  (tablaArriba.getSelectedRow()>=0)){
                            Producto  producto =(Producto)modeloTablaProductosArriba.getValueAt(tablaArriba.getSelectedRow() , 2);
                            //     desplegarFoto(producto);
                            productoSeleccionado = producto;

                        }

                    }
                }
            );
            tablaArriba.setFont(new java.awt.Font("Decker", 0, 18)); // NOI18N
            tablaArriba.setModel(modeloTablaProductosArriba);
            tablaArriba.setToolTipText("");
            tablaArriba.setGridColor(new java.awt.Color(204, 204, 204));
            tablaArriba.setRowHeight(25);
            tablaArriba.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            tablaArriba.setShowGrid(false);
            tablaArriba.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tablaArribaKeyReleased(evt);
                }
            });
            jScrollPane2.setViewportView(tablaArriba);

            txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtDescKeyReleased(evt);
                }
            });

            jPanel10.setLayout(new java.awt.GridLayout(1, 2, 10, 2));

            jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
            jLabel5.setText("Código:");
            jPanel10.add(jLabel5);

            txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCodigoKeyPressed(evt);
                }
            });
            jPanel10.add(txtCodigo);

            jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
            jLabel6.setText("Cantidad:");
            jPanel10.add(jLabel6);

            txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCantidadKeyPressed(evt);
                }
            });
            jPanel10.add(txtCantidad);

            jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
            jLabel7.setText("Desc:%");
            jPanel10.add(jLabel7);

            txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtDescuentoKeyPressed(evt);
                }
            });
            jPanel10.add(txtDescuento);

            jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
            jLabel8.setText("Desc:");

            lblSumatoria.setFont(new java.awt.Font("DejaVu Sans", 0, 36)); // NOI18N
            lblSumatoria.setForeground(new java.awt.Color(255, 102, 0));
            lblSumatoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            lblSumatoria.setText("$ 0.00");
            lblSumatoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblSumatoria.setPreferredSize(new java.awt.Dimension(200, 51));
            lblSumatoria.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    lblSumatoriaActionPerformed(evt);
                }
            });

            jButton1.setText("Imprimir productos");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton3.setText("Cargar todos los productos");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblSumatoria, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(19, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap())
            );

            btnCancelar1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
            btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos_Imagenes/actuliazar_verde.png"))); // NOI18N
            btnCancelar1.setText("Precio 0");
            btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCancelar1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(83, 83, 83))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(btnQuitarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAceptar))))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(131, Short.MAX_VALUE))
            );

            getContentPane().add(jPanel3);
            jPanel3.setBounds(0, 0, 1380, 760);

            getAccessibleContext().setAccessibleDescription("");

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed

        int filaSelec = tablaAbajo.getSelectedRow();
        if(filaSelec == -1){
            JOptionPane.showMessageDialog(this, "Debe seleccionar el articulo que desea modificar su precio", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            modeloTablaProductosAbajo.setValueAt(0.0,tablaAbajo.getSelectedRow() , 6);
            modeloTablaProductosAbajo.setValueAt(0.0,tablaAbajo.getSelectedRow() , 7);
            double sumatoria = getImporteTotal();
            lblSumatoria.setText(String.valueOf(sumatoria));
        }
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarModeloTablaArriba();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String path = "src/Vista/productos.jasper";
            conn = Conexion.getConexion();
            JasperReport  reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint  jprint = JasperFillManager.fillReport(reporte, null, conn);
            JasperViewer   view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblSumatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblSumatoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSumatoriaActionPerformed

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //DATOS OBTENIDOS DE LOS CAMPOS DE TEXTO
            String descripcion = txtDesc.getText();
            String codigoProd = txtCodigo.getText();
            String cantidad = txtCantidad.getText();
            if (descripcion.isEmpty() || codigoProd.isEmpty() || cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no deben estar vacios");
            } else {

                int codigo = (int) tablaArriba.getValueAt(filaSeleccionada, 1);
                Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 2);
                String Pventa = tablaArriba.getValueAt(filaSeleccionada, 3).toString();
                int cant = Integer.parseInt(txtCantidad.getText());
                String descuento = txtDescuento.getText();

                if (descuento.isEmpty()) {

                    double importe = Double.parseDouble(Pventa) * cant;

                    modeloTablaProductosAbajo = (DefaultTableModel) tablaAbajo.getModel();

                    Object ListaProductos[] = {p.getId_producto(),codigo, cant, p,p.getCantidad_producto(),0.0, Pventa, importe};

                    modeloTablaProductosAbajo.addRow(ListaProductos);

                    txtDesc.setText("");
                    txtCodigo.setText("");
                    txtCantidad.setText("");

                    double sumatoria = getImporteTotal();
                    lblSumatoria.setText(String.valueOf(df.format(sumatoria)));

                } else {
                    double Precioventa = Double.parseDouble(Pventa);
                    double desc = Double.parseDouble(txtDescuento.getText());
                    double descuentaso = (Precioventa / 100) * desc;
                    double PrecioConDescuento = Precioventa - descuentaso;
                    double importe = PrecioConDescuento * cant;

                    modeloTablaProductosAbajo = (DefaultTableModel) tablaAbajo.getModel();

                    Object ListaProductos[] = {p.getId_producto(),codigo, cant, p,p.getCantidad_producto(),desc, Precioventa, importe};

                    modeloTablaProductosAbajo.addRow(ListaProductos);

                    txtDesc.setText("");
                    txtCodigo.setText("");
                    txtCantidad.setText("");
                    txtDescuento.setText("");

                    double sumatoria = getImporteTotal();
                    lblSumatoria.setText(String.valueOf(df.format(sumatoria)));

                }
            }

            txtCodigo.requestFocus();

        }
    }//GEN-LAST:event_txtDescuentoKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescuento.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    //BUSCA POR CODIGO DEL PRODUCTO/ID_PROD
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtDesc.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String id = txtCodigo.getText();
            try {
                if (!id.isEmpty()) {

                    ArrayList<Producto> listaProductos = base.obtenerProductosPorCriterio(Integer.parseInt(id));

                    int numeroProducto = listaProductos.size();
                    modeloTablaProductosArriba.setNumRows(numeroProducto);
                    for (int i = 0; i < numeroProducto; i++) {
                        Producto producto = listaProductos.get(i);
                        int idProd = producto.getId_producto();
                        int codigo = producto.getCodigo_producto();
                        double precio = producto.getPrecio_producto();
                        int stock = producto.getCantidad_producto();

                        modeloTablaProductosArriba.setValueAt(idProd, i, 0);
                        modeloTablaProductosArriba.setValueAt(codigo, i, 1);
                        modeloTablaProductosArriba.setValueAt(producto, i, 2);
                        modeloTablaProductosArriba.setValueAt(precio, i, 3);
                        modeloTablaProductosArriba.setValueAt(stock, i, 4);
                        txtDesc.setText(producto.getDesc_producto());
                    }

                    txtCantidad.requestFocus();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Carácter inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    //BUSCA POR CODIGO DEL PROVEEDOR / STRING//BUSCAR POR DESCRIPCION DE PRODUCTO 
    private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txtCodigo.requestFocus();
        }

        String cadena = txtDesc.getText();

        ArrayList<Producto> listaProductos = base.obtenerProductosPorCadenaTexto(cadena);

        int numeroProducto = listaProductos.size();
        modeloTablaProductosArriba.setNumRows(numeroProducto);
        for (int i = 0; i < numeroProducto; i++) {

            Producto producto = listaProductos.get(i);
            int idProd = producto.getId_producto();
            int codigo = producto.getCodigo_producto();
            double precio = producto.getPrecio_producto();
            int stock = producto.getCantidad_producto();

            modeloTablaProductosArriba.setValueAt(idProd, i, 0);
            modeloTablaProductosArriba.setValueAt(codigo, i, 1);
            modeloTablaProductosArriba.setValueAt(producto, i, 2);
            modeloTablaProductosArriba.setValueAt(precio, i, 3);
            modeloTablaProductosArriba.setValueAt(stock, i, 4);

        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            tablaArriba.requestFocus();

        }
    }//GEN-LAST:event_txtDescKeyReleased

//METODO DE LA TABLA ARRIBA QUE LLENA CAMPOS DE TEXTO CON DATOS DE ESTA
    private void tablaArribaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaArribaKeyReleased

        try {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                int filaSelec = modeloTablaProductosArriba.getRowCount();
                if (filaSelec <= 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo");

                } else {

                    filaSeleccionada = tablaArriba.getSelectedRow();
                    String codigo = tablaArriba.getValueAt(filaSeleccionada, 1).toString();
                    Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 2);
                    txtDesc.setText(p.getDesc_producto());
                    txtCodigo.setText(codigo);

                    txtCantidad.requestFocus();
                }

            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_tablaArribaKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        int fila = modeloTablaProductosAbajo.getRowCount();
        if (fila > 0) {
            int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea actualizar el stock?");
            if (quitar == 0) {
                restarStock();
                limpiarTablaAbajo();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe agregar productos a la tabla para actualizar su stock", "Error", JOptionPane.ERROR_MESSAGE);

        }

        /* if (radioPresupuesto.isSelected()) {
            insertarPresupuesto();
        }*/

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarTablaAbajo();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnQuitarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProdActionPerformed
        int numFilas = tablaAbajo.getSelectedRow();
        int filaSelec = modeloTablaProductosAbajo.getRowCount();
        if (filaSelec > 0) {
            int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea eliminar el articulo seleccionado ?");
            if (quitar == 0) {
                if (numFilas == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar el articulo que desea quitar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    modeloTablaProductosAbajo.removeRow(numFilas);
                }
            }
        }
        double sumatoria = getImporteTotal();
        lblSumatoria.setText(String.valueOf(sumatoria));
    }//GEN-LAST:event_btnQuitarProdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public void LimpiarListaProductos() {
        modeloListaProductos.clear();
    }


   

   public void limpiarCampoSiguiente(JComponent Actual, JComponent Sucesor) {

        if (Actual == txtCantidad) {
            txtCodigo.setText("");        
            txtCantidad.setText("");          
            Sucesor.requestFocus();

        }
        if (Actual == txtCodigo) {         
            Sucesor.requestFocus();
        }

    }

    //CAMBIAR DE COMPONENTE
    public void hacerFoco(KeyEvent e, Component antecesor, Component sucesor) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {

            sucesor.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ALT) {
            antecesor.requestFocus();

        }
    }

    public void hacerFoco(Component campoDeTexto) {
        campoDeTexto.setFocusable(true);
    }

    private void LimpiarLista() {
        int numFilas = modeloTablaProductosArriba.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i < 0; i--) {
                modeloTablaProductosArriba.removeRow(i);
            }
        }
    }
    private void limpiarTablaAbajo(){
            int cantidadFilas = modeloTablaProductosAbajo.getRowCount();
             if (cantidadFilas > 0) {
                 int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea finalizar la venta ?");
                 if (quitar == 0) {
                     for (int i = cantidadFilas - 1; i >= 0; i--) {
                         modeloTablaProductosAbajo.removeRow(i);

                     }
                      sumatoria = 0;
                     lblSumatoria.setText(String.valueOf(sumatoria));
                 }
                 
             }
    }
       public void restarStock() {
        int rows = modeloTablaProductosAbajo.getRowCount();
try{
        for (int i = 0; i < rows; i++) {          

            int idProd = (int) modeloTablaProductosAbajo.getValueAt(i, 0);
            int stock = (int) modeloTablaProductosAbajo.getValueAt(i, 4);
            int cant = (int) modeloTablaProductosAbajo.getValueAt(i ,2);           

            int stockActualizado = stock - cant;
             Producto p = new Producto();
            p.setId_producto(idProd);
            p.setCantidad_producto(stockActualizado);
            
            base.actualizarStock(p);

        }
}catch(ClassCastException e){
    JOptionPane.showMessageDialog(null, "No puede actualizar el stock desde aquí");
}
        cargarModeloTablaArriba();
    }
    
        private void cargarPresupuesto() {

       /* int presupuesto = Integer.parseInt(txtPresupuesto.getText());

        ArrayList<Producto> listaProductoAbajo = base.getPresupuestoPorID(presupuesto);
        int numeroProducto = listaProductoAbajo.size();

        for (int i = 0; i < numeroProducto; i++) {

            Presupuesto producto = listaProductoAbajo.get(i);
            //String nomBre = producto.getNomProd();
            double precioVenta = producto.getPrecio_unitario();
            int idClave = producto.getCodigo_prod();
            int cant = producto.getCant_productos();
            Double importe = producto.getpNeto();
            double desc = producto.getDescuento();
            int stock = producto.getStock();

            //INSERTA FILAS Y NO BORRA LAS ANTERIORES
            modeloTablaProductosAbajo.insertRow(i, new Object[]{idClave, cant, producto,  stock,desc, precioVenta, importe});
            importeTotal = (double) modeloTablaProductosAbajo.getValueAt(i, 6);
            sumatoria += (importeTotal);

        }
        lblSumatoria.setText(String.valueOf(sumatoria));*/

    }
        
          /*private void añadirCtaCorrienteCliente() {
        
            
            int rows = modeloTablaProductosAbajo.getRowCount();
            Calendar calendarioLocal = Calendar.getInstance();
            java.util.Date fechaActual = calendarioLocal.getTime();
            long fechaMailSegun = fechaActual.getTime();
            java.util.Date fecha = new Date(fechaMailSegun);
            String fecha1 = String.valueOf(fecha);
            
              for (int i = 0; i < rows; i++) {
               
                  int idProd = (int)  modeloTablaProductosAbajo.getValueAt(i, 0);
                int codigoProd = (int) modeloTablaProductosAbajo.getValueAt(i, 1);
                int cantidadProd = (int) modeloTablaProductosAbajo.getValueAt(i, 2);
                String descripcion = modeloTablaProductosAbajo.getValueAt(i, 3).toString();
                double descuento = (double) modeloTablaProductosAbajo.getValueAt(i, 5);               
                 int idCliente =  comboClientes.getSelectedIndex() + 1 ;
                CtaCorrienteCliente cta = new CtaCorrienteCliente(codigoProd, fecha1,cantidadProd, descuento, idProd, idCliente);
                base.insertarCtaCorrienteCliente(cta);
            }
    }
          
          private void cargarCtaCorrienteCliente() {
              
              int idCliente = comboClientes.getSelectedIndex() +1 ;

        ArrayList<CtaCorrienteCliente> ctaCliente= base.getCtaCorrientePorId(idCliente);
        int numeroCta = ctaCliente.size();

        for (int i = 0; i < numeroCta; i++) {

            CtaCorrienteCliente cta = ctaCliente.get(i);
            int idCta = cta.getIdCtaCliente();
            int codigoProd = cta.getCodigoProd();            
            int cant = cta.getCantidadProd();
            String descripcion = cta.getDescripcionProd();
            double descuento = cta.getDescuento();
            double precio = cta.getPrecioVenta();
            double importe = cta.getImporte();

            //INSERTA FILAS Y NO BORRA LAS ANTERIORES
            modeloTablaProductosAbajo.insertRow(i, new Object[]{idCta, codigoProd, cant,descripcion,"-", descuento, precio,importe});
             double sumatoria = getImporteTotal();
            lblSumatoria.setText(String.valueOf(sumatoria));

        }
        
       
    }*/

  

    

    

    private javax.swing.JTextField campoAgregarExistencia;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoProveedor;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoStock;
    private boolean seleccion;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnQuitarProd;
    private javax.swing.ButtonGroup grupoBusquedaCriterio;
    private javax.swing.ButtonGroup grupo_boletas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lblSumatoria;
    private javax.swing.JTable tablaAbajo;
    private javax.swing.JTable tablaArriba;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtDescuento;
    // End of variables declaration//GEN-END:variables

    

  

}
