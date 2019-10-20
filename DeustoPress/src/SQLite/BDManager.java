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
	
	public BDManager()
	{
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
	
	public void savePhoto(Foto foto)
	{
		this.insertPhoto(foto);
	}
	
	public ArrayList<Usuario> loadUsers()
	{
		ArrayList<Usuario> usuarios = this.selectAllUsers();
		return usuarios;
	}
	
	private void insertUser(Usuario user)
	{
		
		String sql = "INSERT INTO Usuarios(type, username, password, name, email, fec_nac, edad) VALUES(?,?,?,?,?,?,?)";

		this.connect();
		
        try
        		(
                        PreparedStatement pstmt = conn.prepareStatement(sql)
                )
        {
            pstmt.setString(2, user.getNombreUsuario());
            pstmt.setString(3, user.getContraseña());
            pstmt.setString(4, user.getNombreReal());
            pstmt.setString(5, user.getEmail());
            
            if(user instanceof UsuarioNormal)
            {
            	pstmt.setString(6, ((UsuarioNormal) user).getFechaNacimiento());
            	pstmt.setInt(7, ((UsuarioNormal) user).getEdad());
            	pstmt.setInt(1, 0);
            }
            else
            {
            	pstmt.setNull(6, Types.LONGNVARCHAR);
            	pstmt.setNull(7, Types.INTEGER);
            	pstmt.setInt(1, 1);
            }
            
            pstmt.executeUpdate();
            
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing insert. " + e.getMessage());
        }
	}
	
	private void insertPhoto(Foto foto)
	{
		
		
	}
	
	private int seleccionarIdUsuario(Usuario user)
	{
		final String sql = "SELECT id FROM Usuarios WHERE username = "+user.getNombreUsuario();
		
		this.connect();
		
		int id = -1;
		
        try
        		(
        				Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {
           id = rs.getInt("id");
        }
        catch (SQLException e)
        {
            System.out.println("BadAss error executing insert. " + e.getMessage());
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
            	String email = rs.getString("email");
            	
            	if(type == 0)
            	{
            		String fec_nac = rs.getString("fec_nac");
                	int edad = rs.getInt("edad");
                	
                	UsuarioNormal usuarioNormal = new UsuarioNormal(id, username, password, name, email, fec_nac, edad);
                	users.add(usuarioNormal);
            	}
            	else
            	{
            		Administrador administrador = new Administrador(username, password, name, email);
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
	
}
