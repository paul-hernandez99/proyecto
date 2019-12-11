package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SQLite.BDManager;
import foto.Foto;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.VentanaPrincipal;

/**Estamos ante la clase que tiene como función la creación y definición de un panel para los usuarios
 * de tipo usuario comun que accedan a nuestra app BeerMeet.
*@author aritz eraun y Paul Hernandez*/

public class PanelUser extends JPanel
{
	private VentanaPrincipal ventanaPrincipal;
	private BDManager bdManager;
	private UsuarioNormal usuario;
	
	private JPanel panelNorth;
	private JPanel panelWest;
	private JPanel panelEast;
	private JPanel panelSouth;
	
	private PanelInicio panelInicio;
	private PanelPerfil panelPerfil;
	private PanelUsuarios panelUsuarios;
	
	private ArrayList<Foto> fotos_inicio;
	private ArrayList<Foto> fotos_perfil;
	private ArrayList<Foto> fotos_usuarios;
	
	/**Creación del Panel user*/
	
	public PanelUser(VentanaPrincipal ventana) 
	{
		BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
		
		ventanaPrincipal = ventana;
		bdManager = ventanaPrincipal.getBdManager();
		usuario = (UsuarioNormal)ventanaPrincipal.getUsuario();
		
		fotos_inicio = bdManager.loadInicioPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		fotos_perfil = bdManager.loadUsersPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		
		panelPerfil = new PanelPerfil(this);
		panelUsuarios = new PanelUsuarios(this);
		
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
		
		JLabel nombreReal = new JLabel(""+usuario.getNombreReal());
		nombreReal.setForeground(new Color(153, 240, 153));
		nombreReal.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelNorth.add(nombreReal);
		
		JLabel lblqueDeseaHacer = new JLabel("\u00BFQue desea hacer?");
		lblqueDeseaHacer.setForeground(new Color(0, 0, 0));
		lblqueDeseaHacer.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblqueDeseaHacer.setBounds(82, 50, 137, 20);
		panelNorth.add(lblqueDeseaHacer);
		
		JButton btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.setForeground(Color.WHITE);
		btnSubirFoto.setBackground(new Color(255, 102, 102));
		btnSubirFoto.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelSouth.add(btnSubirFoto);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBackground(new Color(255, 102, 102));
		btnUsuarios.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelSouth.add(btnUsuarios);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBackground(new Color(255, 102, 102));
		btnPerfil.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelSouth.add(btnPerfil);
		
		JButton btnSalir = new JButton("Cerrar Sesion");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(153, 240, 153));
		btnSalir.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panelSouth.add(btnSalir);
		
		btnSubirFoto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
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
		
		btnUsuarios.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				goToPanelUsuarios();
				
			}
		});
		
		btnPerfil.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				goToPanelPerfil();
			}
		});
		
		btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ventanaPrincipal.setContentPane(ventanaPrincipal.getPanel_principal());
				ventanaPrincipal.setTexts();
				ventanaPrincipal.revalidate();
			}
		});
	}
	
	/**Este método actualiza las fotos de la bandeja de la entrada del panel dependiendo de la fecha de publicación de cad foto*/
	
	private void goToPanelPerfil()
	{
		panelInicio.setVisible(false);
		panelUsuarios.setVisible(false);
		panelPerfil.setVisible(true);
		panelPerfil.setBackground(Color.WHITE);
		this.add(panelPerfil, BorderLayout.CENTER);
		panelPerfil.setVisible(true);
	}
	
	private void goToPanelUsuarios()
	{
		panelInicio.setVisible(false);
		panelPerfil.setVisible(false);
		panelUsuarios.setVisible(true);
		panelUsuarios.setBackground(Color.WHITE);
		this.add(panelUsuarios, BorderLayout.CENTER);
	}
	
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
	
}
