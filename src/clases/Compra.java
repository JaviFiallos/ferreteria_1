
package clases;

public class Compra {
   
    private int id;
    private double total;
    private String vendedor;

    public Compra() {
    }

    public Compra(double total, String vendedor) {
        this.total = total;
        this.vendedor = vendedor;
    }

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

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
