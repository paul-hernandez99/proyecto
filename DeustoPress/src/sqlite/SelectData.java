package sqlite;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import usuarios.Usuario;
import usuarios.UsuarioNormal;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectData
{
    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect()
    {
        // SQLite connection string
        String name = "database.db";
        String url = "jdbc:sqlite:" + name;
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    public ArrayList<Usuario> selectUsers()
    {
    	String sql = "Select * from Usuarios";
    	
    	ArrayList<Usuario> users = new ArrayList<>();
    			
        try
                (
                        Connection conn = this.connect();
                        Statement stmt  = conn.createStatement();
                        ResultSet rs    = stmt.executeQuery(sql)
                )
        {

            // loop through the result set
            while (rs.next())
            {
                
            	int cod = rs.getInt("cod_user");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String nom_user = rs.getString("nom_user");
                String fecha = rs.getString("fec_user");
                
                users.add(new UsuarioNormal(nombreUsuario, contraseña, nombreReal, fechaDeAlta))
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SelectData app = new SelectData();
        app.selectAll();
    }
}

