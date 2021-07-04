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

public class editFrame extends Frame implements ActionListener {
	TextField t1, t2, t3, t4, t5;
	String s1="";
	String s2="";
	String s3 = "";
	String s4="";
	String s5="";
	Button b5, b6;
	String msg = "";

	editFrame(String username,String password) {
		try {
			Connection con = DbConnection.getConnection();
	 
			Statement stmt=con.createStatement();  
			String sql="SELECT e.name,e.username,e.password,c.college,d.department FROM logindata e JOIN studentcollege c ON e.username = c.username JOIN studentdepartment d ON e.username = d.username WHERE e.username = '"+username+"'";
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
			s1=rs.getString(1);
			s2=rs.getString(2);
			s3=rs.getString(3);
			s4=rs.getString(4);
			s5=rs.getString(5);
			}
			DbConnection.closeConnection(); 
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		/*---------------------------------------------------------------------------------------*/
		this.setVisible(true);
		this.setSize(350, 300);
		// this.setLayout(new BorderLayout());
		this.setLayout(new FlowLayout());
		this.setTitle("Login Page");
		this.setBackground(Color.white);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});/* Annoymous Class */

		Label l1 = new Label("User ID :");
		
		Label l2 = new Label("Name :");
		
		Label l3 = new Label("Password :");
		Label l4 = new Label("College :");
		Label l5 = new Label("Depatment :");
		b5 = new Button("Save");
		b6 = new Button("Back");
		t1 = new TextField(s2,25);
		t2 = new TextField(s1,25);
		t3 = new TextField(s3,25);
		t4 = new TextField(s4,25);
		t5 = new TextField(s5,25);
		this.add(l1);
		this.add(t1);
		t1.setEditable(false);
		this.add(l2);
		this.add(t2);
		this.add(l3);
		this.add(t3);
		this.add(l4);
		this.add(t4);
		this.add(l5);
		this.add(t5);
		this.add(b5);
		this.add(b6);
		b5.addActionListener(this);
		b6.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String Button = e.getActionCommand();
		if (Button.equals("Save")) {
			if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("")
					&& !t4.getText().equals("") && !t5.getText().equals("")) {
				/*System.out.println(t1.getText());
				System.out.println(t2.getText());
				System.out.println(t3.getText());
				System.out.println(t4.getText());
				System.out.println(t5.getText());
				System.out.println("UPDATE LOGINDATA SET Password='"+t3.getText()+"' WHERE username='"+t1.getText()+"'");
				System.out.println("UPDATE STUDENTCOLLEGE SET COLLEGE='"+t4.getText()+"' WHERE username='"+t1.getText()+"'");
				System.out.println("UPDATE STUDENTDEPARTMENT SET DEPARTMENT='"+t5.getText()+"' WHERE username='"+t1.getText()+"'");*/
				try {
					
					Connection con = DbConnection.getConnection();
					Statement stmt0=con.createStatement();
					Statement stmt1=con.createStatement();
					Statement stmt2=con.createStatement();
					Statement stmt3=con.createStatement();
					stmt0.execute("UPDATE LOGINDATA SET Name='"+t2.getText()+"' WHERE username='"+t1.getText()+"'");
					stmt1.execute("UPDATE LOGINDATA SET Password='"+t3.getText()+"' WHERE username='"+t1.getText()+"'");
					stmt2.execute("UPDATE STUDENTCOLLEGE SET COLLEGE='"+t4.getText()+"' WHERE username='"+t1.getText()+"'");
					stmt3.execute("UPDATE STUDENTDEPARTMENT SET DEPARTMENT='"+t5.getText()+"' WHERE username='"+t1.getText()+"'");
					stmt0.close();
					stmt1.close();
					stmt2.close();
					stmt3.close();
					DbConnection.closeConnection();
					msg = "Data Update Complete";
					repaint();
				} catch (Exception re) {
					re.printStackTrace();
				}
			}

		} else {
			msg = "Enter Complete Credentials";
			repaint();
		}
		if (Button.equals("Back")) {
			MyFrameEx ab = new MyFrameEx();
		}
	}

	public void paint(Graphics g) {
		Font f = new Font("Arial", 2, 20);
		g.setFont(f);
		g.setColor(Color.black);
		g.drawString(msg, 80, 250);
	}
}