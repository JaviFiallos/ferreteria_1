package vista;

import clases.Cliente;
import clases.Credito;
import clases.Kardex;
import clases.Producto_;
import clases.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.KardexDAO;
import modelo.VentaBD;
import modelo.productoDAO_;
import clases.Validacion;
import java.awt.Color;
import java.util.Locale;
import modelo.ClienteBD;
import modelo.CreditoBD;

public class panelVentas extends javax.swing.JPanel {

    private double totalPagar = 0.00;
    private int item;
    private DefaultTableModel modelo6 = new DefaultTableModel();

    private Venta v = new Venta();
    private VentaBD vd = new VentaBD();
    private Kardex k = new Kardex();
    private KardexDAO kd = new KardexDAO();
    private productoDAO_ pd = new productoDAO_();
    private Cliente cl = null;
    private ClienteBD clbd = new ClienteBD();
    private Credito cr = new Credito();
    private CreditoBD crbd = new CreditoBD();
    private String cedVendedor;

    public panelVentas(String ced) {
        initComponents();
        cargarProductos();
        this.txtCedula.setColumns(15);
        cedVendedor = ced;
    }

    public static double calcularPrecioConGananciaIVA(double precioCosto) {
        // Calculamos la ganancia del 20%
        double ganancia = precioCosto * 0.20;

        // Sumamos la ganancia al costo
        double precioConGanancia = precioCosto + ganancia;

        // Calculamos el precio con IVA (suponiendo un IVA del 12%)
        double iva = 0.12; // IVA del 12%
        double precioConIVA = precioConGanancia * (1 + iva); // Multiplicamos por 1 + 0.12

        return precioConIVA;
    }

    private void agregarProducto() {
        item = item + 1;
        this.modelo6 = (DefaultTableModel) this.tablaNuevaCompra.getModel();
        int id = ((Producto_) this.comboProductos.getSelectedItem()).getId();
        String descripcion = ((Producto_) this.comboProductos.getSelectedItem()).getNombre();
        int cantidad = Integer.parseInt(this.txtCantidad.getText());
        double precio = calcularPrecioConGananciaIVA(kd.precioVenta(id));
        double total = precio * cantidad;

        for (int i = 0; i < this.tablaNuevaCompra.getRowCount(); i++) {

            if (this.tablaNuevaCompra.getValueAt(i, 0).equals(((Producto_) this.comboProductos.getSelectedItem()).getId())) {
                JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                return;
            }
        }
        String formattedNumber = String.format(Locale.ENGLISH, "%.2f", precio);

        double pre = Double.parseDouble(formattedNumber);
        String formattedNumber2 = String.format(Locale.ENGLISH, "%.2f", total);

        double tl = Double.parseDouble(formattedNumber2);

        ArrayList a = new ArrayList();
        a.add(item);
        a.add(id);
        a.add(descripcion);
        a.add(cantidad);
        a.add(pre);
        a.add(tl);

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

    private void registrarVenta() {
        v.setTotal(this.totalPagar);
        v.setCedCliente(this.txtCedula.getText());
        v.setCedVendedor(cedVendedor);
        if (vd.registrarVenta(v)) {

            registrarVentaKardex();

            JOptionPane.showMessageDialog(null, "Su compra ha sido realizada con exito");

        } else {
            JOptionPane.showMessageDialog(null, "Su compra no se efectuo :(");
        }

    }

    private void registrarVentaKardex() {

        int idCompra = kd.idVenta();
        int cantE = 0;
        for (int i = 0; i < this.tablaNuevaCompra.getRowCount(); i++) {
            int idProd = Integer.parseInt(this.tablaNuevaCompra.getValueAt(i, 0).toString());
            double precio = Double.parseDouble(this.tablaNuevaCompra.getValueAt(i, 3).toString());
            cantE = Integer.parseInt(this.tablaNuevaCompra.getValueAt(i, 2).toString());//Por producto, esto sera para devoluciones      
            k.setProducto(idProd);
            k.setDetalle("Venta");
            k.setCompra(idCompra);
            k.setPrecioUnitario(precio);
            k.setSalida(cantE);
            kd.agregarKardexVenta(k);
        }
    }

    private void limpiarTotal() {
        this.txtCedula.setText("CEDULA");
        this.txtCedula.setForeground(Color.gray);
        this.txtNombre.setText("NOMBRE");
        this.txtNombre.setForeground(Color.gray);
        this.txtCantidad.setText("");
        this.comboProductos.setSelectedIndex(0);
        limpiarTabla(modelo6);
        this.lblTotal.setText("----");

    }

    private void limpiarParcial() {

        this.txtCantidad.setText("");
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
        btnEliminar = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();
        btnAniadir = new javax.swing.JButton();
        btnCredito = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        lblTotal = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JFormattedTextField();
        lbStock = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JFormattedTextField();
        txtNombre = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 500, 270));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ventas");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CLIENTE");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

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

        btnVender.setBackground(new java.awt.Color(255, 102, 0));
        btnVender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnVender.setForeground(new java.awt.Color(0, 0, 0));
        btnVender.setText("Vender");
        btnVender.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 110, 30));

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
        add(btnAniadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 160, 30));

        btnCredito.setBackground(new java.awt.Color(255, 153, 0));
        btnCredito.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCredito.setForeground(new java.awt.Color(0, 0, 0));
        btnCredito.setText("Credito");
        btnCredito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 51), new java.awt.Color(255, 204, 102), null, null));
        btnCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditoActionPerformed(evt);
            }
        });
        add(btnCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 120, 30));

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("PRODUCTO :");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        comboProductos.setFont(new java.awt.Font("Corbel", 0, 12)); // NOI18N
        comboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });
        add(comboProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 200, -1));

        lblTotal.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 255));
        lblTotal.setText("----");
        add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, -1, -1));

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
        add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 200, -1));

        lbStock.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lbStock.setForeground(new java.awt.Color(0, 0, 0));
        add(lbStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 90, 20));

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("TOTAL A PAGAR:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, -1, -1));

        txtCedula.setForeground(new java.awt.Color(204, 204, 204));
        txtCedula.setText("CEDULA");
        txtCedula.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        txtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedulaFocusLost(evt);
            }
        });
        txtCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCedulaMousePressed(evt);
            }
        });
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
        add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 200, -1));

        txtNombre.setForeground(new java.awt.Color(204, 204, 204));
        txtNombre.setText("NOMBRE");
        txtNombre.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNombreMousePressed(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 200, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Minimo Nombre Apellido");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Opcional");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Opcional");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("CANTIDAD :");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel12.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("STOCK:  ");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        borrarSeleccion(this.tablaNuevaCompra, modelo6);
        this.comboProductos.requestFocus();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditoActionPerformed

        if (this.tablaNuevaCompra.getRowCount() > 0) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de comprar estos productos con Credito");
            if (pregunta == 0) {
                if (!this.txtCedula.getText().equals("CEDULA") && !this.txtNombre.getText().equals("NOMBRE")) {
                    if (Validacion.validadorDeCedula(this.txtCedula.getText()) || Validacion.RucPersonaNatural(this.txtCedula.getText())) {
                        if (Validacion.alMenosDosPalabras(2, this.txtNombre.getText())) {
                            if (cl == null) {
                                Cliente c2 = new Cliente();
                                c2.setCedula(this.txtCedula.getText());
                                c2.setNombre(this.txtNombre.getText().substring(0, this.txtNombre.getText().indexOf(' ')));
                                c2.setApellido(this.txtNombre.getText().substring(this.txtNombre.getText().indexOf(' ')));
                                if (clbd.crearCliente(c2)) {
                                    registrarVenta();
                                    cr.setIdVenta(kd.idVenta());
                                    cr.setDes("Credito");
                                    crbd.crearCredito(cr);
                                    limpiarTotal();
                                }
                            } else {
                                registrarVenta();
                                cr.setIdVenta(kd.idVenta());
                                cr.setDes("Credito");
                                crbd.crearCredito(cr);
                                limpiarTotal();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Minimo nombre y apellido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Celuda no Valida");

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Para esta opcion la cedula y nombre deben estar llenos");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese al menos un producto");
        }

    }//GEN-LAST:event_btnCreditoActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirActionPerformed
        if (!this.txtCantidad.getText().equals("") && this.comboProductos.getSelectedIndex() != 0) {
            if (Validacion.esEntero(this.txtCantidad.getText())) {
                if (Integer.parseInt(this.txtCantidad.getText()) > 0) {
                    if (Integer.parseInt(this.lbStock.getText()) <= Integer.parseInt(this.txtCantidad.getText())) {
                        agregarProducto();
                        limpiarParcial();
                    } else {
                        JOptionPane.showMessageDialog(this, "Stock Insuficiente");
                    }

                    this.comboProductos.requestFocus();
                    totalPagar();
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad no debe ser 0");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un numero entero mayor a cero");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Hay campos Vacios");
        }
    }//GEN-LAST:event_btnAniadirActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        if (this.tablaNuevaCompra.getRowCount() > 0) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de comprar estos productos");
            if (pregunta == 0) {
                if (!this.txtCedula.getText().equals("CEDULA") && !this.txtNombre.getText().equals("NOMBRE")) {
                    if (Validacion.validadorDeCedula(this.txtCedula.getText()) || Validacion.RucPersonaNatural(this.txtCedula.getText())) {
                        if (Validacion.alMenosDosPalabras(2, this.txtNombre.getText())) {
                            if (cl == null) {
                                Cliente c2 = new Cliente();
                                c2.setCedula(this.txtCedula.getText());
                                c2.setNombre(this.txtNombre.getText().substring(0, this.txtNombre.getText().indexOf(' ')));
                                c2.setApellido(this.txtNombre.getText().substring(this.txtNombre.getText().indexOf(' ')));
                                if (clbd.crearCliente(c2)) {
                                    registrarVenta();
                                    limpiarTotal();
                                }
                            } else {
                                registrarVenta();
                                limpiarTotal();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Minimo nombre y apellido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Celuda no Valida");

                    }
                    
                }else {
                    registrarVenta();
                    limpiarTotal();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese al menos un producto");
        }

    }//GEN-LAST:event_btnVenderActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaKeyPressed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        char cnum = evt.getKeyChar();

        if (!Character.isDigit(cnum) || this.txtCedula.getText().length() > 13) {
            evt.consume();
        }

    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        char cnum = evt.getKeyChar();

        if (!Character.isDigit(cnum)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCedulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaMousePressed
        // TODO add your handling code here:
        if (this.txtCedula.getText().equals("CEDULA")) {
            this.txtCedula.setText("");
            this.txtCedula.setForeground(Color.BLACK);
        }
        if (this.txtNombre.getText().isEmpty()) {
            this.txtNombre.setText("NOMBRE");
            this.txtNombre.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtCedulaMousePressed

    private void txtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMousePressed
        // TODO add your handling code here:
        if (this.txtNombre.getText().equals("NOMBRE")) {
            this.txtNombre.setText("");
            this.txtNombre.setForeground(Color.BLACK);
        }
        if (this.txtCedula.getText().isEmpty()) {
            this.txtCedula.setText("CEDULA");
            this.txtCedula.setForeground(Color.gray);

        }
    }//GEN-LAST:event_txtNombreMousePressed

    private void txtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusLost
        // TODO add your handling code here:
        if (!this.txtCedula.getText().equals("CEDULA")) {
            cl = clbd.obPersona(this.txtCedula.getText());
            if (cl != null) {
                this.txtNombre.setText(cl.getNombre() + " " + cl.getApellido());
            }
        }
    }//GEN-LAST:event_txtCedulaFocusLost

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        // TODO add your handling code here:
        if (this.comboProductos.getSelectedItem() != null) {
            if (!this.comboProductos.getSelectedItem().toString().equals("Seleccione")) {
                int id = ((Producto_) this.comboProductos.getSelectedItem()).getId();
                this.lbStock.setText(String.valueOf(kd.obtenerSaldo(id).getStock()));
            }
        }


    }//GEN-LAST:event_comboProductosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniadir;
    private javax.swing.JButton btnCredito;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbStock;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaNuevaCompra;
    private javax.swing.JFormattedTextField txtCantidad;
    private javax.swing.JFormattedTextField txtCedula;
    private javax.swing.JFormattedTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
