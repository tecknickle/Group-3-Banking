import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class User {
//Username and password = strings
//Vector with: User IDs
	
//instream/outstream to get messages from server
	protected ObjectOutputStream outstream;
	protected ObjectInputStream instream;
	
	
	protected static MessageType MS;    //should this be static? 

	//verify user by logging in with user name and password. compare with with vector. 
		protected static boolean verifyUser() throws IOException, ClassNotFoundException {
			// make user enter username/password
		    System.out.println("To log in, please enter user type/password");
		    Scanner userInput = new Scanner(System.in);
		    String username,password;
		    System.out.println("User: ");
		    username = userInput.nextLine();
		    
		    System.out.println("Password: ");
		    password = userInput.nextLine();
		    
		    //create a message object to be able to talk to server
		    Message message;
		    //make client variable
		    Client client = null;
		   
		    //check username/password
		    if (username.equals("teller") || username .equals("client") && password.equals("password")) {
		        System.out.println("Welcome! ");
		        System.out.println("User = "  + username + " sucessfully connected to server.");
		        
		        
		        //make new client
		        client = new Client("localhost", 4591);
		        
		        //make messageType = login
		        MS = MessageType.Login;
			    //create message object type "Login" with request encoding: data = username/password
		  
	            message = new Message(0, MS, username+"\n" + password,0);
	            //                    ^
	            //where do I get user ID from? (I just put a 0 as placeholder)

	            //send message to server
	            client.outstream.writeObject(message);
	            client.outstream.flush();
				return true;

		    //try again
		    } else {
		        System.out.println("Sorry, try again ");
		        verifyUser();
	    	}
	    
		    
		    //receive message back from server (in stream)
		    message = (Message) client.instream.readObject();
		    
		    
		    //check if user successfully logged in with message type
		    if (message.getType().equals("Accepted")) {
		    	System.out.println("message from server says: accepeted!");

		        }else {
		        	System.out.println("couldn't get message back from server...");
		        }
			return false;
		    }
}
	