package DataAccess.DTO;

public class UsuarioDTO {
    private Integer IdUsuario      ;
    private String  Usuario        ;
    private String  Clave          ;
    private String  Estado         ;
    private String  FechaCreacion  ;
    private String  FechaModifica  ;

    public UsuarioDTO(){}
    
    public UsuarioDTO(Integer idUsuario, String usuario, String clave, String estado, String fechaCreacion,
                          String fechaModifica) {
        this.IdUsuario     = idUsuario;
        this.Usuario       = usuario;
        this.Clave         = clave;
        this.Estado        = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
    public String getClave() {
        return Clave;
    }
    public void setClave(String clave) {
        Clave = clave;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    @Override
    public String toString(){
        return  "  \n" + getClass().getName()
                + "\n idUsuario      "+ getIdUsuario()
                + "\n Clave          "+ getClave()
                + "\n Estado         "+ getEstado()
                + "\n fechaCreacion  "+ getFechaCreacion()
                + "\n fechaModifica  "+ getFechaModifica();
    }
}
