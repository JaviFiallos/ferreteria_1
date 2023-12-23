
package clases;


public class Proveedor {
    
    private String ruc, nombre, direccion, telefono;

    public Proveedor(String ruc, String nombre, String direccion, String telefono) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Proveedor(String ruc, String nombre) {
        this.ruc = ruc;
        this.nombre = nombre;
    }
    
    

    public Proveedor() {
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
 
    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}
