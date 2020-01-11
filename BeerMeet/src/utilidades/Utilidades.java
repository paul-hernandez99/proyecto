package utilidades;

import java.util.Calendar;

import java.util.Date;
/**Esta clase contiene los métodos necesaios para la transformación de datos de distinto tipo.
*@author aritz eraun y Paul Hernandez*/

public class Utilidades 
{
	/**La función del método fechaDeAlta() es convertir un dato de tipo date a un String reconocible por la BD o otros métodos. */
	
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
		
		if(month.length() == 1)
			month = "0" + month;
		
		if(day.length() == 1)
			day = "0" + day;
		
		if(hora.length() == 1)
			hora = "0" + hora;
		
		if(minuto.length() == 1)
			minuto = "0" + minuto;
		
		String fecha = hora+":"+minuto+" "+day+"/"+month+"/"+year;
		
		return fecha;
	}
	
	public static void main(String[]args)
	{
		String fecha = Utilidades.fechaDeAlta();
		System.out.println(fecha);
	}
}
