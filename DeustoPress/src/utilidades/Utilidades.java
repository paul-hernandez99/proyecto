package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import comentario.Comentario;
import entrada.Entrada;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;

public class Utilidades 
{
	public static void escribirEnFichero(String ruta, ArrayList <Usuario> usuarios)
	{
		File aFile = new File(ruta);
		aFile.delete();
		try 
		{
			FileWriter fw = new FileWriter(aFile);
			for(int i=0; i<usuarios.size(); i++)
			{
				Usuario usuario = usuarios.get(i);
				String user = usuario.getNombreUsuario();
				String pass = usuario.getContraseña();
				String nombre = usuario.getNombreReal();
				String fechaAlta = usuario.getFechaDeAlta();
				int estado;
				if(usuarios.get(i) instanceof Administrador)
				{
					estado = 0;
					if(i != usuarios.size()-1)
					{
						fw.write(user+";"+pass+";"+nombre+";"+fechaAlta+";"+estado+"\n");
					}
					else
					{
						fw.write(user+";"+pass+";"+nombre+";"+fechaAlta+";"+estado);
					}
				}
				else
				{
					estado = 1;
					
					fw.write(user+";"+pass+";"+nombre+";"+fechaAlta+";"+estado);	
				
					if(!((UsuarioNormal)usuario).getEntradas().isEmpty())
					{
						fw.write(";1\n");
						for(int j=0; j<((UsuarioNormal)usuario).getEntradas().size(); j++)
						{
							Entrada entrada = ((UsuarioNormal)usuario).getEntradas().get(j);
							String titulo = entrada.getTitulo();
							String etiqueta = entrada.getEtiqueta();
							int visitas = entrada.getVisitas();
							String fecha = entrada.getFechaCreacion();
							String path = entrada.getPath();
							int likes = entrada.getLikes();
							int dislikes = entrada.getDislikes();
							if(entrada.getComentarios().isEmpty())
							{
								if(j != ((UsuarioNormal)usuario).getEntradas().size()-1)
								{
									fw.write("\t"+titulo+";"+etiqueta+";"+visitas+";"+fecha+";"+path+";"+likes+";"+dislikes+";0\n");
								}
								else
								{
									fw.write("\t"+titulo+";"+etiqueta+";"+visitas+";"+fecha+";"+path+";"+likes+";"+dislikes+";0");
								}
							}
							else
							{
								fw.write("\t"+titulo+";"+etiqueta+";"+visitas+";"+fecha+";"+path+";"+likes+";"+dislikes+";1\n");
								
								for(int k=0; k<entrada.getComentarios().size(); k++)
								{
									Comentario comentario = entrada.getComentarios().get(k);
									String autor = comentario.getNombreUsuarioAutor();
									String fechaComentario = comentario.getFecha();
									String pathComent = comentario.getPath();
									
									if(k == entrada.getComentarios().size()-1 && j==((UsuarioNormal)usuario).getEntradas().size()-1)
									{
										fw.write("\t\t"+fechaComentario+";"+pathComent+";"+autor);
									}
									else
									{
										fw.write("\t\t"+fechaComentario+";"+pathComent+";"+autor+"\n");
									}		
								}
							}
						}
						if(i!=usuarios.size()-1)
						{
							fw.write("\n");
						}
					}
					else
					{
						if(i==usuarios.size()-1)
						{
							fw.write(";0");
						}
						else
						{
							fw.write(";0\n");
						}
					}
				}
			}
			fw.flush();
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public static ArrayList<Usuario> leerUsuarios(String path)
	{
		ArrayList <Usuario> usuarios = new ArrayList<>();
		File aFile = new File(path);
		
		try 
		{
			FileReader fr= new FileReader(aFile);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while(linea!=null)
			{
				Usuario usuario;
				String[] lineaSeparada = linea.split(";");
				if(lineaSeparada[4].equals("0"))
				{
					usuario = new Administrador(lineaSeparada[0], lineaSeparada[1], lineaSeparada[2], lineaSeparada[3]);
					linea = br.readLine();
				}
				else
				{
					usuario = new UsuarioNormal(lineaSeparada[0], lineaSeparada[1], lineaSeparada[2], lineaSeparada[3]);
					if(lineaSeparada[5].equals("1"))
					{
						ArrayList<Entrada>entradas = new ArrayList<Entrada>();
						linea= br.readLine();
						while(linea.startsWith("\t"))
						{
							String[] lineaSeparadaEntrada = linea.split(";");
							lineaSeparadaEntrada[0] = lineaSeparadaEntrada[0].substring(1, lineaSeparadaEntrada[0].length());
							Entrada entrada = new Entrada(lineaSeparadaEntrada[0], lineaSeparadaEntrada[1],(UsuarioNormal)usuario, Integer.parseInt(lineaSeparadaEntrada[2]), lineaSeparadaEntrada[3], lineaSeparadaEntrada[4], Integer.parseInt(lineaSeparadaEntrada[5]), Integer.parseInt(lineaSeparadaEntrada[6]));
							entradas.add(entrada);
							linea= br.readLine();
							if(lineaSeparadaEntrada[7].equals("1"))
							{
								while(linea.startsWith("\t\t"))
								{
									String[] lineaSeparadaComentario = linea.split(";");
									lineaSeparadaComentario[0] = lineaSeparadaComentario[0].substring(2, lineaSeparadaComentario[0].length());
									Comentario comentario = new Comentario(lineaSeparadaComentario[2], lineaSeparadaComentario[1], lineaSeparadaComentario[0]);
									entrada.getComentarios().add(comentario);
									linea = br.readLine();
									if(linea==null)
										break;
								}
							}
							if(linea==null)
								break;
						}
						((UsuarioNormal)usuario).setEntradas(entradas);
					}
					else
						linea = br.readLine();
				}
				usuarios.add(usuario);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public static String leerCadena()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		boolean err = true;
		String cadena="";
		do
		{
			try {
				cadena = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			err=false;
			
		}while(err);
			return cadena;
	}
	
	public static int leerEntero()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Integer entero = null;
		boolean error = true;
		do
		{
			try
			{
				String cadena = br.readLine();
				entero = new Integer(cadena);
				error = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("No tecleó un número entero-Repetir");
			}
			catch (Exception e)
			{
				System.out.println("Error de entrada-Repetir ");
			}
		}
		while(error);
		return entero.intValue();
	}
	
	public static String fechaDeAlta()
	{
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
		String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		
		String fecha = day+"/"+month+"/"+year;
		
		return fecha;
	}
}
