package SQLite;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import comentario.Comentario;
import foto.Foto;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;

/**Esta clase contiene los distintos métodos para la cración y gestion de la base de datos que utilizaremos en nuestra aplicación BeerMeet.
 * Entre esos métodos se encuentran el método de creacion de la BD, la inserción de contenido en la DB y la extracción de datos de la DB.
*@author aritz eraun y Paul Hernandez
*@version 1.3*/
public class BDManager 
{
	private String name;
	private String url;
	private Connection conn;
	
	/**Este método crea una la dirección de la bases de datos, que estará previamente creada
	 * @param esTest : variable booleana que indica que base de datos tomar. Utilizamos un BD para el test de Junit*/
	
	public BDManager(boolean esTest)
	{
		if(esTest)
		{
			this.name = "test.db";
		}
		else
			this.name = "database.db";
		this.url = "jdbc:sqlite:" + this.name;
		this.connect();
	}
	
	/**Este método conecta la BD con la aplicación de BeerMeet. Se crea la conecxión.*/
	
	public void connect()
	{
	    try
	    {
	    	this.conn = DriverManager.getConnection(this.url);
	    }
	    catch (SQLException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	}
	
	/**Este método desconecta la BD con la aplicación de BeerMeet.*/
	
	public void disconnect()
	{
		try
        {
            if (this.conn != null)
            {
                this.conn.close();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("BadAss error closing connection" + ex.getMessage());
        }
	}
	
	/**Este método guarda los datos de los distintos usuarios en la DB.
	 * @param user: Usuario que se desea guardar en BD (Administrador o UsuarioNormal)*/
	
	public void saveUser(Usuario user)
	{
		this.insertUser(user);
		if(user instanceof UsuarioNormal)
		{
			((UsuarioNormal) user).setId(this.seleccionarIdUsuario(user));
		}else {
			((Administrador) user).setId(this.seleccionarIdUsuario(user));
		}
	}
	
	/**Este método recibe información de la DB y crea un ArrayList para el uso en la aplicación.
	 * @return usuarios : todos los usuarios reguistrados en la BD.
	 */
	
	public ArrayList<Usuario> loadUsers()
	{
		ArrayList<Usuario> usuarios = this.selectAllUsers();
		return usuarios;
	}
	
	/**Este método selecciona las fotos guardas en la DB dependiendo del usuario y sus relaciones con otros usuarios.
	 * @param id_user: id desl usuario del que hay buscar los amigos y sus respectivas fotos.
	 * @return fotos: ArrayList de las fotos de los amigos del usuario.*/
	public ArrayList<Foto> loadInicioPhotos(int id_user)
	{
		final String sql = "select * from Fotos join (SELECT id_followed FROM User_User WHERE id_follower = ?) B"
			 	+ " on id_user = B.id_followed;";
		
		ArrayList<Foto> fotos = this.selectPhotos(id_user, sql);
		return fotos;
	}
	/**Este método selecciona las relaciones del usuario (seguidores) para después contabilizarlos.
	 * @param id_user: id del usuario del que hay buscar los amigos para contarlos.
	 * @return seguidores.size(): número de seguidores que tiene el usuario*/
	public int Seguidores(int id_user)
	{
		final String sql = "select * from Usuarios A join (select id_followed from User_User where id_follower = "+id_user+") B on B.id_followed = A.id;";
		
		ArrayList<Usuario> seguidores =this.selectUsuario(sql);
		return seguidores.size();
	}
	/**Este método selecciona las relaciones del usuario (segidos) para después contabilizarlos.
	 * @param id_user: id del usuario del que hay buscar los amigos para contarlos.
	 * @return seguidores.size(): número usuarios a los que sigue el usuario.*/
	public int Seguidos(int id_user)
	{
		final String sql = "select * from Usuarios A join (select * from User_User where id_follower = "+id_user+") B on B.id_follower = A.id;";
		
		ArrayList<Usuario> seguidores = this.selectUsuario(sql);
		
		return seguidores.size();
	}
	/**Este método selecciona las relaciones del usuario con los otros usuarios existentes.
	 * @param id_user: id del usuario del que hay buscar los amigos.
	 * @return seguidores: ArrayList de los seguidores(personas que le siguen) del usuario.*/
	public ArrayList<Usuario> relationships(int id_user)
	{
		final String sql = "select * from Usuarios A join (select id_followed from User_User where id_follower = "+ id_user +") B on B.id_followed = A.id;";
		
		ArrayList<Usuario> seguidores = this.selectUsuario(sql);
		return seguidores;
	}
	
	/**Este método seleciona fotos desde la BD pedemdiendo del usuario.
	 * @param id_user: id del usuario del que hay buscar las fotos.
	 * @return fotos : todas las fotos disponibles en la BD del usuario.*/
	
	public ArrayList<Foto> loadUsersPhotos(int id_user)
	{
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		ArrayList<Foto> fotos = this.selectPhotos(id_user, sql);
		return fotos;
	}
	
	/**Este método guarda las fotos en la BD.
	 * @param foto: foto que se desea guardar en la BD*/
	public void savePhoto(Foto foto)
	{
		this.insertPhoto(foto);
		foto.setCod(this.selectCodPhoto(foto));
	}
	
	/**Este método guarda en la BD los usuarios que se han reguistrado en nuestra aplicaión.
	 * @param user : Usuario a reguistrar en la BD. */
	
	private void insertUser(Usuario user)
	{
		String sql = "INSERT INTO Usuarios (type, username, password, name, surnames, email, fec_nac, age, description) VALUES(?,?,?,?,?,?,?,?,?)";
		
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(2, user.getNombreUsuario());
            pstmt.setString(3, user.getContraseña());
            pstmt.setString(4, user.getNombreReal());
            pstmt.setString(5, user.getApellidos());
            pstmt.setString(6, user.getEmail());
            
            if(user instanceof UsuarioNormal)
            {
            	pstmt.setInt(1, 0);
            	pstmt.setString(7, ((UsuarioNormal) user).getFechaNacimiento());
            	pstmt.setInt(8, ((UsuarioNormal) user).getEdad());
            	pstmt.setString(9, ((UsuarioNormal) user).getDescripcion());
            }
            else
            {
            	pstmt.setInt(1, 1);
            	pstmt.setNull(7, Types.LONGNVARCHAR);
            	pstmt.setNull(8, Types.INTEGER);
            	pstmt.setNull(9, Types.LONGNVARCHAR);
            }
            
            pstmt.executeUpdate(); 
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing insert. " + e.getMessage());
        }
	}
	
	/**Este método seleciona usuarios BD pedemdiendo de su Nombre de usuario.
	 * @param user : usuario al que se desea buscar id.
	 * @return id : id del usuario seleccionado.*/
	
	public int seleccionarIdUsuario(Usuario user)
	{
		final String sql = "SELECT id FROM Usuarios WHERE username = ?";
		
		int id = -1;
		
        try
        		(
        				PreparedStatement pstmt = conn.prepareStatement(sql);
                )
        {
        	pstmt.setString(1, user.getNombreUsuario());
        	ResultSet rs    = pstmt.executeQuery();
        	id = rs.getInt("id");
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing select. " + e.getMessage());
        }
        
        return id;
	}
	
	/**Este método seleciona todos los usuarios de la BD.
	 * @return users : ArrayList de todos los usuarios( Administradores y UsuariosNormales) existentes en la BD.*/
	
	private ArrayList<Usuario> selectAllUsers()
	{
		final String sql = "SELECT * FROM Usuarios";

		ArrayList<Usuario> users = selectUsuario(sql);
		return users;
	}
	
	/**Este método seleciona uno a uno los usuarios de la BD.
	 * @return sql : el comando sql que se ejecutará en la BD para seleccionar los distintos Usuarios.
	 * @return users : ArrayList de todos los usuarios( Administradores y UsuariosNormales) existentes en la BD.*/
	
	private ArrayList<Usuario> selectUsuario(String sql)
	{
		ArrayList<Usuario> users = new ArrayList<>();		
		
        try
                (		
                		PreparedStatement pstmt = conn.prepareStatement(sql);
        				ResultSet rs = pstmt.executeQuery();
                )
        {
        	
            while (rs.next())
            {
            	int id = rs.getInt("id");
            	int type = rs.getInt("type");
            	String username = rs.getString("username");
            	String password = rs.getString("password");
            	String name = rs.getString("name");
            	String apellidos = rs.getString("surnames");
            	String email = rs.getString("email");
            	
            	if(type == 0)
            	{
            		String fec_nac = rs.getString("fec_nac");
                	int edad = rs.getInt("age");
                	String description = rs.getString("description");
                  	UsuarioNormal usuarioNormal = new UsuarioNormal(id, username, password, name, apellidos, email, fec_nac, edad, description);
                	users.add(usuarioNormal);
            	}
            	else
            	{
            		Administrador administrador = new Administrador(id, username, password, name, apellidos, email);
            		users.add(administrador);
            	}
            }
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return users;
	}
	
	/**Este método seleciona fotos desde la BD.
	 * @param id: id del usuario de la foto.
	 * @param sql :  comndo sql que se ejecutará en la BD.
	 * @return fotos: las fotos pertenecientes al usuario seleccionado.*/
	
	private ArrayList<Foto> selectPhotos(int id, String sql)
	{

		ArrayList<Foto> fotos = new ArrayList<>();
		
        try
                (
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                )
        {
        	pstmt.setInt(1, id);
        	ResultSet rs  = pstmt.executeQuery();
        	
            while (rs.next())
            {
            	int cod = rs.getInt("cod");
            	int id_user = rs.getInt("id_user");
            	String path = rs.getString("path");
            	String fec = rs.getString("fec");
            	
            	fotos.add(new Foto(cod, id_user, path, fec));	
            }
        } 
        catch (SQLException e)
        {
        	System.out.println("BadAss error executing insert. " + e.getMessage());
        }
        
        return fotos;
	}
	
	/**Este método inserta objetos de tipo foto en la BD pedemdiendo del tipo de usuario.
	 * @param foto: la foto que se desea guardar en la BD.*/
	
	private void insertPhoto(Foto foto)
	{
		final String sql = "INSERT INTO Fotos(id_user, path, fec) VALUES (?,?,?)";
		
        try
        		(
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            pstmt.setInt(1, foto.getId_user());
            pstmt.setString(2, foto.getPath());
            pstmt.setString(3, foto.getFec());
            
            pstmt.executeUpdate();
            
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing insert. " + e.getMessage());
        }
	}
	
	/**Este método seleciona todos los URls de las fotos guardas en la BD.
	 * @param foto : foto al que se le quiere extraer lel código de identificación.
	 * @return cod : código de identificación de la foto seleccionada.*/
	
	private int selectCodPhoto(Foto foto)
	{
		final String sql = "SELECT cod FROM Fotos WHERE path = ?";
		
		int cod = -1;
		
        try
        		(
        				PreparedStatement pstmt = conn.prepareStatement(sql);
                )
        {
        	pstmt.setString(1, foto.getPath());
        	ResultSet rs = pstmt.executeQuery();
            cod = rs.getInt("cod");
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing select. " + e.getMessage());
        }
        
        return cod;
	}
	/**Método que inserta un foto de tipo perfil en la tabla FotoPerfil.
	 * @param foto: foto de perfil que se desea insertar.*/

	public void insertPhotoPerfil(Foto foto)
	{
		final String sql = "INSERT INTO FotoPerfil(id, path) VALUES (?,?)";
		
	    try
	    		(
	                    PreparedStatement pstmt = conn.prepareStatement(sql)
	            )
	    {
	        pstmt.setInt(1, foto.getId_user());
	        pstmt.setString(2, foto.getPath());
	               
	        pstmt.executeUpdate();
	        
	    }
	    catch (SQLException e)
	    {
	        System.out.println("BadAss error executing insert. " + e.getMessage());
	    }
	}
	/**Método que se quiere seleccionar de la tabla FotoPerfil,
	 * @param id : id del usuario del que se quiere buscar la foto de perfil.
	 * @return path: direccion o ruta relativa en la que se encuenra la foto de perfil del usuario.*/
	public String selectPhotoPerfil(int id) 
	{
		String sql="SELECT path FROM FotoPerfil where id = ? ;";

		String path = null;
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);)
	    {
	    	pstmt.setInt(1, id);
	    	ResultSet rs  = pstmt.executeQuery();
	    	
	        while (rs.next())
	        {
	        	path = rs.getString("path");
	        }
	    } 
	    catch (SQLException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	    
	    return path;
	}
	/**Método que se elimina el foto de perfil del usuario determinado de la tabla FotoPerfil,
	 * @param id : id del usuario del que se quiere eliminar la foto de perfil.*/
	public void deletePhotoPerfil(int id) 
	{
		String sql="DELETE FROM FotoPerfil where id = ? ;";

		try (PreparedStatement pstmt = conn.prepareStatement(sql))
	    {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}	    
	}
	/**Método de busqueda de Nombre de usuario partiendo de la id del usuario.
	 * @param id : id del usuario del que se quiere saber el Nombre de usuario.
	 * @return username : nombre del usuario respecto a su id.*/
	public String SelectNombreUsuaruario(int id)
	{
		String sql="SELECT username FROM Usuarios where id = ? ;";

		String username = null;
		
	    try (PreparedStatement pstmt = conn.prepareStatement(sql))
	    {
	    	pstmt.setInt(1, id);
	    	ResultSet rs  = pstmt.executeQuery();
	        while (rs.next())
	        {
	        	username = rs.getString("username");
	        }
	    } 
	    catch (SQLException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	    
	    return username;
	}
	/**Método que crea una relación de amistad ente usuarios.
	 * @param id_follower : id del seguidor.
	 * @param id_followed : id de la persona seguida.*/	
	public void createRelationship(int id_follower, int id_followed)
	{
		final String sql = "INSERT INTO User_User(id_follower,id_followed) VALUES (?,?)";
		
	    try	( PreparedStatement pstmt = conn.prepareStatement(sql))
	    {
	        pstmt.setInt(1, id_follower);
	        pstmt.setInt(2, id_followed);      
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	        System.out.println("BadAss error executing insert. " + e.getMessage());
	    }
	}
	/**Método que elimina una relación de amistad ente usuarios.
	 * @param id_follower : id del seguidor.
	 * @param id_followed : id de la persona seguida.*/
	public void deleteRelationship(int id_follower, int id_followed)
	{
		final String sql = "DELETE FROM User_User WHERE (id_follower = ? AND id_followed=?)";
		
	    try	( PreparedStatement pstmt = conn.prepareStatement(sql))
	    {
	        pstmt.setInt(1, id_follower);
	        pstmt.setInt(2, id_followed);      
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	        System.out.println("BadAss error executing insert. " + e.getMessage());
	    }
	}
	/**Método que modifica el atributo descripción de un usuario.
	 * @param description : la nueva descripcion que se quiere añadir.
	 * @param nombreUsuario : el username del usuario al que se desea modificar la descripción.*/	
	public void ModifyDescription (String description, String nombreUsuario)
	{
		String sql="UPDATE Usuarios SET description = '"+description+"'  WHERE username='"+nombreUsuario+"';";
		
	    try	( PreparedStatement pstmt = conn.prepareStatement(sql))
	    {   
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	        System.out.println("BadAss error executing insert. " + e.getMessage());
	    }
	}
	/** Método de eliminación de una foto.
	 * 
	 * @param cod : código de identificación de la foto que se desa eliminar.
	 */
	public void deleteFoto(int cod) 
	{
		String sql="DELETE FROM Fotos where cod = ? ;";
		
		try
	    (PreparedStatement pstmt = conn.prepareStatement(sql))
	    {
			pstmt.setInt(1, cod);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}	    
	}
	/**Método de creación de un comentario.
	 * 	
	 * @param comentario : comentario que se quiere insertar o crear.
	 */
	public void createComent(Comentario comentario)
	{
		final String sql = "INSERT INTO Comentarios(cod, id_user, comentario, fec) VALUES (?,?,?,?)";
		this.connect();		
	    try	( PreparedStatement pstmt = conn.prepareStatement(sql))
	    {
	        pstmt.setInt(1, comentario.getCod_fot());
	        pstmt.setInt(2, comentario.getId_user()); 
	        pstmt.setString(3, comentario.getContenido()); 
	        pstmt.setString(4, comentario.getFec()); 
	        pstmt.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	        System.out.println("BadAss error executing insert. " + e.getMessage());
	    }
	}
	/** Método de selección de un comentarios de una foto.
	 * 
	 * @param cod : código único de la foto del cual se quieren seleccionar fotos.
	 * @return comentarios : ArrayList de comentarios de la foto seleccionada.
	 */
	public ArrayList<Comentario> SelectComentarios(int cod)
	{
		
		this.connect();
		final String sql="SELECT * FROM Comentarios WHERE cod = ?";
		ArrayList<Comentario>comentarios=new ArrayList<Comentario>();
		try {
			PreparedStatement pt =conn.prepareStatement(sql);
			pt.setInt(1, cod);
			ResultSet result = pt.executeQuery();
			
			
			while(result.next()) {
				Comentario newComent = new Comentario();
				newComent.setCod_fot(result.getInt("cod"));
				newComent.setId_user(result.getInt("id_user"));
				newComent.setContenido(result.getString("comentario"));
				newComent.setFec(result.getString("fec"));
				comentarios.add(newComent);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return comentarios;	
	}
	/**Método de eliminació de comentario.
	 * 
	 * @param coment : comentario que se desea eliminar.
	 */
	public void DeleteComentario(Comentario coment) {
		final String sql = "DELETE FROM Comentarios WHERE cod = ? AND id_user = ? AND comentario = ? AND fec= ?";
		try {
			PreparedStatement stm =conn.prepareStatement(sql);
			stm.setInt(1, coment.getCod_fot());
			stm.setInt(2, coment.getId_user());
			stm.setString(3, coment.getContenido());
			stm.setString(4, coment.getFec());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/** Método de elimacion de un UsuarioNormal.
	 * 
	 * @param user: el usuario normal que se desea eliminar.
	 */
	public void DeleteUser(UsuarioNormal user) {
		final String sql = "DELETE FROM Usuarios WHERE id = ? ";
		try {
			PreparedStatement stm =conn.prepareStatement(sql);
			stm.setInt(1,user.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	public void DeleteLike (int cod, int id) {
		final String sql ="DELETE FROM LIKES WHERE cod = ? && id = ?";
		try {
			PreparedStatement sr = conn.prepareStatement(sql);
			sr.setInt(1, cod);
			sr.setInt(2, id);
			sr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void CreateLike (int cod, int id) {
		final String sql ="INSERT INTO Likes VALUES(?,?)";
		try {
			PreparedStatement sr = conn.prepareStatement(sql);
			sr.setInt(1, cod);
			sr.setInt(2, id);
			sr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		public ArrayList<Integer> SelectLike (int cod) {
			final String sql ="SELECT * FROM Likes WHERE cod = ?";
			ArrayList<Integer>  likes = new ArrayList<>();
			try {
				PreparedStatement sr = conn.prepareStatement(sql);
				sr.setInt(1, cod);
				ResultSet r=  sr.executeQuery();
				while (r.next()) {
					likes.add(r.getInt("id"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return likes;
	}
	/**Método get de laa connecxión establecida con la BD.
	 * 
	 * @return conn: la conexión establecida con la BD.
	 */
	public Connection getConnection()
	{
		return conn;
	}
}
