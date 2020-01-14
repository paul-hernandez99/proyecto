package usuarios;


/**La función de esta clase usuarios es definir y crear un objeto padre de tipo usuarios.
 * Este objeto totalmente abstracto extenderán los dos tipos posibles de usuarios:los administradores y usuarios comunes.
*@author aritz eraun y Paul Hernandez
*@since 1.1
*@version 1.3*/

public abstract class Usuario 
{
	
/**Los distintos parametros del objeto de tipo usuario*/
	
	private String nombreUsuario;
	private String contraseña;
	private String nombreReal;
	private String apellidos;
	private String email;
	
	/**Constructor vacio de la clase administrador.*/
	public Usuario()
	{	}
   /**Constructor común de la clase administrador
    * @param nombreUsuario :nombre de Usuario del usuario.
	 * @param contraseña : contraseña del usuario.
	 * @param nombreReal : nombre real  del usuario.
	 * @param apellidos : apellidoreal del usuario.
	 * @param email :  email del usuario.*/
	
	public Usuario(String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.nombreReal = nombreReal;
		this.apellidos = apellidos;
		this.email = email;
	}
	/**Método getter del atributo NombreUsuario del usuario.
	 * @return nombreUsuario : nombre de usuario del usuario.*/
	public String getNombreUsuario() 
	{
		return nombreUsuario;
	}
	/**Método setter del atributo NombreUsuario del usuario.
	 * @param nombreUsuario : nombre de usuario del usuario.*/
	public void setNombreUsuario(String nombreUsuario) 
	{
		this.nombreUsuario = nombreUsuario;
	}
	/**Método getter del atributo contraseña del usuario.
	 * @return contraseña : contraseña del usuario.*/
	public String getContraseña() 
	{
		return contraseña;
	}
	/**Método setter del atributo contraseña del usuario.
	 * @param contraseña : contraseña del usuario.*/
	public void setContraseña(String contraseña) 
	{
		this.contraseña = contraseña;
	}
	/**Método getter del atributo nombreReal del usuario.
	 * @return nombreReal : nombre real del usuario.*/
	public String getNombreReal() 
	{
		return nombreReal;
	}
	/**Método setter del atributo nombreReal del usuario.
	 * @param nombreReal : nombre real del usuario.*/
	public void setNombreReal(String nombreReal) 
	{
		this.nombreReal = nombreReal;
	}
	/**Método getter del atributo apellidos del usuario.
	 * @return apellidos : apellidos del usuario.*/
	public String getApellidos() 
	{
		return apellidos;
	}
	/**Método setter del atributo apellidos del usuario.
	 * @param apellidos : apellidos del usuario.*/
	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}
	/**Método getter del atributo email del usuario.
	 * @return email : email del usuario.*/
	public String getEmail() {
		return email;
	}
	/**Método setter del atributo email del usuario.
	 * @param email : email del usuario.*/
	public void setEmail(String email) 
	{
		this.email = email;
	}
	/**Método toString del objeto usuario.
	 * @return respuesta : el mensaje toString que se desea mostrar.*/
	@Override
	public String toString() 
	{
		String respuesta =nombreUsuario +"   "+nombreReal+" "
				 + apellidos ;
		return  respuesta;
	}
}
