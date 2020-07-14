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
	//�ж��ϴ����Ƿ�Ϊmultipart/form-data����
    if(ServletFileUpload.isMultipartContent(req))
    {
        
        try {
            //1. ����DiskFileItemFactory�������û�������С����ʱ�ļ�Ŀ¼
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //System.out.println(System.getProperty("java.io.tmpdir"));//Ĭ����ʱ�ļ���
            
            //2. ����ServletFileUpload���󣬲������ϴ��ļ��Ĵ�С���ơ�
            ServletFileUpload  sfu = new ServletFileUpload(factory);
            sfu.setSizeMax(10*1024*1024);//��byteΪ��λ    ���ܳ���10M  1024byte = 1kb  1024kb=1M 1024M = 1G
            sfu.setHeaderEncoding("utf-8");
            
            //3. ����ServletFileUpload.parseRequest��������request���󣬵õ�һ�������������ϴ����ݵ�List����
            List<FileItem> fileItemList = sfu.parseRequest(req);
            Iterator<FileItem> fileItems = fileItemList.iterator();
            System.out.println("111111111111111");
            //4. ����list��ÿ����һ��FileItem���󣬵�����isFormField�����ж��Ƿ����ϴ��ļ�
            while(fileItems.hasNext())
            {
                FileItem fileItem = fileItems.next();
                //��ͨ��Ԫ��
                if(fileItem.isFormField())
                {
                    String name = fileItem.getFieldName();//name����ֵ
                    String value = fileItem.getString("utf-8");//name��Ӧ��valueֵ
                    System.out.println(name + " = "+value);
                }
                //<input type="file">���ϴ��ļ���Ԫ��
                else
                {   
                
                    String fileName = fileItem.getName();//�ļ�����
                    System.out.println("ԭ�ļ�����" + fileName);//Koala.jpg
                    
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));
                    System.out.println("��չ����"+suffix);//.jpg
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
                    //���ļ�����Ψһ��
                    String newFileName = new Date().getTime() + suffix;
                    System.out.println("���ļ�����" + newFileName);//image\1478509873038.jpg
                  
                    //5. ����FileItem��write()������д���ļ�
                  
                    String a="D:"+"\\"+"project"+"\\"+"hunlianjiaoyou"+"\\"+"WebContent"+"\\"+"ad";
                    File file = new File(a,newFileName);
                
                    if(!file.getParentFile().exists()){
                  
                        file.getParentFile().mkdirs();//���������ļ�·��
                        file.createNewFile();//�����ļ�
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
                    //6. ����FileItem��delete()������ɾ����ʱ�ļ�
                    fileItem.delete();
                    System.out.println("555555555555");
                    /*
                     * �洢�����ݿ�ʱע��
                     *     1.����Դ�ļ�����   Koala.jpg
                     *  2.�������·��      image/1478509873038.jpg
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

