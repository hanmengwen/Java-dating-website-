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

	@WebServlet("/liuyanServlet")
	public class liuyanServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	         //this.doPost(req, resp);

		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8"); 
		 HttpSession session = req.getSession();
		  String name=(String) session.getAttribute("name");
		Dao dao=new Dao();
		
		try {
			ArrayList<Message> MessageList=dao.myliuyan(name);
		
		if(MessageList!=null){
			
			req.setAttribute("MessageList",MessageList);
			req.getRequestDispatcher("/liuyanban.jsp").forward(req,resp);		
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
	    String people=(String) session.getAttribute("name");
	    String word=String.valueOf(req.getParameter("word"));
	    String time=String.valueOf(req.getParameter("time"));
	    System.out.print(user+people+word+time);
	    boolean a;
         Dao dao=new Dao();
	    try {
			a=dao.liuyan(user,people,word,time);
			if(a){
				resp.sendRedirect("liuyan/liuyanchenggong.jsp");
			}else {
			resp.sendRedirect("liuyan/liuyanshibai.jsp");}
			} catch (Exception e) {
			e.printStackTrace();}
				}
		
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

