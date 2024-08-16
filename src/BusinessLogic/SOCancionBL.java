package BusinessLogic;

import java.util.List;

import DataAccess.SOCancionDAO;
import DataAccess.DTO.SOCancionDTO;

public class SOCancionBL {
    private SOCancionDTO persona;
    private SOCancionDAO cDAO = new SOCancionDAO();

    public SOCancionBL(){}

    public List<SOCancionDTO> getAll() throws Exception{
        List<SOCancionDTO> lst = cDAO.readAll();
        for (SOCancionDTO cancionDTO : lst) 
            cancionDTO.setCancion(cancionDTO.getCancion());
        return lst;
    }
    public SOCancionDTO getBy(int idPersona) throws Exception{
        persona = cDAO.readBy(idPersona);
        return persona;
    }

    public String getCancionByNombre(int idPersona,String nombreCancion) throws Exception{
        String cancion;
        persona = cDAO.readBy(idPersona);
        cancion = persona.getCancion();
        return cancion;
    }

    public boolean add(SOCancionDTO cancionDTO) throws Exception{   
        return cDAO.create(cancionDTO);
    }
    public boolean update(SOCancionDTO cancionDTO) throws Exception{
        return cDAO.update(cancionDTO);
    }
    public boolean delete(int idPersona) throws Exception{
        return cDAO.delete(idPersona);
    }
    public Integer getRowCount() throws Exception{
        return cDAO.getRowCount();
    }

    public List <SOCancionDTO> getAllBy(int idUsuario) throws Exception{
        List <SOCancionDTO> lst = cDAO.readAllBy(idUsuario);
        return lst;
    }

    // public static void main(String[] args) throws Exception {
    //     CancionBL cBL = new CancionBL();
    //     for (CancionDTO cancionRegistro : cBL.getAllBy(6)) {
    //         System.out.println(cancionRegistro.toString());
    //     }
    // }
}
