package exceptions;
/**La funci�n de esta clase es definir una excepci�n que puede efectuarse a la hora de la ejecuci�n del programa.
*@author aritz eraun y Paul Hernandez*/
public class Exceptions extends Exception
{/**La excepcion envia un pensage que est� previamente programado seg�n la sitiaci�n en la que nos encontremos.
	*@author aritz eraun y Paul Hernandez*/
	public Exceptions(String mensaje)
	{
		super(mensaje);
	}
}
