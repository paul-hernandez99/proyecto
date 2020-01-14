package usuarios;


/**La funci�n de esta clase usuarios es definir y crear un objeto padre de tipo usuarios.
 * Este objeto totalmente abstracto extender�n los dos tipos posibles de usuarios:los administradores y usuarios comunes.
*@author aritz eraun y Paul Hernandez
*@since 1.1
*@version 1.3*/

public abstract class Usuario 
{
	
/**Los distintos parametros del objeto de tipo usuario*/
	
	private String nombreUsuario;
	private String contrase�a;
	private String nombreReal;
	private String apellidos;
	private String email;
	
	/**Constructor vacio de la clase administrador.*/
	public Usuario()
	{	}
   /**Constructor com�n de la clase administrador
    * @param nombreUsuario :nombre de Usuario del usuario.
	 * @param contrase�a : contrase�a del usuario.
	 * @param nombreReal : nombre real  del usuario.
	 * @param apellidos : apellidoreal del usuario.
	 * @param email :  email del usuario.*/
	
	public Usuario(String nombreUsuario, String contrase�a, String nombreReal, String apellidos, String email)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrase�a = contrase�a;
		this.nombreReal = nombreReal;
		this.apellidos = apellidos;
		this.email = email;
	}
	/**M�todo getter del atributo NombreUsuario del usuario.
	 * @return nombreUsuario : nombre de usuario del usuario.*/
	public String getNombreUsuario() 
	{
		return nombreUsuario;
	}
	/**M�todo setter del atributo NombreUsuario del usuario.
	 * @param nombreUsuario : nombre de usuario del usuario.*/
	public void setNombreUsuario(String nombreUsuario) 
	{
		this.nombreUsuario = nombreUsuario;
	}
	/**M�todo getter del atributo contrase�a del usuario.
	 * @return contrase�a : contrase�a del usuario.*/
	public String getContrase�a() 
	{
		return contrase�a;
	}
	/**M�todo setter del atributo contrase�a del usuario.
	 * @param contrase�a : contrase�a del usuario.*/
	public void setContrase�a(String contrase�a) 
	{
		this.contrase�a = contrase�a;
	}
	/**M�todo getter del atributo nombreReal del usuario.
	 * @return nombreReal : nombre real del usuario.*/
	public String getNombreReal() 
	{
		return nombreReal;
	}
	/**M�todo setter del atributo nombreReal del usuario.
	 * @param nombreReal : nombre real del usuario.*/
	public void setNombreReal(String nombreReal) 
	{
		this.nombreReal = nombreReal;
	}
	/**M�todo getter del atributo apellidos del usuario.
	 * @return apellidos : apellidos del usuario.*/
	public String getApellidos() 
	{
		return apellidos;
	}
	/**M�todo setter del atributo apellidos del usuario.
	 * @param apellidos : apellidos del usuario.*/
	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}
	/**M�todo getter del atributo email del usuario.
	 * @return email : email del usuario.*/
	public String getEmail() {
		return email;
	}
	/**M�todo setter del atributo email del usuario.
	 * @param email : email del usuario.*/
	public void setEmail(String email) 
	{
		this.email = email;
	}
	/**M�todo toString del objeto usuario.
	 * @return respuesta : el mensaje toString que se desea mostrar.*/
	@Override
	public String toString() 
	{
		String respuesta =nombreUsuario +"   "+nombreReal+" "
				 + apellidos ;
		return  respuesta;
	}
}
