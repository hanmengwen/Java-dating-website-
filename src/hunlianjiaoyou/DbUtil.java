package hunlianjiaoyou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil{

private String dbUrl="jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8";
private String dbUserName="root";
private String dbPassword="19970423";


//建立数据库连接
public Connection getCon()throws Exception{

	 Connection con=null;
	try {
         Class.forName("com.mysql.jdbc.Driver");
     } catch (ClassNotFoundException e) {
         
         e.printStackTrace();
     }
		try{
	  con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
	
      }catch(SQLException e){
    	    e.printStackTrace();
      }
		
		  return con;		
		
}





//关闭数据库连接
protected void closeCon(Connection con) throws Exception{
    if(con!=null){
        con.close();
        }}}


