package mx.edu.utez.model.pelicula;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pelicula")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pelicula {
    @XmlElement
    private int id;
    @XmlElement
    private String titulo;
    @XmlElement
    private String descripcion;
    @XmlElement
    private String sinopsis;
    @XmlElement
    private int rating;
    @XmlElement
    private String fecha_registro;
    @XmlElement
    private String fecha_actualizacion;
    @XmlElement
    private int estado;
    @XmlElement
    private int categoria;

    public Pelicula() {
    }

    public Pelicula(String titulo, String descripcion, String sinopsis, int rating, String fecha_registro, String fecha_actualizacion, int estado, int categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sinopsis = sinopsis;
        this.rating = rating;
        this.fecha_registro = fecha_registro;
        this.fecha_actualizacion = fecha_actualizacion;
        this.estado = estado;
        this.categoria = categoria;
    }

    public Pelicula(int id, String titulo, String descripcion, String sinopsis, int rating, String fecha_registro, String fecha_actualizacion, int estado, int categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sinopsis = sinopsis;
        this.rating = rating;
        this.fecha_registro = fecha_registro;
        this.fecha_actualizacion = fecha_actualizacion;
        this.estado = estado;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}