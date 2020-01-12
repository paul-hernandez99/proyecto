package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import foto.Foto;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

import comentario.Comentario;

import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class PanelVisualizar extends JPanel
{
	private PanelUser panelUser;
	public static AbstractBorder bordeCircular = new BordeCircular();  
	public PanelComentario panelComentario;

	public PanelVisualizar(PanelUser panel, Foto foto, Usuario user, String path)
	{
		this.panelUser=panel;
		setBackground(Color.WHITE);
		setLayout(null); 
									
				JLabel adaptador= new JLabel();
				adaptador.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						boolean comprobacion = false;
						if(panelUser.getAdminsitrador() == null) {
							comprobacion =panelUser.getUsuario().getId() ==foto.getId_user();
						}
						if(comprobacion)
					        {
								panel.getPanelPerfil().getPanelVisualizar().setVisible(false);
								panel.getPanelPerfil().setVisible(true);
								panel.goToPanelPerfil();
					        }else {
					        	panel.getPanelUserProfile().getPanelVisualizar().setVisible(false);
								panel.getPanelUserProfile().setVisible(true);
							}
					}
				});
				
				adaptador.setBounds(0,0,65,65);
		        adaptador.setIcon(panelUser.escalar("Imagenes\\System\\j.png", adaptador));
				add(adaptador);
		        
		        JLabel fotoPerfil= new JLabel();
		        fotoPerfil.setBounds(30,80,60,60);
		        fotoPerfil.setIcon(panelUser.escalar(path, fotoPerfil));
		        fotoPerfil.setBorder(bordeCircular);
		        add(fotoPerfil);
		        
		        JLabel nombre = new JLabel();
		        nombre.setForeground(Color.DARK_GRAY);
		        nombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		        nombre.setBounds(88,85,100,55);
		        nombre.setText("   "+user.getNombreUsuario() +"");
		        nombre.setVisible(true);
		        add(nombre);
				
				JLabel imagen = new JLabel();
				imagen.setBounds(85,160,365,365);
		        imagen.setIcon(panelUser.escalar(foto.getPath(), imagen));
		        add(imagen);
		        
		        JLabel borrar = new JLabel("");
		        
		        boolean comprobacion = false;
		        if (panelUser.getAdminsitrador() == null) {
		        	comprobacion=panelUser.getUsuario().getId() ==foto.getId_user();
		        }
		        
		        if(comprobacion ||panelUser.getAdminsitrador() != null )
		        {
			        
			        borrar.addMouseListener(new MouseAdapter() {
			        	@Override
			        	public void mouseClicked(MouseEvent e) {
			        		Object[] options = {"Aceptar",
		                    "Cancelar"};
							int n = JOptionPane.showOptionDialog(null,
							    "¿Deseas eliminar esta foto2?",
							    "Eliminación",
							    JOptionPane.YES_NO_CANCEL_OPTION,
							    JOptionPane.QUESTION_MESSAGE,
							    null,options,options[1]);
							
							if(n ==0) 
							{
				        		panel.getBdManager().deleteFoto(foto.getCod());
				        		ArrayList<Foto> allFotos =panel.getFotos_inicio();
				        		
				        		List<Foto> eliminacion =allFotos.stream().filter(x ->x.getCod()!=foto.getCod()).collect(Collectors.toList());
				        		
				        		ArrayList<Foto> filtrado = new ArrayList<Foto>(eliminacion); 
				        		panel.setFotos_inicio(filtrado);
				        		 
				        		panel.setFotos_perfil(panelUser.getBdManager().loadUsersPhotos(panelUser.getPanelUserProfile().getUser().getId()));
				        		if(panelUser.getAdminsitrador() ==null) {		        		
					        		panelUser.getPanelPerfil().getPanelVisualizar().setVisible(false);
					        		PanelPerfil nuevo = new PanelPerfil(panelUser, null);
					        		panelUser.setPanelPerfil(nuevo);
					        		panelUser.getPanelPerfil().setVisible(true);
					        		panelUser.goToPanelPerfil();
				        		}else {
				        			panelUser.getPanelUserProfile().getPanelVisualizar().setVisible(false);
					        		PanelPerfil nuevo = new PanelPerfil(panelUser, panelUser.getPanelUserProfile().getUser());
					        		panelUser.setPanelUserProfile(nuevo);
					        		panelUser.getPanelUserProfile().setVisible(true);
					        		panelUser.goToPanelPerfil();
				        		}
							}
			        	}
			        });
			        borrar.setIcon(new ImageIcon("Imagenes\\System\\Image_74.png"));
			        borrar.setBounds(484, 99, 38, 36);
			        add(borrar);
			        
			        JLabel comentarios=new JLabel();
			        ArrayList<Comentario> list =panelUser.getBdManager().SelectComentarios(foto.getCod());
			        comentarios.setText("Ver los "+list.size()+" comentarios");
			       comentarios.setBounds(150, 530, 300, 50);
					
					comentarios.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
								panelComentario= new PanelComentario(panelUser,list, 1, foto, user, path);
								if (panelUser.getAdminsitrador() != null) {
									panelUser.getPanelUserProfile().getPanelVisualizar().setVisible(false);
								}else {
									panelUser.getPanelPerfil().getPanelVisualizar().setVisible(false);
								}
								
								panelUser.add(panelComentario,BorderLayout.CENTER);
								panelComentario.setVisible(true);
						}
					});
					add(comentarios);
		        }
	}
	public PanelComentario getPanelComentario() {
		return this.panelComentario;
	}
	public void setPanelComentario(PanelComentario panel)
	{
		this.panelComentario = panel;
	}
}