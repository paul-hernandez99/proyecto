package foto;

public class Foto 
{
	private int cod;
	private int id_user;
	private String path;
	private String fec;
	
	public Foto()
	{
		
	}

	public Foto(int id_user, String path, String fec) 
	{
		super();
		this.id_user = id_user;
		this.path = path;
		this.fec = fec;
	}
	
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
