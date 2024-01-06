package controlador;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import clases.Conexion;
import modelo.UsuarioDAO;
import clases.Usuario;
import modelo.usuarioEstatico;
import vista.FrameAdministrador;
import vista.FrameVenta;
import vista.frameLogin1;

public class configVentanaLog implements MouseListener {

    public frameLogin1 fr = new frameLogin1();
    Conexion cn = new Conexion();

    public configVentanaLog() {

        this.fr.etiLog.addMouseListener(this);
        this.fr.etiCerrar.addMouseListener(this);
        this.fr.txtCedula.addMouseListener(this);
        this.fr.txtContra.addMouseListener(this);

    }

    public void iniciar() {

        this.fr.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent mc) {

        if (mc.getSource() == fr.etiCerrar) {
            System.exit(0);
        }

        if (mc.getSource() == fr.etiLog) {

            UsuarioDAO cu = new UsuarioDAO();

            //========Creacion del usuario EStatico===========
            Usuario mu = new usuarioEstatico().usuarioEstatico;
            //==========Fecha===================
            Date fecha = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //=================================
            String contra = new String(this.fr.txtContra.getPassword());
            String usuario = this.fr.txtCedula.getText();
            mu.setCedula(usuario);
            mu.setContra(contra);

            if (!contra.isEmpty() && !usuario.isEmpty()) {

                //cifrado de contraseña
//                String contraEncrip = hash.sha1(contra);
//                mu.setClave(contraEncrip);
//                mu.setUsuario(usuario);
//                mu.setSesion_u(formatoFecha.format(fecha));
                //##############################################################
                if (cn.testConnection()) {
                    if (cu.iniciarSesion(mu)) {
                        this.fr.dispose();
                        if(mu.isRol()){
                            FrameAdministrador fa = new FrameAdministrador();
                            fa.setVisible(true);
                        }else{
                            FrameVenta fv = new FrameVenta();
                            fv.setVisible(true);
                        }
                    }else{
                    JOptionPane.showMessageDialog(null, "Datos Incorrectos");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No hay conexion con el servidor, cierre el programa e intentelo de nuevo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent mp
    ) {

        if (mp.getSource() == fr.txtCedula) {
            reinicarCedula();
        }
        if (mp.getSource() == fr.txtContra) {
            reiniciarContra();
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0
    ) {

    }

    @Override
    public void mouseEntered(MouseEvent me
    ) {
        if (me.getSource() == fr.etiCerrar) {
            fr.panelCerrar.setBackground(new Color(202, 7, 4));
        }

        if (me.getSource() == fr.etiLog) {
            fr.panelLog.setBackground(new Color(160, 2, 2));//160, 2, 2//204,0,51
        }
    }

    @Override
    public void mouseExited(MouseEvent mex
    ) {
        if (mex.getSource() == fr.etiCerrar) {
            fr.panelCerrar.setBackground(new Color(41, 41, 41));
        }
        if (mex.getSource() == fr.etiLog) {
            fr.panelLog.setBackground(new Color(202, 7, 4));
        }
    }

    private void reinicarCedula() {

        if (fr.txtCedula.getText().equals("Ingrese su nombre de Usuario")) {
            fr.txtCedula.setText("");
            fr.txtCedula.setForeground(Color.black);
        }
        if (String.valueOf(fr.txtContra.getPassword()).isEmpty()) {

            fr.txtContra.setText("**********");
            fr.txtContra.setForeground(Color.gray);
        }
    }

    private void reiniciarContra() {

        if (String.valueOf(fr.txtContra.getPassword()).equals("**********")) {
            fr.txtContra.setText("");
            fr.txtContra.setForeground(Color.black);
        }
        if (fr.txtCedula.getText().isEmpty()) {
            fr.txtCedula.setText("Ingrese su nombre de Usuario");
            fr.txtCedula.setForeground(Color.gray);
        }
    }

}
