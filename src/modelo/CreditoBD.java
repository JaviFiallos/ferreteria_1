/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import clases.Conexion;
import clases.Credito;
import clases.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Kiwar
 */
public class CreditoBD extends Conexion{
    private final Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;


    public boolean crearCredito(Credito c){
        
        String sql = "INSERT INTO creditos  (ID_VEN_CRE, DESC_CRE) values (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,c.getIdVenta());
            ps.setString(2, c.getDes());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public List creditosPorCliente(String ced) {
        List<Venta> lista = new ArrayList<>();
        Venta pro = new Venta();
        String sql = """
                     SELECT v.*
                     FROM ventas v
                     JOIN creditos c ON v.ID_VEN = c.ID_VEN_CRE
                     WHERE v.CED_CLI_VEN = ? AND c.EST_CRE = '0';
                     """;

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, ced);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setTotal(rs.getDouble(2));
                pro.setFecha(rs.getString(3));
                pro.setCedVendedor(rs.getString(4));
                pro.setCedCliente(rs.getString(5));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            System.out.println( ex);
        }
        return lista;
    }
    public boolean pagarCredito(int c){
        
        String sql = "UPDATE creditos set EST_CRE=? WHERE ID_VEN_CRE = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2,c);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
