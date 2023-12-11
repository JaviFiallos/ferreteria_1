package modelo;

import clases.Proveedor;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarProveedor(Proveedor c) {

        String sql = "INSERT INTO proveedores (RUC_PROV, NOM_PROV, DIR_PROV, TEL_PROV) VALUES (?,?,?,?)";
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, c.getRuc());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getTelefono());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modificarProveedor(Proveedor c) {

        String sql = "UPDATE proveedores SET NOM_PROV=?, DIR_PROV=?, TEL_PROV=? WHERE RUC_PROV=?";
        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getRuc());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean eliminarProveedor(String cedula) {

        String sql = "DELETE FROM proveedores WHERE RUC_PROV =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            if (ps.execute()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public List listarProveedor() {

        String sql = "SELECT * FROM proveedores";
        List<Proveedor> lista = new ArrayList<>();

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pro = new Proveedor();
                pro.setRuc(rs.getString(1));
                pro.setNombre(rs.getString(2));
                pro.setDireccion(rs.getString(3));
                pro.setTelefono(rs.getString(4));
                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }

}
