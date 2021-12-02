package mx.edu.utez.model.categoria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="categoria")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categoria {
    @XmlElement
    private int id;
    @XmlElement
    private String nombre;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(int id,String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
