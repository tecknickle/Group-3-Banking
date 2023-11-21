import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
//make 2 way communication outputStream/inputStream
	private Socket socket;
	private ObjectOutputStream outstream;
	private ObjectInputStream instream;

//constructor client
public Client(String server, int portNumber) throws IOException {
	//make socket and 2 way connections. 
	socket = new Socket(server, portNumber);
    outstream= new ObjectOutputStream(socket.getOutputStream());
    instream = new ObjectInputStream(socket.getInputStream());

}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//hello 
		info();
		//login
		login();
		
	    /*if login == successful, make loops/functions for:
	     * check balance/deposit/withdrawing
	     *
	     * Is logic going to be on server?
	     *
	     */
	}
	
	
	private static void login() throws IOException, ClassNotFoundException {
		// make user enter ID (Java Scanner)
	    System.out.println("enter ID to log in:");
	    Scanner userInput = new Scanner(System.in);
	    String ui = userInput.next();
	    
	    //create a message object to be able to talk to server
	    Message message;
	    //make client variable
	    Client client = null;
	   
	    //check array or vector once server has those
	    if (ui.equals("realID")) {
	        System.out.println("Welcome: " + ui.toString());
	        System.out.println("User = "+ ui);

	        
	       
	        //make new client
	        client = new Client("localhost", 6969);
	       
		    //create message object to login with order:
	        //Message(String ID, String type, String data, float funds)
	        message = new Message(ui, "login", "", 0);
	        
		    //send to server (out stream)
	        client.outstream.writeObject(message);
		   
	    	}else {
		        System.out.println("Sorry, try again ");
		        login();
	    	}
	    
	    
	  //receive message back from server (in stream)
	    message = (Message) client.instream.readObject();
	    
	    //if logged in, prompt the user with message
	    if (message.getData().equals("success")) {
	        System.out.println("You have successfully logged in!");
	        }
	    
	    }

	
	
	//print a message
	private static void info() {
		System.out.println("This is the Client Class");
		System.out.println(":)\n");
		
	}

}

