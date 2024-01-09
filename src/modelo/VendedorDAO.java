package modelo;

import clases.Conexion;
import clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarVendedor(Usuario c) {

        String sql = "INSERT INTO usuarios (CED_USU, CON_USU, NOM_USU, APE_USU, SUE_USU, CEL_USU, ROL_USU) VALUES (?,?,?,?,?,?,?)";
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getContra());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getApellido());
            ps.setDouble(5, c.getSueldo());
            ps.setString(6, c.getCelular());
            ps.setBoolean(7, false);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modificarVendedor(Usuario c) {

        String sql = "UPDATE usuarios SET CON_USU=?, NOM_USU=?, APE_USU=?, SUE_USU=?, CEL_USU=? WHERE CED_USU=?";
        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, c.getContra());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setDouble(4, c.getSueldo());
            ps.setString(5, c.getCelular());
             ps.setString(6, c.getCedula());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean eliminarVendedor(String cedula) {

        String sql = "DELETE FROM usuarios WHERE CED_USU =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            if (ps.executeUpdate()>0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public List listarVendedor() {

        String sql = "SELECT CED_USU, CON_USU, NOM_USU, APE_USU, SUE_USU, CEL_USU FROM usuarios where ROL_USU='0'";
        List<Usuario> lista = new ArrayList<>();

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario pro = new Usuario();
                pro.setCedula(rs.getString("CED_USU"));
                pro.setContra(rs.getString("CON_USU"));
                pro.setNombre(rs.getString("NOM_USU"));
                pro.setApellido(rs.getString("APE_USU"));
                pro.setSueldo(rs.getDouble("SUE_USU"));
                pro.setCelular(rs.getString("CEL_USU"));
                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }
}
