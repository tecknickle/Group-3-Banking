import java.util.Scanner;

public class Teller {
	public static void main(String[] args) {
		//hello 
		info();
		//login
		login();
		
	   
	    /*if login == successful, make loops/functions for:
	     * check balance/deposit/withdrawing
	     * freeze/delete account/create account
	     *
	     * Is logic going to be on server?
	     *
	     */
	}
	
	
	private static void login() {
		// make user enter ID (Java Scanner)
	    System.out.println("enter Teller ID to log in:");
	    Scanner userInput = new Scanner(System.in);
	    String ui = userInput.next();
	    
	    //create a message object to send to server
	    //Message message;
	    
	    //Then check if server made successful login (check another message)
	   
	    //make client variable
	    Client client = null;
	   
	    //create a compare function to check for valid ID.
	    if (ui.equals("realID")) {
	        System.out.println("Welcome!  ");
	       
	        //make new client
	        //Teller = new Teller("localhost", "port number?");
	       
		    //create message object to login
		   
		    //send to server
		   
	    	}else {
		        System.out.println("Sorry, try again ");
		        login();
	    	}
		
	}
	//print a message
	private static void info() {
		System.out.println("This is the Teller Class");
		System.out.println(":)\n");
		
	}
	
	
}

