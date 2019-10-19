package usuarios;

public abstract class Usuario 
{
	private String nombreUsuario;
	
	private String contraseña;
	
	private String nombreReal;
	
	private String email;
	
	public Usuario()
	{
		
	}

	public Usuario(String nombreUsuario, String contraseña, String nombreReal, String email)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
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

	public String getContraseña() 
	{
		return contraseña;
	}

	public void setContraseña(String contraseña) 
	{
		this.contraseña = contraseña;
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
