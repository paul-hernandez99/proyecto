package usuarios;

import java.util.ArrayList;
import entrada.Entrada;
import utilidades.Utilidades;

public class UsuarioNormal extends Usuario 
{
	private ArrayList <Entrada> entradas;
	
	public UsuarioNormal()
	{
		super();
	}
	
	public UsuarioNormal(String nombreUsuario, String contrase�a, String nombreReal, String fechaDeAlta) 
	{
		super(nombreUsuario, contrase�a, nombreReal, fechaDeAlta);
		this.entradas = new ArrayList<Entrada>();
	}

	public ArrayList<Entrada> getEntradas() 
	{
		return entradas;
	}

	public void setEntradas(ArrayList<Entrada> entradas) 
	{
		this.entradas = entradas;
	}	
	
}
