package ventanas.panels;

import javax.swing.JPanel;

import interfaces.IPanelUsuarios;
import usuarios.Usuario;
import utilidades.Utilidades;
import ventanas.ModificarContraseña;
import ventanas.ModificarNombreReal;
import ventanas.ModificarNombreUsuario;
import ventanas.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog;

public class PanelModificarDatos extends JLabel
{
	
	public PanelModificarDatos(JLabel panel, VentanaPrincipal ventanaPrincipal) 
	{
		setIcon(new ImageIcon("Imagenes/Wallpaper.jpg"));
		setLayout(null);
		setBounds(100, 100, 548, 365);
		
		JLabel lblqueDatoDeseas = new JLabel("\u00BFQue dato deseas modificar?");
		lblqueDatoDeseas.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblqueDatoDeseas.setBounds(34, 40, 223, 20);
		add(lblqueDatoDeseas);
		
		JButton btnNombreDeUsuario = new JButton("Nombre de usuario");
		btnNombreDeUsuario.setBackground(new Color(102, 204, 255));
		btnNombreDeUsuario.setForeground(Color.WHITE);
		btnNombreDeUsuario.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnNombreDeUsuario.setBounds(180, 96, 182, 39);
		add(btnNombreDeUsuario);
		
		JButton btnContrasea = new JButton("Contrase\u00F1a");
		btnContrasea.setForeground(new Color(255, 255, 255));
		btnContrasea.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnContrasea.setBackground(new Color(102, 204, 255));
		btnContrasea.setBounds(180, 151, 182, 39);
		add(btnContrasea);
		
		JButton btnNombreReal = new JButton("Nombre real");
		btnNombreReal.setForeground(new Color(255, 255, 255));
		btnNombreReal.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnNombreReal.setBackground(new Color(102, 204, 255));
		btnNombreReal.setBounds(180, 206, 182, 39);
		add(btnNombreReal);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnAtras.setForeground(new Color(102, 204, 255));
		btnAtras.setBackground(Color.YELLOW);
		btnAtras.setBounds(331, 281, 101, 39);
		add(btnAtras);
		
		btnNombreDeUsuario.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ModificarNombreUsuario modificarNombreUsuario = new ModificarNombreUsuario(ventanaPrincipal);
				modificarNombreUsuario.setVisible(true);
			}
		});
		
		btnContrasea.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ModificarContraseña modificarContraseña = new ModificarContraseña(ventanaPrincipal);
				modificarContraseña.setVisible(true);
			}
		});
		
		btnNombreReal.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ModificarNombreReal modificarNombreReal = new ModificarNombreReal(ventanaPrincipal);
				modificarNombreReal.setVisible(true);
			}
		});
		
		btnAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				((IPanelUsuarios)panel).cargarDatos();
				ventanaPrincipal.setContentPane(panel);
				ventanaPrincipal.revalidate();
			}
		});
	}
}
