import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * La clase representa a una tienda on-line en la
 * que se publican los juegos que se van lanzando al mercado
 * 
 * Un objeto de esta clase guarda en un array los juegos 
 *
 * @author - Sergio Garrués Aizcorbe
 */
public class RevistaOnLineJuegos 
{
    private String nombre;
    private Juego[] juegos;
    private int total;

    /**
     * Constructor  
     * Crea el array de juegos al tamaño máximo indicado por la constante
     * e inicializa el resto de atributos
     */
    public RevistaOnLineJuegos(String nombre, int n) {
        this.nombre = nombre;
        juegos = new Juego[n];
        total = 0; 
    }

    /**
     * Devuelve true si el array está completo, false en otro caso
     */
    public boolean estaCompleta() {
        return total == juegos.length;
    }

    /**
     *    Añade un nuevo juego solo si el array no está completo y no existe otro juego
     *    ya con el mismo nombre.  Si no se puede añadir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El juego se añade de tal forma que queda insertado en orden alfabético de título
     *    (de menor a mayor)
     *     !!OJO!! No hay que ordenar ni utilizar ningún algoritmo de ordenación
     *    Hay que insertar en orden 
     *    
     */
    public void add(Juego juego) {
        String str = "";

        if (estaCompleta()) {
            System.out.println("Lista completa");
        }
        else{
            if(existeJuego(juego.getTitulo()) != -1){
                System.out.println("Ya está publicado el juego " + juego.getTitulo() + " en la revista on-line");
            }
            else if(total == 0){
                juegos[0] = juego;
                total++;
            }

            else{    

                if(juego.getTitulo().compareToIgnoreCase(juegos[total - 1].getTitulo()) >= 0 && existeJuego(juego.getTitulo()) == -1){
                    juegos[total] = juego;
                }
                else{
                    for(int i = total - 1 ; i >= 0 && juego.getTitulo().compareToIgnoreCase(juegos[i].getTitulo()) < 0; i--)
                    {

                        juegos[i + 1] = juegos[i];
                        juegos[i] = juego;
                    }

                }

                total++;
            }
        }

    }


    /**
     * Efectúa una búsqueda en el array del juego cuyo titulo se
     * recibe como parámetro. Es ndiferente mayúsculas y minúsculas
     * Si existe el juego devuelve su posición, si no existe devuelve -1
     */
    public int existeJuego(String titulo) {
        int posi = -1;

        if (total > 0){
            for(int i = total - 1;i >= 0;i--){

                if( juegos[i].getTitulo().equals(titulo.toUpperCase()) ){

                    posi = i;
                }

            }
        }
        return posi;
    }

    /**
     * Representación textual de la revista
     * Utiliza StringBuilder como clase de apoyo.
     * Se incluye el nombre de la  revista on-line.
     * (Ver resultados de ejecución)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nLos mejores juegos en nuestra revista " + nombre + " (" + total + " juegos)\n\n");

        for (int i = 0; i < total; i++){
            if(juegos[i] != null)
            {
                sb.append(juegos[i].toString());
                sb.append("\n--------------------\n");
            }
        }
        
        return sb.toString();
    }
    /**
     *  Se puntúa el juego de título indicado con 
     *  la puntuación recibida como parámetro. 
     *  La puntuación es un valor entre 1 y 10 (asumimos esto como correcto)
     *  Si el juego no existe se muestra un mensaje en pantalla
     */
    public void puntuar(String titulo, int puntuacion) {
        
        for (int i = 0; i < total; i++) 
        {
            if (existeJuego(titulo.toUpperCase()) != -1)  {
                juegos[i].puntuar(puntuacion);
            }
            else{
                System.out.println("No existe el juego " + titulo + "\n");
            }
        }
    }

    /**
     * Devuelve un array con los nombres de los juegos 
     * con una valoración media mayor a la indicada  
     * 
     * El array se devuelve todo en mayúsculas y ordenado ascendentemente
     */
    public String[] valoracionMayorQue(double valoracion) {
        int mayor = 0;
        int cont = 0;

        for(int i = 0; i < total; i++)
        {
            if(juegos[i].getValoracionMedia() > valoracion)
            {
                mayor++;   
            }
        }

        String[] str = new String[mayor];

        for(int i = 0; i < total; i++)
        {
            if(juegos[i].getValoracionMedia() > valoracion)
            {
                str[cont] = juegos[i].getTitulo().toUpperCase();
                cont++;
            }
            
        }
        Arrays.sort(str);
        return str;

    }

    /**
     * Borrar los juegos del género indicado devolviendo
     * el nº de juegos borradas
     */
    public int borrarDeGenero(Genero genero) {
        int borra = 0;

        for(int i = 0; i < total; i++)
        {
            if(juegos[i].getGenero().equals(genero))
            {
                juegos[i] = null;
                borra++;
            }
        }

        return borra;

    }

    /**
     * Lee de un fichero de texto los datos de los juegos
     * con ayuda de un objeto de la  clase Scanner
     * y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("juegos.txt"));
            while (sc.hasNextLine()) {
                Juego juego = new Juego(sc.nextLine()); 
                this.add(juego);

            }

        } catch (IOException e) {
            System.out.println("Error al leer del fichero");
        } finally {
            sc.close();
        }

    }


}
