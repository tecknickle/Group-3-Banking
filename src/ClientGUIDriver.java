import java.io.IOException;
import java.net.UnknownHostException;

public class ClientGUIDriver {
	public static void main(String[] args) {
		ClientGUI the = new ClientGUI();
		try {
			the.run();
		}
		catch (Exception e) {}
	}
}
