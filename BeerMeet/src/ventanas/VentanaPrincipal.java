package ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import SQLite.BDManager;
import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import ventanas.panels.PanelRecuContraseña;
import ventanas.panels.PanelUser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

/**Estamos ante la clase que define la ventana con la que el usuario podrá interactuar con la aplicación y realizar 
 * las distintas funciones que se le ofrecen.
*@author aritz eraun y Paul Hernandez*/

public class VentanaPrincipal extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private BDManager bdManager;
	
	private ArrayList<Usuario> usuarios;
	private Usuario usuario;
	
	private JPanel panel;
	private JPanel panel_principal;
	
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
	private JLabel lblhasOlvidadoTu = new JLabel("\u00BFHas olvidado tu contrase\u00F1a?");
	
	private boolean singUpMenu = false;
	
	/**Este método es el método MAIN, el cual pone en funcionamiento toda la aplicación
	 * @param  args : pilla del programa.*/
	
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
	
	/**Este métoo crea el primer panel, el cual da la bienvenida al usuario.*/
	
	public VentanaPrincipal() 
	{
		bdManager = new BDManager(false);
		usuarios = bdManager.loadUsers();
		this.setBounds(670, 60, 600, 920);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.createPanels();
		this.createIcon();
		this.createJlabels();
		this.createJtextfields();
		this.createButtons();
		this.setElementsSizePosition();
		
		this.setTexts();
		this.loginMenuVisible();
	}
	
	/**Este método pone en funcionaminto el layout elegido*/
	
	private void createPanels()
	{
		panel_principal = new JPanel();
		panel_principal.setBackground(Color.WHITE);
		panel_principal.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(new Color(153, 240, 153),3);
		panel.setBorder(border);
		panel.setLayout(null);
		panel_principal.add(panel);
		
		this.add(panel_principal);
	}
	
	/***Este método crea el icono del fondo de la aplicación.*/
	
	private void createIcon()
	{
		logo = new JLabel(new ImageIcon("Imagenes/System/Wallpaper.png"));
		panel_principal.add(logo);
		
	}
	/***Este método crea los distintos Labels visibles en el panel principal.*/
	
	public  void createJlabels()
	{
		intro = new JLabel("Welcome to Beermeet:");
		intro.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		panel_principal.add(intro);

		info = new JLabel("Login:", SwingConstants.CENTER);
		info.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		info.setBounds(100, 10, 150, 30);
		panel.add(info);
		
		infoFecha = new JLabel("*Enter your birth date (dd-mm-yyyy)");
		infoFecha.setFont(new Font("Gill Sans MT", Font.BOLD,10));
		infoFecha.setBounds(65, 284, 180, 10);
		panel.add(infoFecha);
		
		lblhasOlvidadoTu.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				goToPanelRecuContraseña();
			}
		});
		
		lblhasOlvidadoTu.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblhasOlvidadoTu.setBounds(200, 817, 229, 20);
		panel_principal.add(lblhasOlvidadoTu);

	}
	
	/***Este método crea los distintos textFields visibles en el panel principal.*/
	
	private void createJtextfields()
	{
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setBounds(65, 50, 220, 30);
		panel.add(username);
		
		username.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				username.setFocusable(true);
			}
		});
		
		username.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				username.setText("");
				username.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.ITALIC, 16));
		password.setBounds(65, 100, 220, 30);
		panel.add(password);

		password.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				password.setFocusable(true);
			}
		});
		
		password.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				password.setText("");
				password.setEchoChar('*');
				password.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.ITALIC, 16));
		name.setBounds(65, 150, 110, 30);
		panel.add(name);
		
		name.addMouseMotionListener(new MouseAdapter() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				name.setFocusable(true);
			}
		});
		
		name.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				name.setText("");
				name.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		apellidos = new JTextField();
		apellidos.setFont(new Font("Tahoma", Font.ITALIC, 16));
		apellidos.setBounds(205, 150, 110, 30);
		panel.add(apellidos);
		
		apellidos.addMouseMotionListener(new MouseAdapter() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				apellidos.setFocusable(true);
			}
		});
		
		apellidos.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				apellidos.setText("");
				apellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.ITALIC, 16));
		email.setBounds(65, 200, 220, 30);
		panel.add(email);
		
		email.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				email.setFocusable(true);
			}
		});
		
		email.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				email.setText("");
				email.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		day = new JTextField();
		day.setFont(new Font("Tahoma", Font.ITALIC, 15));
		day.setBounds(65, 250, 40, 30);
		panel.add(day);
		
		day.addMouseMotionListener(new MouseAdapter() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				day.setFocusable(true);
			}
		});
		
		day.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				day.setText("");
				day.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		month = new JTextField();
		month.setFont(new Font("Tahoma", Font.ITALIC, 12));
		month.setBounds(155, 250, 40, 30);
		panel.add(month);
		
		month.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				month.setFocusable(true);
			}
		});
		
		month.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				month.setText("");
				month.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		year = new JTextField();
		year.setFont(new Font("Tahoma", Font.ITALIC, 15));
		year.setBounds(245, 250, 40, 30);
		panel.add(year);
		
		year.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				year.setFocusable(true);
			}
		});
		
		year.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				year.setText("");
				year.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
	
	}
	
	/***Este método crea los distintos Buttons visibles en el panel principal y sus
	 * respectivas acciones y liseners.*/
	
	private void createButtons()
	{
		btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignIn.setBackground(new Color(255, 102, 102));
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setFocusable(false);
		btnSignIn.setBounds(125, 180, 100, 40);
		panel.add(btnSignIn);
		
		btnSignIn.addActionListener(arg0 -> {
			
			String user = username.getText();
			String pass = password.getText();
			
			try 
			{
				comprobarUsuarioLogin(user, pass);
				
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
					goToPanelUser(1);
				} 
				else 
				{
					goToPanelUser(0);
				}
				
			} 
			catch (Exceptions e) 
			{
				JOptionPane.showMessageDialog(VentanaPrincipal.this, e.getMessage());
			}
		});
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSignUp.setBackground(new Color(255, 102, 102));
		btnSignUp.setFocusable(false);
		btnSignUp.setBounds(125, 240, 100, 40);
		panel.add(btnSignUp);
		
		btnSignUp.addActionListener(e -> registrationMenuVisible());
		
		btnExit = new JButton("Exit");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnExit.setBackground(new Color(153, 240, 153));
		btnExit.setFocusable(false);
		btnExit.setBounds(125, 300, 100, 40);
		panel.add(btnExit);
		
		btnExit.addActionListener(e -> VentanaPrincipal.this.dispose());
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnConfirm.setBackground(new Color(255, 102, 102));
		btnConfirm.setFocusable(false);
		btnConfirm.setBounds(125, 320, 100, 40);
		panel.add(btnConfirm);
		
		btnConfirm.addActionListener(e -> createUser());
		
		btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnBack.setBackground(new Color(153, 240, 153));
		btnBack.setFocusable(false);
		btnBack.setBounds(125, 380, 100, 40);
		panel.add(btnBack);
		
		btnBack.addActionListener(e -> loginMenuVisible());
	}
	
	/***Este método define las posiciones exactas de los componentes visibles en el panel principal dependiendo del tamaño del frame en cada momento.*/
	
	private void setElementsSizePosition()
	{
		panel_principal.addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent evt)
			{
				if(!singUpMenu)
				{
					panel.setBounds(((panel_principal.getBounds().width)/2)-175, 405, 350, 360);
				}
				else
					panel.setBounds(((panel_principal.getBounds().width)/2)-175, 405, 350, 440);
				
				logo.setBounds(((panel_principal.getBounds().width)/2)-185, 0, 370, 370);
				intro.setBounds(((panel_principal.getBounds().width)/2)-115, 350, 230, 30);
				lblhasOlvidadoTu.setBounds(((panel_principal.getBounds().width)/2)-100, 820, 230, 20);
			}
		});
		
	}
	
	/***Este método es un método lógico para la creación de usuaraios. Comprueba que los datos
	 * sean correctos, y una vez comprobado, escribe el usuario en la BD.*/
	
	private void createUser()
	{
		try 
		{
			String username = this.username.getText();
			String password = this.password.getText();
			String name = this.name.getText();
			String apellidos = this.apellidos.getText();
			String email = this.email.getText();
			String fecNac = this.day.getText() +"-"+ this.month.getText() +"-"+ this.year.getText();
			String description = null;
			
			comprobarUsuarioRegistration(username, email);
			comprobacionFecha();
			UsuarioNormal usuario = new UsuarioNormal(username, password, name, apellidos, email, fecNac,description);
			
			bdManager.saveUser(usuario);
			
			this.usuario = usuario;
			usuarios.add(usuario);
			
			goToPanelUser(0);
		} 
		catch (Exceptions e) 
		{
			JOptionPane.showMessageDialog(VentanaPrincipal.this, e.getMessage());
		}
		
	}
	
	/***Este método pone visibles los distintos elementos en el panel del panel principal.*/
	
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
		apellidos.setVisible(true);
		email.setVisible(true);
		day.setVisible(true);
		month.setVisible(true);
		year.setVisible(true);
		
		setTexts();
		
		panel.setBounds(((panel_principal.getBounds().width)/2)-175, 405, 350, 440);
	}
	
	/***Este método pone visibles los distintos elementos en el panel principal.*/
	
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
		apellidos.setVisible(false);
		email.setVisible(false);
		day.setVisible(false);
		month.setVisible(false);
		year.setVisible(false);
		
		panel.setBounds(((panel_principal.getBounds().width)/2)-175, 405, 350, 360);
		
		setTexts();
	}
	
	/***Este método inserta los distintos textos a los labels localizados en el panel del panel principal.*/
	
	public void setTexts()
	{
		username.setText("Username");
		password.setText("Password");
		password.setEchoChar((char)0);
		name.setText("Name");
		apellidos.setText("Surnames");
		email.setText("Email");
		day.setText("Day");
		month.setText("Month");
		year.setText("Year");
		
		username.setFocusable(false);
		password.setFocusable(false);
		name.setFocusable(false);
		apellidos.setFocusable(false);
		email.setFocusable(false);
		day.setFocusable(false);
		month.setFocusable(false);
		year.setFocusable(false);
	}
	
	/***Este método crea un enlace entre el panel principal y el panelUser.*/
	
	private void goToPanelUser(int tipo)
	{
		PanelUser panelUser = new PanelUser(VentanaPrincipal.this, tipo);
		setContentPane(panelUser);
		revalidate();
	}
	
	/***Este método crea un enlace entre el panel principal y el panelRecuContraseña.*/
	
	private void goToPanelRecuContraseña()
	{
		PanelRecuContraseña panelRecuContraseña = new PanelRecuContraseña(VentanaPrincipal.this, usuarios);
		setContentPane(panelRecuContraseña);
		revalidate();
	}

	/***Estamos ante un método lógico que tiene como función comprobar si el usuario introducido y su contraseña son correctas.*/
	
	private void comprobarUsuarioLogin(String usuario, String password) throws Exceptions
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
		
		username.setFocusable(false);
		this.password.setFocusable(false);
		this.password.setText("Password");
		this.password.setEchoChar((char)0);
		
		if(!encontrado)
		{
			username.setText("Username");
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
	
	public void comprobarUsuarioRegistration(String usuario, String email) throws Exceptions
	{

		for(Usuario a: usuarios)
		{
			if(a.getNombreUsuario().equals(usuario))
			{
				username.setText("Username");
				throw new Exceptions("Nombre de usuario existente");
			}
			else if(a.getEmail().equals(email))
			{
				this.email.setText("Email");
				throw new Exceptions("Email existente");
			}
		}
	}
	
	public void comprobacionFecha() throws Exceptions 
	{
		if(day.getText().length() == 2 && month.getText().length() == 2 && year.getText().length() == 2) 
		{
			 try 
			 {
				int n= new Integer(day.getText()) ;
				int i = new Integer(month.getText()) ;
				int j = new Integer(year.getText()) ;
			 }
			 catch(Exception e)
			 {
				 throw new Exceptions("Introduzca en el formato adecuado la fecha.");
			 }
		}
		else 
		{
			 throw new Exceptions("Introduzca en el formato adecuado la fecha.");
		}
	}
	
/** Este método es un retorno al panel principal
 * @return panel_principal : panelPrincipal.*/
	
	public JPanel getPanel_principal() 
	{
		return panel_principal;
	}

	public BDManager getBdManager() 
	{
		return bdManager;
	}
	
	public void setBdManager(BDManager bdManager) 
	{
		this.bdManager = bdManager;
	}
	
	public void setPanel_principal(JPanel panel_principal) 
	{
		this.panel_principal = panel_principal;
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
