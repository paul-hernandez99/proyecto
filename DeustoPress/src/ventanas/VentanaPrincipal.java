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
//Comprobar registro user
public class VentanaPrincipal extends JFrame 
{
	private ArrayList<Usuario> usuarios;
	private Usuario usuario;
	
	private JPanel panel;
	private JPanel contentPane;
	
	private JScrollPane jScrollPane;
	
	private JLabel logo;
	private JLabel intro;
	private JLabel registration;
	
	private JTextField username;
	private JPasswordField password;
	private JTextField name;
	private JTextField email;
	private JTextField day;
	private JTextField month;
	private JTextField year;
	
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JButton btnCancel;
	
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
		
		registration = new JLabel("Registration info:");
		registration.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		panel.add(registration);
	}
	
	private void createJtextfields()
	{
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setFocusable(false);
		username.setText("Username");
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
		name.setText("name");
		panel.add(name);
		
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
		email.setText("email");
		panel.add(email);
		
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
		day.setFont(new Font("Tahoma", Font.ITALIC, 16));
		day.setVisible(false);
		day.setText("day");
		panel.add(day);
		
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
		month.setFont(new Font("Tahoma", Font.ITALIC, 16));
		month.setVisible(false);
		month.setText("month");
		panel.add(month);
		
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
		year.setFont(new Font("Tahoma", Font.ITALIC, 16));
		year.setVisible(false);
		year.setText("year");
		panel.add(year);
		
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
		panel.add(btnSignUp);
		
		btnSignUp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				registrationJButtonsVisible();
				
				btnSignUp.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						createUser();
					}
				});
			}
		});
		
		btnCancel = new JButton("Exit");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel.setBackground(new Color(153, 240, 153));
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				VentanaPrincipal.this.dispose();
			}
		});
	}
	
	private void setElementsSizePosition()
	{
		contentPane.addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent evt)
			{
				panel.setBounds(((contentPane.getBounds().width)/2)-175, 445, 350, 360);
				logo.setBounds(((contentPane.getBounds().width)/2)-185, 0, 370, 370);
				intro.setBounds(((contentPane.getBounds().width)/2)-115, 390, 230, 30);
				username.setBounds(((panel.getBounds().width)/2)-110, 30, 220, 30);
				password.setBounds(((panel.getBounds().width)/2)-110, 80, 220, 30);
				btnSignIn.setBounds(((panel.getBounds().width)/2)-50, 180, 100, 40);
				btnSignUp.setBounds(((panel.getBounds().width)/2)-50, 240, 100, 40);
				btnCancel.setBounds(((panel.getBounds().width)/2)-50, 300, 100, 40);
			}
		});
		
	}
	
	private void registrationJButtonsVisible()
	{
		registration.setVisible(true);
		name.setVisible(true);
		email.setVisible(true);
		day.setVisible(true);
		month.setVisible(true);
		year.setVisible(true);
	}
	
	private void createUser()
	{
		String username = this.username.getText();
		String password = this.password.getText();
		String name = this.name.getText();
		String email = this.email.getText();
		String fecNac = this.day.getText() +"-"+ this.month.getText() +"-"+ this.year.getText();
		
		UsuarioNormal usuario = new UsuarioNormal(username, password, name, email, fecNac);
		
		BDManager bdManager = new BDManager();
		bdManager.saveUser(usuario);
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
