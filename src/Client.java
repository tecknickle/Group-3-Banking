
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Handler;


public class Client extends User{
//create socket for 2 way communication outputStream/inputStream
	private Socket socket;
	private ClientHandler currHandler;
	private String ID;
	
	
	//main
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			info();
			//verify (user class)
			verifyUser();
					
		}
		
	
	//constructor
	public Client(String server, int portNumber) throws IOException {
		//make socket and 2 way connections. 
		socket = new Socket(server, portNumber);
	    outstream= new ObjectOutputStream(socket.getOutputStream());
	    instream = new ObjectInputStream(socket.getInputStream());
	}

	//NOTE: ID = string with what? 
	private static boolean addAccount(String id) {
		//STUB: add a bank account to user with ID "id"
		return false;

		
	}

	//NOTE: ID = string with what? 
	private static boolean removeAccount(String id) {
		//STUB: remove an account from user with ID "id"
		return false;

		
	}
	
	

	//getAccount method for vectors 
		Vector<String> getAccounts(String accountID) throws IOException, ClassNotFoundException{
			//create message object to get accountID info 
	        MessageType mt = MessageType.getInfo;
		    Message message = new Message(0,mt, "",0);
			//send message to server
            outstream.writeObject(message);
            outstream.flush();
		    


			//read the info and separate account info 
			instream.readObject();
			
			//make vector to put object of info in
	        BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
			Vector <String> accountInfo = new Vector<>();
			String info;
			
			
			//while loop to read all info from instream object
			while ((info = reader.readLine()) != null){
				//comma separated
				String[] tokens = info.split(",");
				//tokens based on Bank account info encoding we discussed on discord
				if(tokens.length == 3) {
					String accountName = tokens [0];
					String status = tokens[1];
					String balance = tokens[2];

					
					//put info of object in vector
					accountInfo.add("User's Name: " + accountName);
					accountInfo.add("Status: " + status);
					accountInfo.add("Balance: " + balance);
					
				}else {
					System.out.println("please format string as:\n"
							+ "account name, status, balance");
				}
			}
			
			//return account info. you can print it. 
			return accountInfo;
			
		}
		
		
	//Get Client Handler - class still needs to be implemented
	public ClientHandler getHandler() {
		return currHandler;
		//stub

	}
	
	//Set Client Handler
	public  void setHandler() {
		//stub
	}
	
	
	

	//print a message
	private static void info() {
		System.out.println("This is the Client Class");
		System.out.println(":)\n");
		
	}

}

