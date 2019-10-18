package usuarios;

import java.util.ArrayList;

import comentario.Comentario;
import entrada.Entrada;
import utilidades.Utilidades;

public abstract class Usuario 
{
	private String nombreUsuario;
	
	private String contraseña;
	
	private String nombreReal;
	
	private String fechaDeAlta;
	
	public Usuario()
	{
		
	}

	public Usuario(String nombreUsuario, String contraseña, String nombreReal, String fechaDeAlta)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.nombreReal = nombreReal;
		this.fechaDeAlta = fechaDeAlta;
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

	public String getFechaDeAlta() 
	{
		return fechaDeAlta;
	}

	public void setFechaDeAlta(String fechaDeAlta) 
	{
		this.fechaDeAlta = fechaDeAlta;
	}
	
}
