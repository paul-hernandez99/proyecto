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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SQLite.BDManager;
import foto.Foto;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.VentanaPrincipal;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PanelBusqueda extends JPanel {


private VentanaPrincipal ventanaPrincipal;
	
	private BDManager bdManager;

	private JLabel nombreReal;
	
	private JPanel panelNorth;
	private JPanel panelWest;
	private JPanel panelCenter;
	private JPanel panelEast;
	private JPanel panelSouth;
	private ArrayList<Foto> fotos_perfil;
	private JTextField txtKokp = new JTextField();
	private JList<Usuario> list = new JList<Usuario>();
	private String contBusque;
	
	public PanelBusqueda( VentanaPrincipal ventana, String textBusqueda) {
		java.awt.BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
        
		bdManager = new BDManager(false);
		
		ventanaPrincipal = ventana;
				
		panelNorth = new JPanel();
		panelNorth.setBackground(Color.WHITE);
		this.add(panelNorth, BorderLayout.NORTH);
		
		panelWest = new JPanel();
		panelWest.setBackground(new Color(255, 102, 102));
		this.add(panelWest, BorderLayout.WEST);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		this.add(panelCenter, BorderLayout.CENTER);
		SpringLayout sl_panelCenter = new SpringLayout();
		
		
		sl_panelCenter.putConstraint(SpringLayout.NORTH, list, 13, SpringLayout.SOUTH, txtKokp);
		sl_panelCenter.putConstraint(SpringLayout.WEST, list, 2, SpringLayout.WEST, txtKokp);
		sl_panelCenter.putConstraint(SpringLayout.SOUTH, list, 122, SpringLayout.SOUTH, txtKokp);
		sl_panelCenter.putConstraint(SpringLayout.EAST, list, 333, SpringLayout.WEST, txtKokp);
		panelCenter.setLayout(sl_panelCenter);
		
		txtKokp.setText(textBusqueda);
		txtKokp.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
			contBusque =getTextoo(); 
			ArrayList<Usuario> usuariosBusqueda = bdManager.loadUsers();
			ArrayList<Usuario> usuarioSelect = new ArrayList<Usuario>();
			for(byte i=0; i<usuariosBusqueda.size();i++) {
				if(usuariosBusqueda.get(i).getNombreUsuario().contains(contBusque)||usuariosBusqueda.get(i).getNombreReal().contains(contBusque)||usuariosBusqueda.get(i).getApellidos().contains(contBusque)) {
	
				usuarioSelect.add(usuariosBusqueda.get(i));
				}
			}
			if(usuarioSelect.size()==0) {
				list.setVisible(false);
			}else {
				list.setVisible(true);
				cargarLista(usuarioSelect);
			}
			}
	});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Usuario usuario =list.getSelectedValue(); 
				PanelMostrarPerfil panelMPerfil = new PanelMostrarPerfil(ventanaPrincipal,usuario, contBusque);
				ventanaPrincipal.setContentPane(panelMPerfil);
				ventanaPrincipal.setTexts();
				ventanaPrincipal.revalidate();
			}
		});
		list.setBackground(Color.WHITE);
		panelCenter.add(list);
		
		sl_panelCenter.putConstraint(SpringLayout.NORTH, txtKokp, 76, SpringLayout.NORTH, panelCenter);
		sl_panelCenter.putConstraint(SpringLayout.WEST, txtKokp, 45, SpringLayout.WEST, panelCenter);
		sl_panelCenter.putConstraint(SpringLayout.EAST, txtKokp, -54, SpringLayout.EAST, panelCenter);
		panelCenter.add(txtKokp);
		txtKokp.setColumns(10);
		txtKokp.setVisible(true);
		
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
				PanelPerfil panel = new PanelPerfil(ventanaPrincipal);
				ventanaPrincipal.setContentPane(panel);
				ventanaPrincipal.setTexts();
				ventanaPrincipal.revalidate();
				
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
	public String getTextoo() {
		return this.txtKokp.getText();
	}
	
	public void cargarLista(ArrayList <Usuario> usuarioSelect){
		DefaultListModel<Usuario> model = new DefaultListModel<>();
		for(byte n=0;n<usuarioSelect.size();n++) {
			model.addElement(usuarioSelect.get(n));
			list.setModel(model);
	}
	}
}
