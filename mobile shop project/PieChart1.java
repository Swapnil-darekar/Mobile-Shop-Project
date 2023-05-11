import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.data.general.*;
import org.jfree.ui.*;


class PieChart1 extends JFrame
{
	JFreeChart pieChart;
	ChartPanel chartPanel;
	PieChart1()
	{
		super("Pie Chart demo");
		setSize(600,600);
		setLocation(300,100);
		
		pieChart=ChartFactory.createPieChart("Watch sales",createDataset(),true,true,false);
		
		chartPanel=new ChartPanel( pieChart );
		setContentPane( chartPanel );
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	static PieDataset  createDataset()
	{
		DefaultPieDataset dataset =new DefaultPieDataset( );
		
		
		
		
		dataset.setValue("Tissot" , new Double(20) );
		dataset.setValue("Fasttrack" , new Double(30) );
		dataset.setValue("Rado" , new Double(40) );
		dataset.setValue("Fossil" , new Double(10) );
		dataset.setValue("Titan" , new Double(70) );
		return dataset;
	}
	
	public static void main(String args[])
	{
		new PieChart1();
	}
}


