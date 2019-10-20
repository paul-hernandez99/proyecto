package ventanas.panels;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import entrada.Entrada;
import usuarios.Usuario;
import usuarios.UsuarioNormal;
import utilidades.Utilidades;
import ventanas.VentanaPrincipal;
import ventanas.VentanaVisualizarEntrada;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Color;

public class PanelVisualizarEntradas extends JLabel
{
	private JList listaFicheros; 
	private JButton btnVer;
	private VentanaPrincipal ventanaPrincipal;
	private Entrada entradaSeleccionada;
	private JTextField textField;
	private JTextField textEtiqueta;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAo;
	
	public PanelVisualizarEntradas(JLabel panel1, VentanaPrincipal ventana) 
	{
		ventanaPrincipal = ventana;
		setLayout(null);
		setIcon(new ImageIcon("Imagenes/Wallpaper.jpg"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(22, 16, 516, 98);
		add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		rdbtnAutor.setBackground(new Color(255, 255, 204));
		rdbtnAutor.setBounds(118, 12, 95, 29);
		panel.add(rdbtnAutor);
		
		JRadioButton rdbtnEtiqueta = new JRadioButton("Etiqueta");
		rdbtnEtiqueta.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		rdbtnEtiqueta.setBackground(new Color(255, 255, 204));
		rdbtnEtiqueta.setBounds(220, 12, 104, 29);
		panel.add(rdbtnEtiqueta);
		
		JRadioButton rdbtnFecha = new JRadioButton("Fecha");
		rdbtnFecha.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		rdbtnFecha.setBackground(new Color(255, 255, 204));
		rdbtnFecha.setBounds(341, 12, 95, 29);
		panel.add(rdbtnFecha);
		
		JRadioButton rdbtnDefault = new JRadioButton("Default");
		rdbtnDefault.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		rdbtnDefault.setBackground(new Color(255, 255, 204));
		rdbtnDefault.setBounds(429, 55, 87, 29);
		panel.add(rdbtnDefault);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnAutor);
		buttonGroup.add(rdbtnEtiqueta);
		buttonGroup.add(rdbtnFecha);
		buttonGroup.add(rdbtnDefault);
		
		JLabel lblFiltrado = new JLabel("Filtrado:");
		lblFiltrado.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblFiltrado.setBounds(28, 16, 69, 20);
		panel.add(lblFiltrado);
		
		listaFicheros = new JList();
		cargarLista();
		listaFicheros.setBounds(22, 130, 431, 204);
		add(listaFicheros);
		
		btnVer = new JButton("Ver");
		btnVer.setForeground(new Color(255, 255, 255));
		btnVer.setBackground(new Color(102, 204, 255));
		btnVer.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnVer.setBounds(465, 133, 70, 29);
		add(btnVer);
		btnVer.setVisible(false);
		
		JButton btnSalir = new JButton("Atras");
		btnSalir.setBackground(Color.YELLOW);
		btnSalir.setForeground(new Color(102, 204, 255));
		btnSalir.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnSalir.setBounds(465, 172, 70, 29);
		add(btnSalir);
		
		textField = new JTextField("Username del autor");
		textField.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textField.setBounds(57, 56, 347, 26);
		panel.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(102, 204, 255));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnBuscar.setBounds(311, 55, 93, 29);
		panel.add(btnBuscar);
		btnBuscar.setVisible(false);
		
		txtDia = new JTextField();
		txtDia.setFont(new Font("Tahoma", Font.ITALIC, 16));
		txtDia.setText("Dia");
		txtDia.setBounds(36, 56, 66, 26);
		panel.add(txtDia);
		txtDia.setColumns(10);
		txtDia.setVisible(false);
		
		txtMes = new JTextField();
		txtMes.setFont(new Font("Tahoma", Font.ITALIC, 16));
		txtMes.setText("Mes");
		txtMes.setBounds(122, 56, 66, 26);
		panel.add(txtMes);
		txtMes.setColumns(10);
		txtMes.setVisible(false);
		
		txtAo = new JTextField();
		txtAo.setFont(new Font("Tahoma", Font.ITALIC, 16));
		txtAo.setText("A\u00F1o");
		txtAo.setBounds(215, 56, 66, 26);
		panel.add(txtAo);
		txtAo.setColumns(10);
		txtAo.setVisible(false);
		
		textEtiqueta = new JTextField("Etiqueta");
		textEtiqueta.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textEtiqueta.setBounds(57, 56, 347, 26);
		panel.add(textEtiqueta);
		textEtiqueta.setColumns(10);
		textEtiqueta.setVisible(false);
		
		rdbtnDefault.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtDia.setVisible(false);
				txtMes.setVisible(false);
				txtAo.setVisible(false);
				textEtiqueta.setVisible(false);
				textField.setVisible(false);
				btnBuscar.setVisible(false);
				cargarLista();
			}
		});
		txtDia.addFocusListener(new FocusListener() 
		{
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e)
			{
				txtDia.setText("");
				txtDia.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		txtMes.addFocusListener(new FocusListener() 
		{
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) 
			{
				txtMes.setText("");
				txtMes.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		txtAo.addFocusListener(new FocusListener() 
		{
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) 
			{
				txtAo.setText("");
				txtAo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		
		textField.addFocusListener(new FocusListener() 
		{
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) 
			{
				textField.setText("");
				textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void removeUpdate(DocumentEvent e) 
			{
				cargarListaAutor();
			}
			
			public void insertUpdate(DocumentEvent e)
			{
				cargarListaAutor();
			}
			
			public void changedUpdate(DocumentEvent e) 
			{
				cargarListaAutor();
			}
		});
		
		textEtiqueta.addFocusListener(new FocusListener() 
		{
			public void focusLost(FocusEvent e) {}
			public void focusGained(FocusEvent e) 
			{
				textEtiqueta.setText("");
				textEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
		});
		textEtiqueta.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void removeUpdate(DocumentEvent e) 
			{
				cargarListaEtiqueta();
			}
			
			public void insertUpdate(DocumentEvent e) 
			{
				cargarListaEtiqueta();
			}
			
			public void changedUpdate(DocumentEvent e) 
			{
				cargarListaEtiqueta();
			}
		});
		
		rdbtnAutor.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtDia.setVisible(false);
				txtMes.setVisible(false);
				txtAo.setVisible(false);
				textEtiqueta.setVisible(false);
				textField.setVisible(true);
				btnBuscar.setVisible(false);
			}
		});
		
		rdbtnEtiqueta.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtDia.setVisible(false);
				txtMes.setVisible(false);
				txtAo.setVisible(false);
				textField.setVisible(false);
				textEtiqueta.setVisible(true);
				btnBuscar.setVisible(false);
				
			}
		});
		
		rdbtnFecha.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				textField.setVisible(false);
				txtDia.setVisible(true);
				txtMes.setVisible(true);
				txtAo.setVisible(true);
				textEtiqueta.setVisible(false);
				btnBuscar.setVisible(true);
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cargarListaFecha();
			}
		});
		
		listaFicheros.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				entradaSeleccionada = (Entrada)listaFicheros.getSelectedValue();
				btnVer.setVisible(true);
			}
		});
		
		btnVer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if(listaFicheros.isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(PanelVisualizarEntradas.this, "Ninguna entrada seleccionada");
				}
				else
				{
					entradaSeleccionada.setVisitas();
					Utilidades.escribirEnFichero("Usuarios.txt", ventanaPrincipal.getUsuarios());
					VentanaVisualizarEntrada ventanaVisualizarEntrada = new VentanaVisualizarEntrada(ventanaPrincipal, entradaSeleccionada);
					ventanaVisualizarEntrada.setVisible(true);
				}
			}
		});
		
		btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ventanaPrincipal.setContentPane(panel1);
				revalidate();
			}
		});
		
	}
	
	public void cargarLista()
	{
		DefaultListModel <Entrada> lm = new DefaultListModel();
		listaFicheros.setModel(lm);
		for(Usuario usuario: ventanaPrincipal.getUsuarios())
		{
			if(usuario instanceof UsuarioNormal)
			{
				//for(Entrada entrada: ((UsuarioNormal) usuario).getEntradas())
				{
					//lm.addElement(entrada);
				}
			}
		}
	}
	
	public void cargarListaAutor()
	{
		String autor = textField.getText().toUpperCase();
		DefaultListModel <Entrada> lm = new DefaultListModel();
		listaFicheros.setModel(lm);
		for(Usuario usuario: ventanaPrincipal.getUsuarios())
		{
			if(usuario instanceof UsuarioNormal)
			{
				//for(Entrada entrada: ((UsuarioNormal) usuario).getEntradas())
				{
					//if(entrada.getAutor().getNombreUsuario().toUpperCase().contains(autor))
					{
						//lm.addElement(entrada);
					}
				}
			}
		}
	}
	
	public void cargarListaEtiqueta()
	{
		String etiqueta = textEtiqueta.getText().toUpperCase();
		DefaultListModel <Entrada> lm = new DefaultListModel();
		listaFicheros.setModel(lm);
		for(Usuario usuario: ventanaPrincipal.getUsuarios())
		{
			if(usuario instanceof UsuarioNormal)
			{
				//for(Entrada entrada: ((UsuarioNormal) usuario).getEntradas())
				{
					//if(entrada.getEtiqueta().toUpperCase().contains(etiqueta))
					{
						//lm.addElement(entrada);
					}
				}
			}
		}
	}
	
	public void cargarListaFecha()
	{
		String dia = txtDia.getText();
		String mes = txtMes.getText();
		String año = txtAo.getText();
		String fecha = dia+"/"+mes+"/"+año;
		DefaultListModel <Entrada> lm = new DefaultListModel();
		listaFicheros.setModel(lm);
		for(Usuario usuario: ventanaPrincipal.getUsuarios())
		{
			if(usuario instanceof UsuarioNormal)
			{
				//for(Entrada entrada: ((UsuarioNormal) usuario).getEntradas())
				{
					//if(entrada.getFechaCreacion().equals(fecha))
					{
						//lm.addElement(entrada);
					}
				}
			}
		}
	}
}
