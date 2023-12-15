package modelo;

import clases.Conexion;
import clases.Producto_;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productoDAO_ extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarProducto(Producto_ p) {

        String sql = "INSERT INTO productos (NOM_PRO, ID_MAR_PRO, MOD_PRO, DES_PRO, ID_PRO_GEN) VALUES (?,?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getIdMarca());
            ps.setString(3, p.getModelo());
            ps.setString(4, p.getDescripcion());
            ps.setInt(5, p.getIdProdGen());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modificarProducto(Producto_ p) {

        String sql = "UPDATE productos SET NOM_PRO=?, ID_MAR_PRO=?, MOD_PRO=?, DES_PRO=?, ID_PRO_GEN=? WHERE ID_PRO=?";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getIdMarca());
            ps.setString(3, p.getModelo());
            ps.setString(4, p.getDescripcion());
            ps.setInt(5, p.getIdProdGen());
            ps.setInt(6, p.getId());

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean eliminarProducto(int id) {

        String sql = "DELETE FROM productos WHERE ID_PRO=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.execute()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public List listarProducto() {

        String sql = "SELECT ID_PRO, NOM_PRO FROM productos";
        List<Producto_> lista = new ArrayList<>();

          try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               Producto_ pro = new Producto_();
                
                pro.setId(rs.getInt("ID_PRO"));
                pro.setNombre(rs.getString("NOM_PRO"));

                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }

}

// genericos
/*   
    public boolean registrarProducto(Producto_ p) {

    }

    public boolean modificarProducto(Producto_ p) {

    }

    public boolean eliminarProducto(int id) {

    }

    public List listarCategoria() {
        
    }
 */
