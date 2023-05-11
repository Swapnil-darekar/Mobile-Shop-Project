import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;


class DateButton extends JButton
{
     static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
     static DateChooser DATE_CHOOSER = new DateChooser((JFrame)null,"Select Date");
     Date date;

   public void fireActionPerformed( ActionEvent e )
    {
	    Date newDate = DATE_CHOOSER.select(date);
	    if (newDate == null)
	     return;

	    setDate( newDate );
    }

    public DateButton( Date date )
    {
	    super( DATE_FORMAT.format(date) );
	    this.date = date;
    }

    public DateButton()
    {
	    this( new Date() );
    }

    public void setDate( Date date )
    {
	    Date old = this.date;
	    this.date = date;
	    setText( DATE_FORMAT.format(date) );
	    firePropertyChange( "date", old, date );
    }
}
