package hunlianjiaoyou;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/XinxixiugaiServlet")

public class XinxixiugaiServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	         this.doPost(req, resp);
	         }

	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8"); 
		//测试是否zhuceServlet启动了
		PrintWriter out=resp.getWriter();
		out.println("Servlet启动了");
		 HttpSession session = req.getSession();

		//接受变量
	String name=(String) session.getAttribute("name");
	String password=String.valueOf(req.getParameter("password"));
	String age = String.valueOf(req.getParameter("age"));
	String adress=String.valueOf(req.getParameter("adress"));
	String sex=String.valueOf(req.getParameter("sex"));
	String inform=String.valueOf(req.getParameter("inform"));
	DbUtil db= new DbUtil();

	Dao dao=new Dao();
	out.println("1111111111111111111");
	
	

	try {
	//数据库链接
	Connection con=null;
	con=db.getCon();
	//修改信息
	int a=dao.xiugaixinxi(name,password,age,adress,sex,inform);
	out.println("22222222222222222");
	//设置session

	session.setAttribute("name",name);
	session.setAttribute("age",age);
	session.setAttribute("sex",sex);
	session.setAttribute("adress",adress);
	session.setAttribute("inform",inform);

	
	if(a!=0){
		resp.sendRedirect("01.jsp");	
	}else {
	resp.sendRedirect("xiugaishibai.jsp");}
	} catch (Exception e) {
	e.printStackTrace();}}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
