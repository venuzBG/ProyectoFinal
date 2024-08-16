package BusinessLogic;

import DataAccess.DTO.SOPersonaDTO;
import DataAccess.SOPersonaDAO;
import java.util.List;

public class SOPersonaBL {
    private SOPersonaDTO persona;
    private SOPersonaDAO pDAO = new SOPersonaDAO();

    public SOPersonaBL(){}

    public List<SOPersonaDTO> getAll() throws Exception{
        List<SOPersonaDTO> lst = pDAO.readAll();
        for (SOPersonaDTO personaDTO : lst) 
            personaDTO.setNombre(personaDTO.getNombre().toUpperCase());
        return lst;
    }
    public SOPersonaDTO getBy(int idPersona) throws Exception{
        persona = pDAO.readBy(idPersona);
        return persona;
    }
    public boolean add(SOPersonaDTO personaDTO) throws Exception{   
        return pDAO.create(personaDTO);
    }
    public boolean update(SOPersonaDTO personaDTO) throws Exception{
        return pDAO.update(personaDTO);
    }
    public boolean delete(int idPersona) throws Exception{
        return pDAO.delete(idPersona);
    }
    public Integer getRowCount() throws Exception{
        return pDAO.getRowCount();
    }

    public boolean correoExiste(String correo) throws Exception {
        List<SOPersonaDTO> personas = pDAO.readAll();
        for (SOPersonaDTO persona : personas) {
            if (persona.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }
}
