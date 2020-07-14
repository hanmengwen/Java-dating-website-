package hunlianjiaoyou;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/DengluServlet")
public class DengluServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         this.doPost(req, resp);
         req.setCharacterEncoding("UTF-8");         
         resp.setCharacterEncoding("UTF=8");
      resp.setContentType("text/html;charset=UTF-8");
//使用request对象的getSession()获取session，如果session不存在则创建一个
    //  HttpSession session = req.getSession();
//将数据存储到session中
       
        //获取session的Id
    //     String sessionId = session.getId();
         //判断session是不是新创建的
   //     if (session.isNew()) {
   //          resp.getWriter().print("session创建成功，session的id是："+sessionId);
   //      }else {
   //        resp.getWriter().print("服务器已经存在该session了，session的id是："+sessionId);
   //      }
 }
         
 




protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {

//创建cookie
//Cookie c=new Cookie("msg","hell");
//发送cookie
//resp.addCookie(c);
 	resp.setContentType("text/html;charset=UTF-8");
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8"); 
	
	
	 System.out.println("1111111111111111111111");
	//session
	 HttpSession session = req.getSession();
	 String sessionId = session.getId();
	 if (session.isNew()) {
		 System.out.println("session创建成功，session的id是："+sessionId);
	          }else {
	        	  System.out.println("服务器已经存在该session了，session的id是："+sessionId);
		         }
		   
	 System.out.println("222222222222222");
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 



    	String name=String.valueOf(req.getParameter("name"));
    	String password=String.valueOf(req.getParameter("password"));
    	DbUtil db= new DbUtil();
    	user user=new user();
    	user.setPassword(password);
    	user.setName(name);
    	try {
    		//数据库链接
    		Connection con=null;
    		con=db.getCon();
    		//新建user
    		//user resultUser=null;
    		String sql="select*from users where name=? and password=?";
    		PreparedStatement pstmt=con.prepareStatement(sql);
    		pstmt.setString(1, user.getName());
    		pstmt.setString(2, user.getPassword());
    		ResultSet rs=pstmt.executeQuery();

    		if(rs.next()){
    			//resultUser=new user();
    			//resultUser.setName(rs.getString("name"));
    			//resultUser.setPassword(rs.getString("password"));
    			//resultUser.setAdress(rs.getString("adress"));
    			//resultUser.setAge(rs.getInt("age"));
    			//resultUser.setSex(rs.getString("sex"));
    			//resultUser.setInform(rs.getString("inform"));
    			//resultUser.setPhoto1(rs.getString("photo1"));
    			//req.setAttribute("resultUser",resultUser);
    			System.out.println("555555555555");
    			
    			//session上添加的属性
    			//session.setAttribute("user", resultUser);
    			session.setAttribute("name", rs.getString("name"));
    			session.setAttribute("age",rs.getString("age"));
    			session.setAttribute("sex",rs.getString("sex"));
    			session.setAttribute("adress",rs.getString("adress"));
    			session.setAttribute("inform",rs.getString("inform"));
    			session.setAttribute("photo",rs.getString("photo1"));
             // req.getRequestDispatcher("/yonghujiemian.jsp").forward(req,resp);
    			resp.sendRedirect(req.getContextPath()+"/yonghujiemian.jsp");
    		}else{
    		resp.sendRedirect(req.getContextPath()+"/denglushibai.jsp");
         }}catch(Exception e){
        		e.printStackTrace();
    	}
}}