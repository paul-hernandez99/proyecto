package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilidades.Utilidades;
import javax.swing.JTextField;

public class ModificarNombreReal extends JDialog
{
	private JLabel contentPanel = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private VentanaPrincipal ventanaPrincipal;
	private JTextField textField;

	public ModificarNombreReal(VentanaPrincipal ventana) 
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		ventanaPrincipal = ventana;
		setBounds(100, 100, 448, 239);
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblIntroduzcaElNuevo = new JLabel("Introduzca el nuevo nombre real:");
		lblIntroduzcaElNuevo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaElNuevo.setBounds(87, 35, 271, 20);
		contentPanel.add(lblIntroduzcaElNuevo);
		
		textField = new JTextField();
		textField.setBounds(118, 79, 196, 26);
		contentPanel.add(textField);
			
		JButton okButton = new JButton("Modificar");
		okButton.setBackground(new Color(102, 204, 255));
		okButton.setForeground(Color.WHITE);
		okButton.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		okButton.setBounds(73, 131, 116, 26);
		contentPanel.add(okButton);

		okButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String nombreReal = textField.getText();
				if (nombreReal.equals("") || nombreReal.contains(";")) 
				{
					JOptionPane.showMessageDialog(ModificarNombreReal.this, "Nombre real no valido");
				} 
				else 
				{
					ventanaPrincipal.getUsuario().setNombreReal(nombreReal);
					Utilidades.escribirEnFichero("Usuarios.txt", ventanaPrincipal.getUsuarios());
					ModificarNombreReal.this.dispose();
				}
			}
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setBackground(Color.YELLOW);
		cancelButton.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		cancelButton.setForeground(new Color(102, 204, 255));
		cancelButton.setBounds(228, 131, 116, 26);
		contentPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ModificarNombreReal.this.dispose();
			}
		});
	}
}
