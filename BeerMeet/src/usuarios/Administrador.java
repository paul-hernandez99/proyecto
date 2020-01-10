package usuarios;

/**La función de esta clase adminsitrador es definir y crear un objeto de tipo administrador que desciende de un objeto usuarios.
*@author aritz eraun y Paul Hernandez*/

public class Administrador extends Usuario
{
	private int id;
	
	/**Estamos ante el constructor del tipo SUPER/VACIO de la clase administrador.*/
	
	public Administrador()
	{
		super();
	}
	
	/**Estamos ante el constructor del tipo SUPER/COMÚN (no posee atributos especificos del objeto) de la clase usuarios.*/
	
	public Administrador(String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email) 
	{
		super(nombreUsuario, contraseña, nombreReal, apellidos, email);
	}
	
	public Administrador(int id, String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email) 
	{
		super(nombreUsuario, contraseña, nombreReal, apellidos, email);
		this.id = id;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	
}
