package SQLite;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import foto.Foto;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
/**Esta clase contiene los distintos métodos para la cración y gestion de la base de datos que utilizaremos en nuestra aplicación BeerMeet.
 * Entre esos métodos se encuentran el método de creacion de la BD, la inserción de contenido en la DB y la extracción de datos de la DB.
*@author aritz eraun y Paul Hernandez*/
public class BDManager 
{
	private String name;
	private String url;
	private Connection conn;
	
	/**Este método crea una base de datos en el caso de que no este previamente creado.*/
	
	public BDManager()
	{
		this.name = "database.db";
		this.url = "jdbc:sqlite:" + this.name;
	}
	
	/**Este método conecta la BD con la aplicación de BeerMeet.*/
	
	private void connect()
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
	
	private void disconnect()
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
	
	/**Este método guarda los datos de los distintos usuarios en la DB.*/
	
	public void saveUser(Usuario user)
	{
		this.insertUser(user);
		if(user instanceof UsuarioNormal)
		{
			((UsuarioNormal) user).setId(this.seleccionarIdUsuario(user));
		}
	}
	
	/**Este método recibe información de la DB y crea un ArrayList para el uso en la aplicación.*/
	
	public ArrayList<Usuario> loadUsers()
	{
		ArrayList<Usuario> usuarios = this.selectAllUsers();
		return usuarios;
	}
	
	/**Este método selecciona las fotos guardas en la DB dependiendo del usuario y sus relaciones con otros usuarios.*/
	
	public ArrayList<Foto> loadInicioPhotos(int id_user)
	{
		final String sql = "SELECT * FROM Fotos A "
			 	+ "JOIN (SELECT id_followed FROM Usuarios A JOIN User_User B ON ? = B.id_follower) B"
			 	+ "ON A.id_user = B.id_followed";
		
		ArrayList<Foto> fotos = this.selectPhotos(id_user, sql);
		return fotos;
	}
	
	/**Este método seleciona fotos desde la BD pedemdiendo del usuario.*/
	
	public ArrayList<Foto> loadUsersPhotos(int id_user)
	{
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		ArrayList<Foto> fotos = this.selectPhotos(id_user, sql);
		return fotos;
	}
	
	/**Este método guarda las fotos en la BD.*/
	
	public void savePhoto(Foto foto)
	{
		this.insertPhoto(foto);
		this.selectCodPhoto(foto);
	}
	
	/**Este método guarda en la BD los usuarios que se ahn reguistrado en nuestra aplicaión.*/
	
	private void insertUser(Usuario user)
	{
		
		String sql = "INSERT INTO Usuarios (type, username, password, name, surnames, email, fec_nac, age) VALUES(?,?,?,?,?,?,?,?)";

		this.connect();
		
        try
        		(
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
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
            }
            else
            {
            	pstmt.setInt(1, 1);
            	pstmt.setNull(7, Types.LONGNVARCHAR);
            	pstmt.setNull(8, Types.INTEGER);
            }
            
            pstmt.executeUpdate();
            
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing insert. " + e.getMessage());
        }
	}
	
	/**Este método seleciona usuarios BD pedemdiendo de su apellido.*/
	
	private int seleccionarIdUsuario(Usuario user)
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
        
        this.disconnect();
        
        return id;
	}
	
	/**Este método seleciona todos los usuarios de la BD.*/
	
	private ArrayList<Usuario> selectAllUsers()
	{
		final String sql = "SELECT * FROM Usuarios";

		ArrayList<Usuario> users = new ArrayList<>();
		
		this.connect();
		
        try
                (
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
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
                	
                	UsuarioNormal usuarioNormal = new UsuarioNormal(id, username, password, name, apellidos, email, fec_nac, edad);
                	users.add(usuarioNormal);
            	}
            	else
            	{
            		Administrador administrador = new Administrador(username, password, name, apellidos, email);
            		users.add(administrador);
            	}
            }
            
            this.disconnect();
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return users;
	}
	
	/**Este método seleciona fotos desde la BD.*/
	
	private ArrayList<Foto> selectPhotos(int id, String sql)
	{

		ArrayList<Foto> fotos = new ArrayList<>();
		
		this.connect();
		
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
            System.out.println(e.getMessage());
        }
        
        this.disconnect();
        
        return fotos;
	}
	
	/**Este método inserta objetos de tipo foto en la BD pedemdiendo del tipo de usuario.*/
	
	private void insertPhoto(Foto foto)
	{
		final String sql = "INSERT INTO Fotos(id_user, path, fec) VALUES (?,?,?)";

		this.connect();
		
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
	
	/**Este método seleciona todos los URls de las fotos guardas en la BD.*/
	
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
        
        this.disconnect();
        
        return cod;
	}
}
