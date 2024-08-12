package BusinessLogic;

import DataAccess.DTO.UsuarioDTO;
import DataAccess.UsuarioDAO;
import java.util.List;

public class UsuarioBL {
    private UsuarioDTO usuario;
    private UsuarioDAO uDAO = new UsuarioDAO();

    public UsuarioBL(){}

    public List<UsuarioDTO> getAll() throws Exception{
        List<UsuarioDTO> lst = uDAO.readAll();
        for (UsuarioDTO usuarioDTO : lst) 
            usuarioDTO.setUsuario(usuarioDTO.getUsuario());
        return lst;
    }
    public UsuarioDTO getBy(int idUsuario) throws Exception{
        usuario = uDAO.readBy(idUsuario);
        return usuario;
    }
    public boolean add(UsuarioDTO usuarioDTO) throws Exception{   
        return uDAO.create(usuarioDTO);
    }
    public boolean update(UsuarioDTO usuarioDTO) throws Exception{
        return uDAO.update(usuarioDTO);
    }
    public boolean delete(int idUsuario) throws Exception{
        return uDAO.delete(idUsuario);
    }
    public Integer getRowCount() throws Exception{
        return uDAO.getRowCount();
    }

    public int getIdUsuarioByUserAndPassword(String usuario, String clave) throws Exception {
        UsuarioDTO usuarioDTO = uDAO.readByUserAndPassword(usuario, clave);
        if (usuarioDTO != null) {
            return usuarioDTO.getIdUsuario();
        } else {
            throw new Exception("Usuario o clave incorrectos");
        }
    }
    


}
