import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket server= null;
		try {
			//Create a ServerSock on socket:4591
	        server = new ServerSocket(4591);
	        server.setReuseAddress(true);
	        
			while(true) {
		        System.out.println("ServerSocket awaiting connections...");

		        // .accept blocks until an inbound connection on this port is attempted
		        Socket client = server.accept();
		        System.out.println("Connection from " + client.getInetAddress().getHostAddress());
		        
		        ClientHandler clientSock = new ClientHandler(client);
		        new Thread(clientSock).start();
			}
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private Message msgIO;
		private OutputStream outputStream;
		private InputStream inputStream;
		private ObjectOutputStream out;
		private ObjectInputStream in;
		//Constructor
		public ClientHandler(Socket socket)  throws IOException, ClassNotFoundException
		{
			clientSocket = socket;
			
		}

		public void run()
		{
			
		}
	}
}
