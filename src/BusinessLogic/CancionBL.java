package BusinessLogic;

import java.util.List;

import DataAccess.CancionDAO;
import DataAccess.DTO.CancionDTO;

public class CancionBL {
    private CancionDTO persona;
    private CancionDAO cDAO = new CancionDAO();

    public CancionBL(){}

    public List<CancionDTO> getAll() throws Exception{
        List<CancionDTO> lst = cDAO.readAll();
        for (CancionDTO cancionDTO : lst) 
            cancionDTO.setCancion(cancionDTO.getCancion());
        return lst;
    }
    public CancionDTO getBy(int idPersona) throws Exception{
        persona = cDAO.readBy(idPersona);
        return persona;
    }

    public String getCancionByNombre(int idPersona,String nombreCancion) throws Exception{
        String cancion;
        persona = cDAO.readBy(idPersona);
        cancion = persona.getCancion();
        return cancion;
    }

    public boolean add(CancionDTO cancionDTO) throws Exception{   
        return cDAO.create(cancionDTO);
    }
    public boolean update(CancionDTO cancionDTO) throws Exception{
        return cDAO.update(cancionDTO);
    }
    public boolean delete(int idPersona) throws Exception{
        return cDAO.delete(idPersona);
    }
    public Integer getRowCount() throws Exception{
        return cDAO.getRowCount();
    }

    public List <CancionDTO> getAllBy(int idUsuario) throws Exception{
        List <CancionDTO> lst = cDAO.readAllBy(idUsuario);
        return lst;
    }

    // public static void main(String[] args) throws Exception {
    //     CancionBL cBL = new CancionBL();
    //     for (CancionDTO cancionRegistro : cBL.getAllBy(6)) {
    //         System.out.println(cancionRegistro.toString());
    //     }
    // }
}
