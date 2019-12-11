package ventanas.panels;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SQLite.BDManager;

public class PanelInicio extends JPanel
{
	private PanelUser panelUser;
	private BDManager bdManager;
	
	public PanelInicio(PanelUser panel)
	{
		this.panelUser = panel;
		bdManager = panelUser.getBdManager();
		
		for(int i=0; i<panelUser.getFotos_inicio().size(); i++)
		{
			JLabel imagen = new JLabel();
			imagen.setSize(175, 175);
			
			ImageIcon imgIcon = new ImageIcon(panelUser.getFotos_inicio().get(i).getPath());
	        Image imgEscalada = imgIcon.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_SMOOTH);
	        Icon iconoEscalado = new ImageIcon(imgEscalada);
	        imagen.setIcon(iconoEscalado);
			this.add(imagen);
		}
	}
}
