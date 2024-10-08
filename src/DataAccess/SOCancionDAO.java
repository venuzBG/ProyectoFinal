package DataAccess;

import DataAccess.DTO.SOCancionDTO;
import Framework.PatException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class SOCancionDAO extends SQLiteDataHelper implements IDAO<SOCancionDTO>{

    @Override
    public SOCancionDTO readBy(Integer id) throws Exception {
        SOCancionDTO c = new SOCancionDTO();
        String query =
         "SELECT IdCancion"
         + " , IdPersona"
         + " , Nombre"
         + " , Cancion"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM SOCancion"
         + " WHERE Estado = 'A'" + "AND IdCancion = "+id.toString();
        
         try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                c = new SOCancionDTO(rs.getInt(1) 
                                   ,rs.getInt(2) //IdUsuario
                                   ,rs.getString(3)  //nombre
                                   ,rs.getString(4)  //Cancion
                                   ,rs.getString(5)  //estado
                                   ,rs.getString(6)  //FechaCrea
                                   ,rs.getString(7)); //FechaModifica/
            }
            
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return c;
    }

    @Override
    public boolean create(SOCancionDTO entity) throws Exception {
        String query = " INSERT INTO SOCancion (IdPersona,Nombre,Cancion) VALUES (?,?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdPersona());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getCancion());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public List<SOCancionDTO> readAll() throws Exception {
         List <SOCancionDTO> lts = new ArrayList<>();
        String query = 
        "SELECT IdCancion"
         + " , IdPersona"
         + " , Nombre"
         + " , Cancion"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM SOCancion"
         + " WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                SOCancionDTO s = new SOCancionDTO(  rs.getInt(1) 
                                                ,rs.getInt(2) //IdUsuario
                                                ,rs.getString(3)  //Nombre
                                                ,rs.getString(4)  //Cancion
                                                ,rs.getString(5)  //estado
                                                ,rs.getString(6)  //FechaCrea
                                                ,rs.getString(7)); //FechaModifica/
                lts.add(s);
            }

        } catch (SQLException e) {
            
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lts;
    }

    @Override
    public boolean update(SOCancionDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE SOCancion SET IdPersona = ?,Nombre = ? ,Cancion=?,FechaModifica = ? WHERE IdCancion = ?";
        try {
            Connection conn = openConnection();     //conectar a BD
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdPersona());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getCancion());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setInt(5, entity.getIdCancion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = " UPDATE SOCancion SET Estado = ? WHERE IdCancion = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement  pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getRowCount()  throws Exception  {
        String query =" SELECT COUNT(*) TotalReg "
                     +" FROM    SOCancion          "
                     +" WHERE   Estado ='A'      ";
        try {
            Connection conn = openConnection();             
            Statement  stmt = conn.createStatement();       
            ResultSet rs   = stmt.executeQuery(query);  
            while (rs.next()) {
                return rs.getInt(1);                   
            }
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "getRowCount()");
        }
        return 0;
    }

    public List<SOCancionDTO> readAllBy(Integer idPersona) throws Exception {
        List <SOCancionDTO> lts = new ArrayList<>();
       String query = 
       "SELECT IdCancion"
        + " , IdPersona"
        + " , Nombre"
        + " , Cancion"
        + " , Estado"
        + " , FechaCreacion"
        + " , FechaModifica"
        + " FROM SOCancion"
        + " WHERE Estado = 'A' AND IdPersona = " + idPersona.toString();
       try {
           Connection conn = openConnection();     //conectar a BD
           Statement  stmt = conn.createStatement();   //CRUD: Select *
           ResultSet rs = stmt.executeQuery(query);  //ejecutar la
           while (rs.next()) { 
               SOCancionDTO s = new SOCancionDTO(  rs.getInt(1) 
                                               ,rs.getInt(2) //IdUsuario
                                               ,rs.getString(3)  //Nombre
                                               ,rs.getString(4)  //Cancion
                                               ,rs.getString(5)  //estado
                                               ,rs.getString(6)  //FechaCrea
                                               ,rs.getString(7)); //FechaModifica/
               lts.add(s);
           }

       } catch (SQLException e) {
           
           throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
       }
       return lts;
   }

}

