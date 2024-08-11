package BusinessLogic.Entities;

import java.util.List;

import BusinessLogic.PersonaBL;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.PersonaDTO;
import DataAccess.DTO.UsuarioDTO;

public class Usuario {
    private UsuarioBL usuarioBL;

    public Usuario() {
        usuarioBL = new UsuarioBL();
    }

    public void ingresarDatos(String usuario, String clave) {
        try {
            List<UsuarioDTO> usuarios = usuarioBL.getAll();

            boolean usuarioValido = false;

            for (UsuarioDTO u : usuarios) {
                if (u.getUsuario().equals(usuario) && u.getClave().equals(clave)) {
                    usuarioValido = true;
                    break;
                }
            }

            if (usuarioValido) {
                System.out.println("Bienvenido, " + usuario);
            } else {
                System.out.println("Error: Usuario o clave incorrecta.");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar ingresar los datos: " + e.getMessage());
        }
    }

    public boolean registrarPersona(String nombre, String apellido, String correo, int idSexo, int idLocalidad) {
        PersonaBL personaBL = new PersonaBL();
        
        try {
            // Verificar si el correo ya existe
            if (personaBL.correoExiste(correo)) {
                System.out.println("Error: ya te has registrado con este usuario.");
                return false;
            }
    
            PersonaDTO nuevaPersona = new PersonaDTO();
            nuevaPersona.setNombre(nombre);
            nuevaPersona.setApellido(apellido);
            nuevaPersona.setCorreo(correo);
            nuevaPersona.setIdCatalogoSexo(idSexo);
            nuevaPersona.setIdLocalidad(idLocalidad);
    
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

    public boolean registrarUsuario(String nombreUsuario, String clave) {
        try {
            // Crear una instancia de UsuarioDTO y establecer los valores
            UsuarioDTO nuevoUsuario = new UsuarioDTO();
            nuevoUsuario.setUsuario(nombreUsuario);
            nuevoUsuario.setClave(clave);

            // Usar UsuarioBL para agregar el nuevo usuario a la base de datos
            return usuarioBL.add(nuevoUsuario);
        } catch (Exception e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            return false;
        }
    }

}
