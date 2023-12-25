/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import clases.Venta;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Kiwar
 */
public class VentaBD extends Conexion {

    private Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarVenta(Venta v) {

        String sql = "INSERT INTO ventas (TOT_VEN, CED_USU_VEN, CED_CLI_VEN) values (?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, v.getTotal());
            ps.setString(2, v.getCedVendedor());
            ps.setString(3, v.getCedCliente());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }
}
