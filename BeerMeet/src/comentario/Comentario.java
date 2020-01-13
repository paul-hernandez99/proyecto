package comentario;
/**La funci�n de esta clase comentario es definir un objeto de tipo comentario con sus respectivos atributos, 
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
	/** M�todo get del atributo Cod_fot.
	 * @return cod_fot : c�digo unico de la foto.*/
	public int getCod_fot()
	{
		return cod_fot;
	}
	/** M�todo set del atributo Cod_fot.
	 * @param cod_fot : c�digo unico de la foto.*/
	public void setCod_fot(int cod_fot) 
	{
		this.cod_fot = cod_fot;
	}
	/** M�todo get del atributo Id_user del objeto foto.
	 * @return id_user : id del user que nos facilitaraa identificar a que usuario pertenece dicha foto.*/
	public int getId_user()
	{
		return id_user;
	}
	
	/** M�todo set del atributo Id_user del objeto foto.
	 * @param id_user : id del user que nos facilitaraa identificar a que usuario pertenece dicha foto.*/
	public void setId_user(int id_user)
	{
		this.id_user = id_user;
	}
	/** M�todo get del atributo contenido.
	 * @return comentario : es el contenido de texto del comentario.*/
	public String getContenido() 
	{
		return contenido;
	}
	/** M�todo set del atributo contenido.
	 * @param contenido : es el contenido de texto del comentario.*/
	public void setContenido(String contenido) 
	{
		this.contenido = contenido;
	}
	/** M�todo get del atributo Fec.
	 * @return Fec : indica la fecha de creaci�n del comentario.*/
	public String getFec() 
	{
		return fec;
	}
	/** M�todo set del atributo Fec.
	 * @param Fec : indica la fecha de creaci�n del comentario.*/
	public void setFec(String fec) 
	{
		this.fec = fec;
	}

	@Override
	/**Estamos ante  el metodo toString. Su funci�n crear un String partiendo de un objeto, dato abstracto .*/
	public String toString()
	{
		String texto = " Fecha: "+this.fec+".\n\tComentario: "+this.contenido;
		
		return texto;
	}

}
