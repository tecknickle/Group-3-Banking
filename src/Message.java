//import for class
import java.io.Serializable;

//implement serializable
public class Message implements Serializable {
	//variables
	protected int ID;
	protected MessageType type;
	protected String data;
	protected float funds;
	
	//constructor to set variables
	public Message(int ID, MessageType type, String data, float funds){
		this.ID = setID (ID);
		this.type = setType(type);
		this.data = setData(data);
		this.funds = setFunds(funds);
		
	}
	
	//setters - set strings accordingly (ID/type/data/funds) (public)
	public int setID(int ID){
		return this.ID = ID;
	}
	public MessageType setType(MessageType type){
		return this.type = type;
	}
	public String setData(String data){
		return this.data = data;
	}
	
	public float setFunds(float funds){
		return this.funds = funds;
	}
	
	
	//getters- get ID/Type/data/funds  (public)
	
	public int getID() {
		return this.ID;
	}
	public MessageType getType(){
		return this.type;
	}
	
	public String getData(){
		return this.data;
	}
	
	public float getFunds(){
		return this.funds;
	}
}
