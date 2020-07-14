package hunlianjiaoyou;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ZhuceServlet")
public class ZhuceServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         this.doPost(req, resp);
         }













@Override
protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html; charset=UTF-8");
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8"); 
	//测试是否zhuceServlet启动了
	PrintWriter out=resp.getWriter();
	out.println("ZhuceServlet启动了");

	//接受变量
String name=String.valueOf(req.getParameter("name"));
String password=String.valueOf(req.getParameter("password"));
String age = String.valueOf(req.getParameter("age"));
String adress=String.valueOf(req.getParameter("adress"));
String sex=String.valueOf(req.getParameter("sex"));
String inform=String.valueOf(req.getParameter("inform"));
DbUtil db= new DbUtil();
user user=new user();
user.setPassword(password);
user.setName(name);
user.setAge(age);
user.setSex(sex);
user.setAdress(adress);
user.setInform(inform);
Dao dao=null;
dao=new Dao();
out.println("1111111111111111111");

try {
//数据库链接
Connection con=null;
con=db.getCon();


boolean a;

a=dao.register(con,user);
out.println("22222222222222222");
if(a){
	resp.sendRedirect("zhucechenggong.jsp");
}else {
resp.sendRedirect("zhuceshibai.jsp");}
} catch (Exception e) {
e.printStackTrace();}
	}}
