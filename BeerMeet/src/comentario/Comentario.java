package comentario;
/**La función de esta clase comentario es definir un objeto de tipo comentario con sus respectivos atributos, 
*la clase tambien contiene los Getters y Setter de cada atributo.
*@author aritz eraun y Paul Hernandez*/
public class Comentario 
{ /**Los distintos atributos que posee el objeto comentario.*/
	private int cod_fot;
	private int id_user;
	private String contenido;
	private String fec;
	/**Constructor del objeto comentario.*/
	public Comentario()
	{
		
	}
	/**Constructor del objeto comentario.*/
	public Comentario(int cod_fot, int id_user, String contenido, String fec) 
	{
		super();
		this.cod_fot = cod_fot;
		this.contenido = contenido;
		this.fec = fec;
	}
	
	public int getCod_fot()
	{
		return cod_fot;
	}

	public void setCod_fot(int cod_fot) 
	{
		this.cod_fot = cod_fot;
	}

	public int getId_user()
	{
		return id_user;
	}

	public void setId_user(int id_user)
	{
		this.id_user = id_user;
	}

	public String getContenido() 
	{
		return contenido;
	}

	public void setContenido(String contenido) 
	{
		this.contenido = contenido;
	}

	public String getFec() 
	{
		return fec;
	}

	public void setFec(String fec) 
	{
		this.fec = fec;
	}

	@Override
	/**Estamos ante  el metodo Tostring. Su función crear un String partiendo de un objeto, dato abstracto .*/
	public String toString()
	{
		String texto = "\tAutor: "+this.getNombre()+". Fecha: "+this.fec+".\n\tComentario: "+this.contenido;
		
		return texto;
	}
	//Pendiente de hacer
	private String getNombre()
	{
		String name = "";
		return name;
	}
	
}
