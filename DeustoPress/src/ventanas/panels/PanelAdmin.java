package ventanas.panels;

import javax.swing.JPanel;

import usuarios.Administrador;
import usuarios.Usuario;
import ventanas.BorrarUsuario;
import ventanas.RegistrarUsuario;
import ventanas.VentanaPrincipal;
import ventanas.VentanaVisualizarEntrada;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import interfaces.IPanelUsuarios;

public class PanelAdmin extends JLabel implements IPanelUsuarios
{
	private VentanaPrincipal ventanaPrincipal;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	
	public PanelAdmin(VentanaPrincipal ventana) 
	{
		ventanaPrincipal = ventana;
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		setIcon(new ImageIcon("Imagenes/wallpaper.jpg"));
		
		JLabel lblImage = new JLabel(new ImageIcon("Imagenes/Usuario.png"));
		lblImage.setBounds(445, 22, 54, 54);
		add(lblImage);
		
		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(102, 204, 255));
		btnModificarDatos.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnModificarDatos.setBounds(160, 77, 187, 38);
		add(btnModificarDatos);
		
		JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
		btnRegistrarUsuario.setForeground(Color.WHITE);
		btnRegistrarUsuario.setBackground(new Color(102, 204, 255));
		btnRegistrarUsuario.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnRegistrarUsuario.setBounds(160, 142, 187, 38);
		add(btnRegistrarUsuario);
		
		JButton btnBorrarUsuario = new JButton("Borrar Usuario");
		btnBorrarUsuario.setForeground(Color.WHITE);
		btnBorrarUsuario.setBackground(new Color(102, 204, 255));
		btnBorrarUsuario.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnBorrarUsuario.setBounds(160, 210, 187, 38);
		add(btnBorrarUsuario);
		
		JButton btnVisualizarEntradas = new JButton("Visualizar Entradas");
		btnVisualizarEntradas.setForeground(Color.WHITE);
		btnVisualizarEntradas.setBackground(new Color(102, 204, 255));
		btnVisualizarEntradas.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnVisualizarEntradas.setBounds(160, 274, 187, 38);
		add(btnVisualizarEntradas);
		
		JButton btnSalir = new JButton("Cerrar Sesion");
		btnSalir.setForeground(new Color(102, 204, 255));
		btnSalir.setBackground(Color.YELLOW);
		btnSalir.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnSalir.setBounds(419, 103, 110, 25);
		add(btnSalir);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(102, 204, 255));
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblNewLabel.setBounds(53, 10, 187, 20);
		add(lblNewLabel);
		
		JLabel lblqueDeseaHacer = new JLabel("\u00BFQue desea hacer?");
		lblqueDeseaHacer.setForeground(new Color(0, 0, 0));
		lblqueDeseaHacer.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblqueDeseaHacer.setBounds(83, 41, 137, 20);
		add(lblqueDeseaHacer);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(390, 68, 166, 20);
		add(lblNewLabel_1);
		
		cargarDatos();
		
		btnModificarDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				PanelModificarDatos panelModificarDatos = new PanelModificarDatos(PanelAdmin.this, ventanaPrincipal);
				ventanaPrincipal.setContentPane(panelModificarDatos);
				ventanaPrincipal.revalidate();
			}
		});
		
		btnRegistrarUsuario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegistrarUsuario registrarUsuario = new RegistrarUsuario(ventanaPrincipal);
				registrarUsuario.setVisible(true);
			}
		});
		
		btnBorrarUsuario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BorrarUsuario borrarUsuario = new BorrarUsuario(ventanaPrincipal);
				borrarUsuario.setVisible(true);
			}
		});
		
		btnVisualizarEntradas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				PanelVisualizarEntradas panelVisualizarEntradas = new PanelVisualizarEntradas(PanelAdmin.this, ventanaPrincipal);
				ventanaPrincipal.setContentPane(panelVisualizarEntradas);
				ventanaPrincipal.revalidate();
			}
		});
		
		btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ventanaPrincipal.setContentPane(ventanaPrincipal.getPanelLogin());
				ventanaPrincipal.revalidate();
			}
		});
	}

	@Override
	public void cargarDatos()
	{
		lblNewLabel_1.setText(ventanaPrincipal.getUsuario().getNombreUsuario());
		lblNewLabel.setText("Bienvenido: "+ventanaPrincipal.getUsuario().getNombreReal());
	}	
}
