package ventanas.panels;

import ventanas.VentanaPrincipal;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
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
		
		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(102, 204, 255));
		btnModificarDatos.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnModificarDatos.setBounds(160, 77, 187, 38);
		add(btnModificarDatos);
		
		JButton btnRegistrarAdmin = new JButton("Registrar Admin");
		btnRegistrarAdmin.setForeground(Color.WHITE);
		btnRegistrarAdmin.setBackground(new Color(102, 204, 255));
		btnRegistrarAdmin.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnRegistrarAdmin.setBounds(160, 142, 187, 38);
		add(btnRegistrarAdmin);
		
		JButton btnBorrarUsuario = new JButton("Borrar Usuario");
		btnBorrarUsuario.setForeground(Color.WHITE);
		btnBorrarUsuario.setBackground(new Color(102, 204, 255));
		btnBorrarUsuario.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnBorrarUsuario.setBounds(160, 210, 187, 38);
		add(btnBorrarUsuario);
		
		JButton btnVisualizarFotos = new JButton("Visualizar Fotos");
		btnVisualizarFotos.setForeground(Color.WHITE);
		btnVisualizarFotos.setBackground(new Color(102, 204, 255));
		btnVisualizarFotos.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnVisualizarFotos.setBounds(160, 274, 187, 38);
		add(btnVisualizarFotos);
		
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
				
			}
		});
		
		btnRegistrarAdmin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		btnBorrarUsuario.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		
		btnVisualizarFotos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ventanaPrincipal.setContentPane(ventanaPrincipal.getPanel_principal());
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
