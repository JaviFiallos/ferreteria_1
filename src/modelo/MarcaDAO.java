
package modelo;

import clases.Conexion;
import clases.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO extends Conexion{
 
       private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarMarca(Marca c) {

        String sql = "INSERT INTO marcas (NOM_MAR, CAL_MAR) values (?,?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCalidad());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean eliminarMarca(int id) {
        
        String sql = "DELETE FROM marcas WHERE ID_MAR=?";

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

    public List listarMarca() {

        String sql = "SELECT * FROM marcas";
         List<Marca> lista = new ArrayList<>();
         
         try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Marca pro = new Marca();
                
                pro.setId(rs.getInt("ID_MAR"));
                pro.setNombre(rs.getString("NOM_MAR"));
                pro.setCalidad(rs.getString("CAL_MAR"));

                lista.add(pro);
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return lista;
    }
    
}
