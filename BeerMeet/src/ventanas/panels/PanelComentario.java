package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

import SQLite.BDManager;
import comentario.Comentario;
import foto.Foto;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;

public class PanelComentario extends JPanel {
	private PanelUser panelUser;
	public static AbstractBorder bordeCircular = new BordeCircular();  
	private BDManager bdManager;
	private int contador;
	
	public PanelComentario(PanelUser panel, ArrayList<Comentario>coments) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		add(panel_3, BorderLayout.SOUTH);
			
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.white);
		add(panel_2, BorderLayout.NORTH);
		
		JLabel simbolo= new JLabel();
		simbolo.setBounds(0, 0, 50, 50);
		simbolo.setBackground(Color.BLACK);
		ImageIcon symbol = new ImageIcon("Imagenes/System/1.png");
        Image symbolEscalada = symbol.getImage().getScaledInstance(simbolo.getWidth(),simbolo.getHeight(), Image.SCALE_SMOOTH);
        Icon SinboloiconEscalado = new ImageIcon(symbolEscalada);
        simbolo.setIcon(SinboloiconEscalado);
		 panel_2.add(simbolo);
		
		this.panelUser=panel;
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new GridBagLayout());
	
		JScrollPane scrollPane = new JScrollPane(panel_1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(panel_1);
		scrollPane.setBounds(0,0,560,790);
		add(scrollPane,BorderLayout.CENTER);
		
		
		int posicion = 0;
		bdManager = panelUser.getBdManager();
		
		for(int i=0; i<coments.size(); i++)
		{
			contador =i;
			
			JLabel fotoPerfil= new JLabel();
			fotoPerfil.setBounds(0,0,100,100);
			GridBagConstraints gfotoPerfil = new GridBagConstraints();
			gfotoPerfil .gridx =  1;
			gfotoPerfil .gridy = 1+posicion;
			String fotoPath = panelUser.getBdManager().selectPhotoPerfil(coments.get(i).getId_user());
		       if(fotoPath==null) {
		    	   fotoPath= "Imagenes/System/descarga.jpg";
		       }
			
			ImageIcon foto = new ImageIcon(fotoPath);
			Image fotoEscalada = foto.getImage().getScaledInstance(fotoPerfil.getWidth(),fotoPerfil.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconEscalado = new ImageIcon(fotoEscalada);
	        fotoPerfil.setBorder(bordeCircular);
	        fotoPerfil.setIcon(iconEscalado);
	        fotoPerfil.setName(""+i);
	        
	        fotoPerfil.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				ArrayList<Usuario> useers= bdManager.loadUsers();
				ArrayList<UsuarioNormal>usuarios = new ArrayList();
				for(byte i=0; i<useers.size();i++)
				{
					if(useers.get(i)instanceof UsuarioNormal) {
						usuarios.add((UsuarioNormal) useers.get(i));
					}
				}
				
				String nombre= bdManager.SelectNombreUsuaruario(panelUser.getFotos_inicio().get(contador).getId_user());
				List<Usuario> userr=usuarios.stream().filter(x -> x.getId() == coments.get(new Integer(fotoPerfil.getName())).getId_user()).collect(Collectors.toList());
					
					PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, (UsuarioNormal)userr.get(0));
					panelUser.add(panelPerfilUser, BorderLayout.CENTER);
					panelUser.setPanelUserProfile(panelPerfilUser);
					setVisible(false);
				}
			});
	        panel_1.add(fotoPerfil,gfotoPerfil);
	        
	        JLabel comentario = new JLabel();
			comentario.setBounds(0,0,50,50);
			comentario.setText("   "+coments.get(i).getContenido());
			comentario.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gco= new GridBagConstraints();
			gco .gridx = 2;
			gco .gridy = posicion+1;	
			comentario.setVisible(true);
			panel_1.add(comentario, gco);
			
			posicion+=2;
		}
	}

}
