package ventanas.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import usuarios.Usuario;
import usuarios.UsuarioNormal;

public class PanelUsuarios extends JPanel
{
	private PanelUser panelUser;
	private JList<UsuarioNormal> listaUsuarios;
	private JTextField txtUsername;
	
	public PanelUsuarios(PanelUser panel)
	{
		this.setLayout(null);
		
		panelUser = panel;
		
		txtUsername = new JTextField("Busqueda de usuario");
		txtUsername.setFont(new Font("Gill Sans MT", Font.ITALIC, 18));
		txtUsername.setBounds(200, 50, 180, 30);
		this.add(txtUsername);
		
		listaUsuarios = new JList<>();
		listaUsuarios.setBackground(Color.WHITE);
		listaUsuarios.setBounds(210, 100, 400, 200);
		this.add(listaUsuarios);
		
		txtUsername.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				txtUsername.setText("Busqueda de usuario");
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				txtUsername.setText("");
			}
		});
		
		txtUsername.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				cargarLista();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				cargarLista();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				cargarLista();
			}
		});
	}
	
	private void cargarLista()
	{
		DefaultListModel<UsuarioNormal> lm = new DefaultListModel<>();
		listaUsuarios.setModel(lm);
		for(Usuario usuario: panelUser.getVentanaPrincipal().getUsuarios())
		{
			if(usuario instanceof UsuarioNormal)
			{
				if(usuario.getNombreUsuario().contains(txtUsername.getText()) || usuario.getNombreReal().contains(txtUsername.getText()) || usuario.getApellidos().contains(txtUsername.getText()))
				{
					lm.addElement((UsuarioNormal) usuario);
				}
			}
		}
	}
}
