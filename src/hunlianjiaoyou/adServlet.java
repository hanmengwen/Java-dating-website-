package hunlianjiaoyou;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



@SuppressWarnings({ "unused", "serial" })
@WebServlet("/adServlet")
public class adServlet extends HttpServlet{
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }
	   
	   
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//user user=(user)req.getAttribute("resultUser");
	//String username=user.getName();
	//判断上传表单是否为multipart/form-data类型
    if(ServletFileUpload.isMultipartContent(req))
    {
        
        try {
            //1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //System.out.println(System.getProperty("java.io.tmpdir"));//默认临时文件夹
            
            //2. 创建ServletFileUpload对象，并设置上传文件的大小限制。
            ServletFileUpload  sfu = new ServletFileUpload(factory);
            sfu.setSizeMax(10*1024*1024);//以byte为单位    不能超过10M  1024byte = 1kb  1024kb=1M 1024M = 1G
            sfu.setHeaderEncoding("utf-8");
            
            //3. 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            List<FileItem> fileItemList = sfu.parseRequest(req);
            Iterator<FileItem> fileItems = fileItemList.iterator();
            System.out.println("111111111111111");
            //4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
            while(fileItems.hasNext())
            {
                FileItem fileItem = fileItems.next();
                //普通表单元素
                if(fileItem.isFormField())
                {
                    String name = fileItem.getFieldName();//name属性值
                    String value = fileItem.getString("utf-8");//name对应的value值
                    System.out.println(name + " = "+value);
                }
                //<input type="file">的上传文件的元素
                else
                {   
                
                    String fileName = fileItem.getName();//文件名称
                    System.out.println("原文件名：" + fileName);//Koala.jpg
                    
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));
                    System.out.println("扩展名："+suffix);//.jpg
                    //if((suffix!=".jpg")&&(suffix!=".png")){
                    	
                    	//resp.sendRedirect(req.getContextPath()+"/tupianshibai.jsp");
                    	
                 //   }equals(name)
                    String png=".png";
                    String jpg=".jpg";
                    if(!suffix.equals(png)&&!suffix.equals(jpg)){
                	
                	resp.sendRedirect(req.getContextPath()+"/tupianshibai.jsp");
                	
              }
                  //  if(!suffix.equals(jpg)){
                    	
                    //	resp.sendRedirect(req.getContextPath()+"/tupianshibai.jsp");
                //    	
                //  }
                    //新文件名（唯一）
                    String newFileName = new Date().getTime() + suffix;
                    System.out.println("新文件名：" + newFileName);//image\1478509873038.jpg
                  
                    //5. 调用FileItem的write()方法，写入文件
                  
                    String a="D:"+"\\"+"project"+"\\"+"hunlianjiaoyou"+"\\"+"WebContent"+"\\"+"ad";
                    File file = new File(a,newFileName);
                
                    if(!file.getParentFile().exists()){
                  
                        file.getParentFile().mkdirs();//创建父级文件路径
                        file.createNewFile();//创建文件
                        System.out.println(file.exists());
                    }

                  //  if(!file.exists()) { 
                  //  	 System.out.println("66666666666");//A
                  //      file.mkdir();          //B
                  //      file.createNewFile();  //C
                 //   }

                    System.out.println(file.getAbsolutePath());
                    System.out.println("sssssssssssss");
                    fileItem.write(file);
                    System.out.println("55555555555");
                    //6. 调用FileItem的delete()方法，删除临时文件
                    fileItem.delete();
                    System.out.println("555555555555");
                    /*
                     * 存储到数据库时注意
                     *     1.保存源文件名称   Koala.jpg
                     *  2.保存相对路径      image/1478509873038.jpg
                     * 
                     */
                   
                 //  user user=(user)req.getAttribute("resultUser");
                 //   name=req.getAttribute("resultUser").getName();
                    Dao dao=null;
                    dao=new Dao();
                   // HttpSession session = req.getSession();
                
                  
                  //  String name=(String) session.getAttribute("name");
                   // session.setAttribute("name",);
                   
                    dao.ad(newFileName);
                  
                   // session.setAttribute("photo", newFileName);
                    System.out.println("66666666666");
                    

                    
                    resp.sendRedirect(req.getContextPath()+"/admin.jsp");
                  //  req.getRequestDispatcher("/yonghujiemian.jsp").forward(req, resp);
                }
            }
            
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
	
	
    }}
	   
	   
}

