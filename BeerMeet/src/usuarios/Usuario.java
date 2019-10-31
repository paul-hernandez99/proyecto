package usuarios;
/**La funci�n de esta clase usuarios es definir y crear un objeto padre de tipo usuarios.
 * Este objeto totalmente abstracto extender�n los dos tipos posibles de usuarios:los administradores y usuarios comunes.
*@author aritz eraun y Paul Hernandez*/
public abstract class Usuario 
{/**Los distintos parametros del objeto de tipo usuario*/
	private String nombreUsuario;
	private String contrase�a;
	private String nombreReal;
	private String apellidos;
	private String email;
	/**Constructor vacio de la clase administrador.*/
	public Usuario()
	{
		
	}
   /**Constructor com�n de la clase administrador*/
	public Usuario(String nombreUsuario, String contrase�a, String nombreReal, String apellidos, String email)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrase�a = contrase�a;
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
