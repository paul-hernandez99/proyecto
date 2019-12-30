package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import foto.Foto;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.AbstractBorder;

import SQLite.BDManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class PanelPerfil extends JPanel 
{
	private static final long serialVersionUID = 1L;
	PanelUser panelUser;
	JLabel lblNewLabel = new JLabel();
	String path = null;
	UsuarioNormal user;
	boolean esPerfilPropio;
	int n_g;
	PanelVisualizar panelV ;
	
	public static AbstractBorder bordeCircular = new BordeCircular();       

	public PanelPerfil(PanelUser panel, UsuarioNormal usuario) 
	{
		this.setBackground(Color.WHITE);
		
		this.panelUser = panel;
		
		if(usuario == null || panelUser.getUsuario().getNombreUsuario().equals(usuario.getNombreUsuario()))
		{
			user = panelUser.getUsuario();
			esPerfilPropio = true;
		}
		else
		{
			user = usuario;
			esPerfilPropio = false;
		}
		
		path = panelUser.getBdManager().selectPhotoPerfil(user.getId());
		
		if(path==null) 
		{
			path ="Imagenes/System/descarga.jpg";
			ponerFotoPerfil();
		}
		else 
		{
			ponerFotoPerfil();
		}
		
		if(esPerfilPropio)
		{
			lblNewLabel.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					path = uploadPhotoAndGetPathPerfil();
					
					if(path != null)
					{	panelUser.getBdManager().deletePhotoPerfil(panelUser.getUsuario().getId());
						Foto foto = new Foto(panelUser.getUsuario().getId(), path, null);
						panelUser.getBdManager().insertPhotoPerfil(foto);
						ponerFotoPerfil();
					}
					
				}
			});
		}
		
		JLabel lblSeguidores = new JLabel("Seguidos");
		
		BDManager bdManager = new BDManager(false);
		JLabel lblOo = new JLabel();
		lblOo.setText(""+bdManager.Seguidos(user.getId()));
		
		JLabel label = new JLabel("Seguidores");
		
		JLabel label_1 = new JLabel("");
		label_1.setText(""+bdManager.Seguidores(user.getId()));
		
		
		JButton btnSeguir = new JButton("Seguir");
		
		if (esPerfilPropio) {
			btnSeguir.setVisible(false);
			btnSeguir.setEnabled(false);
		}else {
		
			ArrayList<Usuario> relationships=panelUser.getBdManager().relationships(panelUser.getUsuario().getId());
			List<Usuario> existeRelacion=relationships.stream().filter(x -> x.getNombreUsuario().equals(usuario.getNombreUsuario())).collect(Collectors.toList());
					
			if(existeRelacion.size()==1)
			{
				btnSeguir.setText("Dejar de seguir");
				cargarFotos();
				btnSeguir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panelUser.getBdManager().deleteRelationship(panelUser.getUsuario().getId(), usuario.getId());
						
						PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, usuario);
						panelUser.add(panelPerfilUser, BorderLayout.CENTER);
						panelUser.getPanelPerfil().setVisible(false);
						panelUser.getPanelUserProfile().setVisible(false);
						panelUser.setPanelUserProfile(panelPerfilUser);
						
					}
				});
			}else if(existeRelacion.size()==0){
				btnSeguir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panelUser.getBdManager().createRelationship(panelUser.getUsuario().getId(), usuario.getId());
						
						PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, usuario);
						panelUser.add(panelPerfilUser, BorderLayout.CENTER);
						panelUser.getPanelPerfil().setVisible(false);
						panelUser.getPanelUserProfile().setVisible(false);
						panelUser.setPanelUserProfile(panelPerfilUser);				
					}
				});
			}	
		}
		JLabel lblPublicaciones = new JLabel("Publicaciones");
		
		JLabel label_2 = new JLabel();
		
		JTextPane txtdes = new JTextPane();
		
		txtdes.setText("Escriba su descripci\u00F3n...");
		txtdes.setVisible(false);
		txtdes.enable(false);
		
		JLabel lblDesciption = new JLabel("");
		lblDesciption.setForeground(Color.GRAY);
		lblDesciption.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		if(esPerfilPropio) {
			lblDesciption.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent iepa) {
					txtdes.setVisible(true);
					txtdes.enable(true);
					txtdes.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode()==KeyEvent.VK_ENTER){
								lblDesciption.setText(txtdes.getText());
								bdManager.ModifyDescription(txtdes.getText(), panelUser.getUsuario().getNombreUsuario());
								txtdes.setVisible(false);
								txtdes.enable(false);
								panelUser.getUsuario().setDescripcion(panelUser.getUsuario().getNombreUsuario());
							}
						}
					});
					
				}
			});
		}
		if(esPerfilPropio)
		{
			lblDesciption.setText(panelUser.getUsuario().getDescripcion());
		}else {
			lblDesciption.setText(usuario.getDescripcion());
		}
		lblDesciption.setVisible(true);
		add(lblDesciption);
		
		
		
		GroupLayout gl_panelCenter = new GroupLayout(this);
		gl_panelCenter.setHorizontalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(74)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(lblOo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(44))
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDesciption, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
								.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnSeguir, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
									.addGroup(gl_panelCenter.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblSeguidores)
										.addGap(18)
										.addComponent(lblPublicaciones))
									.addComponent(txtdes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)))
					.addGap(47))
		);
		gl_panelCenter.setVerticalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(52)
							.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(lblOo)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCenter.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(lblSeguidores)
								.addComponent(lblPublicaciones))
							.addGap(18)
							.addComponent(lblDesciption, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtdes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(27)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSeguir)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		
		this.setLayout(gl_panelCenter);
		label_2.setText(panelUser.getVentanaPrincipal().getBdManager().loadUsersPhotos(user.getId()).size()+"");
		if(esPerfilPropio) {
			cargarFotos();
		}
	}
	/**Este método actualiza las fotos de la bandeja de la entrada del panel dependiendo de la fecha de publicación de cad foto*/
	
	private String uploadPhotoAndGetPathPerfil()
	{
		FileDialog dialog = new FileDialog(panelUser.getVentanaPrincipal(),"Select Image to upload", FileDialog.LOAD);
		dialog.setVisible(true);
		
		String path = null;
		
		if(dialog.getFile() != null)
		{
			String type = dialog.getFile().substring(dialog.getFile().length() - 4);
			path = "Imagenes/data/Perfil/"+panelUser.getUsuario().getNombreUsuario()+type;
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
	
	private void ponerFotoPerfil()
	{
		lblNewLabel.setSize(180, 150);
		ImageIcon imgIcon = new ImageIcon(path);
        Image imgEscalada = imgIcon.getImage().getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        lblNewLabel.setIcon(iconoEscalado);
        lblNewLabel.setBorder(bordeCircular);
	}

	private  void cargarFotos()
	{
		ArrayList<Foto> fotos;
		if(esPerfilPropio){
			fotos = panelUser.getFotos_perfil();
		}else{
			fotos = panelUser.getVentanaPrincipal().getBdManager().loadUsersPhotos(user.getId());
		}
		if(fotos.size()>0)
		{

		ArrayList<Foto> fotosPar =new ArrayList<Foto>();
		ArrayList<Foto> fotosImpar = new ArrayList<Foto>();
		
		for(byte n = 0 ;n  <fotos.size() ; n++) 
		{
			Foto foto =fotos.get(n);
			if(n%2 == 0) {
				fotosPar.add(foto);
			}else{
				fotosImpar.add(foto);
			
				}
			}
		Thread hilo = new Thread(){
			public void run() {
				for(byte n = 0 ; n <fotosPar.size() ; n++) 
				{
					JLabel labeln= new JLabel("");
					labeln.setSize(175, 175);
					labeln.setLocation(50, 250 + 300 * n);
					ImageIcon imgIcon = new ImageIcon(fotosPar.get(n).getPath());
			        Image imgEscalada = imgIcon.getImage().getScaledInstance(labeln.getWidth(),labeln.getHeight(), Image.SCALE_SMOOTH);
			        Foto foto= fotosPar.get(n);
			        Icon iconoEscalado = new ImageIcon(imgEscalada);
			        labeln.setIcon(iconoEscalado);
			    	labeln.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							Usuario userr;
							if (esPerfilPropio) {
								userr = panelUser.getUsuario();
							}else {
								userr= user;	
							}
							panelV = new PanelVisualizar(panelUser,foto, userr, path);
							panelUser.add(panelV, BorderLayout.CENTER);
							panelV.setVisible(true);
							panelUser.getPanelPerfil().setVisible(false);
						}
			    	});
						
					add(labeln);
				}
			}
	};
	
		Runnable lanzable = () -> {
			for(byte n = 0 ; n <fotosImpar.size() ; n++) 
			{
				JLabel labeln= new JLabel("");
				labeln.setSize(175, 175);
				labeln.setLocation(300, 250 +300 * n);
				ImageIcon imgIcon = new ImageIcon(fotosImpar.get(n).getPath());
		        Image imgEscalada = imgIcon.getImage().getScaledInstance(labeln.getWidth(),labeln.getHeight(), Image.SCALE_SMOOTH);
		        Icon iconoEscalado = new ImageIcon(imgEscalada);
		        labeln.setIcon(iconoEscalado);
		        Foto foto= fotosImpar.get(n);
		        labeln.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Usuario userr;
						if (esPerfilPropio) {
							userr = panelUser.getUsuario();
						}else {
							userr= user;	
						}
						panelV = new PanelVisualizar(panelUser,foto, userr, path);
						panelUser.add(panelV, BorderLayout.CENTER);
						panelV.setVisible(true);
						panelUser.getPanelPerfil().setVisible(false);
					}
		    	});
				add(labeln);
			}
		};
		Thread hilo_2 = new Thread (lanzable);
		hilo.start();
		hilo_2.start();
		}
	}
	public void setPanelVisualizar(PanelVisualizar PanelV)
	{
		this.panelV=PanelV;
	}
	public PanelVisualizar getPanelVisualizar() {
		return panelV;
	}
}