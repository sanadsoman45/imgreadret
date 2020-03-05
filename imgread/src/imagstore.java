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
public class imagstore {
    Scanner sc=new Scanner(System.in);
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    
    public imagstore()
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
    
public void simpleIUD()
    {
        
        
        try
        {
            createConn();
            String query="insert into emp301 values(?,?)";
            ps = con.prepareStatement(query);
            System.out.println("enter the id:");
            int x=sc.nextInt();
            ps.setInt(1, x);
            InputStream in = new FileInputStream("D:\\bi.jpg");
            ps.setBlob(2, in);
            ps.execute();
          
        }
        catch(Exception x)
        {
            System.out.println("simple iud "+x);
        }
        finally
        {
            try
            {
                ps.close();
            }
            catch(Exception x)
            {
                System.out.println("simple iud finally "+x);
            }
            closeConn();
        }
    }
    
    
    
    public static void main(String[] args) throws SQLException, FileNotFoundException 
    {
        imagstore i = new imagstore();
        i.simpleIUD();
        System.out.println("connection established");
        //System.out.println("contents printed");
    }
}
