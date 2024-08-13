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

import DataAccess.DTO.PersonaDTO;
import Framework.PatException;


public class PersonaDAO extends SQLiteDataHelper implements IDAO<PersonaDTO>{

    @Override
    public PersonaDTO readBy(Integer id) throws Exception {
        PersonaDTO p = new PersonaDTO();
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
         + " FROM Persona"
         + " WHERE Estado = 'A'" + " AND IdPersona = "+id.toString();
        
         try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                p = new PersonaDTO(rs.getInt(1) 
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
    public boolean create(PersonaDTO entity) throws Exception {
        
        String query = " INSERT INTO Persona (Nombre,Apellido,Correo,IdCatalogoSexo,IdLocalidad) VALUES (?,?,?,?,?)";
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
    public List<PersonaDTO> readAll() throws Exception {
        List <PersonaDTO> lts = new ArrayList<>();
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
         + " FROM Persona"
         + " WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();     //conectar a BD
            Statement  stmt = conn.createStatement();   //CRUD: Select *
            ResultSet rs = stmt.executeQuery(query);  //ejecutar la
            while (rs.next()) { 
                PersonaDTO s = new PersonaDTO(  rs.getInt(1) 
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
    public boolean update(PersonaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Catalogo SET Nombre = ?,Apellido=?,Correo=?,IdCatalogoSexo=?,IdCatalogoEstadoCivil=?,IdLocalidad=?,FechaModifica = ? WHERE IdPersona = ?";
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
        String query = " UPDATE Catalogo SET Estado = ? WHERE IdPersona = ?";
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
                     +" FROM    Persona          "
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
