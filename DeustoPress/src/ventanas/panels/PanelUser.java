package ventanas.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import foto.Foto;
import interfaces.IPanelUsuarios;
import ventanas.CrearEntrada;
import ventanas.VentanaPrincipal;

public class PanelUser extends JLabel implements IPanelUsuarios
{
	private VentanaPrincipal ventanaPrincipal;
	
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	
	private ArrayList<Foto> fotos_inicio;
	private ArrayList<Foto> fotos_perfil;
	private ArrayList<Foto> fotos_usuarios;
	
	public PanelUser(VentanaPrincipal ventana) 
	{
		ventanaPrincipal = ventana;
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		setIcon(new ImageIcon("Imagenes/Wallpaper.jpg"));
		
		JLabel lblImage = new JLabel(new ImageIcon("Imagenes/Usuario.png"));
		lblImage.setBounds(445, 22, 54, 54);
		add(lblImage);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(390, 68, 166, 20);
		add(lblNewLabel_1);
		
		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(102, 204, 255));
		btnModificarDatos.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnModificarDatos.setBounds(160, 115, 187, 38);
		add(btnModificarDatos);
		
		JButton btnCrearEntrada = new JButton("Crear Entrada");
		btnCrearEntrada.setForeground(Color.WHITE);
		btnCrearEntrada.setBackground(new Color(102, 204, 255));
		btnCrearEntrada.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCrearEntrada.setBounds(160, 185, 187, 38);
		add(btnCrearEntrada);
		
		JButton btnVisualizarEntradas = new JButton("Visualizar Entradas");
		btnVisualizarEntradas.setForeground(Color.WHITE);
		btnVisualizarEntradas.setBackground(new Color(102, 204, 255));
		btnVisualizarEntradas.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnVisualizarEntradas.setBounds(160, 255, 187, 38);
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
		lblqueDeseaHacer.setBounds(82, 50, 137, 20);
		add(lblqueDeseaHacer);
		
		cargarDatos();
		
		btnModificarDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				PanelModificarDatos panelModificarDatos = new PanelModificarDatos(PanelUser.this, ventanaPrincipal);
				ventanaPrincipal.setContentPane(panelModificarDatos);
				ventanaPrincipal.revalidate();
			}
		});
		
		btnCrearEntrada.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				CrearEntrada crearEntrada = new CrearEntrada(ventanaPrincipal);
				crearEntrada.setVisible(true);
			}
		});
		
		btnVisualizarEntradas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				PanelVisualizarEntradas panelVisualizarEntradas = new PanelVisualizarEntradas(PanelUser.this, ventanaPrincipal);
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
