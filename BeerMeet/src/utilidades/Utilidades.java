package utilidades;

import java.util.List;

import foto.Foto;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
/**Esta clase contiene los m�todos necesaios para la transformaci�n de datos de distinto tipo.
*@author aritz eraun y Paul Hernandez*/

public class Utilidades
{
	/**La funci�n del m�todo fechaDeAlta() es convertir un dato de tipo date a un String reconocible por la BD o otros m�todos. */
	
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
	
	public static <T> List<T> MergeSort(List<T> list)
	{
		if(list.size() == 1)
		{
			return list;
		}
		else
		{
			return Merge(MergeSort(list.subList(0, list.size()/2)), MergeSort(list.subList(list.size()/2, list.size())));
		}
	}
	
	private static <T extends Foto> List<T> Merge(List<T> A, List<T> B)
	{
		while(!A.isEmpty() || !B.isEmpty())
		{
			if(A.get(0).comparar(B.get(0)))
			{
				
			}
		}
	}
	
}
