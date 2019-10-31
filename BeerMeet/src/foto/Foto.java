package foto;
/**La función de esta clase denominada foto, es definir el objeto foto. La clase contiene los distintos atributos del 
 * objeto de tipo foto (el código, la identificación del usuario, etc.) además de los getters y setter que nos facilitan el acceso
 * y modificación de los datos.
*@author aritz eraun y Paul Hernandez*/

public class Foto 
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
