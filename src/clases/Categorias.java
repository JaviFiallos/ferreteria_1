package clases;

public class Categorias {
    
    private int id;
    private String nombre;
    private String ubicaion;

    public Categorias(int id, String nombre, String ubicaion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicaion = ubicaion;
    }

    public Categorias() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicaion() {
        return ubicaion;
    }

    public void setUbicaion(String ubicaion) {
        this.ubicaion = ubicaion;
    }
    
    
 
}
