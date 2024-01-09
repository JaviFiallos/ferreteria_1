/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import clases.Cliente;
import clases.Conexion;
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
public class ClienteBD extends Conexion {

    private final Connection con = getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public Cliente obPersona(String id) {

        Cliente pro = new Cliente();
        String sql = "SELECT CED_CLI,NOM_CLI,APE_CLI FROM clientes WHERE CED_CLI = ? ";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setCedula(rs.getString(1));
                pro.setNombre(rs.getString(2));
                pro.setApellido(rs.getString(3));
                return pro;
            } else {
                System.out.println("No hay resultados");
                return null;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return null;
    }

    public boolean crearCliente(Cliente c) {

        String sql = "INSERT INTO clientes (CED_CLI, NOM_CLI, APE_CLI) values (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public List listarPersona() {

        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT CED_CLI,NOM_CLI,APE_CLI FROM clientes where CED_CLI != 'Cliente final'";

        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Cliente pro = new Cliente();
                pro.setCedula(rs.getString(1));
                pro.setNombre(rs.getString(2));
                pro.setApellido(rs.getString(3));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return lista;
    }

}
