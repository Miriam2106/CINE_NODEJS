package mx.edu.utez.controller;

import mx.edu.utez.model.pelicula.DaoPelicula;
import mx.edu.utez.model.pelicula.Pelicula;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/pelicula")
public class ServicioPelicula {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pelicula> getPeliculas(){
        return new DaoPelicula().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pelicula getPeliculas(@PathParam("id") int id){
        return new DaoPelicula().findPeliculaById(id);
    }

    @POST
    @Path("/save") //Registra
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public String save(MultivaluedMap<String, String> formParams){
        int id = Integer.parseInt(formParams.get("id").get(0));
        if(new DaoPelicula().insertPelicula(getParams(formParams), true))
            return "exitoso";
        return null;
    }

    @POST
    @Path("/save/{id}") //Modifica
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Pelicula save(@PathParam("id") int id, MultivaluedMap<String, String> formParams){
        if(new DaoPelicula().insertPelicula(getParams(id, formParams), false))
            return new DaoPelicula().findPeliculaById(id);
        return null;
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deletePelicula(@PathParam("id") int id){
        return new DaoPelicula().deletePelicula(id);
    }

    private Pelicula getParams(int id, MultivaluedMap<String, String> formParams) {
        String titulo = formParams.get("titulo").get(0);
        String descripcion = formParams.get("descripcion").get(0);
        String sinopsis = formParams.get("sinopsis").get(0);
        int rating = Integer.parseInt(formParams.get("rating").get(0));
        String fecha_registro = formParams.get("fecha_registro").get(0);
        String fecha_actualizacion = formParams.get("fecha_actualizacion").get(0);
        int estado = Integer.parseInt(formParams.get("estado").get(0));
        int categoria = Integer.parseInt(formParams.get("categoria").get(0));

        Pelicula pelicula = new Pelicula(id,titulo, descripcion, sinopsis, rating, fecha_registro, fecha_actualizacion, estado, categoria);
        System.out.println(pelicula);
        return pelicula;
    }

    private Pelicula getParams(MultivaluedMap<String, String> formParams) {
        String titulo = formParams.get("titulo").get(0);
        String descripcion = formParams.get("descripcion").get(0);
        String sinopsis = formParams.get("sinopsis").get(0);
        int rating = Integer.parseInt(formParams.get("rating").get(0));
        String fecha_registro = formParams.get("fecha_registro").get(0);
        String fecha_actualizacion = formParams.get("fecha_actualizacion").get(0);
        int estado = Integer.parseInt(formParams.get("estado").get(0));
        int categoria = Integer.parseInt(formParams.get("categoria").get(0));

        Pelicula pelicula = new Pelicula(titulo, descripcion, sinopsis, rating, fecha_registro, fecha_actualizacion, estado, categoria);
        System.out.println(pelicula);
        return pelicula;
    }
}
