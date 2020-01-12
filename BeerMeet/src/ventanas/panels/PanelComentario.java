package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;

import SQLite.BDManager;
import comentario.Comentario;
import foto.Foto;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.BordeCircular;
import utilidades.Utilidades;

public class PanelComentario extends JPanel {
	private PanelUser panelUser;
	public static AbstractBorder bordeCircular = new BordeCircular();  
	private BDManager bdManager;
	private Foto foto;
	private Usuario user;
	private String path;
	private ArrayList <Comentario> cBerriak;
	public PanelComentario(PanelUser panel, ArrayList<Comentario>coments ,int origen ,Foto foto, Usuario user, String path) {
		
		BorderLayout borderlayout = new java.awt.BorderLayout();
        this.setLayout(borderlayout);
		
		this.panelUser=panel;
		this.foto = foto;
		this.user= user;
		this.path= path;
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		this.add(panel_3, BorderLayout.SOUTH);
			
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.white);
		this.add(panel_2, BorderLayout.NORTH);
		
		JLabel simbolo= new JLabel();
		simbolo.setBounds(0, 0, 50, 50);
		ImageIcon symbol = new ImageIcon("Imagenes/System/1.png");
        Image symbolEscalada = symbol.getImage().getScaledInstance(simbolo.getWidth(),simbolo.getHeight(), Image.SCALE_SMOOTH);
        Icon SinboloiconEscalado = new ImageIcon(symbolEscalada);
        simbolo.setIcon(SinboloiconEscalado);
        simbolo.setHorizontalAlignment(SwingConstants.LEFT);
        simbolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (origen == 0) {
					panelUser.goToPanelInicio();
					panelUser.getPanelInicio().getPanelComentario().setVisible(false);
					panelUser.getPanelInicio().setPanelComentario(null);
				}else if (origen == 1) {
					if(panelUser.getAdminsitrador() == null) {
						panelUser.getPanelPerfil().getPanelVisualizar().setVisible(true);
						panelUser.getPanelPerfil().getPanelVisualizar().getPanelComentario().setVisible(false);
						panelUser.getPanelPerfil().getPanelVisualizar().setPanelComentario(null);
					}else {
						panelUser.getPanelUserProfile().getPanelVisualizar().setVisible(true);
						panelUser.getPanelUserProfile().getPanelVisualizar().getPanelComentario().setVisible(false);
						panelUser.getPanelUserProfile().getPanelVisualizar().setPanelComentario(null);
					
					}
				}
			}
		});
		 panel_2.add(simbolo);
		 
		 JLabel cabezera = new JLabel();
		 cabezera.setText("Comentarios");
		 cabezera.setBounds(0, 0,300, 50);
		 cabezera.setFont(new Font("Helvetica Neue", Font.BOLD, 26));
		 cabezera.setHorizontalAlignment(SwingConstants.LEFT);
		 panel_2.add(cabezera);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new GridBagLayout());
	
		JScrollPane scrollPane = new JScrollPane(panel_1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(panel_1);
		scrollPane.setBounds(0,0,560,490);
		add(scrollPane,BorderLayout.CENTER);
		
		JLabel profile =new JLabel();
		profile.setBounds(0,0,65,65);
		String direccionI;
		if(panelUser.getAdminsitrador() != null) {
			direccionI= "Imagenes\\System\\Wallpaper.png";
		}else {
			direccionI=panelUser.getBdManager().selectPhotoPerfil(panelUser.getUsuario().getId());
		}
		
		ImageIcon pro = new ImageIcon(direccionI);
		Image proEscalada = pro.getImage().getScaledInstance(profile.getWidth(),profile.getHeight(), Image.SCALE_SMOOTH);
        Icon proEscalado = new ImageIcon(proEscalada);
        profile.setBorder(bordeCircular);
        profile.setIcon(proEscalado);
        panel_3.add(profile);
		
        JLabel publicar =new JLabel();
        
		 JTextField newComent =new JTextField();
	        newComent.setBounds(0, 0, 600, 65);
	        String nombreComentador;
	        if(panelUser.getAdminsitrador() == null) {
	        	nombreComentador = panelUser.getUsuario().getNombreUsuario();
	        }else {
	        	nombreComentador = panelUser.getAdminsitrador().getNombreUsuario();
	        }
	        newComent.setText("Comentar como "+nombreComentador+"....                         ");
	        newComent.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
	        newComent.setForeground(new Color(220, 220, 220));
	        newComent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					newComent.setText("");
					newComent.setForeground(Color.BLACK);
					publicar.setEnabled(true);
				}
			});
			panel_3.add(newComent);
		
			
			publicar.setEnabled(false);
			publicar.setBounds(0, 0, 50, 50);
			publicar.setText("    Publicar");
			publicar.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
			publicar.setHorizontalAlignment(SwingConstants.LEFT);
			publicar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (newComent.getText().length()>0) {
							Comentario comentario = new Comentario();
							comentario.setCod_fot(foto.getCod());
							comentario.setContenido(newComent.getText());
							if(panelUser.getAdminsitrador() == null) {
								comentario.setId_user(panelUser.getUsuario().getId());
							}else {
								comentario.setId_user(panelUser.getAdminsitrador().getId());
							}
							String fec = Utilidades.fechaDeAlta();
							comentario.setFec(fec);
							panelUser.getBdManager().createComent(comentario);
							cBerriak= panelUser.getBdManager().SelectComentarios(foto.getCod());
							
							if(origen == 0) {
								casoInicio();
							}else {
								casoVisualizar();
							}
						}
					}
				});
		panel_3.add(publicar);
		int posicion = 0;
		bdManager = panelUser.getBdManager();

		for(int i=0; i<coments.size(); i++)
		{
			JLabel fotoPerfil= new JLabel();
			fotoPerfil.setBounds(0,0,65,65);
			GridBagConstraints gfotoPerfil = new GridBagConstraints();
			gfotoPerfil .gridx =  1;
			gfotoPerfil .gridy = 1+posicion;
			String fotoPath = panelUser.getBdManager().selectPhotoPerfil(coments.get(i).getId_user());
		       if(fotoPath==null) {
		    	   fotoPath= "Imagenes/System/descarga.jpg";
		       }
			
			ImageIcon fotoP = new ImageIcon(fotoPath);
			Image fotoEscalada = fotoP.getImage().getScaledInstance(fotoPerfil.getWidth(),fotoPerfil.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconEscalado = new ImageIcon(fotoEscalada);
	        fotoPerfil.setBorder(bordeCircular);
	        fotoPerfil.setIcon(iconEscalado);
	        fotoPerfil.setName(""+i);
	        
	        fotoPerfil.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				ArrayList<Usuario> useers= bdManager.loadUsers();
				ArrayList<UsuarioNormal>usuarios = new ArrayList<UsuarioNormal>();
				for(byte i=0; i<useers.size();i++)
				{
					if(useers.get(i)instanceof UsuarioNormal) {
						usuarios.add((UsuarioNormal) useers.get(i));
					}
				}
				
			//	String nombre= bdManager.SelectNombreUsuaruario(panelUser.getFotos_inicio().get(contador).getId_user());
				List<Usuario> userr=usuarios.stream().filter(x -> x.getId() == coments.get(new Integer(fotoPerfil.getName())).getId_user()).collect(Collectors.toList());
					
					PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, (UsuarioNormal)userr.get(0));
					panelUser.add(panelPerfilUser, BorderLayout.CENTER);
					panelUser.setPanelUserProfile(panelPerfilUser);
					setVisible(false);
				}
			});
	        panel_1.add(fotoPerfil,gfotoPerfil);
	        
	        JLabel nombre = new JLabel();
			nombre.setBounds(0,0,50,50);
			nombre.setText("   "+bdManager.SelectNombreUsuaruario(coments.get(i).getId_user()) +":  ");
			nombre.setHorizontalAlignment(SwingConstants.LEFT);
			nombre.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
			GridBagConstraints gnombre= new GridBagConstraints();
			gnombre .gridx = 2;
			gnombre .gridy = posicion+1;	
			nombre.setVisible(true);
			panel_1.add(nombre, gnombre);
	        
	        JLabel comentario = new JLabel();
			comentario.setBounds(0,0,50,50);
			comentario.setText("   "+coments.get(i).getContenido());
			comentario.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
			comentario.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gco= new GridBagConstraints();
			gco .gridx = 3;
			gco .gridy = posicion+1;	
			comentario.setVisible(true);
			panel_1.add(comentario, gco);
			
			boolean esElMismoUser = false;
			if(panelUser.getAdminsitrador() ==null)
			{
				esElMismoUser = coments.get(i).getId_user() == panelUser.getUsuario().getId();
			}
			if(esElMismoUser|| panelUser.getAdminsitrador() != null|| origen == 1)
			{
				JLabel borrar = new JLabel();
				borrar.setBounds(0, 0, 50,50);
				GridBagConstraints gborrar= new GridBagConstraints();
				gborrar .gridx = 4;
				gborrar .gridy = posicion+1;
				ImageIcon borrado = new ImageIcon("Imagenes/System/Image_74.png");
		        Image borradoEscalada = borrado.getImage().getScaledInstance(borrar.getWidth(),borrar.getHeight(), Image.SCALE_SMOOTH);
		        Icon borradoiconEscalado = new ImageIcon(borradoEscalada);
		        borrar.setIcon(borradoiconEscalado);
		        borrar.setName(i+"");
		        borrar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Object[] options = {"Aceptar",
			                    "Cancelar"};
						int n = JOptionPane.showOptionDialog(null,
						    "¿Deseas eliminar este comentario?",
						    "Eliminación",
						    JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,options,options[1]);
						
						if(n == 0) {
							panelUser.getBdManager().DeleteComentario(coments.get(new Integer(borrar.getName())));
							cBerriak= panelUser.getBdManager().SelectComentarios(foto.getCod());
							if(origen == 0) {
								casoInicio();
							}else {
								casoVisualizar();
							}
						}
					}
				});
			
				panel_1.add(borrar, gborrar);
			}
			
			posicion+=3;
		}
	}
	public void casoInicio() {
		PanelInicio inicio = new PanelInicio(panelUser);
		panelUser.getPanelInicio().getPanelComentario().setVisible(false);
		panelUser.setPanelInicio(inicio);
		PanelComentario nuevo = new PanelComentario(panelUser,cBerriak, 0, foto, null, null);
		panelUser.goToPanelInicio();
		panelUser.getPanelInicio().setPanelComentario(nuevo);
		panelUser.getPanelInicio().setVisible(false);
		panelUser.add(nuevo,BorderLayout.CENTER);
		nuevo.setVisible(true);
	}
	public void casoVisualizar() {
		PanelVisualizar visualizar = new PanelVisualizar(panelUser, foto, user, path);
		panelUser.getPanelPerfil().getPanelVisualizar().getPanelComentario().setVisible(false);
		panelUser.getPanelPerfil().setPanelVisualizar(visualizar);
		PanelComentario nuevo = new PanelComentario(panelUser,cBerriak, 1,foto, user,path);
		panelUser.getPanelPerfil().getPanelVisualizar().setPanelComentario(null);
		panelUser.getPanelPerfil().getPanelVisualizar().setPanelComentario(nuevo);
		panelUser.add(nuevo,BorderLayout.CENTER);
		panelUser.getPanelPerfil().getPanelVisualizar().getPanelComentario().setVisible(true);
	}

}
