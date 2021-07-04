package awt.student;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyFrameEx extends Frame implements ActionListener{
	TextField t1,t2;
	Button b1,b2,b3,b4;
	String msg = "";
		MyFrameEx(){
			this.setVisible(true);
			this.setSize(350,300);
			this.setLayout(new FlowLayout());
			this.setTitle("Login Page");
			this.setBackground(Color.white);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent arg0) {   
						System.exit(0);
					}   
				});
			
			Label l1 = new Label("Username :");
			Label l2 = new Label("Password :");
			b1 = new Button("Login");
			b2 = new Button("Reset");
			b3 = new Button("Cancel");
			b4 = new Button("New Registration");
			t1 = new TextField(25);
			t2 = new TextField(25);
			t2.setEchoChar('*');
			this.add(l1);
			this.add(t1);
			this.add(l2);
			this.add(t2);
			this.add(b1);
			this.add(b2);
			this.add(b3);
			this.add(b4);
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			
			
		}

		
		public void actionPerformed(ActionEvent e) {
			String Button = e.getActionCommand();
			if(Button.equals("Login")) {
				String u1 = t1.getText();
				String u2 = t2.getText();
				//validateCredentials(u1,u2);
				if(validateCredentials(u1,u2)) {
					msg = "Login Successfull";
					repaint();
				for(int i=0;i<5;i++) {
					msg=msg+".";
					repaint();
					}
				}
				else {
					msg = "Login Denied";
					repaint();
				}
				
			}
			if(Button.equals("Reset")) {
				t1.setText("");
				t2.setText("");
			}
			if(Button.equals("Cancel")) {
				System.exit(0);
			}
			if(Button.equals("New Registration")) {
				Mymethods r = new Mymethods();
				r.registrationFrame();
			}
			
		}
public void paint(Graphics g) {
			Font f = new Font("Arial",2,20);
			g.setFont(f);
			g.setColor(Color.black);
			g.drawString(msg, 100,150);
}
	

public boolean validateCredentials(String userName, String password) {
	try {
		boolean status = false;
		Connection con = DbConnection.getConnection();
 
		Statement stmt=con.createStatement();  
		String sql="select count (*) from LOGINDATA where username = '"+userName+"' and password = '"+password+"'";
		System.out.println(sql);
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			//System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
			if(rs.getString(1).equals("1")){
				status = true;
				getData(userName,password);
			}
			else
			{
				status = false;
			}
		
		}
		DbConnection.closeConnection(); 
		return status;
	}
	catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	}
	


public void getData(String userName,String password) {
		try {
			//boolean status = false;
			Connection con = DbConnection.getConnection();
			Statement stmt=con.createStatement();
			
			//System.out.println("select * from LOGINDATA where username = '"+userName+"' and password = '"+password+"'");
			ResultSet rs=stmt.executeQuery("SELECT e.name,e.username,e.password,c.college,d.department FROM logindata e JOIN studentcollege c ON e.username = c.username JOIN studentdepartment d ON e.username = d.username WHERE e.username = '"+userName+"'");
			while(rs.next()) {
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
			Mymethods c = new Mymethods();
			c.Frame1(rs.getString(1),rs.getString(4),rs.getString(5),userName,password);
			}
			DbConnection.closeConnection();  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}