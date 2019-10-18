package ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import comentario.Comentario;
import entrada.Entrada;
import utilidades.Utilidades;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaVisualizarEntrada extends JDialog
{
	private JLabel contentPane;
	private Entrada entrada;
	private JTextArea contenidoEntrada;
	private JTextArea comentarios;
	
	public VentanaVisualizarEntrada(VentanaPrincipal ventana, Entrada entradaSeleccionada) 
	{
		entrada = entradaSeleccionada;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 597, 622);
		contentPane = new JLabel(new ImageIcon("Imagenes/Wallpaper.jpg"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo: "+entrada.getTitulo());
		lblTitulo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblTitulo.setBounds(40, 16, 199, 20);
		contentPane.add(lblTitulo);
		
		JLabel lblEtiqueta = new JLabel("Etiqueta: "+entrada.getEtiqueta());
		lblEtiqueta.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblEtiqueta.setBounds(249, 16, 225, 20);
		contentPane.add(lblEtiqueta);
		
		JLabel lblFechaDeCreacion = new JLabel("Fecha de creacion: "+entrada.getFechaCreacion());
		lblFechaDeCreacion.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblFechaDeCreacion.setBounds(249, 37, 269, 20);
		contentPane.add(lblFechaDeCreacion);
		
		JLabel lblAutor = new JLabel("Autor: "+entrada.getAutor().getNombreUsuario());
		lblAutor.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblAutor.setBounds(40, 37, 199, 20);
		contentPane.add(lblAutor);
		
		JLabel lblVisitas = new JLabel("Visitas: "+entrada.getVisitas());
		lblVisitas.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblVisitas.setBounds(39, 65, 140, 20);
		contentPane.add(lblVisitas);
		
		JLabel lblContenido = new JLabel("Contenido:");
		lblContenido.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblContenido.setBounds(212, 93, 104, 20);
		contentPane.add(lblContenido);
		
		JButton btnSalir = new JButton("OK");
		btnSalir.setBackground(Color.YELLOW);
		btnSalir.setForeground(new Color(102, 204, 255));
		btnSalir.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnSalir.setBounds(201, 521, 115, 29);
		contentPane.add(btnSalir);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(40, 128, 467, 140);
		contentPane.add(sp);
		
		contenidoEntrada = new JTextArea();
		contenidoEntrada.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		sp.setViewportView(contenidoEntrada);
		contenidoEntrada.setLineWrap(true);
		contenidoEntrada.setWrapStyleWord(true);
		contenidoEntrada.setEditable(false);
		cargarEntrada();
		
		JScrollPane sp2 = new JScrollPane();
		sp2.setBounds(50, 449, 434, 29);
		contentPane.add(sp2);
		
		JTextArea textArea_1 = new JTextArea();
		sp2.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setText("Puedes dejar un comentario aqui...");
		textArea_1.setFont(new Font("Gill Sans MT", Font.ITALIC, 15));
		
		JButton btnComent = new JButton("Coment");
		btnComent.setBackground(new Color(102, 204, 255));
		btnComent.setForeground(new Color(255, 255, 255));
		btnComent.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnComent.setBounds(359, 485, 115, 29);
		contentPane.add(btnComent);
		
		JScrollPane sp1 = new JScrollPane();
		sp1.setBounds(40, 363, 467, 70);
		contentPane.add(sp1);
		
		comentarios  = new JTextArea();
		comentarios.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		sp1.setViewportView(comentarios);
		comentarios.setLineWrap(true);
		comentarios.setWrapStyleWord(true);
		comentarios.setEditable(false);
		cargarComentarios();
		
		JLabel lblComentarios = new JLabel("Comentarios:");
		lblComentarios.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblComentarios.setBounds(40, 327, 115, 20);
		contentPane.add(lblComentarios);
		
		JButton btnLike = new JButton("Like");
		btnLike.setForeground(Color.GREEN);
		btnLike.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnLike.setBackground(Color.WHITE);
		btnLike.setBounds(149, 284, 90, 29);
		contentPane.add(btnLike);
		
		JButton btnDislike = new JButton("Dislike");
		btnDislike.setForeground(Color.RED);
		btnDislike.setBackground(Color.WHITE);
		btnDislike.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnDislike.setBounds(297, 284, 90, 29);
		contentPane.add(btnDislike);
		
		JLabel likes = new JLabel();
		likes.setHorizontalAlignment(SwingConstants.CENTER);
		likes.setBounds(149, 284, 100, 29);
		likes.setBackground(Color.WHITE);
		likes.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		likes.setForeground(Color.GREEN);
		likes.setOpaque(true);
		likes.setVisible(false);
		contentPane.add(likes);
		
		JLabel dislikes = new JLabel("Dislikes: "+Integer.toString(entradaSeleccionada.getDislikes()));
		dislikes.setHorizontalAlignment(SwingConstants.CENTER);
		dislikes.setBounds(297, 284, 100, 29);
		dislikes.setBackground(Color.WHITE);
		dislikes.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		dislikes.setForeground(Color.RED);
		dislikes.setOpaque(true);
		dislikes.setVisible(false);
		contentPane.add(dislikes);
		
		btnLike.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				entradaSeleccionada.setLikes();
				likes.setVisible(true);
				dislikes.setVisible(true);
				btnLike.setVisible(false);
				btnDislike.setVisible(false);
				likes.setText("Likes: "+Integer.toString(entradaSeleccionada.getLikes()));
				dislikes.setText("Dislikes: "+Integer.toString(entradaSeleccionada.getDislikes()));
				Utilidades.escribirEnFichero("Usuarios.txt", ventana.getUsuarios());
			}
		});
		
		btnDislike.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				entradaSeleccionada.setDislikes();
				likes.setVisible(true);
				dislikes.setVisible(true);
				btnLike.setVisible(false);
				btnDislike.setVisible(false);
				likes.setText("Likes: "+Integer.toString(entradaSeleccionada.getLikes()));
				dislikes.setText("Dislikes: "+Integer.toString(entradaSeleccionada.getDislikes()));
				Utilidades.escribirEnFichero("Usuarios.txt", ventana.getUsuarios());
			}
		});
		
		textArea_1.addFocusListener(new FocusListener() 
		{
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) 
			{
				textArea_1.setText("");
				textArea_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			}
		});
		
		btnComent.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String contenido = textArea_1.getText();
				if(contenido.equals("Puedes dejar un comentario aqui..."))
				{
					contenido="";
				}
				if(!contenido.isEmpty())
				{
					String path = "Comentarios/0.txt";
					File aFile = new File(path);
					int fileNo = 0;
					if (aFile.exists()) 
					{
						while(aFile.exists())
						{
							fileNo++;
							path = "Comentarios/"+fileNo+".txt";
							aFile = new File(path);
						}
					} 
					try 
					{
						FileWriter fw = new FileWriter(aFile);
						fw.write(contenido);
						fw.flush();
						fw.close();
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					
					String fecha = Utilidades.fechaDeAlta();
					String username = ventana.getUsuario().getNombreUsuario();
					entradaSeleccionada.getComentarios().add(new Comentario(username, path, fecha));
					Utilidades.escribirEnFichero("Usuarios.txt", ventana.getUsuarios());
					textArea_1.setText("");
					cargarComentarios();
				}
				else
				{
					JOptionPane.showMessageDialog(VentanaVisualizarEntrada.this, "Comentario no valido");
				}
			}
		});
		
		btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				VentanaVisualizarEntrada.this.dispose();
			}
		});
	}
	
	public void cargarEntrada()
	{
		String contenido = "";
		File aFile = new File(entrada.getPath());
		String linea;
		try 
		{
			FileReader fr = new FileReader(aFile);
			BufferedReader br = new BufferedReader(fr);
			linea = br.readLine();
			while(linea!=null)
			{
				contenido += linea;	
				linea = br.readLine();
			}
			br.close();
			fr.close();
			contenidoEntrada.setText(contenido);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void cargarComentarios()
	{
		String contenido = "";
		if(!entrada.getComentarios().isEmpty())
		{
			for(Comentario coment: entrada.getComentarios())
			{
				contenido += coment.getNombreUsuarioAutor()+" - "+coment.getFecha()+"\n";
				File aFile = new File(coment.getPath());
				String linea;
				try 
				{
					FileReader fr = new FileReader(aFile);
					BufferedReader br = new BufferedReader(fr);
					linea = br.readLine();
					while(linea!=null)
					{
						contenido += linea;	
						linea = br.readLine();
					}
					br.close();
					fr.close();
					contenido += "\n\n";
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			contenido = "Esta entrada no dispone de comentarios";
		}
		comentarios.setText(contenido);
	}
}
