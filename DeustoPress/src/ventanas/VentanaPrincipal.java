package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import SQLite.BDManager;
import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.Usuario;
import ventanas.panels.PanelAdmin;
import ventanas.panels.PanelUser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

//Mirar diferentes layouts (La de tres franjas puede estar bien), crear un panel con un color neutro para el login
//y para el registro (cuando se le de al boton que aparezcan las cosas del registro).
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
		BDManager bdManager = new BDManager();
		usuarios = bdManager.loadUsers();
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 418);
		
		panelLogin = new JLabel(new ImageIcon("Imagenes/System/Wallpaper.png"));
		panelLogin.setLayout(null);
		panelLogin.setVerticalAlignment(SwingConstants.NORTH);
		setContentPane(panelLogin);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignIn.setBackground(new Color(102, 204, 255));
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(150, 270, 102, 36);
		panelLogin.add(btnSignIn);
		
		JButton btnCancel = new JButton("Salir");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel.setBackground(new Color(102, 204, 255));
		btnCancel.setBounds(315, 270, 102, 36);
		panelLogin.add(btnCancel);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignUp.setBackground(new Color(102, 204, 255));
		btnSignUp.setBounds(480, 270, 102, 36);
		panelLogin.add(btnSignUp);
		
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
		addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				btnSignIn.setBounds(((getBounds().width-570)/2)+150, ((getBounds().height-418)/2)+270, 102, 36);
				btnCancel.setBounds(((getBounds().width-570)/2)+315, ((getBounds().height-418)/2)+270, 102, 36);
				btnSignUp.setBounds(((getBounds().width-570)/2)+480, ((getBounds().height-418)/2)+270, 102, 36);
				lblUsuario.setBounds(((getBounds().width-570)/2)+81, ((getBounds().height-418)/2)+134, 69, 20);
				passwordField.setBounds(((getBounds().width-570)/2)+183, ((getBounds().height-418)/2)+192, 218, 26);
				textField.setBounds(((getBounds().width-570)/2)+183, ((getBounds().height-418)/2)+131, 218, 26);
				lblPassword.setBounds(((getBounds().width-570)/2)+81, ((getBounds().height-418)/2)+195, 80, 20);
	

			}
		});
		
		btnSignIn.addActionListener(new ActionListener() 
		{
			@Override
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
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				VentanaPrincipal.this.dispose();
			}
		});
		
		btnSignUp.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
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
