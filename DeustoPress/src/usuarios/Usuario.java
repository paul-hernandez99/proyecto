package usuarios;

public abstract class Usuario 
{
	private String nombreUsuario;
	
	private String contrase�a;
	
	private String nombreReal;
	
	private String email;
	
	public Usuario()
	{
		
	}

	public Usuario(String nombreUsuario, String contrase�a, String nombreReal, String email)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrase�a = contrase�a;
		this.nombreReal = nombreReal;
		this.nombreReal = nombreReal;
		this.email = email;
	}

	public String getNombreUsuario() 
	{
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) 
	{
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrase�a() 
	{
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) 
	{
		this.contrase�a = contrase�a;
	}

	public String getNombreReal() 
	{
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) 
	{
		this.nombreReal = nombreReal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
}
