package vista;

import clases.Compra;
import clases.Kardex;
import clases.Producto_;
import clases.Proveedor;
import clases.Validacion;
import java.awt.event.ItemEvent;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.CompraDAO;
import modelo.KardexDAO;
import modelo.ProveedorDAO;
import modelo.productoDAO_;

public class panelCompras extends javax.swing.JPanel {

    private double totalPagar = 0.00;
    private int item;
    private DefaultTableModel modelo6 = new DefaultTableModel();

    private Compra c = new Compra();
    private CompraDAO cd = new CompraDAO();
    private Kardex k = new Kardex();
    private KardexDAO kd = new KardexDAO();

    private productoDAO_ pd = new productoDAO_();
    private ProveedorDAO pv = new ProveedorDAO();

    public panelCompras() {
        initComponents();
        cargarProductos();
        cargarProveedores();
    }

    private void agregarProducto() {
        item = item + 1;
        this.modelo6 = (DefaultTableModel) this.tablaNuevaCompra.getModel();
        int id = ((Producto_) this.comboProductos.getSelectedItem()).getId();
        //String cod = ((Producto) this.comboProdComp.getSelectedItem()).getCodigo();;
        String descripcion = ((Producto_) this.comboProductos.getSelectedItem()).getNombre();
        Double precio = round((Double.parseDouble(this.txtPrecio.getText())) * 100) / 100.0;
        int cantidad = Integer.parseInt(this.txtCantidad.getText());
        double total = precio * cantidad;

        for (int i = 0; i < this.tablaNuevaCompra.getRowCount(); i++) {

            if (this.tablaNuevaCompra.getValueAt(i, 0).equals(((Producto_) this.comboProductos.getSelectedItem()).getId())) {
                JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                return;
            }
        }

        ArrayList a = new ArrayList();
        a.add(item);
        a.add(id);
        a.add(descripcion);
        a.add(cantidad);
        a.add(precio);
        a.add(total);

        Object[] o = new Object[5];
        o[0] = a.get(1);
        o[1] = a.get(2);
        o[2] = a.get(3);
        o[3] = a.get(4);
        o[4] = a.get(5);
        modelo6.addRow(o);
        tablaNuevaCompra.setModel(modelo6);

    }

    private void totalPagar() {

        totalPagar = 0.00;
        int numFila = this.tablaNuevaCompra.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double c = Double.parseDouble(String.valueOf(tablaNuevaCompra.getModel().getValueAt(i, 4)));
            totalPagar = totalPagar + c;
        }
        this.lblTotal.setText(String.format("%.2f", totalPagar));
    }

    private void cargarProductos() {
        List<Producto_> lt = pd.listarProducto();
        this.comboProductos.removeAllItems();
        DefaultComboBoxModel dc = new DefaultComboBoxModel();
        comboProductos.setModel(dc);
        dc.addElement("Seleccione");
        for (Producto_ t : lt) {
            dc.addElement(new Producto_(t.getId(), t.getNombre()));
        }
    }

    private void cargarProveedores() {
        List<Proveedor> lt = pv.listarProveedor();
        this.comboProveedor.removeAllItems();
        DefaultComboBoxModel dc = new DefaultComboBoxModel();
        comboProveedor.setModel(dc);
        dc.addElement("Seleccione");
        for (Proveedor t : lt) {
            dc.addElement(new Proveedor(t.getRuc(), t.getNombre(), t.getDireccion(), t.getTelefono()));
        }
    }

    private void borrarSeleccion(JTable tabla, DefaultTableModel modelo) {
        if (tabla.getSelectedRow() >= 0) {
            modelo = (DefaultTableModel) tabla.getModel();
            modelo.removeRow(tabla.getSelectedRow());
            limpiarParcial();
            totalPagar();

        } else {
            JOptionPane.showMessageDialog(null, "No se selecciono nada ");
        }
    }

    private void registrarCompra() {

        String id = ((Proveedor) this.comboProveedor.getSelectedItem()).getRuc();
        c = new Compra(totalPagar, id);
        if (cd.registrarCompra(c)) {
            registrarCompraKardex();
            JOptionPane.showMessageDialog(null, "Su compra ha sido realizada con exito");

        } else {
            JOptionPane.showMessageDialog(null, "Su compra no se efectuo :(");
        }

    }

    private void registrarCompraKardex() {

        int idCompra = kd.idCompra();
        int cantE = 0;
        for (int i = 0; i < this.tablaNuevaCompra.getRowCount(); i++) {
            int idProd = Integer.parseInt(this.tablaNuevaCompra.getValueAt(i, 0).toString());
            double precio = Double.parseDouble(this.tablaNuevaCompra.getValueAt(i, 3).toString());
            cantE = Integer.parseInt(this.tablaNuevaCompra.getValueAt(i, 2).toString());//Por producto, esto sera para devoluciones      
            k.setProducto(idProd);
            k.setDetalle("COMPRA");
            k.setCompra(idCompra);
            k.setPrecioUnitario(precio);
            k.setEntrada(cantE);
            kd.agregarKardexCompra(k);
        }
    }

    private void limpiarTotal() {

        this.txtCantidad.setText("");
        this.txtPrecio.setText("");
        this.comboProductos.setSelectedIndex(0);
        this.comboProveedor.setSelectedIndex(0);
        this.comboProveedor.setEnabled(true);
        limpiarTabla(modelo6);
        this.lblTotal.setText("----");

    }

    private void limpiarParcial() {

        this.txtCantidad.setText("");
        this.txtPrecio.setText("");
        this.comboProductos.setSelectedIndex(0);

    }

    private void limpiarTabla(DefaultTableModel model) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setRowCount(0);
        }
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
        tablaNuevaCompra = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        btnAniadir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        comboProveedor = new javax.swing.JComboBox<>();
        lblTotal = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JFormattedTextField();
        txtPrecio = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 218, 157));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaNuevaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tablaNuevaCompra);
        if (tablaNuevaCompra.getColumnModel().getColumnCount() > 0) {
            tablaNuevaCompra.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaNuevaCompra.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaNuevaCompra.getColumnModel().getColumn(2).setPreferredWidth(50);
            tablaNuevaCompra.getColumnModel().getColumn(3).setPreferredWidth(50);
            tablaNuevaCompra.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 510, 270));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("COMPRAS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("PROVEEDOR:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("PRECIO :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 102, 0));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("BORRAR SELECCION");
        btnEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 160, 30));

        btnComprar.setBackground(new java.awt.Color(255, 102, 0));
        btnComprar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnComprar.setForeground(new java.awt.Color(0, 0, 0));
        btnComprar.setText("COMPRAR !");
        btnComprar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });
        add(btnComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 110, 30));

        btnAniadir.setBackground(new java.awt.Color(255, 102, 0));
        btnAniadir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAniadir.setForeground(new java.awt.Color(0, 0, 0));
        btnAniadir.setText("AÑADIR AL CARRITO");
        btnAniadir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniadirActionPerformed(evt);
            }
        });
        add(btnAniadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 160, 30));

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
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 120, 30));

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("PRODUCTO :");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        comboProductos.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        comboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        add(comboProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 200, -1));

        comboProveedor.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        comboProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboProveedorItemStateChanged(evt);
            }
        });
        add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 200, -1));

        lblTotal.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 255));
        lblTotal.setText("----");
        add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, -1, -1));

        txtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        txtCantidad.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 200, -1));

        txtPrecio.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 200, -1));

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("CANTIDAD :");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("TOTAL A PAGAR:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        borrarSeleccion(this.tablaNuevaCompra, modelo6);
        this.comboProductos.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarTotal();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirActionPerformed
        if (!this.txtPrecio.getText().equals("") && !this.txtCantidad.getText().equals("") && this.comboProductos.getSelectedIndex() != 0
                && this.comboProveedor.getSelectedIndex() != 0) {
            if (Validacion.esDouble(this.txtPrecio.getText())) {
                if (Validacion.esEntero(this.txtCantidad.getText())) {
                    if (Double.parseDouble(this.txtPrecio.getText()) * Integer.parseInt(this.txtCantidad.getText()) != 0) {
                        agregarProducto();
                        limpiarParcial();
                        this.comboProductos.requestFocus();
                        totalPagar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El precio no debe ser 0");
                    }

                } else {
                    //this.txtCantCompra.setText("");
                    JOptionPane.showMessageDialog(null, "Ingrese un numero entero mayor a cero");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un precio valido, use el punto (.) para los decimales");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Hay campos Vacios");
        }
    }//GEN-LAST:event_btnAniadirActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed

        if (this.tablaNuevaCompra.getRowCount() > 0) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de comprar estos productos");
            if (pregunta == 0) {
                registrarCompra();

                limpiarTotal();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese al menos un producto");
        }

    }//GEN-LAST:event_btnComprarActionPerformed

    private void comboProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboProveedorItemStateChanged
        if (this.comboProveedor.getSelectedIndex() != 0) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                this.comboProveedor.setEnabled(false);
                this.comboProductos.requestFocus();
            }
        }
    }//GEN-LAST:event_comboProveedorItemStateChanged

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped

        char c = evt.getKeyChar();
        boolean puntoIngresado = false;
        // Permitir solo números y un solo punto
        if (!(Character.isDigit(c) || (c == '.' && !puntoIngresado && txtPrecio.getText().indexOf('.') == -1))) {
            evt.consume(); // Ignorar el carácter ingresado si no es un número o el segundo punto
        }

        if (c == '.' && !puntoIngresado) {
            puntoIngresado = true; // Marcar que se ingresó el punto
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c))) {
            evt.consume(); // Ignorar el carácter ingresado si no es un número o el segundo punto
        }
    }//GEN-LAST:event_txtCantidadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniadir;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JComboBox<String> comboProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaNuevaCompra;
    private javax.swing.JFormattedTextField txtCantidad;
    private javax.swing.JFormattedTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
