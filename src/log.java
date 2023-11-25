//import for class
import java.io.Serializable;
//date
import java.util.Date;

public class log implements Serializable {
	protected Date date;
	protected String user;
	protected String account;
	//protected action enum;   do we need this? shouldn't we just use message enum?
	float amount;
	
	
	//constructor to set variables
	public log(Date date, String user, String account, float amount){
		this.date = setDate(date);
		this.user = setUser(user);
		this.account = setAccount(account);
		this.amount = setAmount(amount);
		
	}

	

	//setters - private or public?
	public String setAccount(String ACCOUNT) {
		return this.account = ACCOUNT;
	}


	public String setUser(String USER) {
		
		return this.user = USER;
	}


	public Date setDate(Date DATE) {
		
		return this.date = DATE;
	}
	
	public float setAmount(float AMOUNT) {
		return this.amount = AMOUNT;
	}
	 

	//getters - public
	public String getAccount(String ACCOUNT) {
		return ACCOUNT;
	}


	public String getUser(String USER) {
		
		return USER;
	}


	public Date getDate(Date DATE) {
		
		return DATE;
	}
	
	public Date getAmount(Date DATE) {
			
		return DATE;
	}
	
}
