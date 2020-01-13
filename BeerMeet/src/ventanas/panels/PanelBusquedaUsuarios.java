package ventanas.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
/**Esta clase contiene los meétodos lógicos y visuales para la creación del panelBusquedaUsuarios
 * y su correcto funcionamiento.
 * @author Aritz E. y Paul H. 
 * @version 1.3*/
public class PanelBusquedaUsuarios extends JPanel
{
	private static final long serialVersionUID = 1L;
	private PanelUser panelUser;
	private JList<UsuarioNormal> listaUsuarios;
	private JTextField txtUsername;
	private UsuarioNormal usuario;
	
/**Método de creacion  del panel PanelBusquedaUsuarios
 * 	
 * @param panel :panel usuarios.
 */
	public PanelBusquedaUsuarios(PanelUser panel)
	{
		this.setLayout(null);
		
		panelUser = panel;
		
		txtUsername = new JTextField("Busqueda de usuario");
		txtUsername.setFont(new Font("Gill Sans MT", Font.ITALIC, 18));
		txtUsername.setBounds(200, 50, 180, 30);
		this.add(txtUsername);
		
		listaUsuarios = new JList<>();
		listaUsuarios.setBounds(240, 100, 100, 200);
		this.add(listaUsuarios);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setBounds(400, 250, 100, 30);
		btnVisualizar.setBackground(new Color(153, 240, 153));
		btnVisualizar.setVisible(false);
		this.add(btnVisualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		if(panelUser.getAdminsitrador() != null) {
			btnEliminar.setBounds(400,285, 100, 30);
			btnEliminar.setBackground(new Color(255, 102, 102));
			btnEliminar.setVisible(false);
			this.add(btnEliminar);
			
			btnEliminar.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Object[] options = {"Aceptar",
                    "Cancelar"};
					int n = JOptionPane.showOptionDialog(null,
				    "¿Deseas eliminar este Usuario?",
					    "Eliminación",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,options,options[1]);
					if (n==0 ) {
						panelUser.getBdManager().DeleteUser(usuario);
						panelUser.getVentanaPrincipal().setUsuarios(panelUser.getBdManager().loadUsers());
						cargarLista();
					}
				}
			});
				
			}
		
		txtUsername.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) {}
			
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
		
		listaUsuarios.addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				usuario = listaUsuarios.getSelectedValue();
			
				btnVisualizar.setVisible(true);
				if(panelUser.getAdminsitrador() != null) {
					btnEliminar.setVisible(true);
				}
			}
		});
		
		btnVisualizar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goToUserProfil();
		}
		});
	}
/**Método que carga la lista que se le mostrará al usuario*/	
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
	
	private void goToUserProfil()
	{
		PanelPerfil panelPerfilUser = new PanelPerfil(panelUser, usuario);
		panelUser.add(panelPerfilUser, BorderLayout.CENTER);
		panelUser.setPanelUserProfile(panelPerfilUser);
		this.setVisible(false);
	}
}
