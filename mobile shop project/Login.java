//javac -cp ".;c:\*" Login.java

/*create table mylogin
(
login_name varchar(50) not null,
password varchar(10) not null,
que varchar(20) not null,
ans varchar(20) not null
);

insert into mylogin values ('admin','admin123','First Bike','Shine');
insert into mylogin values ('komal','komal99','I Love','India');

select * from mylogin;
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;


class Login extends JFrame implements ActionListener
{
Connection conn;
	Statement s;
	ResultSet rs;
	JLabel l1,l2;
    JTextField t1,t2;
    
    JButton b1,b2;
	JMenuBar mbr;
	JMenu m1;
	JMenuItem mi1,mi2;
    int cnt=3;

	Login()
    {   
		
       setSize(400,350);
		setTitle("Login Page");
		setLocation(400,200);
		setLayout(null);
		setVisible(true);

		mbr = new JMenuBar();
		m1 = new JMenu("Menu");
		mi1= new JMenuItem("ChangePassword");
		mi2= new JMenuItem("PasswordRecovery");


        l1 = new JLabel("Login name ");
        l2 = new JLabel("Password ");

        t1 = new JTextField();
        t2 = new JPasswordField();

        b1 = new JButton("Login");
        b2 = new JButton("Exit");

		add(mbr);
		mbr.add(m1);
		m1.add(mi1);
		m1.add(mi2);
		
        add(l1);    add(l2);   add(t1);
        add(t2);  add(b1);     add(b2);

		mbr.setBounds(0,5,40,20);
		m1.setBounds(2,5,40,20);
        l1.setBounds(20,40,80,20);
        t1.setBounds(110,40,80,20);

        l2.setBounds(20,70,80,20);
        t2.setBounds(110,70,80,20);

        b1.setBounds(20,100,80,20);
        b2.setBounds(110,100,80,20);

        b1.setMnemonic('L');
        b2.setMnemonic('X');

        b1.addActionListener(this);
        b2.addActionListener(this);

		mi1.addActionListener(this);
        mi2.addActionListener(this);
 
  try
			{	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/project","root","");
			s = conn.createStatement();
			}
		
				catch(Exception e)
				{
					e.printStackTrace();
				}

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

 
 public void actionPerformed(ActionEvent ae)
 {	
		int cnt=0;
		int f=0;
	
		if(ae.getSource()==b1)
        { 
	
			String s1,s2;
			s1=t1.getText();
			s2=t2.getText();
			
			try
			{
					rs = s.executeQuery("select * from mylogin where login_name='"+s1+"' and  password = '"+s2+"'");
					rs.next();
					rs.getString(1);
					JOptionPane.showMessageDialog(null,"*****Login Successfull ***");
					 new choicefrm();
					dispose();
			
			}
			catch(Exception el)
			{
				JOptionPane.showMessageDialog(null,"Please Try Again !!!","Invalid Password!!!",JOptionPane.ERROR_MESSAGE);
			}
			t1.setText("");
			t2.setText("");
			t1.requestFocus();
		}
   if(ae.getSource()==b2)
   {
    System.exit(0);
   }

   
  if(ae.getSource()==mi1)
   {
		 new ChangePassword();
   }
  
  
  if(ae.getSource()==mi2)
  {
			new PasswordRecovery();
  
  }
  
  
 }
  
  
  public static void main(String args[])
  {
   new Login();
  } 
}