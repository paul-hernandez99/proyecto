package interfaces;

import foto.Foto;
/**Declaracion de la interfaz I comparable. Se utilizará para la ordenación de objetos, principalmente fotos.
 * @author Aritz y Paul
 * @since 1.3*/
public interface IComparable <T extends Foto> 
{
	/**Método abstracto que retorna un booleano y recibe un generico (que tendremos que rellenarlo después.
	 * @param t : objeto genérico ( se especificará después).*/
	public abstract boolean despuesDe(T t);
}
