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


public class PanelPerfil extends JPanel {
	
private VentanaPrincipal ventanaPrincipal;
	
	private BDManager bdManager;

	private JLabel nombreReal;
	
	private JPanel panelNorth;
	private JPanel panelWest;
	private JPanel panelCenter;
	private JPanel panelEast;
	private JPanel panelSouth;
	
	private JLabel foto;
	
	private ArrayList<Foto> fotos_inicio;
	private ArrayList<Foto> fotos_perfil;
	private ArrayList<Foto> fotos_usuarios;
	private JLabel lblLoco;

	/**
	 * Create the panel.
	 */
	public PanelPerfil(VentanaPrincipal ventana) {
		java.awt.BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
        
		bdManager = new BDManager();
		
		ventanaPrincipal = ventana;
		
		fotos_inicio = bdManager.loadInicioPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		fotos_perfil = bdManager.loadUsersPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		
		panelNorth = new JPanel();
		panelNorth.setBackground(Color.WHITE);
		this.add(panelNorth, BorderLayout.NORTH);
		
		panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 102, 102));
		this.add(panelWest, BorderLayout.WEST);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		this.add(panelCenter, BorderLayout.CENTER);
		
		lblLoco = new JLabel("loco");
		panelCenter.add(lblLoco);
		
		panelEast = new JPanel();
		panelEast.setBackground(new Color(255, 102, 102));
		this.add(panelEast, BorderLayout.EAST);
		
		panelSouth = new JPanel();
		panelSouth.setBackground(Color.WHITE);
		this.add(panelSouth, BorderLayout.SOUTH);
		
		nombreReal = new JLabel();
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
		
		cargarDatos();
		
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
				
			}
		});
		
		btnPerfil.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
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


	public void cargarDatos() 
	{
		nombreReal.setText("Bienvenido: "+ventanaPrincipal.getUsuario().getNombreReal());
	}

	}


