package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SQLite.BDManager;
import foto.Foto;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.VentanaPrincipal;

/**Estamos ante la clase que tiene como funci�n la creaci�n y definici�n de un panel para los usuarios
 * de tipo usuario comun que accedan a nuestra app BeerMeet.
*@author aritz eraun y Paul Hernandez*/

public class PanelUser extends JPanel
{
	private VentanaPrincipal ventanaPrincipal;
	private BDManager bdManager;
	private UsuarioNormal usuario;
	private Administrador admin;
	
	private JPanel panelNorth;
	private JPanel panelWest;
	private JPanel panelEast;
	private JPanel panelSouth;
	
	private PanelInicio panelInicio;
	private PanelPerfil panelPerfil;
	private PanelBusquedaUsuarios panelUsuarios;
	private PanelPerfil panelUserProfile;
	private PanelCrearAdmin panelCrearAdmin;
	
	private ArrayList<Foto> fotos_inicio = new ArrayList<Foto>();
	private ArrayList<Foto> fotos_perfil;
	private ArrayList<Usuario> seguidos;
	private JLabel btnPaginaPrincipal = new JLabel();
	private JLabel btnUsuarios = new JLabel();
	private JLabel btnPerfil = new JLabel();
	private JLabel btnSubirFoto = new JLabel();
	private JLabel btnSalir = new JLabel();
	private JLabel btnCrearPerfil = new JLabel();
	
	/**Constructor de la clase PanelUser
	 * 
	 * @param ventana Ventana principal en la que visualizara este panel
	 * @param tipo tipo de Usuario. Si el 0 sera normal y si es 1 Administrador
	 */
	public PanelUser(VentanaPrincipal ventana, int tipo) 
	{
		BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
		
		ventanaPrincipal = ventana;
		bdManager = ventanaPrincipal.getBdManager();
		if(tipo ==0) {
			usuario = (UsuarioNormal)ventanaPrincipal.getUsuario();
			
			fotos_inicio = Utilidades.MergeSort(bdManager.loadInicioPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId()));
			fotos_perfil = Utilidades.MergeSort(bdManager.loadUsersPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId()));
			seguidos = bdManager.relationships(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		
			panelPerfil = new PanelPerfil(this, null);
			
		}else if (tipo ==1) {
			admin =(Administrador) ventanaPrincipal.getUsuario();
			ArrayList <Usuario> Usuario =ventanaPrincipal.getUsuarios();
			ArrayList <Usuario> UsuarioRefinado =new ArrayList<>();
			ArrayList <Foto> ayudante=new ArrayList<>();
			for(byte i=0;i<Usuario.size();i++) {
				if(Usuario.get(i)instanceof UsuarioNormal) {
					UsuarioRefinado.add(Usuario.get(i));
				
				}
			}
			for (byte n=0; n<UsuarioRefinado.size();n++) {
				ayudante= bdManager.loadUsersPhotos(((UsuarioNormal)UsuarioRefinado.get(n)).getId());
				
				for(byte g=0; g<ayudante.size();g++) {
					fotos_inicio.add(ayudante.get(g));
				}
			}
					
		}
		panelUsuarios = new PanelBusquedaUsuarios(this);
		if(tipo ==1) {
			panelCrearAdmin= new PanelCrearAdmin(this);
		}
		
		
		panelNorth = new JPanel();
		panelNorth.setBackground(Color.WHITE);
		this.add(panelNorth, BorderLayout.NORTH);
		
		panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 102, 102));
		this.add(panelWest, BorderLayout.WEST);
		
		panelEast = new JPanel();
		panelEast.setBackground(new Color(255, 102, 102));
		this.add(panelEast, BorderLayout.EAST);
		
		panelSouth = new JPanel();
		panelSouth.setBackground(Color.WHITE);
		this.add(panelSouth, BorderLayout.SOUTH);
		
		panelInicio = new PanelInicio(this);
		panelInicio.setBackground(Color.WHITE);
		this.add(panelInicio, BorderLayout.CENTER);
	
		
		String contenido = null;
		if(tipo == 0) {
			contenido =""+usuario.getNombreReal();
		}else {
			contenido = ""+admin.getNombreReal();
		}
		JLabel nombreReal = new JLabel(""+contenido);
		nombreReal.setForeground(new Color(153, 240, 153));
		nombreReal.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelNorth.add(nombreReal);
		
		JLabel lblqueDeseaHacer = new JLabel("\u00BFQue desea hacer?");
		lblqueDeseaHacer.setForeground(new Color(0, 0, 0));
		lblqueDeseaHacer.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblqueDeseaHacer.setBounds(82, 50, 137, 20);
		panelNorth.add(lblqueDeseaHacer);
		
	
		btnPaginaPrincipal.setBounds(0, 0, 35,40);
		btnPaginaPrincipal.setIcon(escalar("Imagenes\\System\\PaginaPrincipal.png",btnPaginaPrincipal));
	
		btnPaginaPrincipal.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelSouth.add(btnPaginaPrincipal);
		
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBounds(0, 0, 45,60);
		btnUsuarios.setIcon(escalar("Imagenes\\System\\lupaBlanca.jpg", btnUsuarios));
		panelSouth.add(btnUsuarios);
		
		if(tipo ==0) {
			btnSubirFoto.setBounds(0,0,60,50);
			btnSubirFoto.setIcon(escalar("Imagenes\\System\\SubirFotos.jpg", btnSubirFoto));
			panelSouth.add(btnSubirFoto);
			
			btnSubirFoto.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int id_user = ((UsuarioNormal)ventanaPrincipal.getUsuario()).getId();
					String path = uploadPhotoAndGetPath();
					String fec = Utilidades.fechaDeAlta();
					
					if(path != null)
					{
						Foto foto = new Foto(id_user, path, fec);
						
						bdManager.savePhoto(foto);
						
						fotos_perfil.add(foto);
					}
				}
			});
	
			btnPerfil.setBounds(0, 0, 45, 50);
			btnPerfil.setIcon(escalar("Imagenes\\System\\PerfilBlanco.jpg",btnPerfil));
			panelSouth.add(btnPerfil);
			
			btnPerfil.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					goToPanelPerfil();
				}
			});			
		}
		else {
			btnCrearPerfil.setBounds(0, 0, 45, 50);
			btnCrearPerfil.setIcon(escalar("Imagenes\\System\\descarga.png",btnCrearPerfil));
			panelSouth.add(btnCrearPerfil);
			
			btnCrearPerfil.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					goToPanelCrearAdmin();
				}
			});			
		}
		
		btnSalir.setBounds(0,0,50,50);
		btnSalir.setIcon(escalar("Imagenes\\System\\Salir.png", btnSalir));
		panelSouth.add(btnSalir);
		
		btnUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToPanelUsuarios();
				
			}
		});
		btnPaginaPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				goToPanelInicio();
			}
		});
		
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventanaPrincipal.setContentPane(ventanaPrincipal.getPanel_principal());
				ventanaPrincipal.setTexts();
				ventanaPrincipal.revalidate();
			}
		});
	}
	
	/**Este m�todo actualiza las fotos de la bandeja de la entrada del panel dependiendo de la fecha de publicaci�n de cad foto*/
	
	public void goToPanelPerfil()
	{
		goToComun();
		
		panelInicio.setVisible(false);
		panelUsuarios.setVisible(false);
		if(admin ==null) {
			panelPerfil.setVisible(true);
			panelPerfil.setBackground(Color.WHITE);
			this.add(panelPerfil, BorderLayout.CENTER);
			panelPerfil.setVisible(true);
		}else {
			panelCrearAdmin.setVisible(false);
			panelUserProfile.setVisible(true);
			panelUserProfile.setBackground(Color.WHITE);
			this.add(panelUserProfile, BorderLayout.CENTER);
			panelUserProfile.setVisible(true);
		}
	}
	
	/**Este metodo permite al usuario visualizar el panel inicio donde se ubican las fotos de sus seguidos*/
	
	public void goToPanelInicio()
	{
		goToComun();
		
		if(admin == null){
			panelPerfil.setVisible(false);
		}else {
			panelCrearAdmin.setVisible(false);
		}
		panelUsuarios.setVisible(false);
		panelInicio.setBackground(Color.WHITE);
		this.add(panelInicio, BorderLayout.CENTER);
		panelInicio.setVisible(true);
	}
	
	/**Este metodo permite al usuario visualizar el panelBusqueda de usuarios donde se ubica la lista de usuarios para poder acceder a ellos*/
	
	private void goToPanelUsuarios()
	{
		goToComun();
		
		if(admin == null) {
			panelPerfil.setVisible(false);
		}else {
			panelCrearAdmin.setVisible(false);	
		}
		panelInicio.setVisible(false);
		panelUsuarios.setVisible(true);
		panelUsuarios.setBackground(Color.WHITE);
		this.add(panelUsuarios, BorderLayout.CENTER);
	}
	
	/**Este metodo permite a los administradores visualizar el panel de creacion de administradores*/
	
	public void goToPanelCrearAdmin() 
	{
		goToComun();
		
		if(admin == null) {
			panelPerfil.setVisible(false);
		}
		panelInicio.setVisible(false);
		panelUsuarios.setVisible(false);
		panelCrearAdmin.setVisible(true);
		panelCrearAdmin.setBackground(Color.WHITE);
		this.add(panelCrearAdmin, BorderLayout.CENTER);
	}
	
	public void goToComun() 
	{
		if(panelUserProfile != null)
		{
			panelUserProfile.setVisible(false);
			if(panelUserProfile.getPanelVisualizar() != null) {
				panelUserProfile.getPanelVisualizar().setVisible(false);
				if(panelUserProfile.getPanelVisualizar().getPanelComentario() != null)
				{
					panelUserProfile.getPanelVisualizar().getPanelComentario().setVisible(false);	
				}
			}
		}
		btnPaginaPrincipal.setBackground(Color.WHITE);
		if (panelInicio.getPanelComentario() !=null){
			panelInicio.getPanelComentario().setVisible(false);
		}
		if(panelPerfil != null) {
			panelPerfil.setVisible(false);
			if(panelPerfil.getPanelVisualizar() != null) {
				panelPerfil.getPanelVisualizar().setVisible(false);
				if(panelPerfil.getPanelVisualizar().getPanelComentario() != null)
				{
					panelPerfil.getPanelVisualizar().getPanelComentario().setVisible(false);	
				}
			}
		}
	}
	
	/**Este metodo crea un FileDialog donde el usuario elegira la foto que quiera subir a la aplicacion, 
	 * y lo que hara es copiar esa foto desde la ubicacion propia del pc a una carpeta de fotos propia de la aplicacion
	 * @return el path de la foto copiada en la carpeta de fotos de la aplicacion
	 */
	private String uploadPhotoAndGetPath()
	{
		FileDialog dialog = new FileDialog(ventanaPrincipal,"Select Image to upload", FileDialog.LOAD);
		dialog.setVisible(true);
		
		String path = null;
		
		if(dialog.getFile() != null)
		{
			String type = dialog.getFile().substring(dialog.getFile().length() - 4);
			path = "Imagenes/data/"+ventanaPrincipal.getUsuario().getNombreUsuario()+"_"+(fotos_perfil.size()+1)+type;
			Path path_source = new File(dialog.getDirectory() + dialog.getFile()).toPath();
			Path path_target = new File(path).toPath();
			 
			try 
			{
				Files.copy(path_source, path_target, StandardCopyOption.REPLACE_EXISTING);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return path;
	}
	
	/**Este metodo permite escalar una foto para su correcta visualizacion en la aplicacion
	 * 
	 * @param path el path de la foto a escalar
	 * @param imagen el Jlabel donde se escala la foto
	 * @return retorna el Icon ya escalado
	 */
	public Icon escalar(String path,JLabel imagen)
	{
		ImageIcon imgIcon = new ImageIcon(path);
        Image imgEscalada = imgIcon.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_REPLICATE);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
		return iconoEscalado;
	}
	
	public void setPanelUserProfile(PanelPerfil panel)
	{
		panelUserProfile = panel;
	}
	public PanelPerfil getPanelUserProfile()
	{
		return panelUserProfile ;
	}

	public BDManager getBdManager() 
	{
		return bdManager;
	}

	public void setBdManager(BDManager bdManager) 
	{
		this.bdManager = bdManager;
	}

	public UsuarioNormal getUsuario() 
	{
		return usuario;
	}

	public void setUsuario(UsuarioNormal usuario) 
	{
		this.usuario = usuario;
	}
	public Administrador getAdminsitrador() 
	{
		return admin;
	}

	public void setAdministrador(Administrador admin) 
	{
		this.admin = admin;
	}
	
	public ArrayList<Foto> getFotos_inicio() 
	{
		return fotos_inicio;
	}

	public void setFotos_inicio(ArrayList<Foto> fotos_inicio) 
	{
		this.fotos_inicio = fotos_inicio;
	}

	public ArrayList<Foto> getFotos_perfil() 
	{
		return fotos_perfil;
	}

	public void setFotos_perfil(ArrayList<Foto> fotos_perfil) 
	{
		this.fotos_perfil = fotos_perfil;
	}

	public VentanaPrincipal getVentanaPrincipal() 
	{
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) 
	{
		this.ventanaPrincipal = ventanaPrincipal;
	}
	public ArrayList<Usuario> getSeguidos() 
	{
		return seguidos;
	}

	public void setSeguidos(ArrayList<Usuario> seguidos) 
	{
		this.seguidos = seguidos;
	}
	public PanelPerfil getPanelPerfil() 
	{
		return panelPerfil;
	}

	public void setPanelPerfil(PanelPerfil panel) 
	{
		this.panelPerfil = panel;
	}
	public PanelInicio getPanelInicio() 
	{
		return panelInicio;
	}
	public void setPanelInicio(PanelInicio panel) 
	{
		this.panelInicio = panel;
	}
	
}
