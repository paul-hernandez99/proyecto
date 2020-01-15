package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SQLite.BDManager;
import comentario.Comentario;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;
import utilidades.Utilidades;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**Esta clase contiene los meétodos lógicos y visuales para la creación del panelInicio
 * y su correcto funcionamiento.
 * @author Aritz E. y Paul H. 
 * @version 1.3*/

public class PanelInicio extends JPanel
{
	private PanelUser panelUser;
	private BDManager bdManager;
	private int contador =0;
	public static AbstractBorder bordeCircular = new BordeCircular(); 
	public PanelComentario panelComentario;
	
	/**Estamos ante la creación del panel Incio
	 * @param panel : panelUser recibido.*/ 
	
	public PanelInicio(PanelUser panel)
	{
		this.panelUser=panel;
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new GridBagLayout());
	
		JScrollPane scrollPane = new JScrollPane(panel_1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0,0,560,750);
		add(scrollPane);
		scrollPane.setViewportView(panel_1);
		
		
		bdManager = panelUser.getBdManager();
		int posicion=0;
		
		JLabel adaptador= new JLabel();
		adaptador.setBounds(0,0,10,10);
		GridBagConstraints gadaptador = new GridBagConstraints();
		gadaptador .gridx =  0;
		gadaptador .gridy = 0;
		panel_1.add(adaptador,gadaptador);
		
		for(int i=0; i<panelUser.getFotos_inicio().size(); i++)
		{
			contador=i;
			
			JLabel fotoPerfil= new JLabel();
			fotoPerfil.setBounds(0,0,50,50);
			GridBagConstraints gfotoPerfil = new GridBagConstraints();
			gfotoPerfil .gridx =  1;
			gfotoPerfil .gridy = 1+posicion;
			String lt=panelUser.getBdManager().selectPhotoPerfil(panelUser.getFotos_inicio().get(i).getId_user());
			if(panelUser.getBdManager().selectPhotoPerfil(panelUser.getFotos_inicio().get(i).getId_user()) == null) {
				lt= "Imagenes\\System\\descarga.jpg";
			}
			ImageIcon foto = new ImageIcon( lt);
	        Image fotoEscalada = foto.getImage().getScaledInstance(fotoPerfil.getWidth(),fotoPerfil.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconEscalado = new ImageIcon(fotoEscalada);
	        fotoPerfil.setBorder(bordeCircular);
	        fotoPerfil.setIcon(iconEscalado);
	        
	    	fotoPerfil.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				ArrayList<Usuario> useers= bdManager.loadUsers();
				
				String nombre= bdManager.SelectNombreUsuaruario(panelUser.getFotos_inicio().get(contador).getId_user());
				List<Usuario> userr=useers.stream().filter(x -> x.getNombreUsuario().equals(nombre)).collect(Collectors.toList());
					
					PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, (UsuarioNormal)userr.get(0));
					panelUser.add(panelPerfilUser, BorderLayout.CENTER);
					panelUser.setPanelUserProfile(panelPerfilUser);
					setVisible(false);
				}
			});
	        panel_1.add(fotoPerfil,gfotoPerfil);
			
			JLabel nombre = new JLabel();
			nombre.setBounds(0,0,50,50);
			nombre.setText("   "+bdManager.SelectNombreUsuaruario(panelUser.getFotos_inicio().get(i).getId_user()) +"");
			nombre.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gnombre= new GridBagConstraints();
			gnombre .gridx = 2;
			gnombre .gridy = posicion+1;	
			nombre.setVisible(true);
			panel_1.add(nombre, gnombre);
			
			nombre.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				ArrayList<Usuario> useers= bdManager.loadUsers();
				
				String nombre= bdManager.SelectNombreUsuaruario(panelUser.getFotos_inicio().get(contador).getId_user());
				List<Usuario> userr=useers.stream().filter(x -> x.getNombreUsuario().equals(nombre)).collect(Collectors.toList());
					
					PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, (UsuarioNormal)userr.get(0));
					panelUser.add(panelPerfilUser, BorderLayout.CENTER);
					panelUser.setPanelUserProfile(panelPerfilUser);
					setVisible(false);


				}
			});
			
			JLabel imagen = new JLabel();
			imagen.setBounds(0,0,400,400);
			GridBagConstraints gimagen = new GridBagConstraints();
			gimagen .gridx = 3;
			gimagen .gridy = 2+posicion;
			ImageIcon imgIcon = new ImageIcon(panelUser.getFotos_inicio().get(i).getPath());
	        Image imgEscalada = imgIcon.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconoEscalado = new ImageIcon(imgEscalada);
	        imagen.setIcon(iconoEscalado);
	        panel_1.add(imagen, gimagen);
	        
	        if(panelUser.getAdminsitrador() == null) {
		        JLabel likes = new JLabel();
		        likes.setBounds(0,0,40,40);
		        GridBagConstraints glikes = new GridBagConstraints();
				glikes .gridx = 3;
				glikes .gridy = 3+posicion;
		        
		        ArrayList<Integer> arrayLikes =panelUser.getBdManager().SelectLike(panelUser.getFotos_inicio().get(i).getCod());
		        long existeLike =  arrayLikes.stream().filter(x->x == panelUser.getUsuario().getId()).count();
		        if(existeLike ==0) {
		        	likes.setIcon(panelUser.escalar("Imagenes\\System\\like.jpg", likes));
		        }else {
		        	likes.setIcon(panelUser.escalar("Imagenes\\System\\red.jpg", likes));
		        }
		        likes.setName(""+i);
		        likes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
							if(existeLike ==0) {
								panelUser.getBdManager().CreateLike(panelUser.getFotos_inicio().get(new Integer(likes.getName())).getCod(), panelUser.getUsuario().getId());
								actualizaPantall();
							}else {
								panelUser.getBdManager().DeleteLike(panelUser.getFotos_inicio().get(new Integer(likes.getName())).getCod(), panelUser.getUsuario().getId());
								actualizaPantall();
							}
					}
				});
		        panel_1.add(likes, glikes);
		        
		        JLabel nuemeroLikes =new JLabel();
		        nuemeroLikes.setText("                      "+arrayLikes.size()+ " likes.");
		        nuemeroLikes.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		        nuemeroLikes.setBounds(150, 530, 300, 50);
		        GridBagConstraints gnuemeroLikes= new GridBagConstraints();
				gnuemeroLikes .gridx = 3;
				gnuemeroLikes .gridy = 3+posicion;
		        panel_1.add(nuemeroLikes, gnuemeroLikes);
			}
	        JLabel comentarios=new JLabel();
	        ArrayList<Comentario> list =panelUser.getBdManager().SelectComentarios(panelUser.getFotos_inicio().get(i).getCod());
	        comentarios.setText("Ver los "+list.size()+" comentarios");
	        comentarios.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
	        GridBagConstraints gcomentario= new GridBagConstraints();
			gcomentario .gridx = 3;
			gcomentario .gridy = 4+posicion;
			comentarios.setName(i+"");
			comentarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						panelComentario= new PanelComentario(panelUser,list,0, panelUser.getFotos_inicio().get(new Integer(comentarios.getName())), null, null);
						panelUser.getPanelInicio().setVisible(false);
						panelUser.add(panelComentario,BorderLayout.CENTER);
						panelComentario.setVisible(true);
				}
			});
			panel_1.add(comentarios,gcomentario);
	        
	        posicion+=4;
		}
	}
	/**Método getter del panelComentario
	 * @return panelComentario : panelComentario existenete en el momento (instancia).
	 */
	public PanelComentario getPanelComentario() {
		return this.panelComentario;
	}
	/**Método setter del panelComentario
	 * @param panel : panelComentario existenete en el momento (instancia).
	 */
	public void setPanelComentario(PanelComentario panel)
	{
		this.panelComentario = panel;
	}
	/**Método que actualiza el panel en el que nos encontramos (crando uno nuevo y eliminado el viejo*/
	
	public void actualizaPantall() {
		
		panelUser.getPanelInicio().setVisible(false);
		PanelInicio inicio = new PanelInicio(panelUser);
		panelUser.setPanelInicio(inicio);
		panelUser.getPanelInicio().setVisible(true);
		panelUser.add(inicio,  BorderLayout.CENTER);
	}
}
