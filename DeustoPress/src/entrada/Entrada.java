package entrada;

import java.io.File;
import java.util.ArrayList;

import comentario.Comentario;
import usuarios.UsuarioNormal;

public class Entrada 
{
	private String titulo;
	
	private String etiqueta;
	
	private UsuarioNormal autor;
	
	private int visitas;
	
	private String fechaCreacion;
	
	private String path;
	
	private ArrayList<Comentario> comentarios;
	
	private int likes;
	
	private int dislikes;
	
	public Entrada()
	{
		
	}

	public Entrada(String titulo, String etiqueta, UsuarioNormal autor, int visitas, String fechaCreacion, String path, int likes, int dislikes) 
	{
		super();
		this.titulo = titulo;
		this.etiqueta = etiqueta;
		this.autor = autor;
		this.visitas = visitas;
		this.fechaCreacion = fechaCreacion;
		this.path = path;
		this.comentarios = new ArrayList<Comentario>();
		this.likes = likes;
		this.dislikes = dislikes;
	}
	
	public String toString()
	{
		File aFile = new File(path);
		return aFile.getName();
	}
	
	public String getTitulo() 
	{
		return titulo;
	}

	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}

	public String getEtiqueta()
	{
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta)
	{
		this.etiqueta = etiqueta;
	}

	public UsuarioNormal getAutor()
	{
		return autor;
	}

	public void setAutor(UsuarioNormal autor)
	{
		this.autor = autor;
	}

	public int getVisitas()
	{
		return visitas;
	}

	public void setVisitas()
	{
		this.visitas++;
	}

	public String getFechaCreacion()
	{
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String contenido)
	{
		this.path = contenido;
	}

	public ArrayList<Comentario> getComentarios() 
	{
		return comentarios;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) 
	{
		this.comentarios = comentarios;
	}

	public int getLikes() 
	{
		return likes;
	}

	public void setLikes() 
	{
		likes++;
	}

	public int getDislikes() 
	{
		return dislikes;
	}

	public void setDislikes() 
	{
		dislikes++;
	}
	
	
	
}
