package ventanas.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JEditorPane;

public class PanelRecuContraseña extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelRecuContraseña() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(58, 50, 69, 20);
		add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\PC\\Desktop\\Proiektua\\rr.png"));
		lblNewLabel.setBounds(47, 38, 431, 356);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(151, 462, 298, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 17));
		lblUser.setBounds(58, 465, 69, 20);
		add(lblUser);
		
		JButton btnRecuperarContrasea = new JButton("Recuperar Contrase\u00F1a");
		btnRecuperarContrasea.setBounds(231, 567, 218, 29);
		add(btnRecuperarContrasea);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\PC\\Desktop\\descarga.png"));
		lblNewLabel_1.setBounds(15, 635, 98, 98);
		add(lblNewLabel_1);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(15, 567, 201, 29);
		btnVolver.setBackground(new Color(153, 240, 153));
		add(btnVolver);

	}
}
