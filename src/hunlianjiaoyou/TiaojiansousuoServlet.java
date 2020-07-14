package hunlianjiaoyou;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/TiaojiansousuoServlet")

public class TiaojiansousuoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	 	resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8"); 
		//HttpSession session = req.getSession();
		
		 
		//this.doPost(req, resp);
		 Dao dao=new Dao();
		 ArrayList<user> tiaojianlist1=null;
		 ArrayList<user> tiaojianlist2=null;
		try {
			String adress=String.valueOf(req.getParameter("adress"));
		//	if(adress!=null){
		tiaojianlist1 = dao.adress(adress);
		//	req.setAttribute("tiaojianlist",tiaojianlist1);
			//req.getRequestDispatcher("/tiaojiansousuo.jsp").forward(req,resp);		
		//	}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			String age = String.valueOf(req.getParameter("age"));
			tiaojianlist2 = dao.age(age);
		//	if(age!=null){
			tiaojianlist2 = dao.age(age);
			//	req.setAttribute("tiaojianlist",tiaojianlist2);
			//	req.getRequestDispatcher("/tiaojiansousuo.jsp").forward(req,resp);		
			//	}
			} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		if(tiaojianlist1!=null){
			System.out.print("地址");
		req.setAttribute("tiaojianlist",tiaojianlist1);
			req.getRequestDispatcher("/tiaojiansousuo.jsp").forward(req,resp);		
		}
		
		if(tiaojianlist2!=null){
				System.out.print("年龄");
			req.setAttribute("tiaojianlist",tiaojianlist2);
				req.getRequestDispatcher("/tiaojiansousuo.jsp").forward(req,resp);		
			}
		
	         }
	
	
	
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {


		 	resp.setContentType("text/html;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8"); 
	          
			 String sex=String.valueOf(req.getParameter("sex"));
			 String adress=String.valueOf(req.getParameter("adress"));
			 String age=String.valueOf(req.getParameter("age"));
			 System.out.print(adress);
	        try {
	        	Dao dao=new Dao();
	        
				ArrayList<user> tiaojianlist=dao.tiaojiansousuo(sex,adress,age);
				HttpSession session = req.getSession();
				session.setAttribute("xuanzeage",age);
				session.setAttribute("xuanzesex",sex);
				session.setAttribute("xuanzeadress",adress);
				//HttpSession session = req.getSession();
				req.setAttribute("tiaojianlist",tiaojianlist);
				//session.setAttribute("tiaojianlist",tiaojianlist);
				//req.setAttribute("user",user);
				req.getRequestDispatcher("/tiaojiansousuo.jsp").forward(req,resp);
			} catch (Exception e) {			// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	
	}
	
}