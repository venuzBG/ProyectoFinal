
import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;

import BusinessLogic.PersonaBL;
import BusinessLogic.UsuarioBL;
import BusinessLogic.Entities.Usuario;
import DataAccess.DTO.PersonaDTO;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.Form.IngresarPrograma;
import UserInterface.Form.SplashScreenForm;
import DataAccess.PersonaDAO;
import DataAccess.UsuarioDAO;
import UserInterface.Form.Guitarra;

public class App {
    public static void main(String[] args) throws Exception {
        // testing: DAO
        // try {
        //     PersonaDAO sDao = new PersonaDAO();
        //     for(PersonaDTO s : sDao.readAll())
        //         System.out.println(s.toString());
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }
    
        //testing: BL
        // try {
        //     PersonaBL pBL = new PersonaBL();
        //     for(PersonaDTO s : pBL.getAll())
        //         System.out.println(s.toString());
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

        // testing: Usuario
        // try {
        //     UsuarioDAO sDao = new UsuarioDAO();
        //     for(UsuarioDTO s : sDao.readAll())
        //         System.out.println(s.toString());
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

        //testing: UsuarioBL
        // try {
        //     UsuarioBL pBL = new UsuarioBL();
        //     for(UsuarioDTO s : pBL.getAll())
        //         System.out.println(s.toString());
        // } catch (Exception e) {
        //     System.out.println(e.toString());
        // }

        
        //  // Crear una instancia de la clase Usuario
        //  Usuario usuario = new Usuario();

        //  // Simular la entrada de datos por parte del usuario (puedes cambiar estos valores para probar)
        //  String nombreUsuario = "sebastian";  // Aquí va el usuario que deseas probar
        //  String claveUsuario = "1234";     // Aquí va la clave que deseas probar
 
        //  // Llamar al método ingresarDatos para validar el usuario y la clave
        //  usuario.ingresarDatos(nombreUsuario, claveUsuario);

            // Crear una instancia de la clase Usuario
            // Usuario usuario = new Usuario();
    
            // // Llamar al método registrarUsuario para registrar un nuevo usuario
            // boolean exito = usuario.registrarUsuario("Juan", "Pérez", "juan.perez@example.com", 1, 1001);
    
            // Verificar si el registro fue exitoso
            // if (exito) {
            //     System.out.println("El usuario ha sido registrado correctamente.");
            // } else {
            //     System.out.println("Hubo un problema al registrar el usuario.");
            // }

            FlatLightLaf.setup();
            FlatLightLaf.supportsNativeWindowDecorations();
            try {
                    UIManager.setLookAndFeel(new FlatAtomOneDarkIJTheme());
                } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            } 
        
        // SplashScreenForm.show();
        // new IngresarPrograma();
        SwingUtilities.invokeLater(Guitarra::new);
    }
    
}
