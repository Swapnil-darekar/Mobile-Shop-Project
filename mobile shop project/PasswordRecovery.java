import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class PasswordRecovery extends JFrame implements ActionListener
{
 JLabel l1,l2,l3,l4;
 JButton b1,b2;
 JTextField t1,t2;
 JComboBox jcb1;
 JPanel p1,p2,p3;
 String Question[]={"select Question","your Favourate Movie","your Favourate Teacher","First Bike","I Love"};
 
 Connection con;
	Statement stm;
	ResultSet rs;
 String s1,s2,s3,s4;
 
 
 PasswordRecovery()
 {
  setSize(400,350);
  setTitle("Password Recovery");
  setLocation(400,200);
  setLayout(null);
  setVisible(true);
  
  
  l1=new JLabel("Password Recovery");
  l2=new JLabel("Enter Name :");
  l3=new JLabel("Question :");
  l4=new JLabel("Answer :");
  t1=new JTextField(30);
  t2=new JTextField(30);
  jcb1=new JComboBox(Question);
  b1=new JButton("Recover");
  b2=new JButton("Back");
  
  
    p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	
	p1.setLayout(null);
	p2.setLayout(null);
	p3.setLayout(null);
	
  l1.setFont(new Font("Serif",Font.BOLD,30));
  b1.setMnemonic('r');
  b2.setMnemonic('b');
  
    add(p1);
    add(p2);
	add(p3);
	
	
  p1.add(l1);
  
  p2.add(l2);
  p2.add(l3);
  p2.add(l4);
  p2.add(t1);
  p2.add(t2);
  p2.add(jcb1);
  
  p3.add(b1);
  p3.add(b2);
  
  p1.setBounds(20,10,350,50);    
  l1.setBounds(70,5,300,40);
  
  p2.setBounds(20,80,350,140);    
  l2.setBounds(30,10,100,20);
  l3.setBounds(30,50,100,20);
  l4.setBounds(30,90,100,20);
  t1.setBounds(180,10,130,20);
  jcb1.setBounds(180,50,130,20);
  t2.setBounds(180,90,130,20);
  
  
  p3.setBounds(20,240,350,50);    
  b1.setBounds(40,10,100,25);
  b2.setBounds(200,10,100,25);
  

  b2.addActionListener(this);
  b1.addActionListener(this);
   try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
			stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
  
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 }


 public void actionPerformed(ActionEvent ae)
  {
      
     if(ae.getSource()==b1)
			{
				String s1,s2,s3;
				s1 = jcb1.getSelectedItem().toString();
				s2 = t1.getText();
				s3 = t2.getText();
				
				if(jcb1.getSelectedIndex()==0 || s2.length()==0 || s3.length()==0)
					JOptionPane.showMessageDialog(null,"All fields are Necessary !!!","LOGIN Error",JOptionPane.ERROR_MESSAGE);
				
				else
				{
					try
					{
						rs = stm.executeQuery("select * from mylogin where login_name = '" +s2+ "' and que = '" +s1+ "' and ans = '" +s3+ "'");
						
						rs.next();						
						JOptionPane.showMessageDialog(null,"Password Successfully Recovered !!! \n Your Password = "+rs.getString(2));
						
						new Login();
						dispose();
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null,"This is not Valid Information !!! \n Try Again !!!","Invalid Information",JOptionPane.ERROR_MESSAGE);
					}					
					t1.setText("");
					t2.setText("");
					jcb1.setSelectedIndex(0);
					t1.requestFocus();
				}
			}
    if(ae.getSource()==b2)
      {
        //new login();
        dispose();
      }
  }
 
 public static void main(String args[])
 {
  new PasswordRecovery();
 } 
}