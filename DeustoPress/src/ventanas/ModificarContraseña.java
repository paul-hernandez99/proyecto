package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exceptions.Exceptions;
import usuarios.Usuario;
import utilidades.Utilidades;
import ventanas.panels.PanelAdmin;
import javax.swing.JPasswordField;

public class ModificarContraseña extends JDialog 
{
	private final JLabel contentPanel = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private VentanaPrincipal ventanaPrincipal;
	private JPasswordField passwordField;

	public ModificarContraseña(VentanaPrincipal ventana) 
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		ventanaPrincipal = ventana;
		setBounds(100, 100, 448, 239);
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JLabel lblIntroduzcaElNuevo = new JLabel("Introduzca la nueva contrase\u00F1a:");
		lblIntroduzcaElNuevo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaElNuevo.setBounds(87, 35, 271, 20);
		contentPanel.add(lblIntroduzcaElNuevo);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 79, 185, 26);
		contentPanel.add(passwordField);

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
				String contraseña = passwordField.getText();
				if (contraseña.equals("") || contraseña.contains(";"))
				{
					JOptionPane.showMessageDialog(ModificarContraseña.this, "Contraseña no valida");
				}
				else 
				{
					ventanaPrincipal.getUsuario().setContraseña(contraseña);
					Utilidades.escribirEnFichero("Usuarios.txt", ventanaPrincipal.getUsuarios());
					ModificarContraseña.this.dispose();
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
				ModificarContraseña.this.dispose();
			}
		});
	}
}