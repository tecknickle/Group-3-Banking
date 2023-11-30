import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	//Can you put input/outputStreams to send message objects
	//back and forth server to client... 
	static private HashMap <String,User> Users;
	static private HashMap <String,bankAccount> Accounts;
	static private HashMap <String,log> Logs;
	
	static private int b = 0;
	
	Server(){
		Users = new HashMap<String,User>();
		Accounts = new HashMap<String,bankAccount>();
	}
	public static void main(String[] args) {
		ServerSocket server= null;
		try {
			/*****Testing variable access*****/
			System.out.println("1: " + b);
			test t = new test();
			t.change();
			System.out.println("2: " + b);
			t.change(13);
			System.out.println("1: " + b);
			//Create a ServerSock on socket:4591
	        server = new ServerSocket(4591);
	        server.setReuseAddress(true);
	        /*********************************/
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
	
	private static class test{
		int a;
		test(){
			a = 4;
		}
		public void change(int a) {
			b = a;
		}
		public void change() {
			b = a;
		}
	}
	
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private Message msg;
		private OutputStream outputStream;
		private InputStream inputStream;
		private ObjectOutputStream out;
		private ObjectInputStream in;
		private String currClient;
		private boolean loggedIN;
		private boolean Teller;
		private Server parent;
		//Constructor
		public ClientHandler(Socket socket)  throws IOException, ClassNotFoundException
		{
			loggedIN = false;
			Teller = false;
			
			clientSocket = socket;
			
	        //get the input stream from the connected socket
	        outputStream = clientSocket.getOutputStream();
	        inputStream = clientSocket.getInputStream();

	        //create a ObjectInputStream so we can read data from it.
	        out = new ObjectOutputStream(outputStream);
	        in = new ObjectInputStream(inputStream);
		}

		public void run()
		{
			try {
				
				verifyLogin();
				
				String inputStr;
				while(loggedIN) {
					msg = (Message)in.readObject();	
				}
				
		        clientSocket.close();				
			}
			catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		void verifyLogin() {
			String[] input;
			String username, password;
			while(!loggedIN) {
				try {
					msg = (Message)in.readObject();
					if(msg.type == MessageType.Login) {
						input = msg.getData().split("\n");
						username = input[0];
						password = input[1];
						User u = Users.get(input[0]);
						if(u.verifyUser(username,password)) {
							loggedIN = true;
							// send out approval message
						}
						else {
							//send out disapproval message
						}
					}
					else {
						loggedIN = false;
						break;
					}
				}
				catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				}
			}
			if(loggedIN) {
				switch (Message.getType()) {
				case Login:
                    handleLogin(Message);
                    break;
                case Logout:
                    handleLogout(Message);
                    break;
                case Deposit:
                    handleDeposit(Message);
                    break;
                case Withdraw:
                    handleWithdraw(Message);
                    break;
                // Add more cases for other message types
                default:
                    // Handle unknown message types
                    break;
				}
			}
		}
	}
}
