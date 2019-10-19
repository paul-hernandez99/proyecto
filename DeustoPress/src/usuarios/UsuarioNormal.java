package usuarios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UsuarioNormal extends Usuario
{
	private String fechaNacimiento;
	
	private int edad;
	
	
	public UsuarioNormal()
	{
		super();
	}
	
	public UsuarioNormal(String nombreUsuario, String contrase�a, String nombreReal, String email, String fechaNacimiento) 
	{
		super(nombreUsuario, contrase�a, nombreReal, email);
		this.fechaNacimiento = fechaNacimiento;
		this.edad = this.calcularEdad();
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
	
	
	
}
