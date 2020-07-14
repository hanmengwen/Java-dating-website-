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



@WebServlet("/SousuoyonghuServlet")
public class SousuoyonghuServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	         this.doPost(req, resp);
	         }
	
	
	
	
	
	
	
protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8"); 
		HttpSession session = req.getSession();
		//测试是否zhuceServlet启动了
		PrintWriter out=resp.getWriter();
		out.println("Servlet启动了");	
			
        String name=String.valueOf(req.getParameter("name"));
      //  out.println("servlet"+name);	
        Dao dao=new Dao();
        try {
			ArrayList<user> list=dao.sousuo(name);
			req.setAttribute("tiaojianlist",list);
			
			//req.setAttribute("user",user);
			req.getRequestDispatcher("/tiaojiansousuo.jsp").forward(req,resp);
		} catch (Exception e) {			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

}






}
