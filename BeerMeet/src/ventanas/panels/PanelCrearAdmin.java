package ventanas.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.UsuarioNormal;
import ventanas.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.ActionEvent;
/**Esta clase contiene los meétodos lógicos y visuales para la creación del panelCrearAdmin
 * y su correcto funcionamiento.
 * @author Aritz E. y Paul H. 
 * @version 1.3*/

public class PanelCrearAdmin extends JPanel {

	private JTextField username;
	private JPasswordField password;
	private JTextField name;
	private JTextField apellidos;
	private JTextField email;
	private JLabel lblIntroduzcaLosDatos;
	
	/**Estamos ante la creación del panel CrearAdmin
	 * @param panelUser : panelUser recibido.*/ 
	
	public PanelCrearAdmin(PanelUser panelUser) {
		setLayout(null);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setBounds(152, 394, 220, 30);
		add(username);
		
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
		password.setBounds(152, 440, 220, 30);
		add(password);

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
			public void focusGained(FocusEvent arg0) 
			{
				password.setText("");
				password.setEchoChar('*');
				password.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.ITALIC, 16));
		name.setBounds(152, 486, 110, 30);
		add(name);
		
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
		apellidos.setBounds(277, 486, 110, 30);
		add(apellidos);
		
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
		email.setBounds(152, 532, 220, 30);
		add(email);
		
		ponerTexto();
		
		JLabel label = new JLabel("");
		label.setBounds(108, 62, 336, 254);
		label.setIcon(panelUser.escalar("Imagenes\\System\\Wallpaper.png", label));
		add(label);
		
		lblIntroduzcaLosDatos = new JLabel("Introduzca los datos del nuevo Administrador:");
		lblIntroduzcaLosDatos.setBounds(118, 358, 329, 20);
		add(lblIntroduzcaLosDatos);
		
		JButton btnCrearNuevoAdministrador = new JButton("Crear Nuevo Administrador");
		btnCrearNuevoAdministrador.setBackground(new Color(255, 102, 102));
		btnCrearNuevoAdministrador.addActionListener(arg0 -> {
			
			Object[] options = {"Aceptar","Cancelar"};
			
			int n = JOptionPane.showOptionDialog(null,"¿Deseas crear un Nuevo Administrador?","Eliminación",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
			
			if ( n== 0) 
			{
				try 
				{
					Administrador nuevoAdmin = new Administrador();
					nuevoAdmin.setNombreUsuario(username.getText());
					nuevoAdmin.setContraseña(password.getText());
					nuevoAdmin.setNombreReal(name.getText());
					nuevoAdmin.setApellidos(apellidos.getText());
					nuevoAdmin.setEmail(email.getText());
					panelUser.getVentanaPrincipal().comprobarUsuarioRegistration(username.getText(), email.getText());
				
					panelUser.getBdManager().saveUser(nuevoAdmin);
					panelUser.getVentanaPrincipal().getUsuarios().add(nuevoAdmin);
					ponerTexto();
					throw new Exceptions("El Aministrador se ha reguistradocorrectamente.");
				} 
				catch (Exceptions e) 
				{
					JOptionPane.showMessageDialog(PanelCrearAdmin.this, e.getMessage());
				}
			}
		});
		
		btnCrearNuevoAdministrador.setBounds(152, 613, 235, 29);
		add(btnCrearNuevoAdministrador);
		
		
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
		

	}
	
	/**Este método pone texto a los TextFields definidos previamente el la clase
	 * 
	 */
	public void ponerTexto() 
	{
		username.setText("Username");
		password.setText("Password");
		password.setEchoChar((char)0);
		name.setText("Name");
		apellidos.setText("Surnames");
		email.setText("Email");
	}
}
