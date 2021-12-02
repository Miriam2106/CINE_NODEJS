package mx.edu.utez.model.categoria;

import mx.edu.utez.util.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoria {
    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;

    public List<Categoria> findAll(){ //Consultar todos los productos
        List<Categoria> categorias = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            String query = "SELECT id,nombre FROM categoria;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return categorias;
    }

    public Categoria findCategoriaById(int idCategoria){
        Categoria categoria = new Categoria();
        try{
            con = ConnectionMySQL.getConnection();
            String query = "SELECT id,nombre FROM categoria WHERE id = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,idCategoria);
            rs = pstm.executeQuery();
            if (rs.next()){
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return categoria;
    }

    public boolean insertCategoria(Categoria categoria, boolean insert){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            if(insert){
                String query = "INSERT INTO categoria(nombre) values(?);";
                pstm = con.prepareStatement(query);
                pstm.setString(1, categoria.getNombre());
            }else{
                String query = "UPDATE pelicula SET nombre=? WHERE id = ?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1, categoria.getNombre());
                pstm.setInt(2, categoria.getId());
            }
            flag = pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean deleteCategoria(int idCategoria){
        boolean state = false;
        try{
            con = ConnectionMySQL.getConnection();
            String query = "delete from categoria where id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, idCategoria);
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
