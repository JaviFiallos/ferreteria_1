package modelo;

import clases.Compra;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarCompra(Compra c) {

        String sql = "INSERT INTO compras (TOT_COM, RUC_PROV_COM) values (?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, c.getTotal());
            ps.setString(2, c.getVendedor());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

}
