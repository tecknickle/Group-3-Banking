//import for class
import java.io.Serializable;

//implement serializable
public class Message implements Serializable {
	//variables
	protected String ID;
	protected String type;
	protected String data;
	protected float funds;
	
	//constructor
	public Message(){
		this.ID = "Undefined";
		this.type = "Undefined";
		this.data = "Undefinded";
		this.funds = 0;
		
	}
	//constructor to set variables
	public Message(String ID, String type, String data, float funds){
		this.ID = setID (ID);
		this.type = setType(type);
		this.data = setData(data);
		this.funds = setFunds(funds);
		
	}
	
	//setters - set strings accordingly (ID/type/data/funds)
	private String setID(String ID){
		return this.ID = ID;
	}
	public String setType(String type){
		return this.type = type;
	}
	public String setData(String data){
		return this.data = data;
	}
	
	public float setFunds(float funds){
		return this.funds = funds;
	}
	
	
	//getters- get ID/Type/data/funds
	
	public String getID() {
		return this.ID;
	}
	public String getType(){
		return this.type;
	}
	
	public String getData(){
		return this.data;
	}
	
	public float getFunds(){
		return this.funds;
	}
}
