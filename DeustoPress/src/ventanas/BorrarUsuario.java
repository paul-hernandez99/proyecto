package ventanas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import comentario.Comentario;
import entrada.Entrada;
import exceptions.Exceptions;
import usuarios.Administrador;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.panels.PanelAdmin;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.Color;

public class BorrarUsuario extends JDialog 
{

	private JLabel panelBorrar = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JLabel panelFinal = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
	private JTextField textField;
	private VentanaPrincipal ventanaPrincipal;
	private Usuario usuario;
	private int posicion;

	public BorrarUsuario(VentanaPrincipal ventana) 
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		ventanaPrincipal = ventana;
		setBounds(100, 100, 504, 300);
		panelBorrar.setLayout(null);
		panelFinal.setLayout(null);
		setContentPane(panelBorrar);
		
		JLabel lblIntroduzcaElUsername = new JLabel("Introduzca el username del usuario que quieres borrar:");
		lblIntroduzcaElUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblIntroduzcaElUsername.setBounds(36, 41, 431, 20);
		panelBorrar.add(lblIntroduzcaElUsername);
		
		textField = new JTextField();
		textField.setBounds(78, 98, 300, 26);
		panelBorrar.add(textField);
		textField.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(new Color(102, 204, 255));
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnNext.setBounds(78, 173, 115, 29);
		panelBorrar.add(btnNext);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(102, 204, 255));
		btnCancel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setBounds(263, 173, 115, 29);
		panelBorrar.add(btnCancel);
		
		btnNext.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String username = textField.getText();
				try 
				{
					encontrarUsuario(username);
					JTextArea txtrNombreDeUsuario = new JTextArea();
					txtrNombreDeUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
					txtrNombreDeUsuario.setText("Nombre de usuario: " + usuario.getNombreUsuario()
							+ "\r\ncontrase\u00F1a: " + usuario.getContraseña() + "\r\nNombre: "
							+ usuario.getNombreReal() + "\r\nFecha de alta: " + usuario.getFechaDeAlta());
					txtrNombreDeUsuario.setBounds(86, 52, 300, 81);
					panelFinal.add(txtrNombreDeUsuario);

					setContentPane(panelFinal);
					revalidate();
				} 
				catch (Exceptions e) 
				{
					JOptionPane.showMessageDialog(BorrarUsuario.this, e.getMessage());
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				BorrarUsuario.this.dispose();
			}
		});
		
		JLabel lblElUsuarioSeleccionado = new JLabel("El usuario seleccionado es el siguiente:");
		lblElUsuarioSeleccionado.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblElUsuarioSeleccionado.setBounds(86, 16, 300, 20);
		panelFinal.add(lblElUsuarioSeleccionado);
		
		JLabel lblseguroQueQuieres = new JLabel("\u00BFSeguro que quieres borrar la cuenta seleccionada?");
		lblseguroQueQuieres.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblseguroQueQuieres.setBounds(46, 149, 399, 20);
		panelFinal.add(lblseguroQueQuieres);
		
		JButton btnAceptar = new JButton("Eliminar");
		btnAceptar.setBackground(new Color(102, 204, 255));
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnAceptar.setBounds(86, 185, 115, 29);
		panelFinal.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(Usuario user: ventanaPrincipal.getUsuarios())
				{
					if(user instanceof UsuarioNormal)
					{
						for(Entrada entrada: ((UsuarioNormal) user).getEntradas())
						{
							if(entrada.getAutor().getNombreUsuario().equals(usuario.getNombreUsuario()))
							{
								File aFile = new File(entrada.getPath());
								aFile.delete();
								for(Comentario comentario: entrada.getComentarios())
								{
									File file = new File(comentario.getPath());
									file.delete();
								}
							}
							ArrayList<Comentario>found = new ArrayList<>();
							for(Comentario comentario: entrada.getComentarios())
							{
								if(comentario.getNombreUsuarioAutor().equals(usuario.getNombreUsuario()))
								{
									File aFile = new File(comentario.getPath());
									aFile.delete();
									found.add(comentario);
								}
							}
							entrada.getComentarios().removeAll(found);
						}
					}
				}
				
				ventanaPrincipal.getUsuarios().remove(posicion);
				Utilidades.escribirEnFichero("Usuarios.txt", ventanaPrincipal.getUsuarios());
				BorrarUsuario.this.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(102, 204, 255));
		btnCancelar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCancelar.setBackground(Color.YELLOW);
		btnCancelar.setBounds(271, 185, 115, 29);
		panelFinal.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				BorrarUsuario.this.dispose();
			}
		});
	}
	
	public void encontrarUsuario(String username) throws Exceptions
	{
		ArrayList<Usuario>usuarios = ventanaPrincipal.getUsuarios();
		Usuario user = null;
		boolean encontrado = false;
		for(int i=0; i<usuarios.size(); i++)
		{
			user = usuarios.get(i);
			if(user.getNombreUsuario().equals(username)&&!user.getNombreUsuario().equals(ventanaPrincipal.getUsuario().getNombreUsuario()))
			{
				encontrado = true;
				usuario = user;
				posicion = i;
			}
		}
		if(!encontrado)
			throw new Exceptions("Usuario no encontrado");
	}
}
