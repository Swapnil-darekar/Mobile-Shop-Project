import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class ChangePassword extends JFrame implements ActionListener
{
 JLabel l1,l2,l3,l4,luser;
 JButton b1,b2,b3;
 JTextField tuser;
 JPasswordField t1,t2,t3;
 JPanel p1,p2,p3;
 
 Connection con=null;
 Statement stmt=null;
 PreparedStatement pr=null;
 ResultSet rs=null; 
 String s1,s2,s5,sql;
 int flag=0,f=0;
 
 ChangePassword()
 {
  setSize(400,400);
  setTitle("Change Password");
  setLocation(400,100);
  setLayout(null);
  setVisible(true);
  
  
  l1=new JLabel("Change Password");
  l2=new JLabel("Enter Old Password :");
  l3=new JLabel("Enter new Password :");
  l4=new JLabel("Re-Enter new Password :");
  luser=new JLabel("User Name:");
  
  t1=new JPasswordField(30);
  t2=new JPasswordField(30);
  t3=new JPasswordField(30);
  tuser=new JTextField(30);
  
  b1=new JButton("Submit");
  b2=new JButton("Back");
  b3=new JButton("Clear");
  

  l1.setFont(new Font("Serif",Font.BOLD,30));
  b1.setMnemonic('s');
  b2.setMnemonic('b');
  
    p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	
	p1.setLayout(null);
	p2.setLayout(null);
	p3.setLayout(null);
	    
    add(p1);
    add(p2);
	add(p3);
	
	
  
  
  p1.add(l1);
  
  p2.add(l2);
  p2.add(l3);
  p2.add(l4);
  p2.add(luser);
  
  p2.add(t1);
  p2.add(t2);
  p2.add(t3); 
  p2.add(tuser);   

  
  p3.add(b1);
  p3.add(b2);
  p3.add(b3);
  
  p1.setBounds(20,10,350,50);
  l1.setBounds(50,5,250,40);
  
  
  p2.setBounds(20,80,350,200);
  luser.setBounds(20,20,150,20);
  l2.setBounds(20,60,200,20);
  l3.setBounds(20,100,200,20);
  l4.setBounds(20,140,200,20);
  
  tuser.setBounds(230,20,100,20);
  t1.setBounds(230,60,100,20);
  t2.setBounds(230,100,100,20);
  t3.setBounds(230,140,100,20);
  
  p3.setBounds(20,300,350,50);
  b1.setBounds(20,10,100,25);
  b2.setBounds(130,10,100,25);
  b3.setBounds(240,10,100,25);
  
  b1.addActionListener(this); 
  b2.addActionListener(this);
  b3.addActionListener(this);
  

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


  


  public void actionPerformed(ActionEvent ae)
   {
	   if(ae.getSource()==b1)
	{
	 try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
	  rs=stmt.executeQuery("select login_name,password from mylogin");
	  if(t1.getText().length()!=0 && t2.getText().length()!=0 && t3.getText().length()!=0 && tuser.getText().length()!=0)
	  {
	   if(t2.getText().equals(t3.getText()))
	   { 
	    while(rs.next())
	    {
		 s1=rs.getString(1);
	     s2=rs.getString(2);
		 //System.out.println(""+s2);
		 
		 if(s1.equals(tuser.getText()) && s2.equals(t1.getText()))
	     {
		  f=1;
		  break;
	      //JOptionPane.showMessageDialog(null,"Please Enter Another Password");
		 }
	    //System.out.print(""+f);
		 
	   }
		
	    if(f==1)
		{
			   
	   	 sql="update mylogin set password='"+t3.getText()+"'  where login_name='"+tuser.getText()+"' and password='"+t1.getText()+"'";
		 pr=con.prepareStatement(sql);
		 pr.execute();
		 pr.close();
		 JOptionPane.showMessageDialog(null,"Password Change Successfully");			  		  
		// new MainMenu();
		 dispose();
        }	
		else
        {
	     JOptionPane.showMessageDialog(null,"Wrong user name or old password","Error",JOptionPane.ERROR_MESSAGE);			  		  
        }
       	   
	  }
        else
        {
         JOptionPane.showMessageDialog(null,"Wrong Re-Entered Password","Error",JOptionPane.ERROR_MESSAGE);			  		  
		}
	}  
		else
        {
         JOptionPane.showMessageDialog(null,"Filled All Information","Error",JOptionPane.ERROR_MESSAGE);			  
	    }
	}
			
       catch(Exception e1)
       {
	    System.out.println(e1);
       }	
    }
    		
		 
      if(ae.getSource()==b2) 
      {
        dispose();
      }
	  
	  if(ae.getSource()==b3) 
      {
       t1.setText("");
	   t2.setText("");
	   t3.setText("");
	   tuser.setText("");
	   
      }
     
   }
 
 public static void main(String args[])
 {
  new ChangePassword();
 } 
}