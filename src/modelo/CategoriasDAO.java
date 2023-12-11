package modelo;

import clases.Categorias;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarCategoria(Categorias c) {

        String sql = "INSERT INTO categorias (NOM_CAT, UBI_CAT) values (?,?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getUbicaion());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean eliminarCategoria(int id) {
        
        String sql = "DELETE FROM categorias WHERE ID_CAT=?";

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

    public List listarCategoria() {

        String sql = "SELECT * FROM categorias";
         List<Categorias> lista = new ArrayList<>();
         
         try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorias pro = new Categorias();
                
                pro.setId(rs.getInt("ID_CAT"));
                pro.setNombre(rs.getString("NOM_CAT"));
                pro.setUbicaion(rs.getString("UBI_CAT"));

                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }
}
