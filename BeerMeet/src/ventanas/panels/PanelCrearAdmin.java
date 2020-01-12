package ventanas.panels;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelCrearAdmin extends JPanel {

	private JTextField username;
	private JPasswordField password;
	private JTextField name;
	private JTextField apellidos;
	private JTextField email;
	public PanelCrearAdmin() {
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setBounds(65, 50, 220, 30);
		add(username);
		
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
				username.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.ITALIC, 16));
		password.setBounds(65, 100, 220, 30);
		add(password);

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
				password.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.ITALIC, 16));
		name.setBounds(65, 150, 110, 30);
		add(name);
		
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
				name.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		apellidos = new JTextField();
		apellidos.setFont(new Font("Tahoma", Font.ITALIC, 16));
		apellidos.setBounds(205, 150, 110, 30);
		add(apellidos);
		
		apellidos.addMouseMotionListener(new MouseMotionListener() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				apellidos.setFocusable(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		apellidos.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
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
		add(email);
		
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
				email.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		

	}

}
