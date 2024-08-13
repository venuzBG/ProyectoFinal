package BusinessLogic.Entities;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static ArrayList<String> convertStringToArrayList(String input) {
        // Divide la cadena en un array de Strings usando el signo "-" como separador
        String[] elementos = input.split("-");
        
        // Convierte el array en un ArrayList y lo retorna
        return new ArrayList<>(Arrays.asList(elementos));
    }


    
}
