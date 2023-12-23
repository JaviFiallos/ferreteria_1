package modelo;

import clases.Conexion;
import clases.Genericos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class GenericosDAO extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarGenericos(Genericos c) {

        String sql = "INSERT INTO productos_genericos (NOM_PRO_GEN, DES_PRO_GEN, ID_CAT_GEN) values (?,?,?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getCategoria());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modificarGenericos(Genericos c) {

        String sql = "UPDATE productos_genericos SET NOM_PRO_GEN=?, DES_PRO_GEN=?, ID_CAT_GEN=? WHERE ID_PRO_GEN=?";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getCategoria());
            ps.setInt(4, c.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean eliminarGenericos(int id) {

        String sql = "DELETE FROM productos_genericos WHERE ID_PRO_GEN=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public List listarGenericos() {

        String sql = "SELECT * FROM productos_genericos";
        List<Genericos> lista = new ArrayList<>();

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Genericos pro = new Genericos();

                pro.setId(rs.getInt("ID_PRO_GEN"));
                pro.setNombre(rs.getString("NOM_PRO_GEN"));
                pro.setDescripcion(rs.getString("DES_PRO_GEN"));
                pro.setCategoria(rs.getInt("ID_CAT_GEN"));
                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }

    public void listarQuery(DefaultTableModel modelo, String sql) {

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantColumnas = rsMD.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[cantColumnas];
                for (int i = 0; i < cantColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }

}
