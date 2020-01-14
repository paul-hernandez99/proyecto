package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import foto.Foto;
import usuarios.Usuario;
import utilidades.BordeCircular;
import javax.swing.border.AbstractBorder;
import comentario.Comentario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Font;
/**Esta clase contiene los meétodos lógicos y visuales para la creación del panelVisualizar
 * y su correcto funcionamiento.
 * @author Aritz E. y Paul H. 
 * @version 1.3*/

public class PanelVisualizar extends JPanel
{
	private PanelUser panelUser;
	public static AbstractBorder bordeCircular = new BordeCircular();  
	public PanelComentario panelComentario;
	private Foto foto;
	private Usuario user;
	private String path;
	/**
	 * Estamos ante el constructos o creación del PanelVisualizar
	 * @param panel : panelUser recibido.
	* @param foto : foto que se desea visualizar.
	 * @param user : usuario existente al que pertenece la foto.
	 * @param path : ruta.
	 */
	public PanelVisualizar(PanelUser panel, Foto foto, Usuario user, String path)
	{
		this.panelUser=panel;
		this.foto = foto;
		this.user = user;
		this.path = path;
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
		        adaptador.setIcon(panelUser.escalar("Imagenes\\System\\1.png", adaptador));
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
			        borrar.setBounds(485,100, 38, 36);
			        add(borrar);
			        
			        JLabel likes = new JLabel();
			        ArrayList<Integer> arrayLikes =panelUser.getBdManager().SelectLike(foto.getCod());
			        long existeLike =  arrayLikes.stream().filter(x->x == panelUser.getUsuario().getId()).count();
			        
			        if(panelUser.getAdminsitrador() == null) {
				        likes.setBounds(100,530,40,40);
				        
				        if(existeLike ==0) {
				        	likes.setIcon(panelUser.escalar("Imagenes\\System\\like.jpg", likes));
				        }else {
				        	likes.setIcon(panelUser.escalar("Imagenes\\System\\red.jpg", likes));
				        }
				        
				        likes.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
									if(existeLike ==0) {
										panelUser.getBdManager().CreateLike(foto.getCod(), panelUser.getUsuario().getId());
										actualizaPantall();
									}else {
										panelUser.getBdManager().DeleteLike(foto.getCod(), panelUser.getUsuario().getId());
										actualizaPantall();
									}
							}
						});
				        add(likes);
		        	}
			        JLabel nuemeroLikes =new JLabel();
			        nuemeroLikes.setText(arrayLikes.size()+ " likes.");
			        nuemeroLikes.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
			        nuemeroLikes.setBounds(150, 530, 300, 50);
			        add(nuemeroLikes);
			        
			        JLabel comentarios=new JLabel();
			        ArrayList<Comentario> list =panelUser.getBdManager().SelectComentarios(foto.getCod());
			        comentarios.setText("Ver los "+list.size()+" comentarios");
			       comentarios.setBounds(150, 580, 300, 50);
			       comentarios.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
					
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
	/**Meto getter del PanelComentario existente.
	 * @return panel :  panelComentarios existente (instancia).
	 */
	public PanelComentario getPanelComentario() {
		return this.panelComentario;
	}
	/**Meto setter del PanelComentario existente.
	 * @param panel : nuevo panelComentarios.
	 */
	public void setPanelComentario(PanelComentario panel)
	{
		this.panelComentario = panel;
	}
	/**Método que actualiza el panel en el que nos encontramos (crando uno nuevo y eliminado el viejo*/
	
	public void actualizaPantall() {
		PanelVisualizar nuevo = new PanelVisualizar(panelUser, foto, user, path);
		
		if(panelUser.getPanelPerfil()  != null) {;
		panelUser.getPanelPerfil().getPanelVisualizar().setVisible(false);
		panelUser.getPanelPerfil().setPanelVisualizar(nuevo);
		panelUser.getPanelPerfil().getPanelVisualizar().setVisible(true);
		panelUser.goToPanelPerfil();
		panelUser.getPanelPerfil().setVisible(false);
		panelUser.add(nuevo, BorderLayout.CENTER);
		nuevo.setVisible(true);
	
	}else {
		panelUser.getPanelUserProfile().setVisible(false);
		panelUser.getPanelUserProfile().setPanelVisualizar(nuevo); 
		panelUser.getPanelUserProfile().setVisible(true);
		panelUser.goToPanelPerfil();
		panelUser.getPanelPerfil().setVisible(false);
		nuevo.setVisible(true);
		panelUser.add(nuevo, BorderLayout.CENTER);
	}
	}
}