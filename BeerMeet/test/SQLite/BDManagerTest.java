package SQLite;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import usuarios.UsuarioNormal;

public class BDManagerTest 
{
	private BDManager tester;
	private UsuarioNormal usuario;
	
	@Before
	public void setUp() throws Exception
	{
		tester = new BDManager(true);
		
		usuario = new UsuarioNormal("manuel", "manuel1", "Manuel", "Garcia", "manuel@opendeusto.es", "12-12-1999");
	}
	
	@After
	public void tearDown() throws Exception
	{
		
	}

	@Test
	public void testSaveUser() 
	{
		fail("Not yet implemented");
		
	}

	@Test
	public void testLoadUsers() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testLoadInicioPhotos() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testLoadUsersPhotos() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSavePhoto() 
	{
		fail("Not yet implemented");
	}

}
