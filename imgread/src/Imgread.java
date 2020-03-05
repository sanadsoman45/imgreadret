import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
public class Imgread 
{
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    
    public Imgread()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (Exception e) 
        {
            System.out.println("Const "+e);
        }
    }
    
    public void createConn()
    {
        try 
        {
            if(con == null)
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sycs", "root", "");   
            }
            else 
            {
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sycs", "root", "");
            }
        }
        catch (Exception e) 
        {
            System.out.println("create conn "+e);
        }
    }
    
    public void closeConn()
    {
        try
        {
            if(!con.isClosed())
            {
                con.close();
            }
        }
        catch(Exception e)
        {
            System.out.println("close conn "+e);
        }
        
    }
    
    
    public static void main(String[] args) throws SQLException, FileNotFoundException 
    {
        Imgread i = new Imgread();
        i.selectQuery();
        System.out.println("connection established");
        i.selectQuery();
        //System.out.println("contents printed");
    }
    
    public void selectQuery()
    {
        try 
        {
            createConn();
            String query="Select * from emp301";
            ps=con.prepareStatement(query);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
             int i = 1;
      System.out.println("Contents of the table are: ");
      while(rs.next()) {
         System.out.println(rs.getInt("emp_id"));
         System.out.println(rs.getInt("emp_id"));
         Blob blob = rs.getBlob("emp_image");
         byte byteArray[] = blob.getBytes(1,(int)blob.length());
         FileOutputStream outPutStream = new
         FileOutputStream("D:\\imgoutput"+i+".jpg");
         outPutStream.write(byteArray);
         outPutStream.flush();
         System.out.println("D:\\imgoutput"+i+".jpg");
         System.out.println("contents printed..");
         i++;
         outPutStream.close();
      }
            rs.close();
            ps.close();
            
        } catch (Exception e) 
        {
            System.out.println("select"+e);
        }
        finally
        {
            try
            {
            closeConn();
            }
            catch(Exception r)
            {
                System.err.println("finally ka"+r);
            }
        }
    }
    
    
}
