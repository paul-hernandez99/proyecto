package ventanas.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import usuarios.Usuario;
import ventanas.VentanaPrincipal;
import email.envioEmail;

import java.awt.Font;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**Estamos un panel que tiene como función facilitarle al usuario la recuperación de su contraseña mediante su correo electrónico.
*@author aritz eraun y Paul Hernandez*/
public class PanelRecuContraseña extends JPanel {
	private JTextField textField;

	/**Este método crea el panel visible por el usuario.
	 * 
	 * @param ventanaPrincipal : ventana Principal del programa,
	 * @param usuarios : usuario al que se deberá de mandar el correo o email.
	 */
	 
	public PanelRecuContraseña(VentanaPrincipal ventanaPrincipal, ArrayList <Usuario> usuarios) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel label = new JLabel("En breve recibiras un email en tu correo");
		label.setBounds(150, 655, 299, 35);
		add(label);
		
		JLabel label1 = new JLabel("indicando tu contraseña actual");
		label1.setBounds(150, 675, 299, 35);
		add(label1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imagenes/System/Wallpaper.png"));
		lblNewLabel.setBounds(150, 124, 329, 306);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText("");
			}
		});
		textField.setBounds(151, 462, 298, 26);
		textField.setText("Nombre de usuario");
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 17));
		lblUser.setBounds(58, 465, 69, 20);
		add(lblUser);
		
		JButton btnRecuperarContrasea = new JButton("Recuperar Contrase\u00F1a");
		btnRecuperarContrasea.setBounds(265, 567, 218, 29);
		btnRecuperarContrasea.setBackground(new Color(255, 102, 102));
		add(btnRecuperarContrasea);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Imagenes/System/info.png"));
		lblNewLabel_1.setBounds(15, 635, 98, 98);
		add(lblNewLabel_1);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventanaPrincipal.setContentPane(ventanaPrincipal.getPanel_principal());
				ventanaPrincipal.setTexts();
				ventanaPrincipal.revalidate();
			}
		});
		btnVolver.setBounds(50, 567, 201, 29);
		btnVolver.setBackground(new Color(152, 240, 153));
		add(btnVolver);

		
		btnRecuperarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario=textField.getText();
				boolean encontrado = false;
				Usuario user = null;
				for(Usuario a: usuarios)
				{
					if(a.getNombreUsuario().equals(nombreUsuario))
					{
						encontrado = true;
						user = a;
						break;
					}
				}
				if(encontrado==false) {
					JOptionPane.showMessageDialog(PanelRecuContraseña.this, "El nombre de usuario introducido no existe. Intentelo de nuevo.");
				}else {
					
					envioEmail.bienvenida(user.getEmail(),user.getNombreReal(), user.getContraseña());
					JOptionPane.showMessageDialog(PanelRecuContraseña.this, "El mensage ha sido enviado con exito."
							+ "\n El mensage de recuperación ha sido enviado al siguiente correo: "+ user.getEmail());
				}
			}
		});
	}
}
