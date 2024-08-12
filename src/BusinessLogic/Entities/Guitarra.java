package BusinessLogic.Entities;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Guitarra {

    private Clip acordeTocando;
    public Cancion cancionTemporal = new Cancion();
    public boolean banderaGrabacion = false;
    public volatile boolean banderaTocarCancion = false; // Usamos volatile para asegurar la visibilidad de la variable
                                                         // entre hilos

    private class Acorde {
        private Clip acordeAudio;
        private String nombreAcorde;
        private String ruta;

        public Acorde(String nombreAcorde) {
            this.nombreAcorde = nombreAcorde;
            this.ruta = "src/UserInterface/Resource/sounds/" + this.nombreAcorde + ".wav";
            cargarSonido(this.ruta);
        }

        public void cargarSonido(String ruta) {
            try {
                File archivoSonido = new File(ruta);

                if (!archivoSonido.exists()) {
                    System.out.println("El archivo de audio no existe: " + ruta);
                    return;
                }

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoSonido);
                acordeAudio = AudioSystem.getClip();
                acordeAudio.open(audioInputStream);
            } catch (Exception e) {
                System.out.println("Error al cargar el sonido: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void tocarAcorde(String nombreAcorde) {
        Acorde acorde = new Acorde(nombreAcorde);
        acordeTocando = acorde.acordeAudio;

        if (acordeTocando != null) {
            acordeTocando.setFramePosition(0);
            acordeTocando.start();
            System.out.println("Reproduciendo acorde: " + nombreAcorde);
            if (banderaGrabacion) {
                cancionTemporal.acordesCancion.add(nombreAcorde);
            }
        } else {
            System.out.println("No se pudo reproducir el acorde: " + nombreAcorde);
        }
    }

    public void grabarCancion() {
        banderaGrabacion = true;
        System.out.println("Empezo la grabacion");
    }

    public void detenerGrabacion() {
        banderaGrabacion = false;
        System.out.println("Grabacion detenida");
    }

    public void guardarCancion() {
        System.out.println(cancionTemporal.acordesCancion.toString());
    }

    public boolean estaReproduciendo() {
        return banderaTocarCancion;
    }

    public void tocarCancion(Cancion cancionTemporal) {
        banderaTocarCancion = true; // Indicar que la canción está en reproducción
        int indiceAcorde = 0;
        for (String acorde : cancionTemporal.acordesCancion) {
            if (!banderaTocarCancion) {
                break; // Si se detiene la reproducción, salir del bucle
            }
            if (indiceAcorde == 0) {
                tocarAcorde(acorde);
            } else {
                try {
                    synchronized (this) {
                        wait(4000); // Esperar 4 segundos entre acordes
                    }
                    if (!banderaTocarCancion) {
                        break; // Salir del bucle si se detiene la reproducción
                    }
                    tocarAcorde(acorde);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restaurar la interrupción
                    System.out.println("Reproducción interrumpida: " + e.getMessage());
                }
            }
            indiceAcorde++;
        }
        banderaTocarCancion = false; // Indicar que la reproducción ha terminado
    }

    public void detenerCancion() {
        banderaTocarCancion = false; // Detener la reproducción de la canción
        if (acordeTocando != null) {
            acordeTocando.stop(); // Detener el clip de audio si está reproduciendo
        }
        Thread.currentThread().interrupt(); // Interrumpir el hilo para asegurar que se detenga inmediatamente
        System.out.println("Cancion Detenida");
    }

    // public static void main(String[] args) throws InterruptedException {
    // Guitarra guitarra = new Guitarra();
    // guitarra.tocarAcorde("C_Major");
    // Thread.sleep(3000);
    // }
}
