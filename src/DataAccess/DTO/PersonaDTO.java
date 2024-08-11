package DataAccess.DTO;

public class PersonaDTO {
    private Integer   IdPersona             ;           
    private String    Nombre                ;           
    private String    Apellido              ;          
    private String    Correo                ;          
    private Integer   IdCatalogoSexo        ;     
    private Integer   IdLocalidad           ;          
    private String    estado                ;           
    private String    fechaCreacion         ;            
    private String    fechaModifica         ;
    
    public PersonaDTO(){}
    
    public PersonaDTO(Integer idPersona, String nombre, String apellido, String correo, Integer idCatalogoSexo, 
                      Integer idLocalidad, String estado, String fechaCreacion,String fechaModifica) {
        this.IdPersona              = idPersona;
        this.Nombre                 = nombre;
        this.Apellido               = apellido;
        this.Correo                 = correo;
        this.IdCatalogoSexo         = idCatalogoSexo;
        this.IdLocalidad            = idLocalidad;
        this.estado                 = estado;
        this.fechaCreacion          = fechaCreacion;
        this.fechaModifica          = fechaModifica;
    }

    public Integer getIdPersona() {
        return IdPersona;
    }
    public void setIdPersona(Integer idPersona) {
        IdPersona = idPersona;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getCorreo() {
        return Correo;
    }
    public void setCorreo(String correo) {
        Correo = correo;
    }
    public Integer getIdCatalogoSexo() {
        return IdCatalogoSexo;
    }
    public void setIdCatalogoSexo(Integer idCatalogoSexo) {
        IdCatalogoSexo = idCatalogoSexo;
    }
    public Integer getIdLocalidad() {
        return IdLocalidad;
    }
    public void setIdLocalidad(Integer idLocalidad) {
        IdLocalidad = idLocalidad;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getFechaModifica() {
        return fechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    @Override
    public String toString(){
        return  "  \n" + getClass().getName()
                + "\n idPersona      "+ getIdPersona()
                + "\n nombre         "+ getNombre()
                + "\n apellido       "+ getApellido()
                + "\n correo         "+ getCorreo()
                + "\n sexo           "+ getIdCatalogoSexo()
                + "\n localidad      "+ getIdLocalidad()
                + "\n estado         "+ getEstado()
                + "\n fechaCreacion  "+ getFechaCreacion()
                + "\n fechaModifica  "+ getFechaModifica();
    }


}
