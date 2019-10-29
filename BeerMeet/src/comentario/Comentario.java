package comentario;

public class Comentario 
{
	private int cod;
	private int cod_fot;
	private int id_user;
	private String contenido;
	private String fec;
	
	public Comentario()
	{
		
	}
	
	public Comentario(int cod_fot, int id_user, String contenido, String fec) 
	{
		super();
		this.cod_fot = cod_fot;
		this.contenido = contenido;
		this.fec = fec;
	}
	
	public Comentario(int cod, int cod_fot, int id_user, String contenido, String fec) 
	{
		super();
		this.cod = cod;
		this.cod_fot = cod_fot;
		this.contenido = contenido;
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

	public int getCod_fot()
	{
		return cod_fot;
	}

	public void setCod_fot(int cod_fot) 
	{
		this.cod_fot = cod_fot;
	}

	public int getId_user()
	{
		return id_user;
	}

	public void setId_user(int id_user)
	{
		this.id_user = id_user;
	}

	public String getContenido() 
	{
		return contenido;
	}

	public void setContenido(String contenido) 
	{
		this.contenido = contenido;
	}

	public String getFec() 
	{
		return fec;
	}

	public void setFec(String fec) 
	{
		this.fec = fec;
	}

	@Override
	public String toString()
	{
		String texto = "\tAutor: "+this.getNombre()+". Fecha: "+this.fec+".\n\tComentario: "+this.contenido;
		
		return texto;
	}
	//Pendiente de hacer
	private String getNombre()
	{
		String name = "";
		return name;
	}
	
}
