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
	/**Constructor del objeto comentario (constructor vacio).*/
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
	/** Método get del atributo Cod_fot.
	 * @return cod_fot : código unico de la foto.*/
	public int getCod_fot()
	{
		return cod_fot;
	}
	/** Método set del atributo Cod_fot.
	 * @param cod_fot : código unico de la foto.*/
	public void setCod_fot(int cod_fot) 
	{
		this.cod_fot = cod_fot;
	}
	/** Método get del atributo Id_user del objeto foto.
	 * @return id_user : id del user que nos facilitaraa identificar a que usuario pertenece dicha foto.*/
	public int getId_user()
	{
		return id_user;
	}
	
	/** Método set del atributo Id_user del objeto foto.
	 * @param id_user : id del user que nos facilitaraa identificar a que usuario pertenece dicha foto.*/
	public void setId_user(int id_user)
	{
		this.id_user = id_user;
	}
	/** Método get del atributo contenido.
	 * @return comentario : es el contenido de texto del comentario.*/
	public String getContenido() 
	{
		return contenido;
	}
	/** Método set del atributo contenido.
	 * @param contenido : es el contenido de texto del comentario.*/
	public void setContenido(String contenido) 
	{
		this.contenido = contenido;
	}
	/** Método get del atributo Fec.
	 * @return Fec : indica la fecha de creación del comentario.*/
	public String getFec() 
	{
		return fec;
	}
	/** Método set del atributo Fec.
	 * @param Fec : indica la fecha de creación del comentario.*/
	public void setFec(String fec) 
	{
		this.fec = fec;
	}

	@Override
	/**Estamos ante  el metodo toString. Su función crear un String partiendo de un objeto, dato abstracto .*/
	public String toString()
	{
		String texto = " Fecha: "+this.fec+".\n\tComentario: "+this.contenido;
		
		return texto;
	}

}
