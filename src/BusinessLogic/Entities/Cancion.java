package BusinessLogic.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import BusinessLogic.CancionBL;
import DataAccess.DTO.CancionDTO;

public class Cancion {
    public String autor;
    public String nombreCancion;
    public ArrayList<String> acordesCancion = new ArrayList<>();

    public static <T> String convertArrayToString(T[] array) {
        // Usa Arrays.stream para convertir el array en una cadena con el separador "-"
        return Arrays.stream(array)
                     .map(Object::toString)
                     .collect(Collectors.joining("-"));
    }
    
    public void CancionGuardarBD(Integer idPersona, String nombre, String[] canciones) {
        try {
            // Convierte el array de canciones a una cadena separada por "-"
            String cancionString = convertArrayToString(canciones);

            // Crea una instancia de CancionDTO con los datos proporcionados
            CancionDTO nuevaCancion = new CancionDTO();
            nuevaCancion.setIdPersona(idPersona);
            nuevaCancion.setNombre(nombre);
            nuevaCancion.setCancion(cancionString);

            // Crea una instancia de CancionBL y guarda la nueva canción
            CancionBL cancionBL = new CancionBL();
            boolean isSaved = cancionBL.add(nuevaCancion);

            if (isSaved) {
                System.out.println("Canción guardada exitosamente.");
            } else {
                System.out.println("No se pudo guardar la canción.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
    public String[] listarCancionesUsuario (int idUsuario){
        List <String> lstNombreCancion = new ArrayList<>();  
         try {
             CancionBL cancionBL = new CancionBL();
             for (CancionDTO cancionRegistro : cancionBL.getAllBy(idUsuario)) {
                lstNombreCancion.add(cancionRegistro.getNombre());
             }
             String [] listadoCanciones = new String[lstNombreCancion.size()];
             listadoCanciones = lstNombreCancion.toArray(new String [0]);
             return listadoCanciones;
         } catch (Exception e) {
             e.printStackTrace();
             return null;   
         }  
     } 
     
     public String obtenerContenidoCancion (Integer idPersona, String nombreCancion) throws Exception{
        String contenido;
        CancionBL cancionRegistro = new CancionBL();
        contenido = cancionRegistro.getCancionByNombre(idPersona, nombreCancion);
        return contenido;
     }
    //   public static void main(String[] args) {
    //      Cancion cancion = new Cancion();
    //      System.out.println(Arrays.toString(cancion.listarCancionesUsuario(6)));
    //   }
}
