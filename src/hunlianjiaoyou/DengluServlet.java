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
//ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
    //  HttpSession session = req.getSession();
//�����ݴ洢��session��
       
        //��ȡsession��Id
    //     String sessionId = session.getId();
         //�ж�session�ǲ����´�����
   //     if (session.isNew()) {
   //          resp.getWriter().print("session�����ɹ���session��id�ǣ�"+sessionId);
   //      }else {
   //        resp.getWriter().print("�������Ѿ����ڸ�session�ˣ�session��id�ǣ�"+sessionId);
   //      }
 }
         
 




protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {

//����cookie
//Cookie c=new Cookie("msg","hell");
//����cookie
//resp.addCookie(c);
 	resp.setContentType("text/html;charset=UTF-8");
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8"); 
	
	
	 System.out.println("1111111111111111111111");
	//session
	 HttpSession session = req.getSession();
	 String sessionId = session.getId();
	 if (session.isNew()) {
		 System.out.println("session�����ɹ���session��id�ǣ�"+sessionId);
	          }else {
	        	  System.out.println("�������Ѿ����ڸ�session�ˣ�session��id�ǣ�"+sessionId);
		         }
		   
	 System.out.println("222222222222222");
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 



    	String name=String.valueOf(req.getParameter("name"));
    	String password=String.valueOf(req.getParameter("password"));
    	DbUtil db= new DbUtil();
    	user user=new user();
    	user.setPassword(password);
    	user.setName(name);
    	try {
    		//���ݿ�����
    		Connection con=null;
    		con=db.getCon();
    		//�½�user
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
    			
    			//session����ӵ�����
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