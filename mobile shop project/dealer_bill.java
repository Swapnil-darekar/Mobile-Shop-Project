import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.sql.*;

class dealer_bill extends JFrame implements ActionListener
{
	JTextField tsr,trate,tgst,tgstamt,tqty,tamt,tdiscount,tfamt,tpname;
	JLabel lsr,lsname,lpname,lrate,lqty,lgst,lgstamt,ldiscount,lamt,lfamt,ldate;
	JButton clr,add,remove,back,exit;
	DateButton calb1;
	int id,rate,gstamt,amt,famt,disc;
	String sql;
	
	Connection cn=null;
	Statement stm;
	ResultSet rs;
	PreparedStatement prstm;
	
	dealer_bill()
	{
		super("Dealer Bill");
		setSize(650,500);
		setLocation(150,150);
		setLayout(null);
		
		tsr = new JTextField();
		trate = new JTextField();
		tqty = new JTextField("1");
		tgst = new JTextField("18 %");
		tgstamt = new JTextField();
		tdiscount = new JTextField();
		tamt = new JTextField();
		tfamt = new JTextField();
		tpname = new JTextField();
		
		calb1 = new DateButton();
		
		lsr = new JLabel("Sr. No.");
		ldate = new JLabel("Date");
		lsname = new JLabel("Shop Name : UNIQUE MOBILE SHOP");
		lpname = new JLabel("Product Name");
		lrate = new JLabel("Rate");
		lqty = new JLabel("Quantity");
		lgst = new JLabel("GST %");
		lgstamt = new JLabel("GST Amt");
		ldiscount = new JLabel("Discount");
		lamt = new JLabel("Amount");
		lfamt = new JLabel("Final Amt");
		
		clr = new JButton("CLEAR");
		add = new JButton("SAVE");
		remove = new JButton("REMOVE");
		back = new JButton("BACK");
		exit = new JButton("EXIT");
		
		add(tsr);	add(trate);		add(tqty);	add(tgst);	add(tpname);	add(tdiscount);		add(tamt);	add(tfamt);		add(tgstamt);
		add(lgstamt);	add(lsr);	add(ldate);	add(lsname);	add(lpname);	add(lrate);	add(lqty);	add(lgst);	add(ldiscount);
		add(lamt);	add(lfamt);		add(calb1);
		add(clr);	add(add);	add(remove);	add(back);	add(exit);	
		
		lsr.setBounds(50,50,80,30);
		tsr.setBounds(140,50,100,30);
		ldate.setBounds(270,50,60,30);
		calb1.setBounds(340,50,100,20);
		lsname.setBounds(100,100,200,30);
	//	tsname.setBounds(200,100,200,30);
		lpname.setBounds(50,140,140,30);
		tpname.setBounds(50,170,140,30);
		lrate.setBounds(200,140,120,30);
		trate.setBounds(200,170,120,30);
		lqty.setBounds(330,140,80,30);
		tqty.setBounds(330,170,80,30);
		lgst.setBounds(420,140,100,30);
		tgst.setBounds(420,170,100,30);
		
		lgstamt.setBounds(280,210,100,30);
		tgstamt.setBounds(380,210,100,30);
		lamt.setBounds(280,250,100,30);
		tamt.setBounds(380,250,100,30);
		ldiscount.setBounds(280,290,100,30);
		tdiscount.setBounds(380,290,100,30);
		lfamt.setBounds(280,330,100,30);
		tfamt.setBounds(380,330,100,30);
		
		
		clr.setBounds(50,370,120,30);
		add.setBounds(190,370,120,30);
		remove.setBounds(330,370,120,30);
		back.setBounds(130,410,120,30);
		exit.setBounds(270,410,120,30);
		
		clr.setMnemonic('C');
		add.setMnemonic('S');
		remove.setMnemonic('R');
		back.setMnemonic('B');
		exit.setMnemonic('X');
		
		clr.setToolTipText("Click here to Clear All Fields");
		add.setToolTipText("Click here to Save");
		remove.setToolTipText("Click here to Remove Entry");
		back.setToolTipText("Click here to Back");
		exit.setToolTipText("Click here to Exit");
		
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/project","root","");
			stm =  cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		clr.addActionListener(this);
		add.addActionListener(this);
		remove.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);
		
		tsr.setEditable(false);
		tqty.setEditable(false);
		tgst.setEditable(false);
		tgstamt.setEditable(false);
		tamt.setEditable(false);
		tfamt.setEditable(false);
		getMax();	
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void getMax()
	{
		try
		{
			sql = "select max(dbill) from deal_bill";
			rs = stm.executeQuery(sql);
			rs.next();
			id = rs.getInt(1);
			id++;
			tsr.setText(""+id);
		}
		catch(Exception ex)
		{
			id=1;
			tsr.setText(""+id);
		}
	}
	
	void refresh()
	{
		tpname.setText("");
		trate.setText("");
		tdiscount.setText("");
		tgstamt.setText("");
		tamt.setText("");
		tfamt.setText("");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==add)
			{
				if(tpname.getText().length()==0 || trate.getText().length()==0 || tdiscount.getText().length()==0)
							
					JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);     

				else
				{
					rate = Integer.parseInt(trate.getText());
					gstamt = (rate*18)/100;
					tgstamt.setText(""+gstamt);
					amt = gstamt + rate;
					tamt.setText(""+amt);
					disc = Integer.parseInt(tdiscount.getText());
					famt = amt - disc;
					tfamt.setText(""+famt);
					
					sql = "insert into deal_bill values("+tsr.getText()+",'"+calb1.getText()+"','"+tpname.getText()+"',"+trate.getText()+","+tamt.getText()+","+tfamt.getText()+")";
												
					prstm = cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
												
					JOptionPane.showMessageDialog(null,"*** Record Successfully Saved ***");
					new prod_details();
					dispose();
				}
				getMax();
			}
			
			if(e.getSource()==clr)
			{
				refresh();
				getMax();
			}
			
			if(e.getSource()==back)
			{
				new choicefrm();
				dispose();
			}
			
			if(e.getSource()==exit)
				System.exit(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new dealer_bill();
	}
}