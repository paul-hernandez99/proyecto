package foto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.IComparable;

/**La funci�n de esta clase denominada foto, es definir el objeto foto. La clase contiene los distintos atributos del 
 * objeto de tipo foto (el c�digo, la identificaci�n del usuario, etc.) adem�s de los getters y setter que nos facilitan el acceso
 * y modificaci�n de los datos.
*@author aritz eraun y Paul Hernandez
*@since 1.1
 *@version 1.3*/

public class Foto implements IComparable
{
	private int cod;
	private int id_user;
	private String path;
	private String fec;
	
	/**Constructor del objeto foto (constructo vacio)*/
	public Foto()
	{		
	}
	
	/**Constructor del objeto foto
	 * @param id_user: id del user al que pertenece la foto.
	 * @param path: localizacion o direccion relativa de la imagen o foto.
	 * @param fec: fecha de creaci�n de la imagen o foto.*/
	public Foto(int id_user, String path, String fec) 
	{ 
		super();
		this.id_user = id_user;
		this.path = path;
		this.fec = fec;
	}
	
	/**Constructor del objeto foto ( con todos sus atributos)
	 * @param cod: co�digo �nico de identificaci�n de la imagen o foto.
	 * @param id_user: id del user al que pertenece la foto.
	 * @param path: localizacion o direccion relativa de la imagen o foto.
	 * @param fec: fecha de creaci�n de la imagen o foto.*/
	public Foto(int cod, int id_user, String path, String fec) 
	{
		super();
		this.cod = cod;
		this.id_user = id_user;
		this.path = path;
		this.fec = fec;
	}
	/**El m�todo despuesDe ordena las fotos dependiendo de su fec (fecha de creaci�n) mediante un
	 * m�todo gen�rico y la implementaci�n de una interfaz.
	 * @param foto: es el objeto foto (con todos sus atributos).*/
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
	/** M�todo get del atributo Cod.
	 * @return Cod : c�digo unico de la foto.*/
	public int getCod() 
	{
		return cod;
	}
	/** M�todo set del atributo Cod.
	 * @param cod : c�digo unico de la foto.*/
	public void setCod(int cod)
	{
		this.cod = cod;
	}
	/** M�todo get del atributo Id_user del objeto foto.
	 * @return id_user : id del user que nos facilitaraa identificar a que usuario pertenece dicha foto.*/
	public int getId_user() 
	{
		return id_user;
	}
	/** M�todo set del atributo Id_user del objeto foto.
	 * @param id_user : id del user que nos facilitaraa identificar a que usuario pertenece dicha foto.*/
	public void setId_user(int id_user) 
	{
		this.id_user = id_user;
	}
	/** M�todo get del atributo path.
	 * @return path : indica la direcci�n o ruta en el que se encuentra la foto almacenada (ruta relativa).*/
	public String getPath() 
	{
		return path;
	}
	/** M�todo set del atributo path.
	 * @param path : indica la direcci�n o ruta en el que se encuentra la foto almacenada (ruta relativa).*/
	public void setPath(String path) 
	{
		this.path = path;
	}
	/** M�todo get del atributo Fec.
	 * @return Fec : indica la fecha de creaci�n de la foto.*/
	public String getFec() 
	{
		return fec;
	}
	/** M�todo set del atributo Fec.
	 * @param fec : indica la fecha de creaci�n de la foto.*/
	public void setFec(String fec) 
	{
		this.fec = fec;
	}
}
