package usuarios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


/**La función de esta clase UsuariosComunes es definir y crear un objeto de tipo administrador que desciende de un objeto usuarios.
*@author aritz eraun y Paul Hernandez*/


public class UsuarioNormal extends Usuario
{
	/**Atributos especificos de un objeto UsuarioComun*/
	
	private int id;
	private String fechaNacimiento;
	private int edad;
	private String descripcion;
	
	/**Constructor Vacio de la clase UsuarioComun*/
	
	public UsuarioNormal()
	{
		super();
	}
	
	/**Constuctor super del objeto UsuarioComun (sin algunos atributos).
	 * @param nombreUsuario :nombre de Usuario del usuario.
	 * @param contraseña : contraseña del usuario.
	 * @param nombreReal : nombre real  del usuario.
	 * @param apellidos : apellidoreal del usuario.
	 * @param email :  email del usuario.
	 * @param fechaNacimiento : fecha de nacimiento del usuario.
	 * @param description: descripcion del usuario.*/
	
	public UsuarioNormal(String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email, String fechaNacimiento, String description) 
	{
		super(nombreUsuario, contraseña, nombreReal, apellidos, email);
		this.fechaNacimiento = fechaNacimiento;
		this.edad = this.calcularEdad();
	}
	
	/**Constuctor super del objeto UsuarioComun (con todos los atributos).
	 * @param nombreUsuario :nombre de Usuario del usuario.
	 * @param contraseña : contraseña del usuario.
	 * @param nombreReal : nombre real  del usuario.
	 * @param apellidos : apellidoreal del usuario.
	 * @param email :  email del usuario.
	 * @param fechaNacimiento : fecha de nacimiento del usuario.
	 * @param edad : edad del usuario.
	 * @param id : identificador único del usuario.
	 *@param descripcion : descripcion de la cuenta del usuario.*/
	
	public UsuarioNormal(int id, String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email, String fechaNacimiento, int edad, String descripcion) 
	{
		super(nombreUsuario, contraseña, nombreReal, apellidos, email);
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.id = id;
		this.descripcion = descripcion;
	}
	/**Método que calcula la edad del usuario
	 * @return period : edad del usuario.
	 */
	private int calcularEdad()
	{
		Period period = Period.between(fecNacToLocalDate(), LocalDate.now());
		return period.getYears();
	}
	/**Método que da formato fecha al atributo fecha.
	 * @return fechaNacimiento : la fecha de nacimiento en el fomato deseado. */
	private LocalDate fecNacToLocalDate()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(fechaNacimiento, dateFormat);
	}

	/**Método getter del atributo fechaNacimiento del usuarioNormal.
	 * @return fechaNacimiento : fecha de nacimiento del usuarioNormal.*/
	public String getFechaNacimiento() 
	{
		return fechaNacimiento;
	}
	/**Método setter del atributo fechaNacimiento del usuarioNormal.
	 * @param fechaNacimiento : fecha de nacimiento del usuarioNormal.*/
	public void setFechaNacimiento(String fechaNacimiento) 
	{
		this.fechaNacimiento = fechaNacimiento;
	}
	/**Método getter del atributo edad del usuarioNormal.
	 * @return edad : edad del usuarioNormal.*/
	public int getEdad() 
	{
		return edad;
	}
	/**Método setter del atributo edad del usuarioNormal.
	 * @param edad : edad del usuarioNormal.*/
	public void setEdad(int edad) 
	{
		this.edad = edad;
	}
	/**Método getter del atributo id del usuarioNormal.
	 * @return id : id del usuarioNormal.*/
	public int getId() 
	{
		return id;
	}
	/**Método setter del atributo id del usuarioNormal.
	 * @param id : id del usuarioNormal.*/
	public void setId(int id) 
	{
		this.id = id;
	}
	/**Método getter del atributo descripcion del usuarioNormal.
	 * @return descripcion: descripcion de la cuenta del usuarioNormal.*/
	public String getDescripcion() 
	{
		return descripcion;
	}
	/**Método setter del atributo descripcion del usuarioNormal.
	 * @param descripcion: descripcion de la cuenta del usuarioNormal.*/
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
}