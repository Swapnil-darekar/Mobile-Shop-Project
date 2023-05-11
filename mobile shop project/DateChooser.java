import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class DateChooser extends JDialog implements ItemListener, MouseListener, FocusListener, KeyListener, ActionListener
{
    String MONTHS[] = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
    String DAYS[] =	new String[] {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    Color WEEK_DAYS_FOREGROUND = Color.black, DAYS_FOREGROUND = Color.blue,SELECTED_DAY_FOREGROUND = Color.white;
    Color SELECTED_DAY_BACKGROUND = new Color(0,50,150);
    Border EMPTY_BORDER = BorderFactory.createEmptyBorder(1,1,1,1);
    Border FOCUSED_BORDER = BorderFactory.createLineBorder(new Color(60,0,150),1);
    int FIRST_YEAR = 1970, LAST_YEAR = 2030;
    GregorianCalendar calendar;
    JLabel[][] days;
    FocusablePanel daysGrid;
    JComboBox month, year;
    JButton ok,cancel;
    int offset, lastDay;
    JLabel day;
    boolean okClicked;

    class FocusablePanel extends JPanel
    {
		public FocusablePanel( LayoutManager layout )
        {
			super( layout );
		}

		public boolean isFocusable()
        {
			return true;
		}
    }

    void construct()
    {
		calendar = new GregorianCalendar();

		month = new JComboBox(MONTHS);
		month.addItemListener( this );

		year = new JComboBox();

		for ( int i=FIRST_YEAR; i<=LAST_YEAR; i++ )
			year.addItem( Integer.toString(i) );

		year.addItemListener( this );

		days = new JLabel[7][7];

		for ( int i=0; i<7; i++ )
        {
			days[0][i] = new JLabel(DAYS[i],JLabel.RIGHT);
			days[0][i].setForeground( WEEK_DAYS_FOREGROUND );
		}

		for ( int i=1; i<7; i++ )
			for ( int j=0; j<7; j++ )
			{
				days[i][j] = new JLabel(" ",JLabel.RIGHT);
				days[i][j].setForeground( DAYS_FOREGROUND );
				days[i][j].setBackground( SELECTED_DAY_BACKGROUND );
				days[i][j].setBorder( EMPTY_BORDER );
				days[i][j].addMouseListener( this );
			}

		ok = new JButton("Ok");
		ok.addActionListener( this );
		cancel = new JButton("Cancel");
		cancel.addActionListener( this );

		JPanel monthYear = new JPanel();
		monthYear.add( month );
		monthYear.add( year );

		daysGrid = new FocusablePanel(new GridLayout(7,7,5,0));
		daysGrid.addFocusListener( this );
		daysGrid.addKeyListener( this );

		for ( int i=0; i<7; i++ )
			for ( int j=0; j<7; j++ )
			daysGrid.add( days[i][j] );

		daysGrid.setBackground( Color.white );
		daysGrid.setBorder( BorderFactory.createLoweredBevelBorder() );
		JPanel daysPanel = new JPanel();
		daysPanel.add( daysGrid );

		JPanel buttons = new JPanel();
		buttons.add( ok );
		buttons.add( cancel );

		Container dialog = getContentPane();
		dialog.add( "North", monthYear );
		dialog.add( "Center", daysPanel );
		dialog.add( "South", buttons );

		pack();
		setResizable( false );
    }

    int getSelectedDay()
    {
		if ( day == null )
			return -1 ;
		try {
			return Integer.parseInt(day.getText());
		}
        catch (Exception e )
        {
            JOptionPane.showMessageDialog(null,e);
		}
		return -1;
    }

    void setSelected( JLabel newDay )
    {
		if ( day != null )
        {
			day.setForeground( DAYS_FOREGROUND );
			day.setOpaque( false );
			day.setBorder( EMPTY_BORDER );
		}

		day = newDay;
		day.setForeground( SELECTED_DAY_FOREGROUND );
		day.setOpaque( true );

        if ( daysGrid.hasFocus() )
			day.setBorder( FOCUSED_BORDER );
    }

    void setSelected( int newDay )
    {
    	setSelected( days[(newDay+offset-1)/7+1][(newDay+offset-1)%7] );
    }

    void update()
    {
		int iday = getSelectedDay();

		for ( int i=0; i<7; i++ )
        {
			days[1][i].setText( " " );
			days[5][i].setText( " " );
			days[6][i].setText( " " );
		}

		calendar.set( Calendar.DATE, 1 );
		calendar.set( Calendar.MONTH, month.getSelectedIndex());
		calendar.set( Calendar.YEAR, year.getSelectedIndex()+FIRST_YEAR );

		offset = calendar.get(Calendar.DAY_OF_WEEK)-Calendar.SUNDAY;
		lastDay = calendar.getActualMaximum(Calendar.DATE);
		for ( int i=0; i<lastDay; i++ )
			days[(i+offset)/7+1][(i+offset)%7].setText( String.valueOf(i+1) );

		if ( iday != -1 )
        {
			if ( iday > lastDay )
			iday = lastDay;
			setSelected( iday );
		}
    }

    public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == ok ) okClicked = true;
		setVisible(false);
    }

    public void focusGained( FocusEvent e )
    {
		setSelected( day );
    }

    public void focusLost( FocusEvent e )
    {
		setSelected( day );
    }

    public void itemStateChanged( ItemEvent e )
    {
		update();
    }

    public void keyPressed( KeyEvent e )
    {
	int iday = getSelectedDay();

	switch ( e.getKeyCode() )
    {
	case KeyEvent.VK_LEFT:
	    if ( iday > 1 )
		setSelected( iday-1 );
	    break;
	case KeyEvent.VK_RIGHT:
	    if ( iday < lastDay )
		setSelected( iday+1 );
	    break;
	case KeyEvent.VK_UP:
	    if ( iday > 7 )
		setSelected( iday-7 );
	    break;
	case KeyEvent.VK_DOWN:
	    if ( iday <= lastDay-7 )
		setSelected( iday+7 );
	    break;
	}
    }

    public void mouseClicked( MouseEvent e )
    {
	    JLabel day = (JLabel)e.getSource();
	    if ( !day.getText().equals(" ") )
	        setSelected( day );

	    daysGrid.requestFocus();
    }

    public void keyReleased( KeyEvent e ) {}
    public void keyTyped( KeyEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
    public void mouseExited( MouseEvent e) {}
    public void mousePressed( MouseEvent e ) {}
    public void mouseReleased( MouseEvent e) {}



    public DateChooser( Frame owner, String title )
    {
	super( owner, title, true );
    setLocation(300,300);
	construct();
    }

    public Date select( Date date )
    {
	calendar.setTime( date );
	int _day = calendar.get(Calendar.DATE);
	int _month = calendar.get(Calendar.MONTH);
	int _year = calendar.get(Calendar.YEAR);

	year.setSelectedIndex( _year-FIRST_YEAR );
	month.setSelectedIndex(_month);
	setSelected( _day );
	okClicked = false;
	setVisible(true);
	if ( !okClicked )
	    return null;
	calendar.set( Calendar.DATE, getSelectedDay() );
	calendar.set( Calendar.MONTH, month.getSelectedIndex() );
	calendar.set( Calendar.YEAR, year.getSelectedIndex()+FIRST_YEAR );
	return calendar.getTime();
    }
}