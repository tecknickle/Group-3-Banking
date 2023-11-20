import java.util.Scanner;
public class Client {
	public static void main(String[] args) {
		//
		info();
		
		login();
		
	   
	    /*if login == successful, make loops/functions for:
	     * check balance/deposit/withdrawing
	     *
	     * Is logic going to be on server?
	     *
	     */
	}
	
	
	private static void login() {
		// String input with the Java Scanner
	    System.out.println("enter ID to log in:");
	    Scanner userInput = new Scanner(System.in);
	    String ui = userInput.next();
	    //create a message object to send to server?
	    //Message message;
	   
	    //make client variable
	    Client client = null;
	   
	    //if user typed correct ID connect to server
	    if (ui.equals("realID")) {
	        System.out.println("Welcome!  ");
	       
	        //make new client
	        //client = new Client("localhost", "port number?");
	       
		    //create message object to login
		   
		    //send to server
		   
	    	}else {
		        System.out.println("Sorry, try again ");
		        login();
	    	}
		
	}
	
	//print a message
	private static void info() {
		System.out.println("This is the Client Class");
		System.out.println(":)\n");
		
	}
	
	
}

