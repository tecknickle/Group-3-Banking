import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

//Client extends User to login with user name and password. 
public class Client extends User{
//create socket for 2 way communication outputStream/inputStream
	private Socket socket;
	
	
//constructor
public Client(String server, int portNumber) throws IOException {
	//make socket and 2 way connections. 
	socket = new Socket(server, portNumber);
    outstream= new ObjectOutputStream(socket.getOutputStream());
    instream = new ObjectInputStream(socket.getInputStream());
    

}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		info();
		//login (verify user) = in user class
		login();
		System.out.println("logged in! (from clinet class)");

		
		
		//should't the add/remove account functions be in bank account class?
		
	}
	
	
	
	//print a message
	private static void info() {
		System.out.println("This is the Client Class");
		System.out.println(":)\n");
		
	}

}

