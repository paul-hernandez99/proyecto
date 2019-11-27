package SQLite;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import foto.Foto;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BDManagerTest 
{
	private BDManager tester;
	private Usuario usuario;
	private Foto foto;
	
	@Before
	public void setUp() throws Exception
	{
		tester = new BDManager(true);
		usuario = new UsuarioNormal("manuel", "manuel1", "Manuel", "Garcia", "manuel@opendeusto.es", "12-12-1999");
		foto = new Foto(1, "Imagenes/data/manuel_1.jpg", "14:00 02/01/2019");
	}

	@After
	public void tearDown() throws Exception
	{
		
	}

	@Test
	public void testA_SelectAllUsers() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Usuario> users;
		UsuarioNormal user;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectAllUsers");
		method.setAccessible(true);
		
		users = (ArrayList<Usuario>) method.invoke(tester);
		
		
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
	}
	
	@Test
	public void testB_LoadUsers() 
	{
		ArrayList<Usuario> users;
		UsuarioNormal user;
		
		users = tester.loadUsers();
		
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
		
	}
	
	@Test
	public void testC_InsertUser() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		ArrayList<Usuario>users;
		UsuarioNormal user;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("insertUser", Usuario.class);
		method.setAccessible(true);
		method.invoke(tester, usuario);
		
		users = tester.loadUsers();
		user = (UsuarioNormal)users.get(1);
		
		if(users.get(0) instanceof Administrador)
			fail();
		
		assertEquals(2,user.getId());
		assertEquals("manuel",user.getNombreUsuario());
		assertEquals("manuel1",user.getContraseña());
		assertEquals("Manuel",user.getNombreReal());
		assertEquals("Garcia",user.getApellidos());
		assertEquals("manuel@opendeusto.es",user.getEmail());
		assertEquals("12-12-1999",user.getFechaNacimiento());
	}

	@Test
	public void testD_SeleccionarIdUsuario() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		int id_user;
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("seleccionarIdUsuario", Usuario.class);
		method.setAccessible(true);
		id_user = (int)method.invoke(tester, this.usuario);
		
		assertEquals(2,(id_user));
	}

	@Test
	public void testE_SaveUser() 
	{
		tester.saveUser(usuario);
		assertEquals(3,((UsuarioNormal)usuario).getId());
	}
	
	@Test
	public void testF_SelectPhotos() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Foto> fotos;
		Foto foto;
		int id_user = 2;
		
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectPhotos", int.class, String.class);
		method.setAccessible(true);
		
		fotos = (ArrayList<Foto>) method.invoke(tester, id_user, sql);
		
		foto = fotos.get(0);
		
		assertEquals(2,foto.getCod());
		assertEquals(2,foto.getId_user());
		assertEquals("Imagenes/data/manuel_1.jpg",foto.getPath());
		assertEquals("13:30 01/02/2019",foto.getCod());

	}
	
	@Test
	public void testG_InsertPhoto() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<Foto> fotos;
		Foto foto;
		int id_user = 1;
		final String sql = "SELECT * FROM Fotos WHERE id_user = ?";
		
		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("insertPhoto", Foto.class);
		method.setAccessible(true);
		
		method.invoke(tester, this.foto);
		
		Method method1 = Class.forName("SQLite.BDManager").getDeclaredMethod("selectPhotos", int.class, String.class);
		method1.setAccessible(true);
		
		fotos = (ArrayList<Foto>) method1.invoke(tester, id_user, sql);
		foto = fotos.get(1);
		
		assertEquals(3,foto.getCod());
		assertEquals(1,foto.getId_user());
		assertEquals("Imagenes/data/paul_2.jpg",foto.getPath());
	}
	
	@Test
	public void testH_SelectCodPhoto() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		int cod;

		Method method = Class.forName("SQLite.BDManager").getDeclaredMethod("selectCodPhoto", Foto.class);
		method.setAccessible(true);
		
		cod = (int) method.invoke(tester, this.foto);
		
		assertEquals(3,cod);
	}

	@Test
	public void testI_LoadInicioPhotos() 
	{
		ArrayList<Foto> fotos;
		
		fotos = tester.loadInicioPhotos(2);
		
		Foto foto1 = fotos.get(0);
		Foto foto2 = fotos.get(1);
		
		assertEquals(1,foto1.getCod());
		assertEquals(1,foto1.getId_user());
		assertEquals("Imagenes/data/paul_2.jpg",foto1.getPath());
		assertEquals("14:30 01/02/2019",foto1.getFec());
		
		assertEquals(3,foto2.getCod());
		assertEquals(1,foto2.getId_user());
		assertEquals("Imagenes/data/paul_2.jpg",foto2.getPath());
	}

	@Test
	public void testJ_LoadUsersPhotos() 
	{
		ArrayList<Foto> fotos;
		
		fotos = tester.loadUsersPhotos(1);
		
		Foto foto1 = fotos.get(0);
		Foto foto2 = fotos.get(1);
		
		assertEquals(1,foto1.getCod());
		assertEquals(1,foto1.getId_user());
		assertEquals("Imagenes/data/paul_2.jpg",foto1.getPath());
		assertEquals("14:30 01/02/2019",foto1.getFec());
		
		assertEquals(3,foto2.getCod());
		assertEquals(1,foto2.getId_user());
		assertEquals("Imagenes/data/paul_2.jpg",foto2.getPath());
	}

	@Test
	public void testK_SavePhoto() 
	{
		tester.savePhoto(foto);
		assertEquals(4,foto.getCod());
	}

}
