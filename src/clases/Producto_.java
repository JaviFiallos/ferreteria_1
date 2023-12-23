
package clases;


public class Producto_ {
    
    private int id;
    private String nombre;
    private int idMarca;
    private int idProdGen;
    private String modelo;
    private String descripcion;

    public Producto_() {
    }

    public Producto_(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Producto_(String nombre, int idMarca, int idProdGen, String modelo, String descripcion) {
        this.nombre = nombre;
        this.idMarca = idMarca;
        this.idProdGen = idProdGen;
        this.modelo = modelo;
        this.descripcion = descripcion;
    }

    public Producto_(int id, String nombre, int idMarca, int idProdGen, String modelo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.idMarca = idMarca;
        this.idProdGen = idProdGen;
        this.modelo = modelo;
        this.descripcion = descripcion;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdProdGen() {
        return idProdGen;
    }

    public void setIdProdGen(int idProdGen) {
        this.idProdGen = idProdGen;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
