package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import SQLite.BDManager;
import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import ventanas.panels.PanelAdmin;
import ventanas.panels.PanelUser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

public class VentanaPrincipal extends JFrame 
{
	private BDManager bdManager;
	
	private ArrayList<Usuario> usuarios;
	private Usuario usuario;
	
	private JPanel panel;
	private JPanel contentPane;
	
	private JScrollPane jScrollPane;
	
	private JLabel logo;
	private JLabel intro;
	private JLabel info;
	private JLabel infoFecha;
	
	private JTextField username;
	private JPasswordField password;
	private JTextField name;
	private JTextField apellidos;
	private JTextField email;
	private JTextField day;
	private JTextField month;
	private JTextField year;
	
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JButton btnExit;
	private JButton btnConfirm;
	private JButton btnBack;
	
	private boolean singUpMenu = false;
	
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
		bdManager = new BDManager();
		usuarios = bdManager.loadUsers();
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(670, 60, 600, 920);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.createPanels();
		this.createIcon();
		this.createJlabels();
		this.createJtextfields();
		this.createButtons();
		this.setElementsSizePosition();
		
	}
	
	private void createPanels()
	{
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(new Color(153, 240, 153),3);
		panel.setBorder(border);
		panel.setLayout(null);
		contentPane.add(panel);
		
		jScrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(jScrollPane);
	}
	
	private void createIcon()
	{
		logo = new JLabel(new ImageIcon("Imagenes/System/Wallpaper.png"));
		contentPane.add(logo);
		
	}
	
	private void createJlabels()
	{
		intro = new JLabel("Welcome to Beermeet:");
		intro.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		contentPane.add(intro);

		info = new JLabel("Login:", SwingConstants.CENTER);
		info.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		info.setBounds(100, 10, 150, 30);
		panel.add(info);
		
		infoFecha = new JLabel("*Enter your birth date (dd-mm-yyyy)");
		infoFecha.setFont(new Font("Gill Sans MT", Font.BOLD,10));
		infoFecha.setVisible(false);
		infoFecha.setBounds(65, 284, 180, 10);
		panel.add(infoFecha);
	}
	
	private void createJtextfields()
	{
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setFocusable(false);
		username.setText("Username");
		username.setBounds(65, 50, 220, 30);
		panel.add(username);
		
		username.addMouseMotionListener(new MouseMotionListener() 
		{
			
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				username.setFocusable(true);
			}

			@Override
			public void mouseDragged(MouseEvent e) {}
			
		});
		
		username.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				username.setText("");
			}
		});
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.ITALIC, 16));
		password.setFocusable(false);
		password.setEchoChar((char)0);
		password.setText("Password");
		password.setBounds(65, 100, 220, 30);
		panel.add(password);

		password.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				password.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		password.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent arg0) {}
			
			@Override
			public void focusGained(FocusEvent arg0) 
			{
				password.setText("");
				password.setEchoChar('*');
			}
		});
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.ITALIC, 16));
		name.setVisible(false);
		name.setText("Name");
		name.setBounds(65, 150, 220, 30);
		panel.add(name);
		
		name.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				name.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		name.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				name.setText("");
			}
		});
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.ITALIC, 16));
		email.setVisible(false);
		email.setText("Email");
		email.setBounds(65, 200, 220, 30);
		panel.add(email);
		
		email.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				email.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		email.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				email.setText("");
			}
		});
		
		day = new JTextField();
		day.setFont(new Font("Tahoma", Font.ITALIC, 15));
		day.setVisible(false);
		day.setText("Day");
		day.setBounds(65, 250, 40, 30);
		panel.add(day);
		
		day.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				day.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		day.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				day.setText("");
			}
		});
		
		month = new JTextField();
		month.setFont(new Font("Tahoma", Font.ITALIC, 12));
		month.setVisible(false);
		month.setText("Month");
		month.setBounds(155, 250, 40, 30);
		panel.add(month);
		
		month.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				month.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		month.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				month.setText("");
			}
		});
		
		year = new JTextField();
		year.setFont(new Font("Tahoma", Font.ITALIC, 15));
		year.setVisible(false);
		year.setText("Year");
		year.setBounds(245, 250, 40, 30);
		panel.add(year);
		
		year.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				year.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		year.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				year.setText("");
			}
		});
	
	}
	
	private void createButtons()
	{
		btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignIn.setBackground(new Color(255, 102, 102));
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setFocusable(false);
		btnSignIn.setBounds(125, 180, 100, 40);
		panel.add(btnSignIn);
		
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
		btnSignUp.setFocusable(false);
		btnSignUp.setBounds(125, 240, 100, 40);
		panel.add(btnSignUp);
		
		btnSignUp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				registrationMenuVisible();
			}
		});
		
		btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnExit.setBackground(new Color(153, 240, 153));
		btnExit.setFocusable(false);
		btnExit.setBounds(125, 300, 100, 40);
		panel.add(btnExit);
		
		btnExit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				VentanaPrincipal.this.dispose();
			}
		});
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnConfirm.setBackground(new Color(255, 102, 102));
		btnConfirm.setFocusable(false);
		btnConfirm.setBounds(125, 320, 100, 40);
		btnConfirm.setVisible(false);
		panel.add(btnConfirm);
		
		btnConfirm.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				createUser();
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnBack.setBackground(new Color(153, 240, 153));
		btnBack.setFocusable(false);
		btnBack.setBounds(125, 380, 100, 40);
		btnBack.setVisible(false);
		panel.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				loginMenuVisible();
			}
		});
	}
	
	private void setElementsSizePosition()
	{
		contentPane.addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent evt)
			{
				if(!singUpMenu)
				{
					panel.setBounds(((contentPane.getBounds().width)/2)-175, 405, 350, 360);
				}
				else
					panel.setBounds(((contentPane.getBounds().width)/2)-175, 405, 350, 440);
				
				logo.setBounds(((contentPane.getBounds().width)/2)-185, 0, 370, 370);
				intro.setBounds(((contentPane.getBounds().width)/2)-115, 350, 230, 30);
			}
		});
		
	}
	
	private void createUser()
	{
		String username = this.username.getText();
		String password = this.password.getText();
		String name = this.name.getText();
		String apellidos = this.apellidos.getText();
		String email = this.email.getText();
		String fecNac = this.day.getText() +"-"+ this.month.getText() +"-"+ this.year.getText();
		
		UsuarioNormal usuario = new UsuarioNormal(username, password, name, apellidos, email, fecNac);
		
		bdManager.saveUser(usuario);
	}
	
	private void registrationMenuVisible()
	{
		singUpMenu = true;
		
		btnSignIn.setVisible(false);
		btnSignUp.setVisible(false);
		btnExit.setVisible(false);
		
		
		btnConfirm.setVisible(true);
		btnBack.setVisible(true);
		
		infoFecha.setVisible(true);
		info.setText("Registration info:");
		name.setVisible(true);
		email.setVisible(true);
		day.setVisible(true);
		month.setVisible(true);
		year.setVisible(true);
		
		setTexts();
		
		panel.setBounds(((contentPane.getBounds().width)/2)-175, 405, 350, 440);
	}
	
	private void loginMenuVisible()
	{
		singUpMenu = false;
		
		btnSignIn.setVisible(true);
		btnSignUp.setVisible(true);
		btnExit.setVisible(true);
		
		btnConfirm.setVisible(false);
		btnBack.setVisible(false);
		
		infoFecha.setVisible(false);
		info.setText("Login:");
		name.setVisible(false);
		email.setVisible(false);
		day.setVisible(false);
		month.setVisible(false);
		year.setVisible(false);
		
		panel.setBounds(((contentPane.getBounds().width)/2)-175, 405, 350, 360);
		
		setTexts();
	}
	
	private void setTexts()
	{
		username.setText("Username");
		password.setText("Password");
		password.setEchoChar((char)0);
		name.setText("Name");
		email.setText("Email");
		day.setText("Day");
		month.setText("Month");
		year.setText("Year");
		
		username.setFocusable(false);
		password.setFocusable(false);
		name.setFocusable(false);
		email.setFocusable(false);
		day.setFocusable(false);
		month.setFocusable(false);
		year.setFocusable(false);
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
		this.password.setText("Password");
		this.password.setEchoChar((char)0);
		this.password.setFocusable(false);
		
		if(!encontrado)
		{
			username.setText("Username");
			username.setFocusable(false);
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
		return panel;
	}

	public void setPanel1(JPanel panel1) 
	{
		this.panel = panel1;
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

	public void setPassword(JPasswordField password) 
	{
		this.password = password;
	}
	
}
