package mx.edu.utez.model.pelicula;

import mx.edu.utez.util.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPelicula {
    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;

    public List<Pelicula> findAll(){
        List<Pelicula> peliculas = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            String query = "SELECT id,titulo,descripcion,sinopsis,rating,fecha_registro, fecha_actualizacion,estado,categoria FROM pelicula;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                Pelicula pelicula = new Pelicula();
                pelicula.setId(rs.getInt("id"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                pelicula.setSinopsis(rs.getString("sinopsis"));
                pelicula.setRating(rs.getInt("rating"));
                pelicula.setFecha_registro(rs.getString("fecha_registro"));
                pelicula.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
                pelicula.setEstado(rs.getInt("estado"));
                pelicula.setCategoria(rs.getInt("categoria"));
                peliculas.add(pelicula);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return peliculas;
    }
    //
    public Pelicula findPeliculaById(int idPelicula){
        Pelicula pelicula = new Pelicula();
        try{
            con = ConnectionMySQL.getConnection();
            String query = "SELECT id,titulo,descripcion,sinopsis,rating,fecha_registro, fecha_actualizacion,estado,categoria FROM pelicula WHERE id = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,idPelicula);
            rs = pstm.executeQuery();
            if (rs.next()){
                pelicula.setId(rs.getInt("id"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                pelicula.setSinopsis(rs.getString("sinopsis"));
                pelicula.setRating(rs.getInt("rating"));
                pelicula.setFecha_registro(rs.getString("fecha_registro"));
                pelicula.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
                pelicula.setEstado(rs.getInt("estado"));
                pelicula.setCategoria(rs.getInt("categoria"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return pelicula;
    }

    public boolean insertPelicula(Pelicula pelicula, boolean insert){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            if(insert){
                String query = "INSERT INTO pelicula(titulo,descripcion,sinopsis,rating,fecha_registro, fecha_actualizacion,estado,categoria) values(?,?,?,?,now(),now(),1,?);";
                pstm = con.prepareStatement(query);
                pstm.setString(1, pelicula.getTitulo());
                pstm.setString(2, pelicula.getDescripcion());
                pstm.setString(3, pelicula.getSinopsis());
                pstm.setInt(4, pelicula.getRating());
                pstm.setInt(5, pelicula.getCategoria());
            }else{
                String query = "UPDATE pelicula SET titulo=?,descripcion=?,sinopsis=?,rating=?,fecha_actualizacion=now(),categoria=? WHERE id = ?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1, pelicula.getTitulo());
                pstm.setString(2, pelicula.getDescripcion());
                pstm.setString(3, pelicula.getSinopsis());
                pstm.setInt(4, pelicula.getRating());
                pstm.setInt(5, pelicula.getCategoria());
                pstm.setInt(6, pelicula.getId());
            }
            flag = pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean deletePelicula(int idPelicula){
        boolean state = false;
        try{
            con = ConnectionMySQL.getConnection();
            String query = "delete from pelicula where id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idPelicula);
            state = pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return state;
    }

    public void closeConnection(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
            if(statement != null){
                statement.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
