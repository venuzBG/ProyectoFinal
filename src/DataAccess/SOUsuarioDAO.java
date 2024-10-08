package DataAccess;

import DataAccess.DTO.SOUsuarioDTO;
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

public class SOUsuarioDAO extends SQLiteDataHelper implements IDAO<SOUsuarioDTO> {

    @Override
    public SOUsuarioDTO readBy(Integer id) throws Exception {
        SOUsuarioDTO u = new SOUsuarioDTO();
        String query =
         "SELECT IdUsuario"
         + " , Usuario"
         + " , Clave"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM SOPersona"
         + " WHERE Estado = 'A'" + id.toString();
        
         try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                u = new SOUsuarioDTO(rs.getInt(1) 
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
    public boolean create(SOUsuarioDTO entity) throws Exception {
        String query = " INSERT INTO SOUsuario (Usuario,Clave) VALUES (?,?)";
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
    public List<SOUsuarioDTO> readAll() throws Exception {
        List <SOUsuarioDTO> lts = new ArrayList<>();
        String query = 
        "SELECT IdUsuario"
         + " , Usuario"
         + " , Clave"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM SOUsuario"
         + " WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                SOUsuarioDTO s = new SOUsuarioDTO(  rs.getInt(1) 
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
    public boolean update(SOUsuarioDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE SOUsuario SET Usuario = ?,Clave=?,FechaModifica = ? WHERE IdUsuario = ?";
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
        String query = " UPDATE SOCatalogo SET Estado = ? WHERE IdUsuario = ?";
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
                     +" FROM    SOUsuario          "
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

    public SOUsuarioDTO readByUserAndPassword(String usuario, String clave) throws Exception {
        SOUsuarioDTO u = new SOUsuarioDTO();
        String query = 
            "SELECT IdUsuario"
            + " , Usuario"
            + " , Clave"
            + " , Estado"
            + " , FechaCreacion"
            + " , FechaModifica"
            + " FROM SOUsuario"
            + " WHERE Usuario = ? AND Clave = ? AND Estado = 'A'";
    
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                u = new SOUsuarioDTO(rs.getInt(1), 
                                   rs.getString(2),  
                                   rs.getString(3),  
                                   rs.getString(4),  
                                   rs.getString(5),  
                                   rs.getString(6)); 
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readByUserAndPassword()");
        }
        return u;
    }
    
}

