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
	
	public Foto recibirFoto()
	{
		Foto foto = null;
		try 
		{
			Socket socket = new Socket("88.9.156.211", 51350);
			OutputStream outputStream = socket.getOutputStream();
			PrintStream mensaje = new PrintStream(outputStream);	
			
			mensaje.println(2);
			
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			int tamaino = Integer.parseInt(bufferedReader.readLine());
			String path = bufferedReader.readLine();
			String fecha = bufferedReader.readLine();
				
			System.out.println(tamaino+" "+path+" "+fecha);
				
			byte[] imagenAr = new byte[10000];
				
			inputStream.read(imagenAr);
			System.out.println(imagenAr[0]);
			BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(imagenAr));
			ImageIO.write(imagen, "jpg", new File(path));
			
			foto = new Foto(path, fecha);
			
			System.out.println("foto recibida");
			
			imagen.flush();
			inputStreamReader.close();
			inputStream.close();
			outputStream.close();
			socket.close();
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return foto;
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
	
	public int peticionSqlFotos(String sql)
	{
		int num_fotos = -1;
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
			
			num_fotos = Integer.parseInt(bufferedReader.readLine());
			
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
		
		return num_fotos;
	}
	
	public static void main(String[] args)
	{
		SocketCliente socketCliente = new SocketCliente();
		
		ArrayList<Foto> fotos = new ArrayList<>();
		
		int num_fotos = socketCliente.peticionSqlFotos("yeah");
		
		System.out.println(num_fotos);
		
		for(int i = 0; i < num_fotos; i++)
		{
			Foto foto = socketCliente.recibirFoto();
			fotos.add(foto);
		}
		
		System.out.println(fotos.get(1).getPath());
	}
}
	