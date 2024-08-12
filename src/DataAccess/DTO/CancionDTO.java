package DataAccess.DTO;

public class CancionDTO {
    private Integer IdCancion;                
    private Integer IdPersona;             
    private String  Cancion;    
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public CancionDTO(){}

    public CancionDTO(Integer idCancion, Integer idPersona, String cancion, String estado,
            String fechaCreacion, String fechaModifica) {
        this.IdCancion = idCancion;
        this.IdPersona = idPersona;
        this.Cancion = cancion;
        this.Estado = estado;
        this.FechaCreacion = fechaCreacion;
        this.FechaModifica = fechaModifica;
    }
    public Integer getIdCancion() {
        return IdCancion;
    }
    public void setIdCancion(Integer idCancion) {
        IdCancion = idCancion;
    }
    public Integer getIdPersona() {
        return IdPersona;
    }
    public void setIdPersona(Integer idPersona) {
        IdPersona = idPersona;
    }
    public String getCancion() {
        return Cancion;
    }
    public void setCancion(String cancion) {
        Cancion = cancion;
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
                + "\n idCancion      "+ getIdCancion()
                + "\n idPersona      "+ getIdPersona()
                + "\n Estado         "+ getEstado()
                + "\n fechaCreacion  "+ getFechaCreacion()
                + "\n fechaModifica  "+ getFechaModifica();
    }
}
