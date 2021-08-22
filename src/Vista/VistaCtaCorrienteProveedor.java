/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.BaseDatos;
import Modelo.Cliente;
import Modelo.CtaCorrienteCliente;
import Modelo.CtaCorrienteProveedor;
import Modelo.Proveedor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author maxid
 */
public class VistaCtaCorrienteProveedor extends javax.swing.JInternalFrame {

    /**
     * Creates new form CtaCorrienteCliente
     */
    DefaultComboBoxModel modeloComboProveedor = new DefaultComboBoxModel();
    DefaultTableModel modeloTablaCtaCorrienteProveedor = new DefaultTableModel();
    BaseDatos base = new BaseDatos();

    public VistaCtaCorrienteProveedor() {
        initComponents();
        cargarProveedorEnComboBox();
        cargarColumnasCtaCorrienteClientes();
        lblSumatoriaCtaCorriente.setEditable(false);
        AutoCompleteDecorator.decorate(comboProveedor);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox<>();
        btnNuevoCliente = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCtaCorrienteProveedor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDebe = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblSumatoriaCtaCorriente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnIngresarMovimiento = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtPago = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1378, 788));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel11.setText("Proveedor");

        comboProveedor.setModel(modeloComboProveedor       );

        btnNuevoCliente.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        btnNuevoCliente.setText("Nuevo Proveedor");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jButton6.setText("Consultar cta corriente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tablaCtaCorrienteProveedor.setFont(new java.awt.Font("Malgun Gothic", 0, 18)); // NOI18N
        tablaCtaCorrienteProveedor.setModel(modeloTablaCtaCorrienteProveedor);
        tablaCtaCorrienteProveedor.setGridColor(new java.awt.Color(204, 204, 204));
        tablaCtaCorrienteProveedor.setRowHeight(25);
        tablaCtaCorrienteProveedor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaCtaCorrienteProveedor);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel1.setText("Pagué");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel2.setText("Saldo");

        lblSumatoriaCtaCorriente.setFont(new java.awt.Font("DejaVu Sans", 0, 36)); // NOI18N
        lblSumatoriaCtaCorriente.setForeground(new java.awt.Color(255, 102, 0));
        lblSumatoriaCtaCorriente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSumatoriaCtaCorriente.setText("$ 0.00");
        lblSumatoriaCtaCorriente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblSumatoriaCtaCorriente.setPreferredSize(new java.awt.Dimension(200, 51));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel3.setText("Debo");

        btnIngresarMovimiento.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        btnIngresarMovimiento.setText("Insertar");
        btnIngresarMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarMovimientoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setText("Fecha");

        txtFecha.setBackground(new java.awt.Color(51, 51, 51));
        txtFecha.setDateFormatString("yyyy/MM/dd");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setText("Descripción");

        jButton1.setText("Eliminar registro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSumatoriaCtaCorriente, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnIngresarMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addComponent(btnIngresarMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSumatoriaCtaCorriente, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25)))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoCliente)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        try{
        String nombre = JOptionPane.showInputDialog("Ingrese el NOMBRE del nuevo cliente");      

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ha ingresado el nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Proveedor p = new Proveedor(nombre);
            base.insertarProveedor(p);
            cargarProveedorEnComboBox();
            JOptionPane.showMessageDialog(null, "Se ha registrado a un nuevo cliente", "Nuevo cliente", 1);
        }
        }catch(NullPointerException e){
    JOptionPane.showMessageDialog(null, "No ha ingresado nada");
}
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cargarCtaCorrienteProveedor();
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnIngresarMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarMovimientoActionPerformed

        int filas = modeloTablaCtaCorrienteProveedor.getRowCount();
        if (filas == 0) {
            insertarMovimientoEnCtaCorriente();
        } else {
            ActualizarCuentaCorriente();
        }
        txtDebe.setText("");
        txtPago.setText("");
    }//GEN-LAST:event_btnIngresarMovimientoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   int numFilas = tablaCtaCorrienteProveedor.getSelectedRow();
        int filaSelec = modeloTablaCtaCorrienteProveedor.getRowCount();
        if (filaSelec > 0) {
            int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea eliminar el registro seleccionado ?");
            if (quitar == 0) {
                if (numFilas == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar el registro que desea quitar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    CtaCorrienteProveedor cta = (CtaCorrienteProveedor) modeloTablaCtaCorrienteProveedor.getValueAt(tablaCtaCorrienteProveedor.getSelectedRow(), 1);
                    base.eliminarFilaCtaCorrienteProveedor(cta);

                    cargarCtaCorrienteProveedor();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaEnString = sdf.format(txtFecha.getDate());
            java.util.Date fechaEnDate = sdf.parse(fechaEnString);
            java.sql.Date date1 = new java.sql.Date(fechaEnDate.getTime());           
            String fechaDeHoy = sdf.format(new Date().getTime());
            java.util.Date fechaEnDate2 = sdf.parse(fechaDeHoy);
            java.sql.Date date2 = new java.sql.Date(fechaEnDate2.getTime());
            int idCliente = comboProveedor.getSelectedIndex() +1;
            ArrayList<CtaCorrienteProveedor> lista = base.ctaCorrienteProveedorPorFechas(idCliente,date1, date2);
            int numeroCta = lista.size();
            modeloTablaCtaCorrienteProveedor.setNumRows(numeroCta);
            
            for (int i = 0; i < numeroCta; i++) {
                
                CtaCorrienteProveedor cta = lista.get(i);
                int idCta = cta.getIdCtaProveedor();
                Date fecha = cta.getFecha();
                String descripcion = cta.getDescripcion();
                double debe = cta.getDebe();
                double haber = cta.getHaber();
                double saldo = cta.getSaldo();
                int id_Cliente = cta.getIdProveedor();
                
                modeloTablaCtaCorrienteProveedor.setValueAt(fecha, i, 0);
                modeloTablaCtaCorrienteProveedor.setValueAt(cta, i, 1);
                modeloTablaCtaCorrienteProveedor.setValueAt(debe, i, 2);
                modeloTablaCtaCorrienteProveedor.setValueAt(haber, i, 3);
                modeloTablaCtaCorrienteProveedor.setValueAt(saldo, i, 4);
                
            }
        } catch (ParseException ex) {
            Logger.getLogger(VistaCtaCorrienteProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresarMovimiento;
    public javax.swing.JButton btnNuevoCliente;
    public javax.swing.JComboBox<String> comboProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblSumatoriaCtaCorriente;
    private javax.swing.JTable tablaCtaCorrienteProveedor;
    private javax.swing.JTextField txtDebe;
    private javax.swing.JTextField txtDescripcion;
    public com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtPago;
    // End of variables declaration//GEN-END:variables

    private void cargarColumnasCtaCorrienteClientes() {

        modeloTablaCtaCorrienteProveedor.addColumn("Fecha");
        modeloTablaCtaCorrienteProveedor.addColumn("Descripción");
        modeloTablaCtaCorrienteProveedor.addColumn("Debo");
        modeloTablaCtaCorrienteProveedor.addColumn("Pagué");
        modeloTablaCtaCorrienteProveedor.addColumn("Saldo");

        TableColumn ColFecha = tablaCtaCorrienteProveedor.getColumn("Fecha");
        TableColumn ColDesc = tablaCtaCorrienteProveedor.getColumn("Descripción");
        TableColumn ColDebe = tablaCtaCorrienteProveedor.getColumn("Debo");
        TableColumn ColHaber = tablaCtaCorrienteProveedor.getColumn("Pagué");
        TableColumn ColSaldo = tablaCtaCorrienteProveedor.getColumn("Saldo");

        ColFecha.setMaxWidth(170);
        ColFecha.setMinWidth(10);

        ColDesc.setMaxWidth(550);
        ColDesc.setMinWidth(10);

        ColDebe.setMaxWidth(120);
        ColDebe.setMinWidth(10);

        ColHaber.setMaxWidth(120);
        ColHaber.setMinWidth(10);

        ColSaldo.setMaxWidth(120);
        ColSaldo.setMinWidth(10);

    }

    private void cargarProveedorEnComboBox() {

        modeloComboProveedor.removeAllElements();
        ArrayList<Proveedor> listaClientes = base.obtenerProveedor();

        for (Proveedor r : listaClientes) {
            modeloComboProveedor.addElement(r);

        }
    }

    private void cargarCtaCorrienteProveedor() {
        int idProveedor = comboProveedor.getSelectedIndex() + 1;

        ArrayList<CtaCorrienteProveedor> ctaProveedor = base.getCtaCorrienteProveedorPorId(idProveedor);
        int numeroCta = ctaProveedor.size();
        modeloTablaCtaCorrienteProveedor.setNumRows(numeroCta);

        if (numeroCta == 0) {
            JOptionPane.showMessageDialog(null, "El proveedor no tiene iniciada una cuenta corriente ");
         
        } else {
           
            for (int i = 0; i < numeroCta; i++) {

                CtaCorrienteProveedor cta = ctaProveedor.get(i);
                int idCta = cta.getIdCtaProveedor();
                Date fecha = cta.getFecha();
                String descripcion = cta.getDescripcion();
                double debe = cta.getDebe();
                double haber = cta.getHaber();
                double saldo = cta.getSaldo();
                int id_Cliente = cta.getIdProveedor();

                modeloTablaCtaCorrienteProveedor.setValueAt(fecha, i, 0);
                modeloTablaCtaCorrienteProveedor.setValueAt(cta, i, 1);
                modeloTablaCtaCorrienteProveedor.setValueAt(debe, i, 2);
                modeloTablaCtaCorrienteProveedor.setValueAt(haber, i, 3);
                modeloTablaCtaCorrienteProveedor.setValueAt(saldo, i, 4);

            }
            double sumatoria = getSaldoCtaCorriente();
            lblSumatoriaCtaCorriente.setText(String.valueOf(sumatoria));
        }

    }

    private double getSaldoCtaCorriente() {

        int numFilas = modeloTablaCtaCorrienteProveedor.getRowCount() - 1;
        double sumatoria = 0;

        double importe = (double) modeloTablaCtaCorrienteProveedor.getValueAt(numFilas, 4);
        sumatoria += (importe);

        return sumatoria;

    }

    private void insertarMovimientoEnCtaCorriente() {

     try{
        double debe = 0;
        double saldo = 0;
        double pago = 0;
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEnString = sdf.format(txtFecha.getDate());
         java.util.Date fechaEnDate = sdf.parse(fechaEnString);
          java.sql.Date date2 = new java.sql.Date(fechaEnDate.getTime());
        String descripcion = txtDescripcion.getText();
        String stringDebe = txtDebe.getText();
        String stringPago = txtPago.getText();
        int idCliente = comboProveedor.getSelectedIndex() + 1;
        

        if (!stringDebe.isEmpty() && !stringPago.isEmpty()) {
            debe = Double.parseDouble(stringDebe);
             pago = Double.parseDouble(stringPago);
             saldo = debe - pago;
           CtaCorrienteProveedor movimientoCta = new CtaCorrienteProveedor(date2, descripcion, debe, pago, saldo, idCliente);

            base.insertarCtaCorrienteProveedor(movimientoCta);
           
        } else {
            saldo += debe;
            CtaCorrienteProveedor movimientoCta = new CtaCorrienteProveedor(date2, descripcion, debe, pago, saldo, idCliente);

            base.insertarCtaCorrienteProveedor(movimientoCta);
        }
        cargarCtaCorrienteProveedor();
        double sumatoria = getSaldoCtaCorriente();
        lblSumatoriaCtaCorriente.setText(String.valueOf(sumatoria));       
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "La fecha debe ser ingresada");
        } catch (ParseException ex) {
            Logger.getLogger(VistaCtaCorrienteProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ActualizarCuentaCorriente() {

          try{
        double debe = 0;
        double pago = 0;
        double saldoActualizado = 0;
        int fila = modeloTablaCtaCorrienteProveedor.getRowCount() - 1;
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEnString = sdf.format(txtFecha.getDate());
          java.util.Date fechaEnDate = sdf.parse(fechaEnString);
          java.sql.Date date2 = new java.sql.Date(fechaEnDate.getTime());
        String descripcion = txtDescripcion.getText();
        int idCliente = comboProveedor.getSelectedIndex() + 1;
        String stringDebe =txtDebe.getText();
        String stringPago = txtPago.getText();
        double saldo = (double) modeloTablaCtaCorrienteProveedor.getValueAt(fila, 4);

        if (!stringDebe.isEmpty() && !stringPago.isEmpty()) {
              debe = Double.parseDouble(stringDebe);
             pago = Double.parseDouble(stringPago);
          saldoActualizado = (saldo +debe) - pago;
              CtaCorrienteProveedor movimientoCta = new CtaCorrienteProveedor(date2, descripcion, debe, pago, saldoActualizado, idCliente);

            base.insertarCtaCorrienteProveedor(movimientoCta);
        } else if ( !stringPago.isEmpty()) {
               pago = Double.parseDouble(stringPago);
            saldoActualizado = saldo - pago;
            CtaCorrienteProveedor movimientoCta = new CtaCorrienteProveedor(date2, descripcion, debe, pago, saldoActualizado, idCliente);

            base.insertarCtaCorrienteProveedor(movimientoCta);
        } else if (!stringDebe.isEmpty()) {
             debe = Double.parseDouble(stringDebe);
            saldoActualizado = saldo + debe;
            CtaCorrienteProveedor movimientoCta = new CtaCorrienteProveedor(date2, descripcion, debe, pago, saldoActualizado, idCliente);

            base.insertarCtaCorrienteProveedor(movimientoCta);
        }
        cargarCtaCorrienteProveedor();       
        double sumatoria = getSaldoCtaCorriente();
        lblSumatoriaCtaCorriente.setText(String.valueOf(sumatoria));
       }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "La fecha debe ser ingresada");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Carácter inválido","Error",JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(VistaCtaCorrienteProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
