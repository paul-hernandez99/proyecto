package foto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.IComparable;

/**La función de esta clase denominada foto, es definir el objeto foto. La clase contiene los distintos atributos del 
 * objeto de tipo foto (el código, la identificación del usuario, etc.) además de los getters y setter que nos facilitan el acceso
 * y modificación de los datos.
*@author aritz eraun y Paul Hernandez*/

public class Foto implements IComparable
{
	private int cod;
	private int id_user;
	private String path;
	private String fec;
	
	/**Constructor del objeto foto*/
	public Foto()
	{		
	}
	
	/**Constructor del objeto foto*/
	public Foto(int id_user, String path, String fec) 
	{ 
		super();
		this.id_user = id_user;
		this.path = path;
		this.fec = fec;
	}
	
	/**Constructor del objeto foto*/
	
	public Foto(int cod, int id_user, String path, String fec) 
	{
		super();
		this.cod = cod;
		this.id_user = id_user;
		this.path = path;
		this.fec = fec;
	}
	
	@Override
	public boolean despuesDe(Foto foto) 
	{
		try 
		{
			int hora1 = Integer.parseInt(this.getFec().substring(0, 2));
			int minutos1 = Integer.parseInt(this.getFec().substring(3, 5));
			
			Date fecha1 = new SimpleDateFormat("dd/MM/yyyy").parse(this.getFec().substring(6));
			
			int hora2 = Integer.parseInt(foto.getFec().substring(0, 2));
			int minutos2 = Integer.parseInt(foto.getFec().substring(3, 5));
			Date fecha2 = new SimpleDateFormat("dd/MM/yyyy").parse(foto.getFec().substring(6));
			
			if(fecha1.compareTo(fecha2) < 0)
			{
				return false;
			}
			else if(fecha1.compareTo(fecha2) > 0)
			{
				return true;
			}
			else if(fecha1.compareTo(fecha2) == 0)
			{
				if(hora1 < hora2)
				{
					return false;
				}
				else if(hora1 > hora2)
				{
					return true;
				}
				else if(hora1 == hora2)
				{
					if(minutos1 < minutos2)
					{
						return false;
					}
					else if(minutos1 > minutos2)
					{
						return true;
					}
					else if(minutos1 == minutos2)
					{
						return false;
					}
				}
			}
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getCod() 
	{
		return cod;
	}

	public void setCod(int cod)
	{
		this.cod = cod;
	}

	public int getId_user() 
	{
		return id_user;
	}

	public void setId_user(int id_user) 
	{
		this.id_user = id_user;
	}

	public String getPath() 
	{
		return path;
	}

	public void setPath(String path) 
	{
		this.path = path;
	}

	public String getFec() 
	{
		return fec;
	}

	public void setFec(String fec) 
	{
		this.fec = fec;
	}
}
