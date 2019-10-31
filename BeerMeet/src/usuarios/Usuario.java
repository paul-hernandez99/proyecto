package usuarios;
/**La función de esta clase usuarios es definir y crear un objeto padre de tipo usuarios.
 * Este objeto totalmente abstracto extenderán los dos tipos posibles de usuarios:los administradores y usuarios comunes.
*@author aritz eraun y Paul Hernandez*/
public abstract class Usuario 
{/**Los distintos parametros del objeto de tipo usuario*/
	private String nombreUsuario;
	private String contraseña;
	private String nombreReal;
	private String apellidos;
	private String email;
	/**Constructor vacio de la clase administrador.*/
	public Usuario()
	{
		
	}
   /**Constructor común de la clase administrador*/
	public Usuario(String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.nombreReal = nombreReal;
		this.apellidos = apellidos;
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

	public String getApellidos() 
	{
		return apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
}
