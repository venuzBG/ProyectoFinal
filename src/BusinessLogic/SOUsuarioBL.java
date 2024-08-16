package BusinessLogic;

import DataAccess.DTO.SOUsuarioDTO;
import DataAccess.SOUsuarioDAO;
import java.util.List;

public class SOUsuarioBL {
    private SOUsuarioDTO usuario;
    private SOUsuarioDAO uDAO = new SOUsuarioDAO();

    public SOUsuarioBL(){}

    public List<SOUsuarioDTO> getAll() throws Exception{
        List<SOUsuarioDTO> lst = uDAO.readAll();
        for (SOUsuarioDTO usuarioDTO : lst) 
            usuarioDTO.setUsuario(usuarioDTO.getUsuario());
        return lst;
    }
    public SOUsuarioDTO getBy(int idUsuario) throws Exception{
        usuario = uDAO.readBy(idUsuario);
        return usuario;
    }
    public boolean add(SOUsuarioDTO usuarioDTO) throws Exception{   
        return uDAO.create(usuarioDTO);
    }
    public boolean update(SOUsuarioDTO usuarioDTO) throws Exception{
        return uDAO.update(usuarioDTO);
    }
    public boolean delete(int idUsuario) throws Exception{
        return uDAO.delete(idUsuario);
    }
    public Integer getRowCount() throws Exception{
        return uDAO.getRowCount();
    }

    public int getIdUsuarioByUserAndPassword(String usuario, String clave) throws Exception {
        SOUsuarioDTO usuarioDTO = uDAO.readByUserAndPassword(usuario, clave);
        if (usuarioDTO != null) {
            return usuarioDTO.getIdUsuario();
        } else {
            throw new Exception("Usuario o clave incorrectos");
        }
    }
    


}
