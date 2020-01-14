package SQLite;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.ibatis.common.jdbc.ScriptRunner;

import foto.Foto;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;

/**
 * Constructor de la clase BDManagerTest. Los test estan ordenados alfabeticamente para poder controlar su orden de ejecucion. 
 * Asi mismo, iremos ejecutando los test prioritarios para poder testear luego los demas.
 * @author Aritz eta Paul
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BDManagerTest 
{
	private static BDManager tester;
	
	private static Usuario usuario1;
	private static Usuario usuario2;
	
	private static Usuario administrador;
	
	private static Foto foto1;
	private static Foto foto2;
	
	/**
	 * Se trata del setUp pero con @BeforeClass. Es decir, solo se ejecuta una vez antes de empezar con los testeos a diferencia del @Before que lo ejecuta antes de cada test.
	 * Crea distintas istancias de los objetos de nuestra aplicacion para posteriormente testearlas. Ademas, hemos añadido un script sql que inserta ciertos a la bd
	 * test.db para la correcta ejecucion de los tests.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception
	{
		tester = new BDManager(true);
		
		tester.connect();
		
		usuario1 = new UsuarioNormal("manuel", "manuel1", "Manuel", "Garcia", "manuel@opendeusto.es", "12-12-1999",null);
		usuario2 = new UsuarioNormal("ander", "aderr", "Ander", "Etxekalte", "ander@opendeusto.es", "30-12-1999",null);
		
		administrador = new Administrador("josu", "josuel", "Josu", "Armendariz", "josu@opendeusto.es");
		
		foto1 = new Foto(3,"Imagenes/data/manuel_1.jpg", "16:00 02/01/2019");
		foto2 = new Foto(5,"Imagenes/data/ander_1.jpg", "18:00 02/01/2019");
		
		ScriptRunner sr = new ScriptRunner(tester.getConnection(), false, true);
		Reader reader = new BufferedReader(new FileReader(new File("SetUp.sql")));
	    sr.runScript(reader);
	    
	}
	
	/**
	 * Se trata del tearDown pero con @AfterClass. Es decir, solo se ejecuta una vez despues de todos los testeos a diferencia del @After que lo ejecuta despues de cada test.
	 * Ejecuta el script sql que hemos añadido para la finalizacion de los test y deconecta la bd. Lo que hace el script es borrar todo el contenido de test.db
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDown() throws Exception
	{
		ScriptRunner sr = new ScriptRunner(tester.getConnection(), false, true);
		Reader reader = new BufferedReader(new FileReader(new File("DeleteScript.sql")));
	    sr.runScript(reader);
	    
	    tester.disconnect();
	}
	/**
	 * Testeo del metodo selectUsuario de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testA_SelectUsuario() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Usuario> users;
		UsuarioNormal user;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectUsuario", String.class);
		method.setAccessible(true);
		
		users = (ArrayList<Usuario>) method.invoke(tester, "SELECT * FROM Usuarios");
		
		
		if(users.get(0) instanceof Administrador)
			fail();
		
		user = (UsuarioNormal)users.get(0);
		
		assertEquals(1,user.getId());
		assertEquals("paul",user.getNombreUsuario());
		assertEquals("lqaz1875",user.getContraseña());
		assertEquals("Paul",user.getNombreReal());
		assertEquals("Hernandez Guridi",user.getApellidos());
		assertEquals("paul.hernandez@opendeusto.es",user.getEmail());
		assertEquals("30-01-1999",user.getFechaNacimiento());
		assertEquals(20,user.getEdad());
	}
	/**
	 * Testeo del metodo selectAllUsers de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testB_SelectAllUsers() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Usuario> users;
		UsuarioNormal user;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectAllUsers");
		method.setAccessible(true);
		
		users = (ArrayList<Usuario>) method.invoke(tester);
		
		if(users.get(1) instanceof Administrador)
			fail();
		
		user = (UsuarioNormal)users.get(1);
		
		assertEquals(2,user.getId());
		assertEquals("andoni",user.getNombreUsuario());
		assertEquals("ando",user.getContraseña());
		assertEquals("Andoni",user.getNombreReal());
		assertEquals("Azpiazu",user.getApellidos());
		assertEquals("andoni@opendeusto.es",user.getEmail());
		assertEquals("30-01-1998",user.getFechaNacimiento());
		assertEquals(19,user.getEdad());
	}
	/**
	 * Testeo del metodo loadUsers de la clase BDManager.
	 */
	@Test
	public void testC_LoadUsers() 
	{
		ArrayList<Usuario> users;
		UsuarioNormal user;
		
		users = tester.loadUsers();
		
		if(users.get(1) instanceof Administrador)
			fail();
		
		user = (UsuarioNormal)users.get(0);
		
		assertEquals(1,user.getId());
		assertEquals("paul",user.getNombreUsuario());
		assertEquals("lqaz1875",user.getContraseña());
		assertEquals("Paul",user.getNombreReal());
		assertEquals("Hernandez Guridi",user.getApellidos());
		assertEquals("paul.hernandez@opendeusto.es",user.getEmail());
		assertEquals("30-01-1999",user.getFechaNacimiento());
		assertEquals(20,user.getEdad());
		
	}
	/**
	 * Testeo del metodo insertUser de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testD_InsertUser() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		ArrayList<Usuario>users;
		UsuarioNormal user;
		Usuario admin;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("insertUser", Usuario.class);
		method.setAccessible(true);
		method.invoke(tester, usuario1);
		method.invoke(tester, administrador);
		
		users = tester.loadUsers();
		user = (UsuarioNormal)users.get(2);
		admin = users.get(3);
		
		if(users.get(2) instanceof Administrador)
			fail();
		
		assertEquals(3,user.getId());
		assertEquals("manuel",user.getNombreUsuario());
		assertEquals("manuel1",user.getContraseña());
		assertEquals("Manuel",user.getNombreReal());
		assertEquals("Garcia",user.getApellidos());
		assertEquals("manuel@opendeusto.es",user.getEmail());
		assertEquals("12-12-1999",user.getFechaNacimiento());
		assertEquals(20,user.getEdad());
		
		if(admin instanceof UsuarioNormal)
			fail();
		
		assertEquals(4,((Administrador)admin).getId());
		assertEquals("josu",admin.getNombreUsuario());
		assertEquals("josuel",admin.getContraseña());
		assertEquals("Josu",admin.getNombreReal());
		assertEquals("Armendariz",admin.getApellidos());
		assertEquals("josu@opendeusto.es",admin.getEmail());
		
	}
	/**
	 * Testeo del metodo seleccionarIdUsuario de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testE_SeleccionarIdUsuario() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		int id_user;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("seleccionarIdUsuario", Usuario.class);
		method.setAccessible(true);
		id_user = (int)method.invoke(tester, this.usuario1);
		
		assertEquals(3,id_user);
	}
	/**
	 * Testeo del metodo saveUser de la clase BDManager.
	 */
	@Test
	public void testF_SaveUser() 
	{
		tester.saveUser(usuario2);
		assertEquals(5,((UsuarioNormal)usuario2).getId());
	}
	/**
	 * Testeo del metodo selectPhotos de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testG_SelectPhotos() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Foto> fotos;
		Foto foto;
		int id_user = 1;
		
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectPhotos", int.class, String.class);
		method.setAccessible(true);
		
		fotos = (ArrayList<Foto>) method.invoke(tester, id_user, sql);
		
		foto = fotos.get(0);
		
		assertEquals(1,foto.getCod());
		assertEquals(1,foto.getId_user());
		assertEquals("Imagenes/data/paul_1.jpg",foto.getPath());
		assertEquals("14:00 02/01/2019",foto.getFec());

	}
	/**
	 * Testeo del metodo insertPhoto de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testH_InsertPhoto() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Foto> fotos;
		Foto foto;
		int id_user = 3;
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("insertPhoto", Foto.class);
		method.setAccessible(true);
		
		method.invoke(tester, this.foto1);
		
		Method method1 = Class.forName("SQLite.BDManager").getDeclaredMethod("selectPhotos", int.class, String.class);
		method1.setAccessible(true);
		
		fotos = (ArrayList<Foto>) method1.invoke(tester, id_user, sql);
		foto = fotos.get(0);
		
		assertEquals(2,foto.getCod());
		assertEquals(3,foto.getId_user());
		assertEquals("Imagenes/data/manuel_1.jpg",foto.getPath());
		assertEquals("16:00 02/01/2019", foto.getFec());
	}
	/**
	 * Testeo del metodo selectCodPhoto de la clase BDManager. Como el metodo es privado hemos usado reflexion para acceder a el desde el test.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testI_SelectCodPhoto() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		int cod;

		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectCodPhoto", Foto.class);
		method.setAccessible(true);
		
		cod = (int) method.invoke(tester, this.foto1);
		
		assertEquals(2,cod);
	}
	/**
	 * Testeo del metodo relationships de la clase BDManager.
	 */
	@Test
	public void testJ_Relationships()
	{
		ArrayList<Usuario> seguidos = tester.relationships(2);
		
		assertEquals(seguidos.get(0).getNombreUsuario(),"paul");
	}
	/**
	 * Testeo del metodo createRelationships de la clase BDManager.
	 */
	@Test
	public void testK_CreateRelationship()
	{
		tester.createRelationship(1,3);
		
		ArrayList<Usuario> seguidos = tester.relationships(1);
		
		assertEquals(seguidos.get(0).getNombreUsuario(), "manuel");
	}
	/**
	 * Testeo del metodo loadInicioPhotos de la clase BDManager.
	 */
	@Test
	public void testL_LoadInicioPhotos() 
	{
		ArrayList<Foto> fotos;
		
		fotos = tester.loadInicioPhotos(1);
		
		Foto foto1 = fotos.get(0);
		
		assertEquals(2,foto1.getCod());
		assertEquals(3,foto1.getId_user());
		assertEquals("Imagenes/data/manuel_1.jpg",foto1.getPath());
		assertEquals("16:00 02/01/2019",foto1.getFec());
	}
	/**
	 * Testeo del metodo loadUsersPhotos de la clase BDManager.
	 */
	@Test
	public void testM_LoadUsersPhotos() 
	{
		ArrayList<Foto> fotos;
		
		fotos = tester.loadUsersPhotos(1);
		
		Foto foto1 = fotos.get(0);
		
		assertEquals(1,foto1.getCod());
		assertEquals(1,foto1.getId_user());
		assertEquals("Imagenes/data/paul_1.jpg",foto1.getPath());
		assertEquals("14:00 02/01/2019",foto1.getFec());	
	}
	/**
	 * Testeo del metodo savePhoto de la clase BDManager.
	 */
	@Test
	public void testN_SavePhoto() 
	{
		tester.savePhoto(foto2);
		assertEquals(3,foto2.getCod());
		assertEquals(5,foto2.getId_user());
		assertEquals("Imagenes/data/ander_1.jpg",foto2.getPath());
		assertEquals("18:00 02/01/2019",foto2.getFec());
	}

}
