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

import DataAccess.DTO.SOPersonaDTO;
import Framework.PatException;


public class SOPersonaDAO extends SQLiteDataHelper implements IDAO<SOPersonaDTO>{

    @Override
    public SOPersonaDTO readBy(Integer id) throws Exception {
        SOPersonaDTO p = new SOPersonaDTO();
        String query =
         "SELECT IdPersona"
         + " , Nombre"
         + " , Apellido"
         + " , Correo"
         + " , IdCatalogoSexo"
         + " , IdLocalidad"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM SOPersona"
         + " WHERE Estado = 'A'" + " AND IdPersona = "+id.toString();
        
         try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                p = new SOPersonaDTO(rs.getInt(1) 
                                   ,rs.getString(2)  //nombre
                                   ,rs.getString(3)  //apellido
                                   ,rs.getString(4)  //correo
                                   ,rs.getInt(5)     //IdCatalogoSexo
                                   ,rs.getInt(6)  //IdLocalidad
                                   ,rs.getString(7)  //estado
                                   ,rs.getString(8)  //FechaCrea
                                   ,rs.getString(9)); //FechaModifica/
            }
            
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return p;

    }

    @Override
    public boolean create(SOPersonaDTO entity) throws Exception {
        
        String query = " INSERT INTO SOPersona (Nombre,Apellido,Correo,IdCatalogoSexo,IdLocalidad) VALUES (?,?,?,?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());   //
            pstmt.setString(2,  entity.getApellido());
            pstmt.setString(3, entity.getCorreo());
            pstmt.setInt(4, entity.getIdCatalogoSexo());
            pstmt.setInt(5, entity.getIdLocalidad());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public List<SOPersonaDTO> readAll() throws Exception {
        List <SOPersonaDTO> lts = new ArrayList<>();
        String query = 
        "SELECT IdPersona"
         + " , Nombre"
         + " , Apellido"
         + " , Correo"
         + " , IdCatalogoSexo"
         + " , IdLocalidad"
         + " , Estado"
         + " , FechaCreacion"
         + " , FechaModifica"
         + " FROM SOPersona"
         + " WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                SOPersonaDTO s = new SOPersonaDTO(  rs.getInt(1) 
                                                ,rs.getString(2)    //nombre
                                                ,rs.getString(3)    //apellido
                                                ,rs.getString(4)    //correo
                                                ,rs.getInt(5)       //IdCatalogoSexo
                                                ,rs.getInt(6)       //IdLocalidad
                                                ,rs.getString(7)    //estado
                                                ,rs.getString(8)    //FechaCrea
                                                ,rs.getString(9)); //FechaModifica/
                lts.add(s);
            }

        } catch (SQLException e) {
            
            throw new PatException(e.getMessage(), getClass().getName(), "readAll");
        }
        return lts;
    }

    @Override
    public boolean update(SOPersonaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE SOCatalogo SET Nombre = ?,Apellido=?,Correo=?,IdCatalogoSexo=?,IdCatalogoEstadoCivil=?,IdLocalidad=?,FechaModifica = ? WHERE IdPersona = ?";
        try {
            Connection conn = openConnection();     //conectar a BD
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getCorreo());
            pstmt.setInt(4, entity.getIdCatalogoSexo());
            pstmt.setInt(6, entity.getIdLocalidad());
            pstmt.setString(7, dtf.format(now).toString());
            pstmt.setInt(8, entity.getIdPersona());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = " UPDATE SOCatalogo SET Estado = ? WHERE IdPersona = ?";
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
                     +" FROM    SOPersona        "
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
