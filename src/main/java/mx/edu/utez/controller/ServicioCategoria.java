package mx.edu.utez.controller;

import mx.edu.utez.model.categoria.Categoria;
import mx.edu.utez.model.categoria.DaoCategoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/categoria")
public class ServicioCategoria {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> getCategoria(){
        return new DaoCategoria().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria getCategoriaById(@PathParam("id") int id){
        return new DaoCategoria().findCategoriaById(id);
    }

    @POST
    @Path("/save") //Registra
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoria save(MultivaluedMap<String, String> formParams){
        int id = Integer.parseInt(formParams.get("id").get(0));
        if(new DaoCategoria().insertCategoria(getParams(formParams), true))
            return new DaoCategoria().findCategoriaById(id);
        return null;
    }

    @POST
    @Path("/save/{id}") //Modifica
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoria save(@PathParam("id") int id, MultivaluedMap<String, String> formParams){
        if(new DaoCategoria().insertCategoria(getParams(id, formParams), false))
            return new DaoCategoria().findCategoriaById(id);
        return null;
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCategoria(@PathParam("id") int id){
        return new DaoCategoria().deleteCategoria(id);
    }

    //insertar
    private Categoria getParams(MultivaluedMap<String, String> formParams) {
        String nombre = formParams.get("nombre").get(0);
        Categoria categoria = new Categoria(nombre);
        System.out.println(categoria);
        return categoria;
    }

    //modificar
    private Categoria getParams(int id,MultivaluedMap<String, String> formParams) {
        String nombre = formParams.get("nombre").get(0);
        Categoria categoria = new Categoria(id,nombre);
        System.out.println(categoria);
        return categoria;
    }
}
