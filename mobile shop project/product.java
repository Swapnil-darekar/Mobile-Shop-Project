import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class product extends JFrame implements ActionListener,ItemListener
{
  JButton insrt,dlt,udt,srch,bk,clr,ext;
  ImageIcon i;
  JLabel lname,lpri,lpdt,ledt,ltyp,lid,lqnt,lcomp,pl,ltitle,limg;
  JTextField tname,tpri,tid,tqnt,tcomp;
  JComboBox cb;
  JPanel pnl;
  JTextField t;
  JRadioButton rb1,rb2;
  ButtonGroup g;
  List lst;
  DateButton tpdt,tedt;
  Font f;

   Connection cn = null;
    PreparedStatement prstm;
     ResultSet rs;
    Statement stm;
    String sql;
    int pno;
  
product()
       {
            super("Product Information");
            setSize(1100,500);
            setLocation(100,200);
            setLayout(null);
            setBackground(Color.GRAY);
  i=new ImageIcon("prod.jpg");
  limg=new JLabel(i);
  lid=new JLabel("Product ID");
  lname=new JLabel("Product Name");
  ltyp=new JLabel("Product Type");
  ltitle=new JLabel("PRODUCT DETAILS");
  f=new Font("Arial",Font.BOLD,25);
  ltitle.setFont(f);
  pl=new JLabel("Search By:");
  lqnt=new JLabel("Product Quantity");
  lcomp=new JLabel("Product Company");
  lpri=new JLabel("Product Prise");
  lpdt=new JLabel("Packing Date");
  ledt=new JLabel("Expire Date");
  tname=new JTextField();
  cb=new JComboBox();
  tqnt=new JTextField();
  tcomp=new JTextField();
  tpri=new JTextField();
  tpdt=new DateButton();
  tedt=new DateButton();
  tid=new JTextField();
  insrt=new JButton("Insert");
  dlt=new JButton("Delete");
  udt=new JButton("Update");
  srch=new JButton("Search");
  bk=new JButton("Back");
  clr=new JButton("Clear");
  ext=new JButton("Exit");
  
  pnl=new JPanel();
  t=new JTextField();
  rb1=new JRadioButton("By Product Type",true);
  rb2=new JRadioButton("By Product Name");
  
  lst=new List();
  g=new ButtonGroup();
   
   add(lname);  add(ltyp);   add(lpri);   add(lpdt);  add(ledt);   add(lid);  add(lqnt);  add(lcomp); 
   add(tname);  add(tpri);   add(tpdt);  add(tedt);   add(cb);     add(tid);  add(tqnt);  add(tcomp);
   add(ltitle);
   cb.addItem("<Select>");       cb.addItem("Hair Product");
   cb.addItem("Skin Product");   cb.addItem("Body Product");
   cb.addItem("Eye Product");    cb.addItem("Nail Product");
   cb.addItem("Perfume Product");  cb.addItem("Lip Product");
   add(pnl);
   
     
   pnl.setLayout(null);  
   pnl.setVisible(false);
   pnl.add(t);     pnl.add(lst); pnl.add(pl);
   pnl.add(rb1);   pnl.add(rb2);
   g.add(rb1);    g.add(rb2); 
    
   add(insrt);  add(dlt);    add(udt);    add(srch);  add(bk);    
   add(clr);    add(ext); 

    add(limg);

  limg.setBounds(0-70,20,1100,500);   
  ltitle.setBounds(350,10,350,30);
  lid.setBounds(30,100,150,30);
  tid.setBounds(190,100,50,30);

  lname.setBounds(30,140,150,30);
  tname.setBounds(190,140,150,30);
 
  ltyp.setBounds(30,180,150,30);
  cb.setBounds(190,180,150,30);
 
  lpri.setBounds(500,100,100,30);
  tpri.setBounds(620,100,50,30);

  lqnt.setBounds(270,100,130,30);
  tqnt.setBounds(410,100,50,30);

  lcomp.setBounds(360,140,150,30);
  tcomp.setBounds(520,140,150,30);

  lpdt.setBounds(360,180,150,30);
  tpdt.setBounds(520,180,150,30);

  ledt.setBounds(30,220,150,30);
  tedt.setBounds(190,220,150,30);
  
  insrt.setBounds(30,310,100,30);
  dlt.setBounds(140,310,100,30);
  udt.setBounds(250,310,100,30);
  srch.setBounds(360,310,100,30);
  bk.setBounds(470,310,100,30);
  clr.setBounds(580,310,100,30);
  ext.setBounds(690,310,100,30);
  
  pnl.setBounds(750,30,400,230);
  pl.setBounds(10,0,100,20);
  rb1.setBounds(10,30,150,20);
  rb2.setBounds(170,30,150,20);
  t.setBounds(10,60,250,20);
  lst.setBounds(10,90,250,140);
   insrt.setMnemonic('I');
   dlt.setMnemonic('D');	
   udt.setMnemonic('U');
   srch.setMnemonic('S');
   bk.setMnemonic('B');
   clr.setMnemonic('C');
   ext.setMnemonic('E');

   tid.setEditable(false);
   
   insrt.addActionListener(this);
   dlt.addActionListener(this);
   udt.addActionListener(this);
   srch.addActionListener(this);
   bk.addActionListener(this);
   clr.addActionListener(this);
   ext.addActionListener(this);
   lst.addItemListener(this);
   
   try
    {
     cn = DriverManager.getConnection("jdbc:mysql:///cosmetic","root","");
     stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     rs=stm.executeQuery("select * from product order by pid");
     
    }
   catch(Exception e1)
   {
    e1.printStackTrace();
   }
   t.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              lst.clear();
       if(rb1.isSelected())
               {
                rs=stm.executeQuery("select pname from product where ptype='"+t.getText()+"'");
                 while(rs.next())
                lst.addItem(rs.getString(1));
                }
     else
      {
     rs=stm.executeQuery("select * from product where pname like '%"+ t.getText() + "%'");
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
     
          while(rs.next())
            
            lst.addItem(rs.getString(3));
            
           } 
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });

   text_validator(tid);
   text_validator(tpri);
   text_val(tname);
   text_val(tcomp);
   setVisible(true);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   getMax();
  }

void text_validator(final JTextField tt)
{
   tt.addKeyListener(new KeyAdapter()
   {
    public void keyTyped(KeyEvent e)
	 {
	   if(tt.getText().length()<=4 && e.getKeyChar()>='0' && e.getKeyChar()<='9')
	       super.keyTyped(e); // Optional
          else
	     {
		   e.consume();  //erase the event
		 
		 }
	 }
	  
   });
  }
 void text_val(final JTextField te)
{
   te.addKeyListener(new KeyAdapter()
   {
    public void keyTyped(KeyEvent e)
	 {
	  if(e.getKeyChar()>='a' && e.getKeyChar()<='z' || e.getKeyChar()>='A' && e.getKeyChar()<='Z' || e.getKeyChar()<=' ')
	       super.keyTyped(e); // Optional
	   else
	     {
		   e.consume();  //erase the event
		 
		 }
	 }
	  
   });
  }
void getMax()
  {
    try
     {
       sql="select max(pid) from product";
       rs=stm.executeQuery(sql);
       rs.next();
       pno=rs.getInt(1);
         pno++;
         tid.setText(""+pno);
     }
    catch(Exception e2)
    {
      pno=1;
      tid.setText(""+pno);
    }
  }


public void itemStateChanged(ItemEvent e2)
 {
    if(e2.getSource()==lst)
    {
     try
      {
       if(rb1.isSelected())
      {
     sql="select * from product where pname='"+lst.getSelectedItem()+"'";
      rs=stm.executeQuery(sql);
      rs.next();
      tid.setText(rs.getString(1));
      tname.setText(rs.getString(3));
      cb.setSelectedItem(rs.getString(2));
      tpri.setText(rs.getString(6));
      tqnt.setText(rs.getString(4));
      tcomp.setText(rs.getString(5));
      tpdt.setText(rs.getString(7));
      tedt.setText(rs.getString(8));
      }
     else if(rb2.isSelected())
      {
     sql="select * from product where pname='"+lst.getSelectedItem()+"'";
      rs=stm.executeQuery(sql);
      rs.next();
      tid.setText(rs.getString(1));
      tname.setText(rs.getString(3));
      cb.setSelectedItem(rs.getString(2));
      tpri.setText(rs.getString(6));
      tqnt.setText(rs.getString(4));
      tcomp.setText(rs.getString(5));
      tpdt.setText(rs.getString(7));
      tedt.setText(rs.getString(8));
      }
      }
     catch(SQLException e3) {e3.printStackTrace();}
    }
 }
public void actionPerformed(ActionEvent e)
  {
    try
   {
    if(e.getSource()==insrt)
    {
       //insrt.setEnabled(true);
        srch.setEnabled(false);
        dlt.setEnabled(false);
        udt.setEnabled(false);
        bk.setEnabled(false);
        ext.setEnabled(false);
        
      if(tname.getText().length()==0 || cb.getSelectedIndex()==0 || tpri.getText().length()==0 || tpdt.getText().length()==0 || tedt.getText().length()==0)
    {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
         
     }
   else
    {
       
  int ans=JOptionPane.showConfirmDialog(null,"Do you want to save?");
     if(ans==0)
     {
      
      String sql;
      int pri,pid;
      String sk_name="pooja";      
      pid=Integer.parseInt(tid.getText());
      pri=Integer.parseInt(tpri.getText());
      
    sql = "insert into product values("+pid+ ",'" +cb.getSelectedItem()+ "','" +tname.getText()+ "',"+tqnt.getText()+ ",'"+tcomp.getText()+ "'," +pri+ ",'" +tpdt.getText()+ "','" +tedt.getText()+ "','"+sk_name+"')";
     prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
     JOptionPane.showMessageDialog(null,"Record successfully added...");
    
      tid.setText("");
      tname.setText("");
      cb.setSelectedIndex(0);
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tqnt.setText("");
      tcomp.setText("");
      tid.requestFocus();
    }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not saved");
      tid.setText("");
      tname.setText("");
      cb.setSelectedItem("");
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tid.requestFocus();
     }
    }
   }
   if(e.getSource()==dlt)
    {
        insrt.setEnabled(false);
        srch.setEnabled(false);
        dlt.setEnabled(true);
        udt.setEnabled(false);
        bk.setEnabled(false);
        ext.setEnabled(false);
       
       if(tname.getText().length()==0 || cb.getSelectedItem().toString().equals("<select>") || tpri.getText().length()==0 || tpdt.getText().length()==0 || tedt.getText().length()==0)
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
   else
    {
       
  int ans=JOptionPane.showConfirmDialog(null,"Are you sure to delete?");
     if(ans==0)
     {
      
      String sql;
      int pri,pid;
      String sk_name="pooja";      
      pid=Integer.parseInt(tid.getText());
      pri=Integer.parseInt(tpri.getText());
      
     sql = "delete from product where pid="+pid;
     prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
    JOptionPane.showMessageDialog(null,"Record successfully deleted...");
     
     tid.setText("");
      tname.setText("");
      cb.setSelectedIndex(0);
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tqnt.setText("");
      tcomp.setText("");
      tid.requestFocus();
    }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not deleted");
        tid.setText("");
      tname.setText("");
      cb.setSelectedItem("");
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tid.requestFocus();
     }
    }
    }
    
   if(e.getSource()==udt)
    {
          insrt.setEnabled(false);
          srch.setEnabled(false);
        dlt.setEnabled(false);
        udt.setEnabled(true);
        bk.setEnabled(false);
        ext.setEnabled(false);
        //tid.setEditable(false);
       // tpdt.setEnabled(false);
        //tedt.setEnabled(false);
        //tqnt.setEditable(false);
        cb.setEnabled(false);
         if(tname.getText().length()==0 || cb.getSelectedItem().toString().equals("<select>") || tpri.getText().length()==0 || tpdt.getText().length()==0 || tedt.getText().length()==0)
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
   else
    {
       
  int ans=JOptionPane.showConfirmDialog(null,"Do you modify record?");
     if(ans==0)
     {
      
      String sql;
      int pri,pid;
      String sk_name="pooja";      
      pid=Integer.parseInt(tid.getText());
      pri=Integer.parseInt(tpri.getText());
      
       sql = "update product set pname='"+tname.getText()+"',price="+pri+",qnt="+tqnt.getText()+" where pid="+pid;
prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
    JOptionPane.showMessageDialog(null,"Record successfully modyfied...");
     
     tid.setText("");
      tname.setText("");
      cb.setSelectedIndex(0);
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tqnt.setText("");
      tcomp.setText("");
      tid.requestFocus();
    }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not modify");
       tid.setText("");
      tname.setText("");
      cb.setSelectedItem("");
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tid.requestFocus();
     }
    }
    }
    
    
   if(e.getSource()==srch)
    {
        insrt.setEnabled(false);
        srch.setEnabled(true);
        dlt.setEnabled(true);
        udt.setEnabled(true);
        bk.setEnabled(false);
        ext.setEnabled(false);
       
       pnl.setVisible(true);
        /* if(rb2.isSelected())
        {
          sql="select * from product where pid="+t.getText();
          rs=stm.executeQuery(sql);
         rs.next();
         tid.setText(rs.getString(1));
         tname.setText(rs.getString(3));
         cb.setSelectedItem(rs.getString(2));
         tqnt.setText(rs.getString(4));
         tcomp.setText(rs.getString(5));
         tpri.setText(rs.getString(6));
         tpdt.setText(rs.getString(7));
         tedt.setText(rs.getString(8));
         
       }*/
     
     }
   
   if(e.getSource()==bk)
   {
    // login2 m1 = new login2();
         //   m1.setVisible(true);
            setVisible(false);
      
        }
   if(e.getSource()==clr)
   {
     tid.setText("");
      tname.setText("");
      cb.setSelectedIndex(0);
      tpri.setText("");
      tpdt.setText("");
      tedt.setText("");
      tqnt.setText("");
      tcomp.setText("");
      t.setText("");
      insrt.setEnabled(true);
        srch.setEnabled(true);
        dlt.setEnabled(true);
        udt.setEnabled(true);
        bk.setEnabled(true);
        ext.setEnabled(true);
       tid.setEditable(true);
        tpdt.setEnabled(true);
        tedt.setEnabled(true);
        tqnt.setEditable(true);
        cb.setEnabled(true);
        getMax();
       
      lst.clear();
   }
  if(e.getSource()==ext)
	{
         System.exit(0);
	}
  
    }
   catch(Exception e1)
    {
	  System.out.println(e1);
     }
   }
 public void refresh()
 {
  try
   {
    rs=stm.executeQuery("select * from product order by pid");
	rs.next();    display();    prstm.close();
   }
   catch(Exception ex)
   {
    ex.printStackTrace();
   }
  }

 public void display()
  {
   try
   {
   tid.setText(rs.getString(1));
   cb.setSelectedItem(rs.getString(2));
   tname.setText(rs.getString(3));
   tqnt.setText(rs.getString(4));
   tcomp.setText(rs.getString(5));
   tpri.setText(rs.getString(6));
   tpdt.setText(rs.getString(7));
   tedt.setText(rs.getString(8));
  }
  catch(Exception ex)
  {
   ex.printStackTrace();
  }
 }
 
public static void main(String args[])
  {
   new product();
  }
}

