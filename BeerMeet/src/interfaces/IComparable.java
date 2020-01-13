package interfaces;

import foto.Foto;
/**Declaracion de la interfaz I comparable. Se utilizar� para la ordenaci�n de objetos, principalmente fotos.
 * @author Aritz y Paul
 * @since 1.3*/
public interface IComparable <T extends Foto> 
{
	/**M�todo abstracto que retorna un booleano y recibe un generico (que tendremos que rellenarlo despu�s.
	 * @param t : objeto gen�rico ( se especificar� despu�s).*/
	public abstract boolean despuesDe(T t);
}
