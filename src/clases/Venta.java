/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Kiwar
 */
public class Venta {

    private int id;
    private double total;
    private String cedVendedor, cedCliente, fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCedVendedor() {
        return cedVendedor;
    }

    public void setCedVendedor(String cedVendedor) {
        this.cedVendedor = cedVendedor;
    }

    public String getCedCliente() {
        return cedCliente;
    }

    public void setCedCliente(String cedCliente) {
        this.cedCliente = cedCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
