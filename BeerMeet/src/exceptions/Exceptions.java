package exceptions;
/**La función de esta clase es definir una excepción que puede efectuarse a la hora de la ejecución del programa.
*@author aritz eraun y Paul Hernandez*/
public class Exceptions extends Exception
{/**La excepcion envia un pensage que está previamente programado según la sitiación en la que nos encontremos.
	*@author aritz eraun y Paul Hernandez
	*@since 1.1
	*@version 1.3
	*@param mensaje : mensaje (texto) que mostrará la excepción.*/
	public Exceptions(String mensaje)
	{
		super(mensaje);
	}
}
