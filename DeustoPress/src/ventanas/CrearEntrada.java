package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import entrada.Entrada;
import exceptions.Exceptions;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.SwingConstants;
import java.awt.Color;

public class CrearEntrada extends JDialog
{
	private JLabel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public CrearEntrada(VentanaPrincipal ventana) 
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 504, 392);
		contentPane = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblTitulo.setBounds(40, 16, 69, 20);
		contentPane.add(lblTitulo);
		
		textField = new JTextField();
		textField.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textField.setBounds(110, 13, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		lblEtiqueta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEtiqueta.setToolTipText("");
		lblEtiqueta.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblEtiqueta.setBounds(15, 52, 80, 20);
		contentPane.add(lblEtiqueta);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textField_1.setBounds(110, 52, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea.setBounds(138, 0, 309, 142);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane sp = new JScrollPane(textArea, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(86, 131, 309, 142);
		contentPane.add(sp);
		
		JLabel lblTexto = new JLabel("Texto:");
		lblTexto.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblTexto.setBounds(200, 94, 69, 20);
		contentPane.add(lblTexto);
		
		JButton btnCrearEntrada = new JButton("Crear Entrada");
		btnCrearEntrada.setBackground(new Color(102, 204, 255));
		btnCrearEntrada.setForeground(new Color(255, 255, 255));
		btnCrearEntrada.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnCrearEntrada.setBounds(86, 289, 142, 29);
		contentPane.add(btnCrearEntrada);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSalir.setForeground(new Color(102, 204, 255));
		btnSalir.setBackground(Color.YELLOW);
		btnSalir.setBounds(254, 289, 132, 29);
		contentPane.add(btnSalir);
		
		btnCrearEntrada.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String titulo = textField.getText();
				String etiqueta = textField_1.getText();
				String contenido = textArea.getText();
				try 
				{
					if(entradaCorrecta(titulo, etiqueta, contenido))
					{
						String path = "Entradas/"+titulo+".txt";
						File aFile = new File(path);
						int fileNo = 0;
						if (aFile.exists()) 
						{
							while(aFile.exists())
							{
								fileNo++;
								path = "Entradas/"+titulo+"(" + fileNo + ").txt";
								aFile = new File("Entradas/"+titulo+"(" + fileNo + ").txt");
							}
						} 
						
						try 
						{
							FileWriter fw = new FileWriter(aFile);
							fw.write(contenido);
							fw.flush();
							fw.close();
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						
						String fecha = Utilidades.fechaDeAlta();
						Entrada entrada = new Entrada(titulo, etiqueta, (UsuarioNormal)ventana.getUsuario(), 0, fecha, path, 0, 0);
						((UsuarioNormal)ventana.getUsuario()).getEntradas().add(entrada);
						Utilidades.escribirEnFichero("Usuarios.txt", ventana.getUsuarios());
						CrearEntrada.this.dispose();
					}
				} 
				catch (Exceptions e) 
				{
					JOptionPane.showMessageDialog(CrearEntrada.this, e.getMessage());
				}
			}
		});
		
		btnSalir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CrearEntrada.this.dispose();
			}
		});
	}
	
	public boolean entradaCorrecta (String titulo, String etiqueta, String contenido) throws Exceptions
	{
		if(titulo.isEmpty()||etiqueta.isEmpty()||contenido.isEmpty())
		{
			throw new Exceptions("Huecos vacios");
		}
		if(titulo.contains(";") && etiqueta.contains(";"))
		{
			
		}
		else if(titulo.contains(";"))
		{
			throw new Exceptions("Titulo no valido");
		}
		else if(etiqueta.contains(";"))
		{
			throw new Exceptions("Etiqueta no valida");
		}
		
		return true;
	}
}
