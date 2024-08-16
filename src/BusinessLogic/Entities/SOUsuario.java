package BusinessLogic.Entities;

import BusinessLogic.SOPersonaBL;
import BusinessLogic.SOUsuarioBL;
import DataAccess.DTO.SOPersonaDTO;
import DataAccess.DTO.SOUsuarioDTO;

public class SOUsuario {
    private SOUsuarioBL usuarioBL;

    public SOUsuario() {
        usuarioBL = new SOUsuarioBL();
    }

    public boolean soregistrarPersona(String sonombre, String soapellido, String socorreo, int soidSexo, int soidLocalidad) {
        SOPersonaBL personaBL = new SOPersonaBL();

        try {
            // Verificar si el correo ya existe
            if (personaBL.correoExiste(socorreo)) {
                System.out.println("Error: ya te has registrado con este usuario.");
                return false;
            }

            SOPersonaDTO nuevaPersona = new SOPersonaDTO();
            nuevaPersona.setNombre(sonombre);
            nuevaPersona.setApellido(soapellido);
            nuevaPersona.setCorreo(socorreo);
            nuevaPersona.setIdCatalogoSexo(soidSexo);
            nuevaPersona.setIdLocalidad(soidLocalidad);

            boolean registrado = personaBL.add(nuevaPersona);

            if (registrado) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el usuario.");
            }

            return registrado;
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            return false;
        }
    }

    public boolean soregistrarUsuario(String sonombreUsuario, String soclave) {
        try {
            // Crear una instancia de UsuarioDTO y establecer los valores
            SOUsuarioDTO nuevoUsuario = new SOUsuarioDTO();
            nuevoUsuario.setUsuario(sonombreUsuario);
            nuevoUsuario.setClave(soclave);

            // Usar UsuarioBL para agregar el nuevo usuario a la base de datos
            return usuarioBL.add(nuevoUsuario);
        } catch (Exception e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            return false;
        }
    }
    ///actualizado para llevar la variable del id del usuario
    public int soIngresarDatos(String sonombreUsuario, String claveUsuario) {
        int idusuariobl = -1; // Valor predeterminado en caso de error
        try {
            SOUsuarioBL usuarioBL = new SOUsuarioBL();
            idusuariobl = usuarioBL.getIdUsuarioByUserAndPassword(sonombreUsuario, claveUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idusuariobl;
    }
    
    public String soObtenerNombreBD (int idUsuario){
        String sonombreUsuario = "usuario";        
        try {
            SOPersonaBL personaBL = new SOPersonaBL();
            sonombreUsuario = personaBL.getBy(idUsuario).getNombre(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sonombreUsuario;
    }
    
    

}
