import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.sql.*;

class prod_details extends JFrame implements ActionListener,ItemListener
{
	JLabel lid,lbrd,lmod,limei,lprice,lfrom,ldisp,lstge,lram,ldisk,lbkup,lcam,lver,l,ldid;
	JTextField tid,tbrd,tmod,timei,tprice,tdisp,tstge,tram,tdisk,tbkup,tcam,tver,t,tdid;
	JComboBox cbfrom;
	JButton bclr,badd,bupdt,bdel,bext,bsrch,bbck,bshow;
	JRadioButton srch_mod,srch_imei;
	ButtonGroup grp;
	JPanel pnl;
	List li;
	
	String colHead[] = {"P-ID","Brand","Price"};	
	String data[][];
	int r_cnt=0,i,id,flg=0;
	
	Connection cn;
	Statement stm;
	ResultSet rs,rs1;
	PreparedStatement prstm;
	String sql;	
	
	prod_details()
	{
		super("Product Details");
		setSize(1500,1000);
		setLayout(null);
		
		lid = new JLabel("Product ID");
		lbrd = new JLabel("Brand");
		lmod = new JLabel("Model No");
		limei = new JLabel("IMEI");
		lprice = new JLabel("Purchase Price");
		lfrom = new JLabel("Purchase From");
		ldisp = new JLabel("Display");
		lstge = new JLabel("Storage");
		lram = new JLabel("RAM");
		ldisk = new JLabel("Hard Disk");
		lbkup = new JLabel("Battery Backup");
		lcam = new JLabel("Camera");
		lver = new JLabel("Andriod Version");
		ldid = new JLabel("Delear ID");
		
		tid = new JTextField();
		tbrd = new JTextField();
		tmod = new JTextField();
		timei = new JTextField();
		tprice = new JTextField();
		tdisp = new JTextField();
		tstge = new JTextField();
		tram = new JTextField();
		tdisk = new JTextField();
		tbkup = new JTextField();
		tcam = new JTextField();
		tver = new JTextField();
		tdid = new JTextField();
		cbfrom = new JComboBox();
		
		bclr = new JButton("CLEAR");
		badd = new JButton("SAVE");	
		bupdt = new JButton("UPDATE");
		bdel = new JButton("DELETE");
		bsrch = new JButton("SEARCH");
		bbck = new JButton("BACK");
		bext = new JButton("EXIT");
		bshow = new JButton("SHOW");
		
		srch_mod = new JRadioButton("Search by Model No.",true);
		srch_imei = new JRadioButton("Search by IMEI");
		grp = new ButtonGroup();
		
		pnl = new JPanel();
		l = new JLabel("Enter Data");
		t = new JTextField();
		li = new List();
	
		add(lid);	 	add(lbrd);		add(lmod);		add(limei);		add(lprice);	add(lfrom);		add(ldisp);
		add(lstge); 	add(lram); 		add(ldisk); 	add(lbkup); 	add(lcam); 		add(lver);		add(ldid);
		add(tid);		add(tbrd); 		add(tmod); 		add(timei); 	add(tprice); 	add(tdisp); 	add(tstge);
		add(tram); 		add(tdisk); 	add(tbkup); 	add(tcam); 		add(tver); 		add(tdid);		add(cbfrom);
		add(bclr);		add(badd);		add(bupdt);		add(bdel);		add(bsrch);		add(bbck);		add(bext);		add(bshow);
		cbfrom.addItem("Select Delear Name");
		
		add(srch_mod);		add(srch_imei);
		grp.add(srch_imei);
		grp.add(srch_mod);
		
		pnl.setLayout(null);
		pnl.setVisible(false);
		pnl.setBackground(Color.BLUE);
		pnl.add(srch_mod);	pnl.add(srch_imei);
		pnl.add(l);			pnl.add(t);	
		pnl.add(li);		add(pnl);
		
		lid.setBounds(50,50,100,30);
		tid.setBounds(160,50,200,30);
		
		lbrd.setBounds(50,100,100,30);
		tbrd.setBounds(160,100,200,30);
		
		lmod.setBounds(50,150,100,30);
		tmod.setBounds(160,150,200,30);
		
		limei.setBounds(50,200,100,30);
		timei.setBounds(160,200,200,30);
		
		lprice.setBounds(50,250,100,30);
		tprice.setBounds(160,250,200,30);
		
		lfrom.setBounds(50,300,100,30);
		cbfrom.setBounds(160,300,200,30);
		
		ldisp.setBounds(50,350,100,30);
		tdisp.setBounds(160,350,200,30);
	
		lstge.setBounds(50,400,100,30);
		tstge.setBounds(160,400,200,30);
		
		lram.setBounds(50,450,100,30);
		tram.setBounds(160,450,200,30);
		
		ldisk.setBounds(50,500,100,30);
		tdisk.setBounds(160,500,200,30);
		
		lbkup.setBounds(50,550,100,30);
		tbkup.setBounds(160,550,200,30);
		
		lcam.setBounds(50,600,100,30);
		tcam.setBounds(160,600,200,30);
		
		lver.setBounds(50,650,100,30);
		tver.setBounds(160,650,200,30);
		
		ldid.setBounds(50,700,100,30);
		tdid.setBounds(160,700,200,30);
				
		badd.setBounds(50,750,150,30);
		bupdt.setBounds(210,750,150,30);
		bdel.setBounds(50,800,150,30);
		bclr.setBounds(210,800,150,30);
		bsrch.setBounds(50,850,150,30);
		bshow.setBounds(210,850,150,30);
		bbck.setBounds(50,900,150,30);
		bext.setBounds(210,900,150,30);		
		
		pnl.setBounds(450,150,400,500);
		srch_mod.setBounds(10,10,190,30);
		srch_imei.setBounds(210,10,180,30);
		
		t.setBounds(100,50,290,30);
		l.setBounds(10,50,80,30);
		li.setBounds(10,90,380,400);
		
		bclr.setMnemonic('C');
		badd.setMnemonic('S');
		bupdt.setMnemonic('U');
		bdel.setMnemonic('D');
		bsrch.setMnemonic('E');
		bbck.setMnemonic('K');
		bext.setMnemonic('X');
		bshow.setMnemonic('H');
		
		bclr.setToolTipText("Click here to Clear all Fields");
		badd.setToolTipText("It is add new record");
		bupdt.setToolTipText("Click here to Update the Record");
		bdel.setToolTipText("Click here to Delete the Record");
		bsrch.setToolTipText("Click here to Search the Record");
		bbck.setToolTipText("Click here to go Back through this Page");
		bext.setToolTipText("Click here to Exit");
		bshow.setToolTipText("Click here to Show all Records");
						
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/project","root","");
			stm =  cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			rs = stm.executeQuery("select * from deal_info order by d_name");			
			while(rs.next())
				cbfrom.addItem(rs.getString(2));
			
			rs1 = stm.executeQuery("select * from deal_bill");
			while(rs1.next())
			{
				tbrd.setText(rs1.getString(3));
				int x = Integer.parseInt(rs1.getString(6));
				x = x+1000;
				tprice.setText(""+x);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		t.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(srch_imei.isSelected())
				{
					char c = e.getKeyChar();
					
					if(Character.isDigit(c) && t.getText().length()<10)
						super.keyTyped(e);
					
					else
					{
						e.consume();
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();
					}
				}
				
				if(e.getKeyChar()==KeyEvent.VK_ENTER)
				{
					try
					{
						li.clear();
						if(srch_mod.isSelected())
							rs = stm.executeQuery("select * from prod_info where p_modelno like '%"+t.getText()+"%'");
						else
							rs = stm.executeQuery("select * from prod_info where p_imei like '%"+t.getText()+"%'");
						
						while(rs.next())
							li.addItem(rs.getString(3)+" - "+rs.getString(4));
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});
		
		text_validator(timei);
		text_validator(tprice);
		
		bclr.addActionListener(this);
		badd.addActionListener(this);
		bupdt.addActionListener(this);
		bdel.addActionListener(this);
		bsrch.addActionListener(this);
		bbck.addActionListener(this);
		bshow.addActionListener(this);
		bext.addActionListener(this);
		li.addItemListener(this);
		cbfrom.addItemListener(this);
				
		getMax();
		tid.setEditable(false);
		tdid.setEditable(false);
		tbrd.setEditable(false);
		tprice.setEditable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	void text_validator(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(tt.getText().length() < 15 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
					super.keyTyped(e);
					
				else
				{
					e.consume();						
					Toolkit tk = Toolkit.getDefaultToolkit();
					tk.beep();
				}
			}
		});
	}
	
	public void refresh()
	{
		tid.setText("");
		tbrd.setText("");
		tmod.setText("");
		tprice.setText("");
	//	cbfrom.setSelectedIndex(0);
		tdisp.setText("");
		tstge.setText("");
		tram.setText("");
		tdisk.setText("");
		tbkup.setText("");
		tcam.setText("");
		tver.setText("");
		timei.setText("");
		tdid.setText("");
		pnl.setVisible(false);
		badd.setEnabled(true);
	}
	
	void getMax()
	{
		try
		{
			sql = "select max(p_id) from prod_info";
			rs = stm.executeQuery(sql);
			rs.next();
			id = rs.getInt(1);
			id++;
			tid.setText(""+id);
		}
		catch(Exception ex)
		{
			id=1;
			tid.setText(""+id);
		}
	}
	
	
	public void dispTab()
	{
		try
		{
			rs = stm.executeQuery("select count(*) from prod_info");
			rs.next();
						
			r_cnt = rs.getInt(1);
			data = new String[r_cnt][3];
						
			rs = stm.executeQuery("select * from prod_info order by p_id");
			i=0;
			while(rs.next())
			{
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(5);
			
				i++;
			}
						
			JTable table = new JTable(data,colHead);
			add(table);
			table.setEnabled(false);
					
			int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;				
						
			JScrollPane jsp = new JScrollPane(table,v,h);
			add(jsp);
			jsp.setBounds(900,150,500,500);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
		
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==bclr)
			{
				refresh();
				getMax();
			}
			
			if(e.getSource()==badd)
			{
				if(tid.getText().length()==0 || tbrd.getText().length()==0 || tmod.getText().length()==0 || tprice.getText().length()==0 || tdisp.getText().length()==0 || tstge.getText().length()==0 || tram.getText().length()==0 || tdisk.getText().length()==0 || tbkup.getText().length()==0 || tcam.getText().length()==0 || tver.getText().length()==0 || timei.getText().length()==0 || cbfrom.getSelectedIndex()==0)
							
					JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);     

				else
				{
					
					sql = "insert into prod_info values("+tid.getText()+",'"+tbrd.getText()+"','"+tmod.getText()+"','"+timei.getText()+"',"+tprice.getText()+","+tdisp.getText()+","+tstge.getText()+","+tram.getText()+","+tdisk.getText()+","+tbkup.getText()+","+tcam.getText()+","+tver.getText()+","+tdid.getText()+")";
												
					prstm = cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
												
					JOptionPane.showMessageDialog(null,"*** Record Successfully Saved ***");
					dispTab();
					refresh();
					getMax();
				}
			}
		
			if(e.getSource()==bupdt)
			{
				if(flg==0)
					JOptionPane.showMessageDialog(null,"Search the Record Before Update !!!","UPDATE Error",JOptionPane.WARNING_MESSAGE);
				
				else
				{
					if(tid.getText().length()==0 || tbrd.getText().length()==0 || tmod.getText().length()==0 || tprice.getText().length()==0 || tdisp.getText().length()==0 || tstge.getText().length()==0 || tram.getText().length()==0 || tdisk.getText().length()==0 || tbkup.getText().length()==0 || tcam.getText().length()==0 || tver.getText().length()==0 || timei.getText().length()==0 || cbfrom.getSelectedIndex()==0)
						
						JOptionPane.showMessageDialog(null,"All fields are required !!!","UPDATE Error",JOptionPane.WARNING_MESSAGE);
					
					else
					{
						sql = "update prod_info set p_brand='"+tbrd.getText()+"',p_modelno='"+tmod.getText()+"',p_imei='"+timei.getText()+"',p_price="+tprice.getText()+",p_display="+tdisp.getText()+",p_storage="+tstge.getText()+",p_ram="+tram.getText()+",p_disk="+tdisk.getText()+",p_battbkup="+tbkup.getText()+",p_camera="+tcam.getText()+",p_verison="+tver.getText()+",d_id="+tdid.getText()+" where p_id="+tid.getText();
						
						prstm = cn.prepareStatement(sql);
						prstm.execute();
						prstm.close();						
						JOptionPane.showMessageDialog(null,"*** Record Successfully Updated ***");
						dispTab();
						refresh();
						getMax();
						flg=0;
					}
				}
			}
		
			if(e.getSource()==bdel)
			{
				if(flg==0)
					JOptionPane.showMessageDialog(null,"Search the Record Before Delete !!!","DELETE Error",JOptionPane.WARNING_MESSAGE);
				
				else
				{
					if(tid.getText().length()==0 || tbrd.getText().length()==0 || tmod.getText().length()==0 || tprice.getText().length()==0 || tdisp.getText().length()==0 || tstge.getText().length()==0 || tram.getText().length()==0 || tdisk.getText().length()==0 || tbkup.getText().length()==0 || tcam.getText().length()==0 || tver.getText().length()==0 || timei.getText().length()==0 || cbfrom.getSelectedIndex()==0)
						
						JOptionPane.showMessageDialog(null,"All fields are required !!!","DELETE Error",JOptionPane.WARNING_MESSAGE);
					
					else
					{
						sql = "delete from prod_info where p_id= "+tid.getText();						
						prstm = cn.prepareStatement(sql);
						prstm.execute();
						prstm.close();						
						JOptionPane.showMessageDialog(null,"*** Record Successfully Deleted ***");
						dispTab();
						refresh();
						getMax();
						flg=0;
					}
				}
			}
		
			if(e.getSource()==bsrch)
			{
				badd.setEnabled(false);
				pnl.setVisible(true);
				t.requestFocus();
			}
		
			if(e.getSource()==bshow)
			{
				dispTab();
			}
			
			if(e.getSource()==bbck)
			{
				new choicefrm();
				dispose();
			}
		
			if(e.getSource()==bext)
			{
				System.exit(0);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		try
		{
			if(e.getSource()==cbfrom)
			{
				sql = "select * from deal_info where d_name='" +cbfrom.getSelectedItem()+ "'";
				rs=stm.executeQuery(sql);
                rs.next();
				tdid.setText(rs.getString(1));
                int z = Integer.parseInt(tdid.getText());
				
				sql = "select * from prod_info where d_id="+z;
                rs=stm.executeQuery(sql);
			//	cbfrom.removeItemListener(this);
				//cbfrom.removeAllItems();
			//	cbfrom.addItem("Select Delear Name");
				
			/*	while(rs.next())
                {
                    cbfrom.addItem(rs.getString(2));
                }*/
            //    rs.close();
            //    cbfrom.addItemListener(this);
			}
			
			if(e.getSource()==li)
			{
				String arr[] = li.getSelectedItem().split("\\-");
				
				if(srch_mod.isSelected())
					sql = "select * from prod_info,deal_info where prod_info.p_modelno = '"+arr[0]+"' AND prod_info.d_id=deal_info.d_id";
				else
					sql = "select * from prod_info,deal_info where prod_info.p_imei = '"+arr[1]+"' AND prod_info.d_id=deal_info.d_id";
				
				rs = stm.executeQuery(sql);
				rs.next();
				tid.setText(rs.getString(1));
				tbrd.setText(rs.getString(2));
				tmod.setText(rs.getString(3));
				timei.setText(rs.getString(4));
				tprice.setText(rs.getString(5));
				tdisp.setText(rs.getString(6));
				tstge.setText(rs.getString(7));
				tram.setText(rs.getString(8));
				tdisk.setText(rs.getString(9));
				tbkup.setText(rs.getString(10));
				tcam.setText(rs.getString(11));
				tver.setText(rs.getString(12));
				tdid.setText(rs.getString(14));
				cbfrom.removeItemListener(this);
				cbfrom.setSelectedItem(rs.getString(15));
		
				cbfrom.addItemListener(this);
				flg=1;
				t.setText("");
				li.clear();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new prod_details();
	}
}