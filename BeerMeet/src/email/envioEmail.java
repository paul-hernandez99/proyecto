package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**La funci�n de esta clase email es enviar la contrase�a al correo electronico del usuario, pra que  este pueda recuperar su  contrase�a.
*@author aritz eraun y Paul Hernandez*/
public class envioEmail {
	/**La funci�n de este metodo es enviar el email al usuario correspondiemte. Para ello utiliamos el servidor de correo de Gmail
	 * y una cuenta previzmente creada para ello.
	*@author aritz eraun y Paul Hernandez
	*@since 1.1
	*@version 1.3
	*@param destinatario : correo destino al que se debe enviar el mensaje.
	*@param asunto : casunto del correo electr�nico que se enviar� al destinatario.
	*@param cuerpo : contiene al completo el mensaja que se enviar� al destinatario. */
	public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente tambi�n.
	    String remitente = "noreply.beermet@gmail.com";  //Para la direcci�n nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "BeerMeetCompany");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticaci�n mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podr�an a�adir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "BeerMeetCompany");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	      System.out.println("el correro no existe");
	    }
	}
	
	/**El metodo recibe las caracteristicas del usuario y crea el mensage para enviarselo mediante el correo oficial de la aplicacion BeerMeet.
	 * @param destinatario : correo destino al que se debe enviar el mensaje.
	 * @param nombre: Nombre personal del usuario del la cuent BEerMeeet y due�o del correo destinatario.
	 * @param contrase�a: contrase�a antigua del usuario, para que la pueda recordar.*/
	
	public static void bienvenida(String destinatario, String nombre, String contrase�a) {
		String asunto = "Recuperaci�n de contrase�a - BeerMet";
	    String cuerpo = "Buenos dias "+nombre+":\n"
	    		+ "Nos dirigimos a usted para que pueda recuperar su contrase�a y acceder a la"
	    		+ " plataforma BeerMet. \n"+ "\n	contrase�a de recuperaci�n: "+ contrase�a;
 
	    enviarConGMail(destinatario, asunto, cuerpo);
	}

}
