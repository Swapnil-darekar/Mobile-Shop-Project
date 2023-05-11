import java.io.*;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
 
public class PDFDemo_Database_Header
{
    public static void main(String[] args) throws SQLException, DocumentException, IOException
    {
        /** The resulting PDF file. */
        String RESULT = "Test.pdf";

        // FIRST PASS, CREATE THE PDF WITHOUT HEADER

    	// Create a database connection
        Connection cn;
        Statement stm;
        ResultSet rs;
			cn = DriverManager.getConnection("jdbc:mysql://localhost:9999/ras","root","");
        stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
        // step 1
        // Document(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom)
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        // step 2
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        rs = stm.executeQuery("select * from mytable");
        for(int i=0;i<5;i++)
        {
            rs.beforeFirst();
            while (rs.next())
            {
                document.add(new Paragraph(rs.getString(1)));
                document.add(new Paragraph(rs.getString(2)));
                document.add(Chunk.NEWLINE);
            }
            document.newPage();
        }
        // step 5
        document.close();
        // Close the database connection
        rs.close();
        cn.close();
    
        // SECOND PASS, ADD THE HEADER

        // Create a reader
        PdfReader reader = new PdfReader(baos.toByteArray());
        // Create a stamper
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(RESULT));

        // Loop over the pages and add a header to each page
        int n = reader.getNumberOfPages();
        for (int i = 1; i <= n; i++)
            getHeaderTable(i, n).writeSelectedRows(0, -1, 34, 803, stamper.getOverContent(i));
        
        // Close the stamper
        stamper.close();

		//To open the pdf file in linux & windows
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new java.io.File(RESULT));
    }

    public static PdfPTable getHeaderTable(int x, int y)
    {
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(527);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(20);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.addCell("Software Heading");
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(String.format("Page %d of %d", x, y));
        return table;
    }
}

