package awt.student;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Framenext extends Frame implements ActionListener{
	Button b4,b5;
	String username;
	String password;
	Framenext(String name, String college, String DEPT,String username, String password){
		this.username=username;
		this.password=password;
		this.setVisible(true);
		this.setSize(350,300);
		//this.setLayout(new BorderLayout());
		this.setLayout(new FlowLayout());
		this.setTitle("Login Page");
		this.setBackground(Color.white);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {   
					System.exit(0);
				}   
			});/* Annoymous Class*/
		
		Label l1 = new Label("Name :"+name);
		Label l2 = new Label("Roll : 26");
		Label l3 = new Label("College :"+college);
		Label l4 = new Label("Depatment :"+DEPT);
		b4 = new Button("Logout");
		b5 = new Button("Edit");
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(b4);
		this.add(b5);
		b4.addActionListener(this);
		b5.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String Button = e.getActionCommand();
		if(Button.equals("Logout")) {
			MyFrameEx ab = new MyFrameEx();
		}
		if(Button.equals("Edit")) {
			Mymethods r = new Mymethods();
			r.editableFrame(username,password);
		}
		
	}
	
}
