package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.UsuarioDTO;
import Framework.PatException;

public class UsuarioDAO extends SQLiteDataHelper implements IDAO<UsuarioDTO> {

    @Override
    public UsuarioDTO readBy(Integer id) throws Exception {
        UsuarioDTO u = new UsuarioDTO();
        String query =
         "SELECT IdUsuario"
         + " , Usuario"
         + " , Clave"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM Persona"
         + " WHERE Estado = 'A'" + id.toString();
        
         try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                u = new UsuarioDTO(rs.getInt(1) 
                                   ,rs.getString(2)  //Usuario
                                   ,rs.getString(3)  //clave
                                   ,rs.getString(8)  //estado
                                   ,rs.getString(9)  //FechaCrea
                                   ,rs.getString(10)); //FechaModifica/
            }
            
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return u;
    }

    @Override
    public boolean create(UsuarioDTO entity) throws Exception {
        String query = " INSERT INTO Usuario (Usuario,Clave) VALUES (?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getUsuario());   //
            pstmt.setString(2,  entity.getClave());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public List<UsuarioDTO> readAll() throws Exception {
        List <UsuarioDTO> lts = new ArrayList<>();
        String query = 
        "SELECT IdUsuario"
         + " , Usuario"
         + " , Clave"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM Usuario"
         + " WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                UsuarioDTO s = new UsuarioDTO(  rs.getInt(1) 
                                                ,rs.getString(2)   //Usuario
                                                ,rs.getString(3)   //Clave
                                                ,rs.getString(4)   //Estado
                                                ,rs.getString(5)   //FechaCreacion
                                                ,rs.getString(6)); //FechaModifica/
                lts.add(s);
            }

        } catch (SQLException e) {
            
            throw new PatException(e.getMessage(), getClass().getName(), "readAll");
        }
        return lts;
    }

    @Override
    public boolean update(UsuarioDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Usuario SET Usuario = ?,Clave=?,FechaModifica = ? WHERE IdUsuario = ?";
        try {
            Connection conn = openConnection();     //conectar a BD
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getUsuario());
            pstmt.setString(2, entity.getClave());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setInt(8, entity.getIdUsuario());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = " UPDATE Catalogo SET Estado = ? WHERE IdUsuario = ?";
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
                     +" FROM    Usuario          "
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
}

