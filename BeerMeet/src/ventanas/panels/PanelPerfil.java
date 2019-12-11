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


public class PanelPerfil extends JPanel 
{
	PanelUser panelUser;
	JLabel lblNewLabel = new JLabel();
	String path = null;
	
	public static AbstractBorder bordeCircular = new BordeCircular();       

	public PanelPerfil(PanelUser panel) 
	{
		this.panelUser = panel;
		
		path = panelUser.getBdManager().selectPhotoPerfil(panelUser.getUsuario().getId());
		
		if(path==null) 
		{
			path ="Imagenes/System/descarga.jpg";
			ponerFotoPerfil();
		}
		else 
		{
			ponerFotoPerfil();
		}
		
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
		
		
		JLabel lblSeguidores = new JLabel("Seguidos");
		
		JLabel lblOo = new JLabel("");
		lblOo.setText(""+panelUser.getBdManager().Seguidos(panelUser.getUsuario().getId()));
		
		JLabel label = new JLabel("Seguidores");
		
		JLabel label_1 = new JLabel("0");
		label_1.setText(""+panelUser.getBdManager().Seguidores((panelUser.getUsuario()).getId()));
		GroupLayout gl_panelCenter = new GroupLayout(this);
		gl_panelCenter.setHorizontalGroup(
			gl_panelCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCenter.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelCenter.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelCenter.createSequentialGroup()
							.addGap(39)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(lblSeguidores))
						.addGroup(Alignment.TRAILING, gl_panelCenter.createSequentialGroup()
							.addGap(74)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblOo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
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
							.addGap(27)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		
		this.setLayout(gl_panelCenter);
		
		cargarFotos();		
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
		if(panelUser.getFotos_perfil().size() != 0) 
		{
			for(int n = 0 ; n  <panelUser.getFotos_perfil().size() ; n++) 
			{
				JLabel labeln= new JLabel("");
				labeln.setSize(175, 175);
				int posicion=n/2;
				
				if(n%2 == 0) 
				{
					labeln.setLocation(50, 250 + 300 * posicion);
				}
				else 
				{
					labeln.setLocation(300, 250 +300 * posicion);
				}
				
				ImageIcon imgIcon = new ImageIcon(panelUser.getFotos_perfil().get(n).getPath());
		        Image imgEscalada = imgIcon.getImage().getScaledInstance(labeln.getWidth(),labeln.getHeight(), Image.SCALE_SMOOTH);
		        Icon iconoEscalado = new ImageIcon(imgEscalada);
		        labeln.setIcon(iconoEscalado);
				this.add(labeln);
				
			}
		}
	}	
}