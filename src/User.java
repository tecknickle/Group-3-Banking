import java.util.Vector;

public class User {
	
	private String name;
	private String password;
	private Vector<String> accounts;
	private boolean teller;
	
	public User(String n, String p, boolean isTeller) {
		name = n;
		password = p;
		teller = isTeller;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	 
	public boolean verify(String pass) {
		return password.equals(pass);
	}
	
	public boolean isTeller() {
		return teller;
	}
	
	public boolean addAccount(String account) {
		if (teller || accounts.contains(account)) {return false;}
		accounts.add(account); return true;
	}
	
	public boolean removeAccount(String account) {
		return accounts.remove(account);
	}
}
	