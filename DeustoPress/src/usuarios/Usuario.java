package usuarios;

import java.util.ArrayList;

import comentario.Comentario;
import entrada.Entrada;
import utilidades.Utilidades;

public abstract class Usuario 
{
	private String nombreUsuario;
	
	private String contrase�a;
	
	private String nombreReal;
	
	private String fechaDeAlta;
	
	public Usuario()
	{
		
	}

	public Usuario(String nombreUsuario, String contrase�a, String nombreReal, String fechaDeAlta)
	{
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrase�a = contrase�a;
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

	public String getFechaDeAlta() 
	{
		return fechaDeAlta;
	}

	public void setFechaDeAlta(String fechaDeAlta) 
	{
		this.fechaDeAlta = fechaDeAlta;
	}
	
}
