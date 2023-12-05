package modelo;

import clases.Usuario;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UsuarioDAO extends Conexion {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarUsuario(Usuario u) {
        return false;
    }

    public boolean iniciarSesion(Usuario u) {

        con = this.getConexion();
        String sql = "SELECT * FROM usuarios WHERE CED_USU=? AND CON_USU=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getCedula());
            ps.setString(2, u.getContra());
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setCedula(rs.getString("CED_USU"));
                u.setNombre(rs.getString("NOM_USU"));
                u.setApellido(rs.getString("APE_USU"));
                u.setSueldo(rs.getInt("SUE_USU"));
                u.setCelular(rs.getString("CEL_USU"));
                u.setRol(rs.getBoolean("ROL_USU"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return false;

    }

}
