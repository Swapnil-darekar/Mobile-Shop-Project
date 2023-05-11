import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class custbill1 extends JFrame implements ActionListener
{
  JLabel l_bno,l_bdt,l_cid,l_cname,l_pid,l_pname,l_price,l_ptype,l_qnt,l_tot,ltitle,l_btm,l_srch,l_id,l_img,l_gtot;
  JTextField t_bno,t_cid,t_cname,t_pid,t_pname,t_price,t_qnt,t_tot,t_btm,t_srch,t_id,t_gtot;
  DateButton bdt;
  JComboBox cb_ptype,cb_srch;
  JButton sve,srch,clr,ext,bill,prnt,bk,edit;
  Font f;
  ImageIcon i;
  
    Connection cn = null;
    PreparedStatement prstm;
     ResultSet rs;
    Statement stm;
    String sql;
    int bno,tid,tot1=0;
    
  custbill1()
  {
    super("Customer Bill");
    setSize(900,600);
    setLocation(250,250);
    setLayout(null);
  //mem alloc
         i=new ImageIcon("logo.png");
         l_img=new JLabel(i);
          
         f=new Font("Arial",Font.BOLD,25);
         ltitle=new JLabel("CUSTOMER BILL");
         ltitle.setFont(f);
         l_bno = new JLabel("Bill No.");
         l_bdt = new JLabel("Bill Date:");
         l_btm = new JLabel("Bill Time:");
         l_cid = new JLabel("Customer ID:");
         l_cname = new JLabel("Customer Name:");
         l_pid = new JLabel("Product ID:");
         l_pname = new JLabel("Product Name:");
         l_ptype = new JLabel("Product Type:");
         l_price = new JLabel("Price:");
         l_qnt = new JLabel("Quantity:");
         l_tot = new JLabel("Total:");
         l_srch = new JLabel("Search By:");
         l_gtot = new JLabel("Grant Total");
         t_gtot = new JTextField();
         l_id = new JLabel("Trans ID");
         t_id = new JTextField();
         t_bno = new JTextField();
          
         bdt = new DateButton();
         t_btm = new JTextField();
         t_cid = new JTextField();
         t_cname = new JTextField();
         t_pid = new JTextField();
         t_pname = new JTextField();
         cb_ptype = new JComboBox();
         cb_srch = new JComboBox();
         t_price = new JTextField();
         t_qnt = new JTextField();
         t_tot = new JTextField();
         t_srch = new JTextField();
         

         sve = new JButton("Save");
         srch = new JButton("Search");
        // mdf = new JButton("Modify");
         clr = new JButton("Clear");
         ext = new JButton("Exit");
         bill = new JButton("Add to Bill");
         prnt = new JButton("Print");
         bk = new JButton("Back");
         edit = new JButton("Edit");

   //add
      add(l_bno);     add(l_bdt);    add(l_cid);   add(l_cname);    add(l_pid);  add(l_btm);   add(l_srch);
      add(l_pname);   add(l_ptype);  add(l_price); add(l_qnt);     add(l_tot);   add(ltitle);
      add(t_bno);     add(bdt);      add(t_cid);   add(t_cname);    add(t_pid);   
      add(l_id);      add(t_id);     add(l_gtot);  add(t_gtot);
      add(t_pname);   add(t_price);  add(t_qnt);   add(t_tot);      add(t_btm);  add(t_srch);
      add(cb_ptype);  add(cb_srch);  cb_srch.addItem("<select>");   cb_srch.addItem("By ID");
      cb_ptype.addItem("Select");
      cb_ptype.addItem("Hair Product");
   cb_ptype.addItem("Skin Product");   cb_ptype.addItem("Body Product");
   cb_ptype.addItem("Eye Product");    cb_ptype.addItem("Nail Product");
   cb_ptype.addItem("Perfume Product");  cb_ptype.addItem("Lip Product");
    add(sve);    add(srch);   add(clr);    add(ext);     add(bill);  
    add(prnt);   add(bk);     add(edit);
       // add(l_img);
 //setBounds

       ltitle.setBounds(320,20,250,30);

    // l_img.setBounds(100,280,600,300);
      // limg1.setBounds(560,210,430,250);
   
       l_id.setBounds(50,90,80,30);
       t_id.setBounds(140,90,50,30);

       l_bno.setBounds(230,90,80,30);
       t_bno.setBounds(300,90,50,30);

      l_bdt.setBounds(390,90,80,30);
      bdt.setBounds(470,90,150,30);
   
      l_btm.setBounds(660,90,80,30);
      t_btm.setBounds(740,90,100,30);

      
      l_cid.setBounds(40,150,100,30);
      t_cid.setBounds(150,150,50,30);

      l_cname.setBounds(240,150,130,30);
      t_cname.setBounds(370,150,150,30);

      l_pid.setBounds(40,210,100,30);
      t_pid.setBounds(140,210,60,30);
 
      l_pname.setBounds(240,210,130,30);
      t_pname.setBounds(370,210,150,30);

      l_ptype.setBounds(550,210,110,30);
      cb_ptype.setBounds(680,210,150,30);

     l_price.setBounds(70,280,60,30);
     t_price.setBounds(140,280,50,30);

     l_qnt.setBounds(280,280,80,30);
     t_qnt.setBounds(370,280,50,30);

     l_tot.setBounds(480,280,60,30);
     t_tot.setBounds(540,280,80,30);

     l_gtot.setBounds(680,280,100,30);
     t_gtot.setBounds(780,280,80,30);


     l_srch.setBounds(630,340,100,30);
      cb_srch.setBounds(740,340,100,30);
      t_srch.setBounds(740,380,100,30);


    sve.setBounds(150,400,100,30);
    srch.setBounds(260,400,100,30);
   // mdf.setBounds(270,330,100,30);
    clr.setBounds(370,400,100,30);
    ext.setBounds(480,400,100,30);
    bill.setBounds(130,450,130,30);
    prnt.setBounds(270,450,100,30);
    bk.setBounds(380,450,100,30);
    edit.setBounds(490,450,100,30);

    sve.setMnemonic('S');
    srch.setMnemonic('r');
    //mdf.setMnemonic('M');
    clr.setMnemonic('C');
    ext.setMnemonic('E');
    bill.setMnemonic('A');
    prnt.setMnemonic('P');
    bk.setMnemonic('B');
    edit.setMnemonic('E');
   sve.addActionListener(this);
   srch.addActionListener(this);
  // mdf.addActionListener(this);
   clr.addActionListener(this);
   ext.addActionListener(this);
   bill.addActionListener(this);
   prnt.addActionListener(this);
   bk.addActionListener(this);
   edit.addActionListener(this);
   t_id.setEditable(true);
   t_bno.setEditable(false);
   t_cname.setEditable(false);
   t_pname.setEditable(false);
   t_price.setEditable(false);
   t_tot.setEditable(false);
   text_validator(t_cid);
   text_validator(t_pid);
   text_validator(t_qnt);
   
     
   try
    {
     cn = DriverManager.getConnection("jdbc:mysql:///cosmetic","root","");
     stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     rs=stm.executeQuery("select * from prod_bill order by b_no");
     rs.first();     
     
    }
   catch(Exception e1)
   {
    e1.printStackTrace();
   }
   
   t_cid.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              
     rs=stm.executeQuery("select * from customer where cid="+t_cid.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
     t_cname.setText(rs.getString(2));
            
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });

   t_pid.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              
     rs=stm.executeQuery("select * from product where pid="+t_pid.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
      {
    t_pname.setText(rs.getString(3));
             cb_ptype.setSelectedItem(rs.getString(2));
             t_price.setText(rs.getString(6));
            } 
            
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });

   t_qnt.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
               rs=stm.executeQuery("select * from product where pid="+t_pid.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
      {
          int tqnt=Integer.parseInt(rs.getString(4));
          int cqnt=Integer.parseInt(t_qnt.getText());
          if(cqnt<=tqnt)
           {
                int qnt=tqnt-cqnt;
              sql="update product set qnt="+qnt+" where pid="+t_pid.getText();
              prstm = cn.prepareStatement(sql);
               prstm.execute();
               prstm.close();
            JOptionPane.showMessageDialog(null,"Remaining Stock is:"+qnt);
              int tot,qnt1,pri;
              qnt1=Integer.parseInt(t_qnt.getText());
              pri=Integer.parseInt(t_price.getText());
              tot=qnt1*pri;
               t_tot.setText(""+tot);
             // int tot,tot1=0;
       //tot=Integer.parseInt(t_tot.getText());
       tot1=tot1+tot;
       t_gtot.setText(""+tot1);
       
              //t_gtot.setText(""+tot1);
             //System.out.println(tot1);
             }
          else
           {
           JOptionPane.showMessageDialog(null,"Stock is not available","avaibility",JOptionPane.WARNING_MESSAGE);
           t_pid.setText("");
         t_pname.setText("");
        cb_ptype.setSelectedIndex(0);
         t_price.setText("");
        t_qnt.setText("");
        t_tot.setText("");
        t_btm.requestFocus();
  
          }
       } 
    
                       }
          catch(Exception ee) { ee.printStackTrace();}
        }
      }
    });



  setVisible(true);
 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   getMax();
   getMax1();
  }
 

 void text_validator(final JTextField tt)
{
   tt.addKeyListener(new KeyAdapter()
   {
    public void keyTyped(KeyEvent e)
	 {
	  if(tt.getText().length()<10 && e.getKeyChar()>='0' && e.getKeyChar()<='9')
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
       sql="select max(b_no) from prod_bill";
       rs=stm.executeQuery(sql);
       rs.next();
       bno=rs.getInt(1);
         bno++;
         t_bno.setText(""+bno);
     }
    catch(Exception e2)
    {
      bno=1;
      t_bno.setText(""+bno);
    }
  }

 void getMax1()
  {
    try
     {
       sql="select max(t_id) from prod_bill";
       rs=stm.executeQuery(sql);
       rs.next();
       tid=rs.getInt(1);
         tid++;
         t_id.setText(""+tid);
     }
    catch(Exception e2)
    {
      tid=1;
      t_id.setText(""+tid);
    }
  }


 public void actionPerformed(ActionEvent e)
  {  
     try
      {
        if(e.getSource()==edit)
         {
           //  new ();
               dispose();
          }
     if(e.getSource()==sve)
       {
         try
         {
          if(t_bno.getText().length()==0 || bdt.getText().length()==0 || t_gtot.getText().length()==0)
            JOptionPane.showMessageDialog(null,"All Fields Are Neccessary");
         else
          {
             int ans=JOptionPane.showConfirmDialog(null,"Do you want to save?");
             if(ans==0)
     {
      
      String sql;
     // int pri,pid;
      
    sql = "insert into bill values("+t_bno.getText()+",'" +bdt.getText()+ "'," +t_gtot.getText()+")";
     prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
     JOptionPane.showMessageDialog(null,"Record successfully added...");
      }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not saved");
      t_btm.setText("");
      bdt.setText("");
      t_cid.setText("");
      t_cname.setText("");
      t_pid.setText("");
      t_pname.setText("");
      cb_ptype.setSelectedIndex(0);
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
      
     }
          }
        }
        catch(Exception ex)
         {
           ex.printStackTrace();
         }        
       }
     if(e.getSource()==srch)
      {
         try
      {
      sve.setEnabled(false);
       // srch.setEnabled(false);
        //mdf.setEnabled(false);
        bill.setEnabled(false);
        prnt.setEnabled(false);
        ext.setEnabled(false);
        bk.setEnabled(false);
       
        
      if(cb_srch.getSelectedIndex()==0 || t_srch.getText().length()==0)
       {
         JOptionPane.showMessageDialog(null,"select ID and enter corresponding input");
         t_srch.requestFocus();
       }
      else if(cb_srch.getSelectedIndex()==1)
         {
           
           String sql;
           sql="select * from prod_bill where t_id="+t_srch.getText();
           rs=stm.executeQuery(sql);
           rs.next();
           
           t_id.setText(rs.getString(1));
           t_bno.setText(rs.getString(2));
           bdt.setText(rs.getString(3));
          
           t_btm.setText(rs.getString(4));
           t_cname.setText(rs.getString(5));
           t_pname.setText(rs.getString(6));
           cb_ptype.setSelectedItem(rs.getString(7));
           t_cid.setText(rs.getString(8));
           t_pid.setText(rs.getString(9));
           t_price.setText(rs.getString(10));
           t_qnt.setText(rs.getString(11));
           t_tot.setText(rs.getString(12));
           
         }
        
            }
   catch(Exception ex2)
    {
      JOptionPane.showMessageDialog(null,"Record Does Not Exists");
    } 
      }
    if(e.getSource()==clr)
     {
      //bdt.setText("");
      t_btm.setText("");
      t_cid.setText("");
      t_cname.setText("");
      t_pid.setText("");
      t_pname.setText("");
      cb_ptype.setSelectedIndex(0);
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
      sve.setEnabled(true);
      srch.setEnabled(true);
      ext.setEnabled(true);
      bill.setEnabled(true);
      prnt.setEnabled(true);
      bk.setEnabled(true);
      getMax();  
      getMax1();
     }
    if(e.getSource()==ext)
      {
        System.exit(0);
      }
  
    if(e.getSource()==bk)
      {
        new choicefrm();
        dispose();
      }
    
    if(e.getSource()==bill)
     {
       srch.setEnabled(false);
        
        //mdf.setEnabled(false);
        //bill.setEnabled(false);
        //prnt.setEnabled(false);
        ext.setEnabled(false);
         bk.setEnabled(false);        

      if(bdt.getText().length()==0 || t_btm.getText().length()==0 || t_cid.getText().length()==0 || t_pid.getText().length()==0 || t_qnt.getText().length()==0)
    {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
        //getMax();
       
    }
   else
    {
       
       sql="select max(t_id) from prod_bill where b_no="+t_bno.getText();
         prstm = cn.prepareStatement(sql);
         prstm.execute();
         prstm.close();
     
  int ans=JOptionPane.showConfirmDialog(null,"Do you want to save?");
     if(ans==0)
     {
      
      String sql;
     // int pri,pid;
      
    sql = "insert into prod_bill values("+t_id.getText()+","+t_bno.getText()+ ",'" +bdt.getText()+ "','" +t_btm.getText()+ "','"+t_cname.getText()+"','"+t_pname.getText()+"','"+cb_ptype.getSelectedItem()+"',"+t_cid.getText()+"," +t_pid.getText()+",'"+t_price.getText()+"',"+t_qnt.getText()+",'"+t_tot.getText()+"')";
     prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
     JOptionPane.showMessageDialog(null,"Record successfully added...");
        getMax1();
      t_pid.setText("");
      t_pname.setText("");
      cb_ptype.setSelectedIndex(0);
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
          }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not saved");
      t_btm.setText("");
      bdt.setText("");
      t_cid.setText("");
      t_cname.setText("");
      t_pid.setText("");
      t_pname.setText("");
      cb_ptype.setSelectedIndex(0);
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
      
     }
    }

      }
    if(e.getSource()==prnt)
     {tot1=0;
       // new customerbill(Integer.parseInt(t_bno.getText()));
       /* sql="select * from prod_bill where b_no="+t_bno.getText();
              prstm = cn.prepareStatement(sql);
               prstm.execute();
               prstm.close();*/

      }
      }
     catch(Exception ex)
      {
        ex.printStackTrace();
      }
  }
 public static void main(String args[])
  {
     new custbill1();
  }
}

