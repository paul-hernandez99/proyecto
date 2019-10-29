package ventanas.panels;

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
import javax.swing.SwingConstants;
import SQLite.BDManager;
import foto.Foto;
import interfaces.IPanelUsuarios;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.VentanaPrincipal;

public class PanelUser extends JLabel implements IPanelUsuarios
{
	private VentanaPrincipal ventanaPrincipal;
	
	private BDManager bdManager;
	
	private JLabel username;
	private JLabel nombreReal;
	
	private ArrayList<Foto> fotos_inicio;
	private ArrayList<Foto> fotos_perfil;
	private ArrayList<Foto> fotos_usuarios;
	
	public PanelUser(VentanaPrincipal ventana) 
	{
		java.awt.BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
        
		bdManager = new BDManager();
		
		ventanaPrincipal = ventana;
		
		fotos_inicio = bdManager.loadInicioPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		fotos_perfil = bdManager.loadUsersPhotos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId());
		
		username = new JLabel();
		username.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		//username.setBounds(390, 68, 166, 20);
		this.add(username, java.awt.BorderLayout.NORTH);
		
		JButton btnSubirFoto = new JButton("Subir foto");
		btnSubirFoto.setForeground(new Color(255, 255, 255));
		btnSubirFoto.setBackground(new Color(102, 204, 255));
		btnSubirFoto.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		//btnSubirFoto.setBounds(160, 115, 187, 38);
		this.add(btnSubirFoto, java.awt.BorderLayout.WEST);
		
		JButton btnUsuarios = new JButton("Crear Entrada");
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setBackground(new Color(102, 204, 255));
		btnUsuarios.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		//btnUsuarios.setBounds(160, 185, 187, 38);
		this.add(btnUsuarios, java.awt.BorderLayout.WEST);
		
		JButton btnPerfil = new JButton("Visualizar Entradas");
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBackground(new Color(102, 204, 255));
		btnPerfil.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		//btnPerfil.setBounds(160, 255, 187, 38);
		this.add(btnPerfil, java.awt.BorderLayout.WEST);
		
		JButton btnSalir = new JButton("Cerrar Sesion");
		btnSalir.setForeground(new Color(102, 204, 255));
		btnSalir.setBackground(Color.YELLOW);
		btnSalir.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		//btnSalir.setBounds(419, 103, 110, 25);
		this.add(btnSalir, java.awt.BorderLayout.WEST);
		
		nombreReal = new JLabel();
		nombreReal.setForeground(new Color(102, 204, 255));
		nombreReal.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		//nombreReal.setBounds(53, 10, 187, 20);
		this.add(nombreReal, java.awt.BorderLayout.NORTH);
		
		JLabel lblqueDeseaHacer = new JLabel("\u00BFQue desea hacer?");
		lblqueDeseaHacer.setForeground(new Color(0, 0, 0));
		lblqueDeseaHacer.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		//lblqueDeseaHacer.setBounds(82, 50, 137, 20);
		this.add(lblqueDeseaHacer, java.awt.BorderLayout.NORTH);
		
		cargarDatos();
		
		btnSubirFoto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int id_user = ((UsuarioNormal)ventanaPrincipal.getUsuario()).getId();
				String path = uploadPhotoAndGetPath();
				String fec = Utilidades.fechaDeAlta();
				
				Foto foto = new Foto(id_user, path, fec);
				
				bdManager.savePhoto(foto);
				
				fotos_perfil.add(foto);
				
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
	private String uploadPhotoAndGetPath()
	{
		FileDialog dialog = new FileDialog(ventanaPrincipal,"Select Image to upload", FileDialog.LOAD);
		dialog.setVisible(true);
		
		String path = "";
		
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

	@Override
	public void cargarDatos() 
	{
		username.setText(ventanaPrincipal.getUsuario().getNombreUsuario());
		nombreReal.setText("Bienvenido: "+ventanaPrincipal.getUsuario().getNombreReal());
	}
}
