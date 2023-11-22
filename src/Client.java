import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
//create variables for 2 way communication outputStream/inputStream
	private Socket socket;
	private ObjectOutputStream outstream;
	private ObjectInputStream instream;
	
	private static MessageType MS;    //should this be static? 

//constructor /client handler
public Client(String server, int portNumber) throws IOException {
	//make socket and 2 way connections. 
	socket = new Socket(server, portNumber);
    outstream= new ObjectOutputStream(socket.getOutputStream());
    instream = new ObjectInputStream(socket.getInputStream());
    

}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		info();
		//login (verify user) = in user 
		login();
		
	    /*if login == successful, make loops/functions for:
	     * check balance/deposit/withdrawing
	     *
	     * Logic on "bank account" class...
	     */
	}
	
	//Login should be in class "User"
	private static void login() throws IOException, ClassNotFoundException {
		// make user enter username/password
	    System.out.println("To log in, please enter Username/password");
	    Scanner userInput = new Scanner(System.in);
	    String username,password;
	    System.out.println("Username: ");
	    username = userInput.nextLine();
	    
	    System.out.println("Password: ");
	    password = userInput.nextLine();
	    
	    //create a message object to be able to talk to server
	    Message message;
	    //make client variable
	    Client client = null;
	   
	    //check vector once user has access to IDs from server.
	    if (username.equals("teller") || username .equals("client") && password.equals("password")) {
	        System.out.println("Welcome! ");
	        System.out.println("User = "  + username);
	        //make new client
	        client = new Client("localhost", 6969);
	        //if user name/password correct, messageType = login
	        MS = MessageType.Login;
	       
		    //create message object to login with order:
	        //message(int ID, MessageType type, string data, float funds)
            message = new Message(0, MS, "",0);

	        
	        
	        
	        
	        //where do I get user ID from?

	    } else {
	        System.out.println("Sorry, try again ");
	        login();
    	}
	    
	    
    
	  //receive message back from server (in stream)
	    message = (Message) client.instream.readObject();
	    
	    if (message.getType().equals("Accepted")) {
	       //Shouldn't the  add/remove account functions be in "BankAccount" Class with other functions?
	        }
	    
	    
	    
	    
	    }

	
	
	//print a message
	private static void info() {
		System.out.println("This is the Client Class");
		System.out.println(":)\n");
		
	}

}

