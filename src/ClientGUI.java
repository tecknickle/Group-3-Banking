import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.io.ObjectOutputStream;
import java.io.IOException; // Second import lets me throw TWO errors at once
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientGUI implements ListSelectionListener, ActionListener {

	private JList<String> list;
	private JScrollPane listPane;
	private JLabel infoPane;
	private JPanel buttonPane;
	private Vector<String> accountNames;
	private Vector<String[]> accountInfo;
	private ObjectOutputStream outObj;
	private ObjectInputStream inObj;
	private String user;
	
	@SuppressWarnings("resource")
	public void run() throws NumberFormatException, UnknownHostException, IOException {
		
		Socket socket;
		inObj = null;
		outObj = null;
		Scanner input = new Scanner(System.in);
		
		try {
			System.out.println("IP?");
			String ip = input.nextLine();
			System.out.println("Port?");
			
			socket = new Socket(ip, Integer.parseInt(input.nextLine()));
			outObj = new ObjectOutputStream(socket.getOutputStream());
			inObj = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			System.out.println("lol. lmao"); // obviously a temporary hack
		}
		
		JFrame frame = new JFrame();
		
		String pass;
		
		do {
			user = JOptionPane.showInputDialog(frame, "Input username");
			pass = JOptionPane.showInputDialog(frame, "Input password");
		}
		while (!login(outObj, inObj, user, pass));
		
		accountInfo = loadAccountInfo(outObj, inObj, user);
		
		list = new JList<String>();
		listPane = new JScrollPane(list);
		list.addListSelectionListener(this);
		infoPane = new JLabel();
		buttonPane = new JPanel();
		
		JButton button1 = new JButton("Deposit"); button1.setActionCommand("deposit"); button1.addActionListener(this);
		JButton button2 = new JButton("Withdraw"); button2.setActionCommand("withdraw"); button2.addActionListener(this);
		JButton button3 = new JButton("Refresh"); button3.setActionCommand("refresh"); button3.addActionListener(this);
		JButton button4 = new JButton("Log Out"); button4.setActionCommand("Die"); button4.addActionListener(this);
		
		buttonPane.add(button1); buttonPane.add(button2); buttonPane.add(button3); buttonPane.add(button4);
		
		JSplitPane innerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPane, infoPane);
		JSplitPane outerSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerSplit, buttonPane);
		
		frame.getContentPane().add(outerSplit);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		accountNames = new Vector<String>();
		
		for (var it = accountInfo.iterator(); it.hasNext();) {
			accountNames.add(it.next()[0]);
		}
		
		list.setListData(accountNames);
		
		frame.setVisible(true);
		
	}
	
	private static boolean login(ObjectOutputStream outObj, ObjectInputStream inObj, String user, String pass) throws IOException, ClassNotFoundException {
		
		Message message = new Message(MessageType.LOGIN_REQ, user + pass, 0);
		
		outObj.writeObject(message);
		outObj.flush();
		
		message = (Message) inObj.readObject();
		
		if (message.getType().equals(MessageType.SUCCESS)) {
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	private static Vector<String[]> loadAccountInfo(ObjectOutputStream outObj, ObjectInputStream inObj, String user) { 
		
		// STUB: This makes dummy data right now. This should handle the server communications to get info on each account of the
		// client and store them in the result vector. Each element's [0] should be the account name, each [1] should be the
		// balance, each [2] should be the state
		
		Vector<String[]> result = new Vector<String[]>();
		result.add(new String[] {"2626262", "45", "GOOD"});
		result.add(new String[] {"scagonga", "57", "FROZEN"});
		return result;
	}
	
	
	
	public void valueChanged(ListSelectionEvent event) {
		if (!event.getValueIsAdjusting() && list.getSelectedIndex() != -1) {
			infoPane.setText("Balance: " + accountInfo.get(list.getSelectedIndex())[1] + "\nStatus: " + accountInfo.get(list.getSelectedIndex())[2]);
		}
	}
	
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case "deposit":
			try {
				if (!deposit(accountNames.get(list.getSelectedIndex()), Float.parseFloat(JOptionPane.showInputDialog("Input deposit amount")))) {
					JOptionPane.showMessageDialog(null, "Deposit failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (NumberFormatException | HeadlessException | IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "withdraw":
			try {
				if (!withdraw(accountNames.get(list.getSelectedIndex()), Float.parseFloat(JOptionPane.showInputDialog("Input deposit amount")))) {
					JOptionPane.showMessageDialog(null, "Withdraw failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (NumberFormatException | HeadlessException | IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "refresh":
			break;
		case "Die":
			logout();
			break;
		}
		
		accountInfo = loadAccountInfo(outObj, inObj, user);
	}
	
	private boolean deposit(String account, float amount) throws IOException, ClassNotFoundException {
		
		Message message = new Message(MessageType.DEPOSIT, account, amount);
				
		outObj.writeObject(message);
		outObj.flush();
		
		message = (Message) inObj.readObject();
		
		if (message.getType().equals(MessageType.SUCCESS)) {
			
			return true;
			
		} else {
			
			return false;
		}
				
	}
	
	private boolean withdraw(String account, float amount) throws IOException, ClassNotFoundException {
		
		Message message = new Message(MessageType.WITHDRAW, account, amount);
				
		outObj.writeObject(message);
		outObj.flush();
		
		message = (Message) inObj.readObject();
		
		if (message.getType().equals(MessageType.SUCCESS)) {
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	private void logout() throws IOException {

		Message message = new Message(MessageType.LOGOUT, "", 0);
				
		outObj.writeObject(message);
		outObj.flush();
		
	}
}
