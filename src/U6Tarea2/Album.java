package U6Tarea2;
import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String nombre;
    private String artista;
    ArrayList<Cancion> canciones;

    public String getNombre() {
        return nombre;
    }

    public Album(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
        canciones = new ArrayList<>();
    }

    private Cancion findSong(String nombre) {
        for (Cancion cancion : canciones) {
            if (cancion.getTitulo().equalsIgnoreCase(nombre)) {
            return cancion;
            }
        }
        return null;
    }

    public boolean addSong(String titulo, double duracion) {
        if(findSong(titulo) == null) {
            canciones.add(new Cancion(titulo, duracion));
            return true;
        } else {
            return false;
        }
    }

    public boolean addToPlaylist(int npista, LinkedList<Cancion> canciones) {
        if (findSong(canciones.get(npista).getTitulo()) != null && npista <= canciones.size() && npista > 0) {
            canciones.add(canciones.get(npista - 1));
            return true;
        } else {
            return false;
        }
    }

    public boolean addToPlaylist(String titulo, LinkedList<Cancion> canciones) {
        if (findSong(titulo) != null) {
            canciones.add(findSong(titulo));
            return true;
        } else {
            return false;
        }
    }

    public void imprimirCanciones() {
        if (canciones.isEmpty()) {
            System.out.println("El álbum no tiene canciones.");
        } else {
            for (Cancion cancion : canciones) {
                System.out.println("-" + cancion);
            }
        }
    }
}
