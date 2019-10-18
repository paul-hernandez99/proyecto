package usuarios;

import java.util.ArrayList;

import utilidades.Utilidades;

public class Administrador extends Usuario
{
	public Administrador()
	{
		super();
	}

	public Administrador(String nombreUsuario, String contraseña, String nombreReal, String fechaDeAlta) 
	{
		super(nombreUsuario, contraseña, nombreReal, fechaDeAlta);
	}
	
}
