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
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegFrame extends Frame implements ActionListener {
	TextField t1, t2, t3, t4, t5;
	Button b5, b6;
	String msg = "";

	RegFrame() {
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
		Label l2 = new Label("Password :");
		Label l3 = new Label("Name :");
		Label l4 = new Label("College :");
		Label l5 = new Label("Depatment :");
		b5 = new Button("Register");
		b6 = new Button("Back");
		t1 = new TextField(25);
		t2 = new TextField(25);
		t3 = new TextField(25);
		t4 = new TextField(25);
		t5 = new TextField(25);
		this.add(l1);
		this.add(t1);
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
		if (Button.equals("Register")) {
			if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("")
					&& !t4.getText().equals("") && !t5.getText().equals("")) {
				System.out.println(t1.getText());
				System.out.println(t2.getText());
				System.out.println(t3.getText());
				System.out.println(t4.getText());
				System.out.println(t5.getText());

				try {
					
					Connection con = DbConnection.getConnection();

					PreparedStatement ps = con.prepareStatement("insert into LOGINDATA values(?,?,?)");
					PreparedStatement ps1 = con.prepareStatement("insert into STUDENTCOLLEGE values(?,?)");
					PreparedStatement ps2 = con.prepareStatement("insert into STUDENTDEPARTMENT values(?,?)");

					ps.setString(1, t3.getText());
					ps.setString(2, t1.getText());
					ps.setString(3, t2.getText());

					ps1.setString(1, t1.getText());
					ps1.setString(2, t4.getText());
					ps2.setString(1, t1.getText());
					ps2.setString(2, t5.getText());
					int i = ps.executeUpdate();
					int j = ps1.executeUpdate();
					int k = ps2.executeUpdate();
					System.out.println((i + j + k) + " records affected");

					DbConnection.closeConnection();
					msg = "Registration Complete";
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