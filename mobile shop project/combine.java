import java.io.*;
import java.sql.*;
import java.text.*;     // For Date Format
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

class democls
{
    String path = "Test.pdf";
    ByteArrayOutputStream baos;
    Document document;
    Paragraph p;
    PdfReader reader;
    PdfStamper stamper;

    PdfPTable table;
    PdfPCell c1;
    Font big,small,head;

    Connection cn;
    Statement stm;
    ResultSet rs;
    int cnt;
    String dt;

    democls()
    {
        big = new Font(Font.FontFamily.TIMES_ROMAN, 22,Font.BOLD);
        small = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
        head = new Font(Font.FontFamily.COURIER, 10,Font.BOLD);

        try
        {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addon","root","");
            stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );

            // Add Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            dt = formatter.format(new java.util.Date());
        }
        catch(Exception e){     e.printStackTrace();    }
    }

    void create_pdf()
    {
        try
        {
            // Document(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom)
            document = new Document(PageSize.A4, 36, 36, 54, 36);
            // step 2
            baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            //document.newPage();   // for new page

            // PDF File Properties
      /*    document.addTitle("I am in Title");
            document.addSubject("I am in Subjece");
            document.addKeywords("I am in Keyword");
            document.addAuthor("I am Author");
            document.addCreator("I am Creator");*/

            // add Simple date format in page
          p = new Paragraph("Date = " + dt);
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            document.add(new Paragraph("\n"));
        }
        catch(Exception e){     e.printStackTrace();    }
    }

    void add_img()
    {
        try
        {
            // Add Image
            Image img = Image.getInstance("TCC.jpg");
            img.scalePercent(50);
            img.setAbsolutePosition(30, 725);
            document.add(img);
        }
        catch(Exception e){     e.printStackTrace();    }
    }

    void write_heading()
    {
        try
        {
            //Write Heading in a PDF
            p = new Paragraph("Retrive the Info from Database",big);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);
        }
        catch(Exception e){     e.printStackTrace();    }
    }

    void create_tab_heading()
    {
        /*
        table = new PdfPTable(3);
        // Width of Table - same for each coloumn
        table.setWidthPercentage(80);
        */

        float[] colsWidth = {8,12,15,10};
		table = new PdfPTable(colsWidth);
		table.setWidthPercentage(40);

        c1 = new PdfPCell(new Phrase("Sr No",small));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        //c1.setFixedHeight(55);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Roll No",small));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Name",small));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Age",small));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
    }

    void add_rows()
    {
        cnt=0;
        try
        {
            rs = stm.executeQuery("select * from mytable");
          //  for(int i=0;i<10;i++)
            {
               rs.beforeFirst();

                while(rs.next())
                {
                    cnt++;

                    // Sr Number
                    c1 = new PdfPCell(new Phrase(Integer.toString(cnt)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    // Roll Number
                    c1 = new PdfPCell(new Phrase(rs.getString(1)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);

                    // Name
                    table.addCell(rs.getString(2));

                    // Age
                    c1 = new PdfPCell(new Phrase(rs.getString(3)));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);
               
                /*    table.addCell(""+cnt);
                    table.addCell(rs.getString(1));
                    table.addCell(rs.getString(2));
                    table.addCell(rs.getString(3));*/
                }
            }
            rs.close();
            cn.close();

            // Coloumn Spanning
            c1 = new PdfPCell(new Phrase("Total No. of Stud = "+cnt));
            c1.setColspan(4);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            document.add(table);
            document.add(new Paragraph("\n"));
            document.close();
        }
        catch(Exception e){     e.printStackTrace();    }
    }

    void close_pdf()
    {
        try
        {
         // Create a reader
         reader = new PdfReader(baos.toByteArray());
         // Create a stamper
         stamper = new PdfStamper(reader, new FileOutputStream(path));

        // Loop over the pages and add a header to each page
        int n = reader.getNumberOfPages();
        for (int i = 1; i <= n; i++)
        {
            /*getHeaderTable()
            rowStart - the first row to be written, zero index
            rowEnd - the last row to be written + 1. If it is -1 all the rows to the end are written
            xPos - the x write coordinate
            yPos - the y write coordinate
            canvas - the PdfContentByte where the rows will be written to*/

            // Header with Heading & Page Number
            getHeaderTable1(i,n).writeSelectedRows(0,-1,34,818,stamper.getOverContent(i));
            // Footer with Date
            getHeaderTable2().writeSelectedRows(0, -1, 34, 35, stamper.getOverContent(i));
        }

        // Close the stamper
        stamper.close();
        // To open the pdf file
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
        }
        catch(Exception e){     e.printStackTrace();    }
    }

    public PdfPTable getHeaderTable1(int x, int y)
    {
        table = new PdfPTable(2);
        table.setTotalWidth(527);
        table.setLockedWidth(true);

        c1 = new PdfPCell(new Phrase("Software Heading",head));
        c1.setFixedHeight(15);
        c1.setBorder(Rectangle.BOTTOM);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(String.format("Page %d of %d", x, y),head));
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c1.setFixedHeight(15);
        c1.setBorder(Rectangle.BOTTOM);
        table.addCell(c1);

        return table;
    }

    public PdfPTable getHeaderTable2()
    {
        table = new PdfPTable(1);
        table.setTotalWidth(527);
        table.setLockedWidth(true);
        c1 = new PdfPCell(new Phrase(dt,head));
        c1.setFixedHeight(15);
        c1.setBorder(Rectangle.TOP);
        c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(c1);
        return table;
    }
}

class combine
{    public static void main(String[] args)
    {
         democls obj = new democls();
         obj.create_pdf();
         obj.add_img();
         obj.write_heading();
         obj.create_tab_heading();
         obj.add_rows();
         obj.close_pdf();
     }
}