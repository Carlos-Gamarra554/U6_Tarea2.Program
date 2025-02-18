package U6Tarea2;

import java.util.*;

public class Main {
    static ArrayList<Album> albumes = new ArrayList<>();
    public static void main(String[] args) {
        Album album1 = new Album("My World", "Dylan G.");
        album1.addSong("Thirsty", 3.5);
        album1.addSong("Moonlight", 4.0);
        album1.addSong("The Eve", 3.35);
        albumes.add(album1);

        Album album2 = new Album("The Beggining", "F. Entertainment");
        album2.addSong("Foolish Love", 3.2);
        album2.addSong("Lucid dream", 2.8);
        album2.addSong("Wonderland", 3.35);
        albumes.add(album2);

        LinkedList<Cancion> playList = new LinkedList<>();
        album1.addToPlaylist("Thirsty", playList);
        album1.addToPlaylist("Moonlight", playList);
        album1.addToPlaylist(0, playList);

        album2.addToPlaylist("Foolish love", playList);
        album2.addToPlaylist("Lucid dream", playList);
        album2.addToPlaylist(2, playList);

        play(playList);
    }

    public static void play(LinkedList<Cancion> playList) {
        Scanner scanner = new Scanner(System.in);
        ListIterator<Cancion> iterator = playList.listIterator();

        boolean haciaAdelante = true;
        int opcion = 1;

        if (playList.isEmpty()) {
            System.out.println("La lista de reproducción está vacía.");
        } else {
            System.out.println("Reproduciendo " + iterator.next());
        }
        System.out.println("----------------------------------------------");
        menu();

        while (opcion != 0) {
            System.out.print("Elige una opción: ");

            if(scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo de la playlist...");
                        break;

                    case 1:
                        if (!haciaAdelante) {
                            if (iterator.hasNext()) iterator.next();
                            haciaAdelante = true;
                        }

                        if (iterator.hasNext()) {
                            System.out.println("Reproduciendo " + iterator.next());
                        } else {
                            System.out.println("No hay más canciones :(");
                            System.out.println("Reproduciendo " + iterator.previous());
                            haciaAdelante = false;
                        }
                        break;

                    case 2:
                        if (haciaAdelante) {
                            if (iterator.hasPrevious()) iterator.previous();
                            haciaAdelante = false;
                        }

                        if (iterator.hasPrevious()) {
                            System.out.println("Reproduciendo " + iterator.previous());
                        } else {
                            System.out.println("Esta es la primera canción en la lista");
                            System.out.println("Reproduciendo " + iterator.next());
                            haciaAdelante = true;
                        }
                        break;

                    case 3:
                        if (haciaAdelante && iterator.hasPrevious()) {
                            System.out.println("Reproduciendo " + iterator.previous());
                            iterator.next();
                        } else if (!haciaAdelante && iterator.hasNext()) {
                            System.out.println("Reproduciendo " + iterator.next());
                            iterator.previous();
                        }
                        break;

                    case 4:
                        System.out.println("Lista de canciones:");
                        imprimirLista(playList);
                        break;

                    case 5:
                        System.out.println("Lista de álbumes:");
                        for (Album album : albumes) {
                            System.out.println("-" + album.getNombre());
                        }
                        System.out.println("----------------------------------------------");
                        break;

                    case 6:
                        imprimirAlbum();
                        break;

                    case 7:
                        System.out.println("Eliminando la canción actual...");
                        iterator.remove();

                        if (iterator.hasNext()) {
                            System.out.println("Reproduciendo " + iterator.next());
                        } else {
                            System.out.println("Reproduciendo " + iterator.previous());
                        }
                        break;

                    case 8:
                        ordenarLista(playList);
                        break;

                    case 9:
                        menu();
                        break;

                    default:
                        System.out.println("Error. Opción no válida");
                }
            } else {
                System.out.println("Entrada no válida. Debes ingresar un número.");
                scanner.nextLine();
                }
            }
        }

    public static void menu() {
        System.out.println("Menú de opciones:\n" +
                "0. Salir de la lista de reproducción\n" +
                "1. Reproducir siguiente canción en la lista\n" +
                "2. Reproducir la canción previa de la lista\n" +
                "3. Repetir la canción actual\n" +
                "4. Imprimir la lista de canciones en la playlist\n" +
                "5. Imprimir lista de álbumes\n" +
                "6. Imprimir canciones de un álbum\n" +
                "7. Eliminar la canción actual definitivamente\n" +
                "8. Ordenar lista de canciones alfabéticamente\n" +
                "9. Volver a imprimir el menú");
        System.out.println("----------------------------------------------");
    }

    public static void imprimirLista(LinkedList<Cancion> playList) {
        Iterator<Cancion> iterador = playList.iterator();
        while (iterador.hasNext()) {
            System.out.println("-" + iterador.next());
        }
        System.out.println("----------------------------------------------");
    }

    public static void imprimirAlbum() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del álbum: ");
        String nombre = sc.nextLine();

        for(Album album : albumes) {
            if(album.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Canciones del álbum:");
                album.imprimirCanciones();
                return;
            } else {
                System.out.println("Álbum no existe");
            }
        }
        System.out.println("----------------------------------------------");
    }

    public static void ordenarLista(LinkedList<Cancion> playList) {
        System.out.println("Ordenando la playlist por nombre...");
        Collections.sort(playList, Comparator.comparing(Cancion::getTitulo));
        System.out.println("Lista de canciones ordenadas:");
        imprimirLista(playList);
    }
}