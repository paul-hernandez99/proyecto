package SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import foto.Foto;
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
	
	public void saveUser(Usuario user)
	{
		this.insertUser(user);
	}
	
	public void savePhoto(Foto foto)
	{
		this.insertPhoto(foto);
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
	
}
