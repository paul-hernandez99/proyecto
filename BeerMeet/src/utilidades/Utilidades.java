package utilidades;

import java.util.Calendar;
import java.util.Date;
/**Esta clase contiene los m�todos necesaios para la transformaci�n de datos de distinto tipo.
*@author aritz eraun y Paul Hernandez*/
public class Utilidades 
{/**La funci�n del m�todo fechaDeAlta() es convertir un dato de tipo date a un String reconocible por la BD o otros m�todos. */
	public static String fechaDeAlta()
	{
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
		String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String minuto = Integer.toString(calendar.get(Calendar.MINUTE));
		
		String fecha = hora+":"+minuto+" "+day+"/"+month+"/"+year;
		
		return fecha;
	}
}
