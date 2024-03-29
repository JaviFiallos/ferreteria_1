/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import clases.Cliente;
import clases.Venta;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteBD;
import modelo.CreditoBD;


public class panelCredito extends javax.swing.JPanel {

    private ClienteBD clbd = new ClienteBD();
    private DefaultTableModel modelo;
    private CreditoBD crbd= new CreditoBD();

    public panelCredito() {
        initComponents();
        this.lbl_ID.setVisible(false);
        cargarCliente();
    }

    private void limpiar() {
        this.lbl_ID.setText("");
        this.cobCliente.setSelectedIndex(0);
        limpiarTabla(modelo);
    }

    public void cargarCliente() {
        List<Cliente> lt = clbd.listarPersona();
        this.cobCliente.removeAllItems();
        DefaultComboBoxModel dc = new DefaultComboBoxModel();
        cobCliente.setModel(dc);
        dc.addElement("Seleccione");
        for (Cliente t : lt) {
            dc.addElement(new Cliente( t.getNombre(),t.getApellido(),t.getCedula()));
        }
    }

    public void limpiarTabla(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setRowCount(0);
        }
    }

    private void cargarTabla() {
        List<Venta> lt = crbd.creditosPorCliente(((Cliente) this.cobCliente.getSelectedItem()).getCedula());
        modelo = (DefaultTableModel) tablaProductos.getModel();
        modelo.setRowCount(0);
        for(Venta v: lt){
            modelo.addRow(new Object[]{v.getId(),v.getTotal(),v.getFecha()});
        }
        this.tablaProductos.setModel(modelo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cobCliente = new javax.swing.JComboBox<>();
        lbl_ID = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 218, 157));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TOTAL", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(50);
            tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(22);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 820, 320));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("GESTION DE CREDITOS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("CLIENTE");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(255, 102, 0));
        btnAgregar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setText("BUSCAR");
        btnAgregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 110, 30));

        btnLimpiar.setBackground(new java.awt.Color(255, 153, 0));
        btnLimpiar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 120, 30));

        cobCliente.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        cobCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cobCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobClienteActionPerformed(evt);
            }
        });
        add(cobCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 200, -1));

        lbl_ID.setForeground(new java.awt.Color(255, 218, 157));
        add(lbl_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 40, 30));

        btnPagar.setBackground(new java.awt.Color(255, 102, 0));
        btnPagar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(0, 0, 0));
        btnPagar.setText("Pagar");
        btnPagar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 110, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        int fila = this.tablaProductos.rowAtPoint(evt.getPoint());
        this.lbl_ID.setText(this.tablaProductos.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        // TODO add your handling code here:
        if (crbd.pagarCredito(Integer.parseInt(this.lbl_ID.getText()))) {
            JOptionPane.showMessageDialog(this, "Se guardo");
            this.cargarTabla();
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void cobClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cobClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> cobCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_ID;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
