package comentario;

public class Comentario 
{
	private String fecha;
	
	private String nombreUsuarioAutor;
	
	private String path;
	
	public Comentario()
	{
		
	}

	public Comentario(String nombreUsuarioAutor, String path, String fecha) 
	{
		super();
		this.fecha = fecha;
		this.path = path;
		this.nombreUsuarioAutor= nombreUsuarioAutor;
	}

	public String getFecha() 
	{
		return fecha;
	}

	public void setFecha(String fecha) 
	{
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

	
	public String getNombreUsuarioAutor() 
	{
		return nombreUsuarioAutor;
	}

	public void setNombreUsuarioAutor(String nombreUsuarioAutor) 
	{
		this.nombreUsuarioAutor = nombreUsuarioAutor;
	}

	@Override
	public String toString()
	{
		String texto = "\tAutor: "+this.nombreUsuarioAutor+". Fecha: "+this.fecha+".\n\tComentario: "+this.path;
		
		return texto;
	}
	
}
