package hunlianjiaoyou;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hunlianjiaoyou.Dao;
import hunlianjiaoyou.DbUtil;
import hunlianjiaoyou.Message;

	@WebServlet("/jubaoServlet")
	public class jubaoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	         //this.doPost(req, resp);

		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8"); 
	
		Dao dao=new Dao();
		
		try {
			ArrayList<jubaoBean> jubaoBeanList=dao.chakanjubao();
		
		if(jubaoBeanList!=null){
			
			req.setAttribute("jubaoBeanList",jubaoBeanList);
			req.getRequestDispatcher("jubao/chakanjubao.jsp").forward(req,resp);		
		}

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	        
	 }
	         
	 




	protected  void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8"); 
		 HttpSession session = req.getSession();
		 String user=String.valueOf(req.getParameter("user"));
	    String people=(String)session.getAttribute("name");
	    String cause=String.valueOf(req.getParameter("cause"));
	    String time=String.valueOf(req.getParameter("time"));
	    System.out.print(user+people+cause+time);
	    boolean a;
         Dao dao=new Dao();
	    try {
			a=dao.jubao(user,people,cause,time);
			if(a){
				resp.sendRedirect("jubao/jubaochenggong.jsp");
			}else {
			resp.sendRedirect("jubao/jubaoshibai.jsp");}
			} catch (Exception e) {
			e.printStackTrace();}
				}
		
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

