package ventanas.panels;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import foto.Foto;
import usuarios.Usuario;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;

public class PanelVisualizar extends JPanel
{
	private PanelUser panelUser;

	public PanelVisualizar(PanelUser panel, Foto foto, Usuario user)
	{
		this.panelUser=panel;
		
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
					
			JLabel adaptador= new JLabel();
			adaptador.setBounds(0,0,10,10);
			
			GridBagConstraints gadaptador = new GridBagConstraints();
			gadaptador .gridx =  0;
			gadaptador .gridy = 0;
			add(adaptador,gadaptador);
			
			JLabel fotoPerfil= new JLabel();
			fotoPerfil.setBounds(0,0,50,50);
			GridBagConstraints gfotoPerfil = new GridBagConstraints();
			gfotoPerfil .gridx =  1;
			gfotoPerfil .gridy = 1;
			ImageIcon fotoIcon = new ImageIcon(panelUser.getFotos_perfil().get(foto.getId_user()).getPath());
	        Image fotoEscalada = fotoIcon.getImage().getScaledInstance(fotoPerfil.getWidth(),fotoPerfil.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconEscalado = new ImageIcon(fotoEscalada);
	        fotoPerfil.setIcon(iconEscalado);
	        
	        this.add(fotoPerfil,gfotoPerfil);
			
			JLabel nombre = new JLabel();
			nombre.setBounds(0,0,50,50);
			nombre.setText("   "+user.getNombreUsuario() +"");
			nombre.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gnombre= new GridBagConstraints();
			gnombre .gridx = 2;
			gnombre .gridy = +1;	
			nombre.setVisible(true);
			this.add(nombre, gnombre);
			
			JLabel imagen = new JLabel();
			imagen.setBounds(0,0,400,400);
			GridBagConstraints gimagen = new GridBagConstraints();
			gimagen .gridx = 3;
			gimagen .gridy = 2;
			ImageIcon imgIcon = new ImageIcon(foto.getPath());
	        Image imgEscalada = imgIcon.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconoEscalado = new ImageIcon(imgEscalada);
	        imagen.setIcon(iconoEscalado);
	        this.add(imagen, gimagen);      
	}
}