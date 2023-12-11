package clases;

public class Marca {

    private int id;
    private String nombre;
    private String calidad;

    public Marca(int id, String nombre, String calidad) {
        this.id = id;
        this.nombre = nombre;
        this.calidad = calidad;
    }

    public Marca() {
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
        return calidad;
    }

    public void setUbicaion(String ubicaion) {
        this.calidad = ubicaion;
    }

}
