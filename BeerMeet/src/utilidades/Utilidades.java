package utilidades;

import foto.Foto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**Esta clase contiene los métodos necesaios para la transformación de datos de distinto tipo.
*@author aritz eraun y Paul Hernandez*/

public class Utilidades <T extends Foto>
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
	
	public static <T extends Foto> ArrayList<T> MergeSort(ArrayList<T> list)
	{
		if(list.size() == 0)
		{
			return list;
		}
		else
		{
			if(list.size() == 1)
			{
				return list;
			}
			else
			{
				ArrayList<T> primero = new ArrayList<>();
				List<T> list1 = list.subList(0, list.size()/2);
				for(int i=0; i<list1.size(); i++)
				{
					primero.add(list1.get(i));
				}
				
				ArrayList<T> segundo = new ArrayList<>();
				List<T> list2 = list.subList(list.size()/2, list.size());
				for(int i=0; i<list2.size(); i++)
				{
					segundo.add(list2.get(i));
				}
				
				return Merge(MergeSort(primero), MergeSort(segundo));
			}
		}
	}
	
	private static <T extends Foto> ArrayList<T> Merge(ArrayList<T> A, ArrayList<T> B)
	{
		ArrayList<T> lista = new ArrayList<>();
		
		while(!A.isEmpty() && !B.isEmpty())
		{
			if((A.get(0)).despuesDe(B.get(0)))
			{
				lista.add(B.get(0));
				
				B.remove(0);
			}
			else
			{
				lista.add(A.get(0));
				
				A.remove(0);
			}
		}
		
		while(!A.isEmpty())
		{
			lista.add(A.get(0));
			
			A.remove(0);
		}
		
		while(!B.isEmpty())
		{
			lista.add(B.get(0));
			
			B.remove(0);
		}
		
		return lista;
	}
	
}
