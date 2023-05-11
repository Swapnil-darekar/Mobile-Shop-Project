/*
create table mydate
(
	roll numeric(5) primary key,
	name varchar(20),
	bdate date
);

insert into mydate values(1,'swapnil','2000-02-17');
insert into mydate values(2,'mayur','1999-07-06');
*/





import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class datedisplayrange extends JFrame  implements ActionListener
{
	JLabel l_roll,l_name,l_date;
	JTextField t_roll,t_name,t_age,t_search;
	DateButton b_date1,b_date2,b_date3;
	JButton b_clear,b_add,b_displyall,b_exit,b_displyrange;

			String sql,s1,s2,name,date;
			int rno;
			Statement stm;
			ResultSet rs;
			Connection cn;
			PreparedStatement prstm;
	
	datedisplayrange()
	{

			setTitle("datedisplayrange");
			
			setLocation(500,300);
			setSize(800,450);
			setLayout(null);
			getContentPane().setBackground(Color.pink);
			setResizable(false);
		
			
			l_roll=new JLabel("Roll no");
			add(l_roll);
			l_roll.setBounds(20,20,150,30);

			l_name=new JLabel("Name");
			add(l_name);
			l_name.setBounds(20,70,150,30);
			
			l_date=new JLabel("Date");
			add(l_date);
			l_date.setBounds(20,120,150,30);	


			t_roll=new JTextField();
			add(t_roll);
			t_roll.setBounds(190,20,160,30);


			t_name=new JTextField();
			add(t_name);
			t_name.setBounds(190,70,160,30);	

			
			b_date1=new DateButton();
			add(b_date1);
			b_date1.setBounds(190,120,150,30);
			b_date1.addActionListener(this);
			
		
		

			

			
			
			b_clear=new JButton("Clear");
			add(b_clear);
			b_clear.setBounds(20,170,150,25);	
			b_clear.addActionListener(this);
			
			b_add=new JButton("ADD");
			add(b_add);
			b_add.setBounds(190,170,150,25);
			b_add.addActionListener(this);


			b_exit=new JButton("Exit");
			add(b_exit);
			b_exit.setBounds(20,200,320,25);	
			b_exit.addActionListener(this);					

			
			b_displyall=new JButton("Display all");
			add(b_displyall);
			b_displyall.setBounds(20,240,320,25);
			b_displyall.addActionListener(this);
			
			b_date2=new DateButton();
			add(b_date2);
			b_date2.setBounds(20,280,150,25);
			b_date2.addActionListener(this);			
			
			b_date3=new DateButton();
			add(b_date3);
			b_date3.setBounds(190,280,150,25);	
			b_date3.addActionListener(this);
			
	
			b_displyrange=new JButton("Disply in range");
			add(b_displyrange);
			b_displyrange.setBounds(20,315,320,25);
			b_displyrange.addActionListener(this);		
			

		try
		{	
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
			stm=cn.createStatement();		
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}

		getMax();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void getMax()
	{
		try
		{
			sql="select max(roll) from mydate";
			rs=stm.executeQuery(sql);
			rs.next();
			rno=rs.getInt(1);
			rno++;
			t_roll.setText(""+rno);
		}
		catch(Exception eap)
		{
			rno=1;
			t_roll.setText(""+rno);
		}
	}
	

	
		public void actionPerformed(ActionEvent ex)
	{
		try
		{
			if(ex.getSource()==b_add)
			{
				if(t_roll.getText().length()==0 && t_name.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null,"All Fields are require ");
					}	
				else
					{
						int roll=Integer.parseInt(t_roll.getText());
						name=t_name.getText();
						date=b_date1.getText();
						sql="insert into mydate values("+roll+",'"+name+"','"+date+"')";
						prstm=cn.prepareStatement(sql);
						prstm.execute();
						JOptionPane.showMessageDialog(null,"Record added successfully ");
						
						t_name.setText("");
						t_roll.setText("");

					}
			}	
				
				
			if(ex.getSource()==b_exit)
			{
				System.exit(0);
			}
			
			
			if(ex.getSource()==b_displyall)
			{
				s1="select count(*) from mydate";
				s2="select * from mydate order by bdate";
				updateTable(s1,s2);
				
			}
			
			
			
			if(ex.getSource()==b_displyrange)
			{
				
				s1="select count(*) from mydate where bdate between'"+b_date2.getText()+"'and'"+b_date3.getText()+"'";
				s2="select * from mydate where bdate between '"+b_date2.getText()+"'and'"+b_date3.getText()+"' order by bdate";
				updateTable(s1,s2);
				
			}
		}
		catch(Exception expp)
		{
			System.out.println(expp);
		}
	}


	void updateTable(String s1,String s2)
	{
		try
		{
			//1 Memory allocation to array
			rs=stm.executeQuery(s1);
			rs.first();
			int rowcnt=rs.getInt(1);
			
			String colHeads[]={"Roll No","Name","Birth Date"};
			String data[][]=new String[rowcnt][3];
			
			//2 Load data into array
			
			rs=stm.executeQuery(s2);
			rs.first();
			for(int i=0;i<rowcnt;i++)
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				rs.next();
			}
			
			
			// 3 create table
			
			JTable table =new JTable(data,colHeads);
			add(table);
			table.setEnabled(false);
			JScrollPane jsp=new JScrollPane(table);
			add(jsp);
			jsp.setBounds(360,10,300,300);
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}
	public static void main(String args[])
	{
		new datedisplayrange();
	}
}