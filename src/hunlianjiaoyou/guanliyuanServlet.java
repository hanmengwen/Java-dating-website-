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



@WebServlet("/guanliyuanServlet")
public class guanliyuanServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //this.doPost(req, resp);
         req.setCharacterEncoding("UTF-8");         
         resp.setCharacterEncoding("UTF=8");
      resp.setContentType("text/html;charset=UTF-8");
      String name=String.valueOf(req.getParameter("name"));
      Dao dao=new Dao();
      System.out.println("����"+name);
      try {
		boolean a= dao.shanchu(name);
	    System.out.println("����2");
		if(a){
			 System.out.println("����3");
           String message="ɾ���ɹ�";
            req.getSession().setAttribute("message", message);
  
        }else{
           String message="ɾ��ʧ��";
            req.getSession().setAttribute("message", message);
            System.out.println("����4");
        }
		if(a){
			resp.sendRedirect("admin.jsp");
			//req.getRequestDispatcher("/shanchuchenggong.jsp").forward(req,resp);
			
		}else{
			//resp.sendRedirect("admin.jsp");
			req.getRequestDispatcher("/admin.jsp").forward(req,resp);
		}
		
		
	} catch (Exception e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
 }
         
 




protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {


 	resp.setContentType("text/html;charset=UTF-8");
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8"); 
	
	

	//session
	 HttpSession session = req.getSession();
	 String sessionId = session.getId();
	 if (session.isNew()) {
		 System.out.println("session�����ɹ���session��id�ǣ�"+sessionId);
	          }else {
	        	  System.out.println("�������Ѿ����ڸ�session�ˣ�session��id�ǣ�"+sessionId);
		         }
		   

	 

    	String name=String.valueOf(req.getParameter("name"));
    	String password=String.valueOf(req.getParameter("password"));
    	DbUtil db= new DbUtil();

    	try {
    		//���ݿ�����
    		Connection con=null;
    		con=db.getCon();

    		String sql="select*from admin where name=? and password=?";
    		PreparedStatement pstmt=con.prepareStatement(sql);
    		pstmt.setString(1, name);
    		pstmt.setString(2, password);
    		ResultSet rs=pstmt.executeQuery();

    		if(rs.next()){

    			System.out.println("555555555555");
    			
    			//session����ӵ�����
    			//session.setAttribute("user", resultUser);
    			session.setAttribute("admin", rs.getString("name"));
               // req.setAttribute("a", 2);
             // req.getRequestDispatcher("/yonghujiemian.jsp").forward(req,resp);
    			resp.sendRedirect(req.getContextPath()+"/admin.jsp");
    		}else{
    			req.getRequestDispatcher("/guanliyuandenglu.jsp").forward(req,resp);
    			//resp.sendRedirect(req.getContextPath()+"/guanliyuandenglu.jsp?a=1");
         }}catch(Exception e){
        		e.printStackTrace();
    	}
}}