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

public class BDManager 
{
	private String name;
	private String url;
	private Connection conn;
	
	public BDManager(boolean test)
	{
		if(test)
		{
			this.name = "test.db";
		}
		else
			this.name = "database.db";
		this.url = "jdbc:sqlite:" + this.name;
	}
	
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
	
	public void saveUser(Usuario user)
	{
		this.insertUser(user);
		if(user instanceof UsuarioNormal)
		{
			((UsuarioNormal) user).setId(this.seleccionarIdUsuario(user));
		}
	}
	
	public ArrayList<Usuario> loadUsers()
	{
		ArrayList<Usuario> usuarios = this.selectAllUsers();
		return usuarios;
	}
	
	public ArrayList<Foto> loadInicioPhotos(int id_user)
	{
		final String sql = "SELECT * FROM Fotos A "
			 	+ "JOIN (SELECT id_followed FROM Usuarios A JOIN User_User B ON ? = B.id_follower) B"
			 	+ "ON A.id_user = B.id_followed";
		
		ArrayList<Foto> fotos = this.selectPhotos(id_user, sql);
		return fotos;
	}
	
	public ArrayList<Foto> loadUsersPhotos(int id_user)
	{
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		ArrayList<Foto> fotos = this.selectPhotos(id_user, sql);
		return fotos;
	}
	
	public void savePhoto(Foto foto)
	{
		this.insertPhoto(foto);
		this.selectCodPhoto(foto);
	}
	
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
