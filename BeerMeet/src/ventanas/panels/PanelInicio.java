package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class PanelInicio extends JPanel
{
	private PanelUser panelUser;
	private BDManager bdManager;
	private int contador =0;
	public static AbstractBorder bordeCircular = new BordeCircular(); 
	public PanelComentario panelComentario;
	
	public PanelInicio(PanelUser panel)
	{
		this.panelUser=panel;
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new GridBagLayout());
	
		JScrollPane scrollPane = new JScrollPane(panel_1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0,0,560,790);
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
			ImageIcon foto = new ImageIcon( panelUser.getBdManager().selectPhotoPerfil(panelUser.getFotos_inicio().get(i).getId_user()));
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
	        
	        JLabel comentarios=new JLabel();
	        ArrayList<Comentario> list =panelUser.getBdManager().SelectComentarios(panelUser.getFotos_inicio().get(i).getCod());
	        comentarios.setText("Ver los "+list.size()+" comentarios");
	        GridBagConstraints gcomentario= new GridBagConstraints();
			gcomentario .gridx = 3;
			gcomentario .gridy = 3+posicion;
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
	        
	        posicion+=3;
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
