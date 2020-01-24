/**
 * Punto de entrada a la aplicación
 * 
 * @author - Sergio Garrués Aizcorbe
 */
public class AppRevistaJuegosOnLine 
{
	public static void main(String[] args)
	{
	    
	    
	    RevistaOnLineJuegos revista = new RevistaOnLineJuegos(args[0], Integer.parseInt(args[1]));
	    System.out.println("\n***************************************");
	    System.out.println("Puntuando...");
	    revista.puntuar("PlanetZoo", 8);
	    revista.puntuar("Steep", 7);
	    revista.puntuar("Catastronauts", 9);
	    revista.puntuar("Wattam", 9);
	    System.out.println("Después de puntuar la revista queda\n");
	    revista.leerDeFichero();
	    System.out.println("");
	    System.out.println(revista.toString());
	    
	    System.out.println("***************************************");
	    System.out.println("");
	    System.out.println("");
	    System.out.println("Juegos con valoración media > 8.2 ");
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append(" [");
	    for(int i = 0; i < revista.valoracionMayorQue(8.2).length; i++)
	    {
	    sb.append(revista.valoracionMayorQue(8.2)[i] + ", ");
	    }
	    sb.append("]");
	    System.out.println(sb);
	}

}
