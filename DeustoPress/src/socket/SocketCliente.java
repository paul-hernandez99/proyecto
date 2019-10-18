package socket;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import foto.Foto;
import ventanas.VentanaPrincipal;

public class SocketCliente 
{
	
	public SocketCliente()
	{
		
	}
	
	public ArrayList<Foto> recibirFotos(String sql)
	{
		ArrayList<Foto> fotos_inicio = new ArrayList<>();
		
		try 
		{
			Socket socket = new Socket("88.9.156.211", 51350);
			OutputStream outputStream = socket.getOutputStream();
			PrintStream mensaje = new PrintStream(outputStream);	
			
			mensaje.println(1);
			mensaje.println(sql);
			
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			int num_fotos = Integer.parseInt(bufferedReader.readLine());
			
			for(int i = 0; i < num_fotos; i++)
			{
				int tamaino = Integer.parseInt(bufferedReader.readLine());
				String path = bufferedReader.readLine();
				String fecha = bufferedReader.readLine();
				
				byte[] imagenAr = new byte[tamaino];
				
				inputStream.read(imagenAr);
				BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(imagenAr));
				ImageIO.write(imagen, "jpg", new File(path));
				
				fotos_inicio.add(new Foto(path, fecha));
			}
			
			socket.close();
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return fotos_inicio;
	}
	
	public void enviarFoto(Foto foto)
	{
		try 
		{
			Socket socket = new Socket("88.9.156.211", 51350);
			OutputStream outputStream = socket.getOutputStream();
			PrintStream mensaje = new PrintStream(outputStream);
			
			File fichero = new File(foto.getPath());
			
			mensaje.println(0);
			mensaje.println(fichero.length());
			mensaje.println(foto.getPath());
			mensaje.println(foto.getFecha());
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			BufferedImage image = ImageIO.read(fichero);
			ImageIO.write(image, "jpg", byteArrayOutputStream);
			outputStream.write(byteArrayOutputStream.toByteArray());
			
			socket.close();
			
		} 
		catch (UnknownHostException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}

}
	