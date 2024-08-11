package BusinessLogic;

import DataAccess.DTO.PersonaDTO;
import DataAccess.PersonaDAO;
import java.util.List;

public class PersonaBL {
    private PersonaDTO persona;
    private PersonaDAO pDAO = new PersonaDAO();

    public PersonaBL(){}

    public List<PersonaDTO> getAll() throws Exception{
        List<PersonaDTO> lst = pDAO.readAll();
        for (PersonaDTO personaDTO : lst) 
            personaDTO.setNombre(personaDTO.getNombre().toUpperCase());
        return lst;
    }
    public PersonaDTO getBy(int idPersona) throws Exception{
        persona = pDAO.readBy(idPersona);
        return persona;
    }
    public boolean add(PersonaDTO personaDTO) throws Exception{   
        return pDAO.create(personaDTO);
    }
    public boolean update(PersonaDTO personaDTO) throws Exception{
        return pDAO.update(personaDTO);
    }
    public boolean delete(int idPersona) throws Exception{
        return pDAO.delete(idPersona);
    }
    public Integer getRowCount() throws Exception{
        return pDAO.getRowCount();
    }

    public boolean correoExiste(String correo) throws Exception {
        List<PersonaDTO> personas = pDAO.readAll();
        for (PersonaDTO persona : personas) {
            if (persona.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }
}
