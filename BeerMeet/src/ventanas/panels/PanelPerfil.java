package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SQLite.BDManager;
import foto.Foto;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;
import utilidades.Utilidades;
import ventanas.VentanaPrincipal;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.AbstractBorder;


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
	
	
	public static AbstractBorder bordeCircular = new BordeCircular();       

	public PanelPerfil(VentanaPrincipal ventana) {
		java.awt.BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
        
		bdManager = new BDManager(false);
		
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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String path = uploadPhotoAndGetPath();
				String fec = Utilidades.fechaDeAlta();
				
				if(path != null)
				{
					int id_user = ((UsuarioNormal)ventanaPrincipal.getUsuario()).getId();
					Foto foto = new Foto(id_user, path, fec);
					
					bdManager.savePhoto(foto);
					
					fotos_perfil.add(foto);
				}
				
				ImageIcon imgIcon = new ImageIcon(path);
		        Image imgEscalada = imgIcon.getImage().getScaledInstance(lblNewLabel.getWidth(),
		        		lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		        Icon iconoEscalado = new ImageIcon(imgEscalada);
		        lblNewLabel.setIcon(iconoEscalado);
		        lblNewLabel.setText("");
		        lblNewLabel.setBorder(bordeCircular);
			}
		});
		
		JLabel lblSeguidores = new JLabel("Seguidores");
		
		JLabel lblOo = new JLabel("");
		lblOo.setText(bdManager.Seguidos(((UsuarioNormal)ventanaPrincipal.getUsuario()).getId()).size()+"");
		GroupLayout gl_panelCenter = new GroupLayout(panelCenter);
		gl_panelCenter.setHorizontalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(67)
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSeguidores)
						.addComponent(lblOo, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelCenter.setVerticalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(52)
							.addComponent(lblOo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSeguidores))
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(27)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		panelCenter.setLayout(gl_panelCenter);
		
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
		cargarFotos();		
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
	public  void cargarFotos()
	{
		if(fotos_perfil.size()!=0) {
			for(byte n=0;n<fotos_perfil.size();n++) {
				JLabel labeln= new JLabel("");
				labeln.setSize(175, 175);
				int posicion=n/2;
				
				if(n%2 ==0) {
					labeln.setLocation(50, 250 + 300 * posicion);
				}else {
					labeln.setLocation(300, 250 +300 * posicion);
				}
				
				ImageIcon imgIcon = new ImageIcon(fotos_perfil.get(n).getPath());
		        Image imgEscalada = imgIcon.getImage().getScaledInstance(labeln.getWidth(),
		        labeln.getHeight(), Image.SCALE_SMOOTH);
		        Icon iconoEscalado = new ImageIcon(imgEscalada);
		        labeln.setIcon(iconoEscalado);
				panelCenter.add(labeln);
				
			}
		}
	}}

