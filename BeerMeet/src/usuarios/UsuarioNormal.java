package usuarios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UsuarioNormal extends Usuario
{
	private int id;
	private String fechaNacimiento;
	private int edad;
	private String descripcion;
	
	public UsuarioNormal()
	{
		super();
	}
	
	public UsuarioNormal(String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email, String fechaNacimiento) 
	{
		super(nombreUsuario, contraseña, nombreReal, apellidos, email);
		this.fechaNacimiento = fechaNacimiento;
		this.edad = this.calcularEdad();
	}
	
	public UsuarioNormal(int id, String nombreUsuario, String contraseña, String nombreReal, String apellidos, String email, String fechaNacimiento, int edad) 
	{
		super(nombreUsuario, contraseña, nombreReal, apellidos, email);
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.id = id;
	}
	
	private int calcularEdad()
	{
		Period period = Period.between(fecNacToLocalDate(), LocalDate.now());
		return period.getYears();
	}
	
	private LocalDate fecNacToLocalDate()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(fechaNacimiento, dateFormat);
	}

	
	public String getFechaNacimiento() 
	{
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) 
	{
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() 
	{
		return edad;
	}

	public void setEdad(int edad) 
	{
		this.edad = edad;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	
}
