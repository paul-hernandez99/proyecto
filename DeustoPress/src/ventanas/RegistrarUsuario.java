package ventanas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.Color;

public class RegistrarUsuario extends JDialog 
{
	private JLabel panelNombreUsuario = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JLabel panelContraseña = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JLabel panelNombreReal = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JLabel panelTipo = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JLabel panelTerminar = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private VentanaPrincipal ventanaPrincipal;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private String userName;
	private String password;
	private String nombreReal;

	public RegistrarUsuario(VentanaPrincipal ventana) 
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		ventanaPrincipal = ventana;
		setBounds(100, 100, 487, 252);
		
		panelContraseña.setLayout(null);
		panelNombreReal.setLayout(null);
		panelTipo.setLayout(null);
		panelTerminar.setLayout(null);
		panelNombreUsuario.setLayout(null);
		
		setContentPane(panelNombreUsuario);
		
		JLabel lblIntroduzcaElNombre = new JLabel("Introduzca el username del nuevo usuario:");
		lblIntroduzcaElNombre.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaElNombre.setBounds(78, 36, 325, 26);
		panelNombreUsuario.add(lblIntroduzcaElNombre);
		
		textField_3 = new JTextField();
		textField_3.setBounds(129, 78, 217, 26);
		panelNombreUsuario.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(new Color(102, 204, 255));
		btnNext.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnNext.setForeground(Color.WHITE);
		btnNext.setBounds(117, 135, 105, 29);
		panelNombreUsuario.add(btnNext);
		
		btnNext.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String usuario = textField_3.getText();
				try 
				{
					nombreUsuarioValido(usuario);
					userName = usuario;
					setContentPane(panelContraseña);
					revalidate();
					
				} 
				catch (Exceptions e1) 
				{
					JOptionPane.showMessageDialog(RegistrarUsuario.this, e1.getMessage());
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel.setForeground(new Color(102, 204, 255));
		btnCancel.setBounds(263, 135, 105, 29);
		panelNombreUsuario.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				RegistrarUsuario.this.dispose();
			}
		});

		JLabel lblIntroduzcaLaContrasea = new JLabel("Introduzca la contrase\u00F1a del nuevo usuario:");
		lblIntroduzcaLaContrasea.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaLaContrasea.setBounds(68, 36, 349, 26);
		panelContraseña.add(lblIntroduzcaLaContrasea);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 78, 217, 26);
		panelContraseña.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBackground(new Color(102, 204, 255));
		btnNewButton.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(117, 135, 105, 29);
		panelContraseña.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String contraseña = textField_1.getText();
				if(contraseña.equals("")||contraseña.contains(";"))
				{
					JOptionPane.showMessageDialog(RegistrarUsuario.this, "Contraseña no valida");
				}
				else
				{
					password = contraseña;
					setContentPane(panelNombreReal);
					revalidate();
				}
			}
		});
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.setBackground(Color.YELLOW);
		btnCancel_1.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel_1.setForeground(new Color(102, 204, 255));
		btnCancel_1.setBounds(263, 135, 105, 29);
		panelContraseña.add(btnCancel_1);
		
		btnCancel_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegistrarUsuario.this.dispose();
			}
		});

		JLabel lblIntroduzcaElNombre_1 = new JLabel("Introduzca el nombre real del usuario a registrar:");
		lblIntroduzcaElNombre_1.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaElNombre_1.setBounds(50, 36, 379, 26);
		panelNombreReal.add(lblIntroduzcaElNombre_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(129, 78, 217, 26);
		panelNombreReal.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnOk_1 = new JButton("Next");
		btnOk_1.setBackground(new Color(102, 204, 255));
		btnOk_1.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnOk_1.setForeground(Color.WHITE);
		btnOk_1.setBounds(117, 135, 105, 29);
		panelNombreReal.add(btnOk_1);
		
		btnOk_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String nombre = textField_2.getText();
				if(nombre.equals("")||nombre.contains(";"))
				{
					JOptionPane.showMessageDialog(RegistrarUsuario.this, "Nombre real no valido");
				}
				else
				{
					nombreReal = nombre;
					setContentPane(panelTipo);
					revalidate();
				}
			}
		});
		
		JButton btnCancel_2 = new JButton("Cancel");
		btnCancel_2.setBackground(Color.YELLOW);
		btnCancel_2.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel_2.setForeground(new Color(102, 204, 255));
		btnCancel_2.setBounds(263, 135, 105, 29);
		panelNombreReal.add(btnCancel_2);
		
		btnCancel_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegistrarUsuario.this.dispose();
			}
		});
	
		JLabel lblelUsuarioA = new JLabel("\u00BFEl usuario a registrar que tipo de usuario va a ser?");
		lblelUsuarioA.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblelUsuarioA.setBounds(40, 36, 401, 26);
		panelTipo.add(lblelUsuarioA);
		
		JButton btnAdministrador = new JButton("Administrador");
		btnAdministrador.setBackground(new Color(102, 204, 255));
		btnAdministrador.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnAdministrador.setForeground(Color.WHITE);
		btnAdministrador.setBounds(60, 90, 160, 29);
		panelTipo.add(btnAdministrador);
		
		btnAdministrador.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String fecha = Utilidades.fechaDeAlta();
				ventanaPrincipal.getUsuarios().add(new Administrador(userName,password, nombreReal, fecha));
				Utilidades.escribirEnFichero("usuarios.txt", ventanaPrincipal.getUsuarios());
				setContentPane(panelTerminar);
				revalidate();
			}
		});
		
		JButton btnUsuarioNormal = new JButton("Usuario Normal");
		btnUsuarioNormal.setBackground(new Color(102, 204, 255));
		btnUsuarioNormal.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnUsuarioNormal.setForeground(Color.WHITE);
		btnUsuarioNormal.setBounds(245, 90, 160, 29);
		panelTipo.add(btnUsuarioNormal);
		
		btnUsuarioNormal.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String fecha = Utilidades.fechaDeAlta();
				ventanaPrincipal.getUsuarios().add(new UsuarioNormal(userName, password, nombreReal, fecha));
				Utilidades.escribirEnFichero("Usuarios.txt", ventanaPrincipal.getUsuarios());
				setContentPane(panelTerminar);
				revalidate();
			}
		});
		
		JButton btnCancel_3 = new JButton("Cancel");
		btnCancel_3.setBackground(Color.YELLOW);
		btnCancel_3.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel_3.setForeground(new Color(102, 204, 255));
		btnCancel_3.setBounds(326, 151, 115, 29);
		panelTipo.add(btnCancel_3);
		
		btnCancel_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegistrarUsuario.this.dispose();
			}
		});
		
		JLabel lblTerminar = new JLabel("Usuario registrado exitosamente");
		lblTerminar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblTerminar.setBounds(102, 45, 295, 20);
		panelTerminar.add(lblTerminar);
		
		JButton btnTerminar = new JButton("Terminar");
		btnTerminar.setBackground(new Color(102, 204, 255));
		btnTerminar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnTerminar.setForeground(Color.WHITE);
		btnTerminar.setBounds(185, 106, 115, 29);
		panelTerminar.add(btnTerminar);
		
		btnTerminar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegistrarUsuario.this.dispose();
			}
		});
	}
	
	public void nombreUsuarioValido(String usuario) throws Exceptions
	{
		for(Usuario a: ventanaPrincipal.getUsuarios())
		{
			Usuario user = a;
			if(user.getNombreUsuario().equals(usuario))
			{
				throw new Exceptions("Nombre de usuario existente");
			}
		}
		if(usuario.contains(";")||usuario.isEmpty())
		{
			throw new Exceptions("Nombre de usuario no valido");
		}
	}
}
