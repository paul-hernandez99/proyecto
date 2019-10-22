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
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

//Mirar diferentes layouts (La de tres franjas puede estar bien), crear un panel con un color neutro para el login
//y para el registro (cuando se le de al boton que aparezcan las cosas del registro).


public class VentanaPrincipal extends JFrame 
{
	private ArrayList<Usuario> usuarios;
	private Usuario usuario;
	
	private JPanel panelLogin;
	private JPanel panel1;
	private JLabel logo;
	private JLabel intro;
	
	private JTextField username;
	private JTextField password;
	
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JButton btnCancel;
	
	private TextField dia;
	private TextField mes;
	private TextField year;
	
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
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(670, 60, 600, 900);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.createPanel();
		this.createIcon();
		this.createIntro();
		this.createJtextfields();
		this.createButtons();
		this.aux();
		
	}
	
	private void createPanel()
	{
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(new Color(153, 240, 153),3);
		panel1.setBorder(border);
		panel1.setLayout(null);
		this.add(panel1);
	}
	
	private void createIcon()
	{
		logo = new JLabel(new ImageIcon("Imagenes/System/Wallpaper.png"));
		this.add(logo);
		logo.requestFocusInWindow();
		
	}
	
	private void createIntro()
	{
		intro = new JLabel("Welcome to Beermeet:");
		intro.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		this.add(intro);
	}
	
	private void createJtextfields()
	{
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setEnabled(false);
		username.setText("Username");
		panel1.add(username);
		
		username.add
		
		username.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				username.setText("");
			}
		});
		
		password = new JTextField();
		password.setFont(new Font("Tahoma", Font.ITALIC, 16));
		password.setText("Password");
		panel1.add(password);
		
	}
	
	private void createButtons()
	{
		btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignIn.setBackground(new Color(255, 102, 102));
		btnSignIn.setForeground(new Color(255, 255, 255));
		panel1.add(btnSignIn);
		
		btnSignIn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String user = username.getText();
				String pass = password.getText();
				
				try 
				{
					comprobarUsuario(user, pass);
					username.setText(null);
					password.setText(null);
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
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignUp.setBackground(new Color(255, 102, 102));
		panel1.add(btnSignUp);
		
		btnSignUp.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		btnCancel = new JButton("Exit");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel.setBackground(new Color(153, 240, 153));
		panel1.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				VentanaPrincipal.this.dispose();
			}
		});
	}
	
	private void aux()
	{
		dia = new TextField();
		dia.setFont(new Font("Tahoma", Font.ITALIC, 16));
		dia.setText("day");
		
		mes = new TextField();
		mes.setFont(new Font("Tahoma", Font.ITALIC, 16));
		mes.setText("month");
		
		year = new TextField();
		year.setFont(new Font("Tahoma", Font.ITALIC, 16));
		year.setText("year");
		
		this.addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent evt)
			{
				panel1.setBounds(((getBounds().width)/2)-175, 445, 350, 360);
				logo.setBounds(((getBounds().width)/2)-185, 0, 370, 370);
				intro.setBounds(((getBounds().width)/2)-115, 390, 230, 30);
				username.setBounds(((panel1.getBounds().width)/2)-110, 40, 220, 30);
				password.setBounds(((panel1.getBounds().width)/2)-110, 100, 220, 30);
				btnSignIn.setBounds(((panel1.getBounds().width)/2)-50, 180, 100, 40);
				btnSignUp.setBounds(((panel1.getBounds().width)/2)-50, 240, 100, 40);
				btnCancel.setBounds(((panel1.getBounds().width)/2)-50, 300, 100, 40);
			}
		});
		
	}
	
	private void comprobarUsuario(String usuario, String password) throws Exceptions
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
			username.setText("");
			this.password.setText("");
			throw new Exceptions("Usuario no existente");
		}
		else
		{
			if(!user.getContraseña().equals(password))
			{
				this.password.setText("");
				throw new Exceptions("Contraseña incorrecta");
			}
		}
	}


	public JPanel getPanelLogin() 
	{
		return panelLogin;
	}

	public void setPanelLogin(JPanel panelLogin) 
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

	public JPanel getPanel1() 
	{
		return panel1;
	}

	public void setPanel1(JPanel panel1) 
	{
		this.panel1 = panel1;
	}

	public JLabel getLogo() 
	{
		return logo;
	}

	public void setLogo(JLabel logo) 
	{
		this.logo = logo;
	}

	public JLabel getIntro() 
	{
		return intro;
	}

	public void setIntro(JLabel intro) 
	{
		this.intro = intro;
	}

	public JTextField getUsername() 
	{
		return username;
	}

	public void setUsername(JTextField username) 
	{
		this.username = username;
	}

	public JTextField getPassword() 
	{
		return password;
	}

	public void setPassword(JTextField password) 
	{
		this.password = password;
	}

	public JButton getBtnSignIn() 
	{
		return btnSignIn;
	}

	public void setBtnSignIn(JButton btnSignIn) 
	{
		this.btnSignIn = btnSignIn;
	}

	public JButton getBtnSignUp() 
	{
		return btnSignUp;
	}

	public void setBtnSignUp(JButton btnSignUp) 
	{
		this.btnSignUp = btnSignUp;
	}

	public JButton getBtnCancel() 
	{
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) 
	{
		this.btnCancel = btnCancel;
	}

	public TextField getDia() 
	{
		return dia;
	}

	public void setDia(TextField dia) 
	{
		this.dia = dia;
	}

	public TextField getMes() 
	{
		return mes;
	}

	public void setMes(TextField mes) 
	{
		this.mes = mes;
	}

	public TextField getYear() 
	{
		return year;
	}

	public void setYear(TextField year) 
	{
		this.year = year;
	}
	
	
	
}
