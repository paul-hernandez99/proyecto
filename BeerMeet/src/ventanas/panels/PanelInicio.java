package ventanas.panels;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SQLite.BDManager;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PanelInicio extends JPanel
{
	private PanelUser panelUser;
	private BDManager bdManager;
	
	public PanelInicio(PanelUser panel)
	{
		this.panelUser=panel;
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(panel_1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0,0,560,790);
		add(scrollPane);
		scrollPane.setViewportView(panel_1);
		
		bdManager = panelUser.getBdManager();
		int posicion=25;
		
		for(int i=0; i<panelUser.getFotos_inicio().size(); i++)
		{
			JLabel fotoPerfil= new JLabel();
			fotoPerfil.setBounds(25,posicion, 50, 50);
			ImageIcon foto = new ImageIcon(panelUser.getFotos_perfil().get(panelUser.getFotos_inicio().get(i).getId_user()).getPath());
	        Image fotoEscalada = foto.getImage().getScaledInstance(fotoPerfil.getWidth(),fotoPerfil.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconEscalado = new ImageIcon(fotoEscalada);
	        fotoPerfil.setIcon(iconEscalado);
	        panel_1.add(fotoPerfil);
			
			JLabel nombre = new JLabel();
			nombre.setText(bdManager.SelectNombreUsuaruario(panelUser.getFotos_inicio().get(i).getId_user()) +"");
			nombre.setHorizontalAlignment(SwingConstants.CENTER);
		
			nombre.setBounds(80, posicion, 100, 50);
			posicion+=60;			
			nombre.setVisible(true);
			panel_1.add(nombre);
			
			JLabel imagen = new JLabel();
			imagen.setBounds(0, posicion,400,400);
			ImageIcon imgIcon = new ImageIcon(panelUser.getFotos_inicio().get(i).getPath());
	        Image imgEscalada = imgIcon.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconoEscalado = new ImageIcon(imgEscalada);
	        imagen.setIcon(iconoEscalado);
	        panel_1.add(imagen);
	        posicion+=410;
		}
	}
}
