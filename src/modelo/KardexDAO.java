/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import clases.Conexion;
import clases.Kardex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class KardexDAO extends Conexion {

    private final Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public Kardex obtenerSaldo(int id) {

        Kardex pro = new Kardex();
        String sql = "SELECT STOCK FROM kardex WHERE ID_PRO = ? ORDER BY ID_KAR DESC LIMIT 1";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setStock(rs.getInt(1));
            } else {
                System.out.println("No hay resultados");
                pro.setStock(0);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return pro;
    }

    public boolean agregarKardexCompra(Kardex k) {

        String sql = "INSERT INTO kardex (ID_PRO, ID_COM, ENTRADA, DETALLE, PRECIO, STOCK) values (?,?,?,?,?,?)";
        try {

            int saldo = obtenerSaldo(k.getProducto()).getStock() + k.getEntrada();

            ps = con.prepareStatement(sql);
            ps.setInt(1, k.getProducto());
            ps.setInt(2, k.getCompra());
            ps.setInt(3, k.getEntrada());
            ps.setString(4, k.getDetalle());
            ps.setDouble(5, k.getPrecioUnitario());
            ps.setInt(6, saldo);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public int idCompra() {
        int id = 0;
        String sql = "SELECT MAX(ID_COM) FROM compras";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }

}
