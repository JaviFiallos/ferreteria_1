package vista;


public class frameLogin1 extends javax.swing.JFrame {

    private int xMouse;
    private int yMouse;

    public frameLogin1() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        subPanelFondo2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        etiUsuario = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        etiContra = new javax.swing.JLabel();
        txtContra = new javax.swing.JPasswordField();
        panelLog = new javax.swing.JPanel();
        etiLog = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        etiLogoJava = new javax.swing.JLabel();
        panelFondoBlanco = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        panelCerrar = new javax.swing.JPanel();
        etiCerrar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelFondoRosa = new javax.swing.JPanel();
        etiLogoUta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        etiContacto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(750, 600));

        panelBg.setBackground(new java.awt.Color(255, 255, 255));
        panelBg.setPreferredSize(new java.awt.Dimension(750, 600));
        panelBg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subPanelFondo2.setBackground(new java.awt.Color(255, 249, 243));
        subPanelFondo2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        jLabel4.setText("INICIAR SESION");
        subPanelFondo2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        etiUsuario.setBackground(new java.awt.Color(0, 0, 0));
        etiUsuario.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        etiUsuario.setText("USUARIO :");
        subPanelFondo2.add(etiUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtCedula.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(153, 153, 153));
        txtCedula.setText("Ingrese su nombre de Usuario");
        txtCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        subPanelFondo2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 230, 30));

        etiContra.setBackground(new java.awt.Color(0, 0, 0));
        etiContra.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        etiContra.setText("CONTRASEÑA :");
        subPanelFondo2.add(etiContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtContra.setForeground(new java.awt.Color(153, 153, 153));
        txtContra.setText("**********");
        txtContra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));
        subPanelFondo2.add(txtContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 230, 30));

        panelLog.setBackground(new java.awt.Color(202, 7, 4));
        panelLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));

        etiLog.setBackground(new java.awt.Color(255, 255, 255));
        etiLog.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        etiLog.setForeground(new java.awt.Color(255, 255, 255));
        etiLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiLog.setText("ENTRAR");
        etiLog.setToolTipText("");
        etiLog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout panelLogLayout = new javax.swing.GroupLayout(panelLog);
        panelLog.setLayout(panelLogLayout);
        panelLogLayout.setHorizontalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(etiLog, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelLogLayout.setVerticalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addComponent(etiLog, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        subPanelFondo2.add(panelLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 110, 40));

        jPanel1.add(subPanelFondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 290, 340));

        jPanel2.setBackground(new java.awt.Color(255, 239, 206));

        etiLogoJava.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sello/logoJava-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(etiLogoJava)
                .addGap(81, 81, 81))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(etiLogoJava)
                .addGap(47, 47, 47))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 220));

        panelBg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 290, 560));

        panelFondoBlanco.setBackground(new java.awt.Color(242, 244, 246));
        panelFondoBlanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topPanel.setBackground(new java.awt.Color(129, 26, 22));
        topPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        topPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topPanelMouseDragged(evt);
            }
        });
        topPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topPanelMousePressed(evt);
            }
        });

        panelCerrar.setBackground(new java.awt.Color(41, 41, 41));
        panelCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        etiCerrar.setBackground(new java.awt.Color(0, 0, 0));
        etiCerrar.setFont(new java.awt.Font("Roboto Light", 1, 24)); // NOI18N
        etiCerrar.setForeground(new java.awt.Color(255, 255, 255));
        etiCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiCerrar.setText("X");

        javax.swing.GroupLayout panelCerrarLayout = new javax.swing.GroupLayout(panelCerrar);
        panelCerrar.setLayout(panelCerrarLayout);
        panelCerrarLayout.setHorizontalGroup(
            panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCerrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(etiCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCerrarLayout.setVerticalGroup(
            panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCerrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(etiCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(panelCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 710, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelFondoBlanco.add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\loginRegistro\\src\\imagenes\\sello\\toyotaLogo.png")); // NOI18N
        panelFondoBlanco.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 330, 220));

        panelBg.add(panelFondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 340));

        panelFondoRosa.setBackground(new java.awt.Color(255, 217, 216));
        panelFondoRosa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiLogoUta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiLogoUta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sello/logoUta.png"))); // NOI18N
        panelFondoRosa.add(etiLogoUta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, 130));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sello/logoFisei-removebg-preview.png"))); // NOI18N
        panelFondoRosa.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 220, 70));

        bottomPanel.setBackground(new java.awt.Color(129, 26, 22));
        bottomPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiContacto.setFont(new java.awt.Font("Roboto Light", 2, 12)); // NOI18N
        etiContacto.setForeground(new java.awt.Color(255, 255, 255));
        etiContacto.setText("email:  fiallosjavier1e@gmail.com        celular:   0987545512");
        bottomPanel.add(etiContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 330, -1));

        panelFondoRosa.add(bottomPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 750, 40));

        panelBg.add(panelFondoRosa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 750, 260));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBg, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void topPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_topPanelMousePressed

    private void topPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topPanelMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_topPanelMouseDragged

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    public javax.swing.JLabel etiCerrar;
    private javax.swing.JLabel etiContacto;
    private javax.swing.JLabel etiContra;
    public javax.swing.JLabel etiLog;
    private javax.swing.JLabel etiLogoJava;
    private javax.swing.JLabel etiLogoUta;
    private javax.swing.JLabel etiUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelBg;
    public javax.swing.JPanel panelCerrar;
    private javax.swing.JPanel panelFondoBlanco;
    private javax.swing.JPanel panelFondoRosa;
    public javax.swing.JPanel panelLog;
    private javax.swing.JPanel subPanelFondo2;
    public javax.swing.JPanel topPanel;
    public javax.swing.JTextField txtCedula;
    public javax.swing.JPasswordField txtContra;
    // End of variables declaration//GEN-END:variables
}
