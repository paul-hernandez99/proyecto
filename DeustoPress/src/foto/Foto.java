package foto;

public class Foto 
{
	private String path;
	
	private String fecha;
	
	public Foto()
	{
		
	}

	public Foto(String path, String fecha) 
	{
		super();
		this.path = path;
		this.fecha = fecha;
	}

	public String getPath() 
	{
		return path;
	}

	public void setPath(String path) 
	{
		this.path = path;
	}

	public String getFecha() 
	{
		return fecha;
	}

	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}
	
	
}
