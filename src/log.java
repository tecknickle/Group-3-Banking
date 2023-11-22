//import for class
import java.io.Serializable;
//date
import java.util.Date;

public class log implements Serializable {
	protected Date date;
	protected String user;
	protected String account;
	//protected action enum; ???
	float amount;
	
	
	//constructor to set variables
	public log(Date date, String user, String account){
		this.date = setDate(date);
		this.user = setUser(user);
		this.account = setAccount(account);

		
	}


	private String setAccount(String ACCOUNT) {
		return this.account = ACCOUNT;
	}


	private String setUser(String USER) {
		
		return this.user = USER;
	}


	private Date setDate(Date DATE) {
		
		return this.date = DATE;
	}
	
	
}
