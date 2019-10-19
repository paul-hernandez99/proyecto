package ventanas;

import java.awt.EventQueue;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SQLite.SelectData;
import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.panels.PanelAdmin;
import ventanas.panels.PanelUser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class VentanaPrincipal extends JFrame 
{
	private JTextField textField;
	private JPasswordField passwordField;
	private ArrayList<Usuario> usuarios;
	private Usuario usuario;
	private JLabel panelLogin;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() 
	{
		usuarios = Utilidades.leerUsuarios("Usuarios.txt");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 418);
	
		JLabel lblimage = new JLabel(new ImageIcon("Imagenes/System/deusto.png"));
		panelLogin = new JLabel(new ImageIcon("Imagenes/System/Wallpaper.jpg"));
		panelLogin.setLayout(null);
		lblimage.setBounds(256, 16, 277, 75);
		panelLogin.add(lblimage);
		setContentPane(panelLogin);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnOk.setBackground(new Color(102, 204, 255));
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setBounds(150, 270, 102, 36);
		panelLogin.add(btnOk);
		
		JButton btnCancel = new JButton("Salir");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel.setBackground(new Color(102, 204, 255));
		btnCancel.setBounds(315, 270, 102, 36);
		panelLogin.add(btnCancel);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblUsuario.setBounds(81, 134, 69, 20);
		panelLogin.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblPassword.setBounds(81, 195, 80, 20);
		panelLogin.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(183, 131, 218, 26);
		panelLogin.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 192, 218, 26);
		panelLogin.add(passwordField);
		
		JLabel lblBienvenidoADeustopress = new JLabel("Bienvenido a DeustoPress:");
		lblBienvenidoADeustopress.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblBienvenidoADeustopress.setBounds(42, 48, 210, 20);
		panelLogin.add(lblBienvenidoADeustopress);
		
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String user = textField.getText();
				String pass = passwordField.getText();
				
				try 
				{
					comprobarUsuario(user, pass);
					textField.setText(null);
					passwordField.setText(null);
					for (Usuario a : usuarios) 
					{
						if (a.getContraseña().equals(pass))
						{
							usuario = a;
							break;
						}
					}
					if (usuario instanceof Administrador) 
					{
						PanelAdmin panelAdmin = new PanelAdmin(VentanaPrincipal.this);
						setContentPane(panelAdmin);
						revalidate();
					} 
					else 
					{
						PanelUser panelUser = new PanelUser(VentanaPrincipal.this);
						setContentPane(panelUser);
						revalidate();
					}
					
				} 
				catch (Exceptions e) 
				{
					JOptionPane.showMessageDialog(VentanaPrincipal.this, e.getMessage());
				}
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				VentanaPrincipal.this.dispose();
			}
		});
	}
	
	public void comprobarUsuario(String usuario, String password) throws Exceptions
	{
		boolean encontrado = false;
		Usuario user = null;
		for(Usuario a: usuarios)
		{
			if(a.getNombreUsuario().equals(usuario))
			{
				encontrado = true;
				user = a;
				break;
			}
		}
		if(!encontrado)
		{
			textField.setText("");
			passwordField.setText("");
			throw new Exceptions("Usuario no existente");
		}
		else
		{
			if(!user.getContraseña().equals(password))
			{
				passwordField.setText("");
				throw new Exceptions("Contraseña incorrecta");
			}
		}
	}
	
	public void cargarUsuarios()
	{
		SelectData selectdata = new SelectData();
		
		usuarios = selectdata.selectUsers();
	}

	public JLabel getPanelLogin() 
	{
		return panelLogin;
	}

	public void setPanelLogin(JLabel panelLogin) 
	{
		this.panelLogin = panelLogin;
	}

	public ArrayList<Usuario> getUsuarios() 
	{
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) 
	{
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() 
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}
	
}
