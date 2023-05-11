//javac -cp ".;Desktop:\*" custbill1.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;		
import javax.swing.*;

class choicefrm1 extends JFrame implements ActionListener 
{
   // Choice cb1;
    JLabel l1;
   
    JButton b1,b2,b3,b4,b5,b6,b7;

      choicefrm1()
      {
        super("Choice Frame");
		setSize(400,400);
		setLocation(400,150);
		setLayout(null);

          //cb1 = new Choice();

          l1 = new JLabel("Choise");
         
       

          b1 = new JButton("Customer Details");
          b2 = new JButton("Dealer Deatils");
          b3 = new JButton("Product Deatils");
          b4 = new JButton("Bill For Customer");
          b5 = new JButton("Bill From Dealer");
          b6 = new JButton("Report");		  
	      b7 = new JButton("BACK");		  
	  
		  
		  

         add(l1); 
	add(b1);    add(b2);    add(b3);
add(b4);    add(b5);    add(b6);
add(b7);    

         

          l1.setBounds(20,50,100,20);
         // cb1.setBounds(130,50,130,20);

         

          b1.setBounds(80,50,130,20);
          b2.setBounds(80,90,130,20);
          b3.setBounds(80,130,130,20);
		  b4.setBounds(80,170,130,20);
          b5.setBounds(80,210,130,20);
          b6.setBounds(80,250,130,20);
          b7.setBounds(80,290,130,20);
		  
		  
		  
		  
         /*   addWindowListener(new WindowAdapter()
            {
              public void windowClosing(WindowEvent e)
               {
                System.exit(0);
               }
            });
			
			*/

          b1.addActionListener(this);
          b2.addActionListener(this);
          b3.addActionListener(this);
		   b4.addActionListener(this);
          b5.addActionListener(this);
          b6.addActionListener(this);
			 b7.addActionListener(this);  
         // cb1.addItemListener(this);

    	setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
           new cust_details();
				dispose();
        }

        if(e.getSource()==b2)
        {
            new dealer_details();
				dispose(); 
        }

        if(e.getSource()==b3)
        {
           new prod_details();
				dispose();
        }
		
		
		 if(e.getSource()==b4)
        {
			new custbill1();
				dispose();
        }

        if(e.getSource()==b5)
        {
             new dealer_bill();
				dispose(); 
        }

        if(e.getSource()==b6)
        {
				new PieChart1();
				dispose();         
        }
		
		 if(e.getSource()==b7)
        {
           new Login();
				dispose();
        }

    }

    

    public static void main(String args[])
    {
        new  choicefrm1();
    }
}