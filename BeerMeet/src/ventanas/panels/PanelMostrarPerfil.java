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
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;
import ventanas.VentanaPrincipal;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.AbstractBorder;


public class PanelMostrarPerfil extends JPanel {
	
	private VentanaPrincipal ventanaPrincipal;
	private BDManager bdManager;
	private JLabel nombreReal;
	private JPanel panelNorth;
	private JPanel panelWest;
	private JPanel panelCenter;
	private JPanel panelEast;
	private JPanel panelSouth;	
	private ArrayList<Foto> fotos_perfil;
	JLabel lblNewLabel = new JLabel("New label");
	int id_user;
	String path;
	
	
	public static AbstractBorder bordeCircular = new BordeCircular();       

	public PanelMostrarPerfil(VentanaPrincipal ventana,Usuario usuario, String txtBusqueda) {
		
		java.awt.BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
        
		bdManager = new BDManager(false);
		
		ventanaPrincipal = ventana;
		
		fotos_perfil = bdManager.loadUsersPhotos(((UsuarioNormal)usuario).getId());
		id_user = ((UsuarioNormal)usuario).getId();
		
		panelNorth = new JPanel();
		panelNorth.setBackground(Color.WHITE);
		this.add(panelNorth, BorderLayout.NORTH);
		
		panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 102, 102));
		this.add(panelWest, BorderLayout.WEST);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		this.add(panelCenter, BorderLayout.CENTER);
		
		path=bdManager.selectPhotoPerfil(id_user);
		if(path==null) {
			path ="Imagenes/System/descarga.jpg";
			ponerfoto();
		}else {
			ponerfoto();
		}
		
		JLabel lblSeguidores = new JLabel("Seguidos");
		
		JLabel lblOo = new JLabel("");
		lblOo.setText(bdManager.Seguidos(((UsuarioNormal)usuario).getId()).size()+"");
		
		JLabel label = new JLabel("Seguidores");
		
		JLabel label_1 = new JLabel("0");
		label_1.setText(bdManager.Seguidores(((UsuarioNormal)usuario).getId()).size()+"");
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelBusqueda panelBusqueda = new PanelBusqueda(ventanaPrincipal,txtBusqueda);
				ventanaPrincipal.setContentPane(panelBusqueda);
				ventanaPrincipal.setTexts();
				ventanaPrincipal.revalidate();
				
			}
		});
		btnVolver.setBounds(0, 16, 115, 29);
		GroupLayout gl_panelCenter = new GroupLayout(panelCenter);
		gl_panelCenter.setHorizontalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(25)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panelCenter.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panelCenter.createSequentialGroup()
									.addGap(39)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(lblSeguidores))
								.addGroup(gl_panelCenter.createSequentialGroup()
									.addGap(74)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblOo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelCenter.setVerticalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(52)
							.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOo)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(lblSeguidores)))
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVolver)
							.addPreferredGap(ComponentPlacement.RELATED)
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
	private void ponerfoto()
	{
		  lblNewLabel.setSize(180, 150);
		ImageIcon imgIcon = new ImageIcon(path);
        Image imgEscalada = imgIcon.getImage().getScaledInstance(lblNewLabel.getWidth(),
        		lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        lblNewLabel.setIcon(iconoEscalado);
        lblNewLabel.setText("");
        lblNewLabel.setBorder(bordeCircular);
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
	}	
}