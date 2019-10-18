package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptions.Exceptions;
import usuarios.Usuario;
import utilidades.Utilidades;
import ventanas.panels.PanelAdmin;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ModificarNombreUsuario extends JDialog
{

	private JLabel contentPanel = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JTextField textField;
	private VentanaPrincipal ventanaPrincipal;

	public ModificarNombreUsuario(VentanaPrincipal ventana) 
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		ventanaPrincipal = ventana;
		
		setBounds(100, 100, 448, 239);
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textField.setBounds(118, 79, 185, 26);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblIntroduzcaElNuevo = new JLabel("Introduzca el nuevo nombre de usuario:");
		lblIntroduzcaElNuevo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaElNuevo.setBounds(51, 35, 321, 20);
		contentPanel.add(lblIntroduzcaElNuevo);

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
				String usuario = textField.getText();
				try 
				{
					NombreUsuarioExistente(usuario);
					ventanaPrincipal.getUsuario().setNombreUsuario(usuario);
					Utilidades.escribirEnFichero("Usuarios.txt", ventanaPrincipal.getUsuarios());
					ModificarNombreUsuario.this.dispose();
					
				}
				catch (Exceptions e) 
				{
					JOptionPane.showMessageDialog(ModificarNombreUsuario.this, e.getMessage());
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
				ModificarNombreUsuario.this.dispose();
			}
		});
	}

	public void NombreUsuarioExistente(String usuario) throws Exceptions 
	{
		if (usuario.equals("") || usuario.contains(";")) 
		{
			throw new Exceptions("Nombre de usuario no valido");
		}
		String nombre = ventanaPrincipal.getUsuario().getNombreUsuario();
		ventanaPrincipal.getUsuario().setNombreUsuario("");
		for (Usuario a : ventanaPrincipal.getUsuarios()) 
		{
			if (a.getNombreUsuario().equals(usuario)) 
			{
				ventanaPrincipal.getUsuario().setNombreUsuario(nombre);
				throw new Exceptions("Nombre de usuario existente");
			}
		}
	}
}
