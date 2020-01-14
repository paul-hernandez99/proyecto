package usuarios;

/**La funci�n de esta clase adminsitrador es definir y crear un objeto de tipo administrador que desciende de un objeto usuarios.
*@author aritz eraun y Paul Hernandez
*@version 1.3
*@since 1.1*/

public class Administrador extends Usuario
{
	private int id;
	
	/**Estamos ante el constructor del tipo SUPER/VACIO de la clase administrador.*/
	
	public Administrador()
	{
		super();
	}
	
	/**Estamos ante el constructor del tipo SUPER/COM�N (no posee atributos especificos del objeto) de la clase usuarios.
	 * @param nombreUsuario :nombre de Usuario del administrador.
	 * @param contrase�a : contrase�a del adminitrador.
	 * @param nombreReal : nombre real  del administrador.
	 * @param apellidos : apellidoreal del adminitrador.
	 * @param email :  email del administrador.*/
	
	public Administrador(String nombreUsuario, String contrase�a, String nombreReal, String apellidos, String email) 
	{
		super(nombreUsuario, contrase�a, nombreReal, apellidos, email);
	}
	
	/**Estamos ante el constructor del tipo SUPER/ESPECIFICO (posee atributos especificos del objeto) de la clase usuarios.
	 * @param nombreUsuario :nombre de Usuario del administrador.
	 * @param contrase�a : contrase�a del adminitrador.
	 * @param nombreReal : nombre real  del administrador.
	 * @param apellidos : apellidoreal del adminitrador.
	 * @param email :  email del administrador.
	 * @param id : identificaci�n �nica del adminitrador.*/
	
	public Administrador(int id, String nombreUsuario, String contrase�a, String nombreReal, String apellidos, String email) 
	{
		super(nombreUsuario, contrase�a, nombreReal, apellidos, email);
		this.id = id;
	}
	/**M�todo getter del atributo id del adminitrador.
	 * @return id : identificaci�n �nica del adminitrador.*/
	public int getId() 
	{
		return id;
	}
	/**M�todo setter del atributo id del adminitrador.
	 * @param id : identificaci�n �nica del adminitrador.*/
	public void setId(int id) 
	{
		this.id = id;
	}
}
