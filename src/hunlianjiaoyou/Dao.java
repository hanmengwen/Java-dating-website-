package hunlianjiaoyou;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {


//注册，返回一个boolean对象
public boolean register(Connection con,user user)throws Exception{

boolean flag=false;
System.out.print("a ");
PreparedStatement pstmt=null;
String sql="INSERT INTO users(name,password,age,sex,adress,inform)VALUES(?,?,?,?,?,?)";
pstmt=con.prepareStatement(sql);
pstmt.setString(1,user.getName());
pstmt.setString(2,user.getPassword());
pstmt.setString(3,user.getAge());
pstmt.setString(4,user.getSex());
pstmt.setString(5,user.getAdress());
pstmt.setString(6,user.getInform());
System.out.print("a ");
try{
	
int s=pstmt.executeUpdate();
if (s>0){
flag = true;

}
}catch(Exception e){
	e.printStackTrace();
}

return flag;
}
//上传图片
public  void photo(String name,String photo)throws Exception{

	DbUtil db= new DbUtil();
	Dao dao=null;
	Connection con=null;
	con=db.getCon();

	PreparedStatement pstmt=null;
	String sql="UPDATE users SET photo1 =? WHERE name = ?";
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1,photo);
	pstmt.setString(2,name);

	int s=pstmt.executeUpdate();
	

}
//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值







//得到某photo
public  String getphoto(String name)throws Exception{

	DbUtil db= new DbUtil();
	Dao dao=null;
	Connection con=null;
	con=db.getCon();
	PreparedStatement pstmt=null;
	//for(int i=1;i<=5;i++){
	String sql="select * from users where name=?";
	//String sql="SELECT(?,?,?,?,?) FROM users WHERE name = ?";
	pstmt=con.prepareStatement(sql);
	//String a="photo"+i;
	pstmt.setString(1,name);
	//pstmt.setString(2, "photo2");
	//pstmt.setString(3, "photo3");
	//pstmt.setString(4, "photo4");
	//pstmt.setString(5, "photo5");
	//pstmt.setString(2,name);
	ResultSet rs=pstmt.executeQuery();
	System.out.print("1111111111111111111111111");
	if(rs.next()){
		
		System.out.print("2222222222222222222");
	   String p=rs.getString("photo1");
	   return p;
	
	}else{
		String b="没照片.";
		System.out.print("33333333333333333333");
		return b;
	}

}



//修改用户信息Dao

public  int xiugaixinxi(String name,String password,String age,String adress,String sex,String inform)throws Exception{

	DbUtil db= new DbUtil();
	Dao dao=null;
	Connection con=null;
	con=db.getCon();
	PreparedStatement pstmt=null;
	String sql="UPDATE users SET password = ?,age=?,adress=?,sex=?,inform=? WHERE name = ?";
	//String sql="UPDATE users SET (password,age,adress,sex,inform)values(?,?,?,?,?)WHERE name = ?";
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1,password);
	pstmt.setString(2,age);
	pstmt.setString(3,adress);
	pstmt.setString(4,sex);
	pstmt.setString(5,inform);
	pstmt.setString(6,name);
	int s=pstmt.executeUpdate();
	return s;
}


//搜索用户的Dao 模糊查询
public  ArrayList<user> sousuo(String name)throws Exception{

	DbUtil db= new DbUtil();
	Dao dao=null;
	Connection con=null;
	con=db.getCon();
	PreparedStatement pstmt=null;
	String sql="select * from users where name like '%"+name+"%'";
	//String sql="select * from users where name like '%?%'";
	pstmt=con.prepareStatement(sql);
	//pstmt.setString(1,name);
	ResultSet rs=pstmt.executeQuery();
	 ArrayList<user> list = new ArrayList<user>();
	 while(rs.next()){
		 // 实例化user
			user resultUser=new user();
			resultUser.setName(rs.getString("name"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setAdress(rs.getString("adress"));
			resultUser.setAge(rs.getString("age"));
			resultUser.setSex(rs.getString("sex"));
			resultUser.setInform(rs.getString("inform"));
			resultUser.setPhoto1(rs.getString("photo1"));
			//System.out.print(resultUser.getPhoto1());
			//System.out.print(resultUser.getPassword());
		 // 将图书对象添加到集合中
		 list.add(resultUser);
	
		 }

	 return list;
}


//otherpeople
public  user otherpeople(String name)throws Exception{
	DbUtil db= new DbUtil();
	Connection con=null;
	con=db.getCon();
	//新建user
	user resultUser=null;
	String sql="select*from users where name=?";
	PreparedStatement pstmt=con.prepareStatement(sql);
	pstmt.setString(1,name);
	ResultSet rs=pstmt.executeQuery();

	if(rs.next()){
		resultUser=new user();
		resultUser.setName(rs.getString("name"));
		resultUser.setPassword(rs.getString("password"));
		resultUser.setAdress(rs.getString("adress"));
		resultUser.setAge(rs.getString("age"));
		resultUser.setSex(rs.getString("sex"));
		resultUser.setInform(rs.getString("inform"));
		resultUser.setPhoto1(rs.getString("photo1"));
		return resultUser;
		//System.out.println("555555555555");
}else{
	return null;
}

}





//条件搜索
public  ArrayList<user> tiaojiansousuo(String sex,String adress,String age)throws Exception{
DbUtil db= new DbUtil();

Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;
//String sql="select * from users where sex="+sex+" and adress="+adress;
//String sql="select * from users where sex="+sex+" and adress="+adress+" and age like '"+age+"%'";
//String sql="select * from users where age like '"+age+"%'";
//String sql="select * from users where sex=? and adress=?";
String sql="select * from users where sex=? and adress=? and age like '"+age+"%'";
pstmt=con.prepareStatement(sql);
pstmt.setString(1,sex);
pstmt.setString(2,adress);
System.out.print("22222222222222222");
ResultSet rs=pstmt.executeQuery();
 ArrayList<user> list = new ArrayList<user>();
 while(rs.next()){
	 // 实例化user
		user resultUser=new user();
		resultUser.setName(rs.getString("name"));
		resultUser.setPassword(rs.getString("password"));
		resultUser.setAdress(rs.getString("adress"));
		resultUser.setAge(rs.getString("age"));
		resultUser.setSex(rs.getString("sex"));
		resultUser.setInform(rs.getString("inform"));
		resultUser.setPhoto1(rs.getString("photo1"));
		System.out.print("1111111111111111111111");
		//System.out.print(resultUser.getPassword());
	 // 将图书对象添加到集合中
	 list.add(resultUser);

	 }

 return list;
}

//只按地址搜索
public  ArrayList<user> adress(String adress)throws Exception{
DbUtil db= new DbUtil();

Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;

String sql="select * from users where adress=?";
pstmt=con.prepareStatement(sql);
pstmt.setString(1,adress);
ResultSet rs=pstmt.executeQuery();
ArrayList<user> list = new ArrayList<user>();
if(!rs.next()){
	return null;
	 }

 while(rs.next()){
	 // 实例化user
		user resultUser=new user();
		resultUser.setName(rs.getString("name"));
		resultUser.setPassword(rs.getString("password"));
		resultUser.setAdress(rs.getString("adress"));
		resultUser.setAge(rs.getString("age"));
		resultUser.setSex(rs.getString("sex"));
		resultUser.setInform(rs.getString("inform"));
		resultUser.setPhoto1(rs.getString("photo1"));
		
		//System.out.print(resultUser.getPassword());

	 list.add(resultUser);

}

 return list;
}





//年龄搜索
public  ArrayList<user> age(String age)throws Exception{
DbUtil db= new DbUtil();
Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;
String sql="select * from users where age like '"+age+"%'";
pstmt=con.prepareStatement(sql);

ResultSet rs=pstmt.executeQuery();
 ArrayList<user> list = new ArrayList<user>();
 while(rs.next()){
	 // 实例化user
		user resultUser=new user();
		resultUser.setName(rs.getString("name"));
		resultUser.setPassword(rs.getString("password"));
		resultUser.setAdress(rs.getString("adress"));
		resultUser.setAge(rs.getString("age"));
		resultUser.setSex(rs.getString("sex"));
		resultUser.setInform(rs.getString("inform"));
		resultUser.setPhoto1(rs.getString("photo1"));
		
		//System.out.print(resultUser.getPassword());
		
	 list.add(resultUser);

}
 return list;
}



//查找所有用户
public  ArrayList<user> suoyouyonghu()throws Exception{
DbUtil db= new DbUtil();

Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;
String sql="select * from users";
pstmt=con.prepareStatement(sql);

ResultSet rs=pstmt.executeQuery();
 ArrayList<user> list = new ArrayList<user>();
 while(rs.next()){
	 // 实例化user
		user resultUser=new user();
		resultUser.setName(rs.getString("name"));
		resultUser.setPassword(rs.getString("password"));
		resultUser.setAdress(rs.getString("adress"));
		resultUser.setAge(rs.getString("age"));
		resultUser.setSex(rs.getString("sex"));
		resultUser.setInform(rs.getString("inform"));
		resultUser.setPhoto1(rs.getString("photo1"));

		//System.out.print(resultUser.getPassword());

	 list.add(resultUser);

}
 return list;

}

//删除用户
public  boolean shanchu(String name)throws Exception{
DbUtil db= new DbUtil();

Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;
System.out.print("1ssssssssssssssssss");
//String sql="delete from users where name= ? ";
String sql="delete from users where name='"+name+"'";
pstmt=con.prepareStatement(sql);
//pstmt.setString(1,name);
System.out.print("2ssssssssssssssssss2");
System.out.print(sql);
boolean flag=false;
int s=pstmt.executeUpdate();
System.out.print("66666666666");
if (s>0){
flag = true;
}else{flag = false;
}
return flag;
}




//随机抽取六名用户
public  ArrayList<user> suiji()throws Exception{
DbUtil db= new DbUtil();

Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;
System.out.print("1ssssssssssssssssss");
//String sql="delete from users where name= ? ";
String sql="SELECT * FROM users ORDER BY  RAND() LIMIT 6";
pstmt=con.prepareStatement(sql);
ResultSet rs=pstmt.executeQuery();
ArrayList<user> list = new ArrayList<user>();
while(rs.next()){
	 // 实例化user
		user resultUser=new user();
		resultUser.setName(rs.getString("name"));
		resultUser.setPassword(rs.getString("password"));
		resultUser.setAdress(rs.getString("adress"));
		resultUser.setAge(rs.getString("age"));
		resultUser.setSex(rs.getString("sex"));
		resultUser.setInform(rs.getString("inform"));
		resultUser.setPhoto1(rs.getString("photo1"));

		//System.out.print(resultUser.getPassword());

	 list.add(resultUser);

}
return list;

}






//留言

public   boolean liuyan(String user,String people,String word,String time)throws Exception{
	boolean flag=false;
	DbUtil db= new DbUtil();
	
	Connection con=null;
	con=db.getCon();
	System.out.print("a");
	PreparedStatement pstmt=null;
	String sql="INSERT INTO message(word,time,user,people)VALUES(?,?,?,?)";
	System.out.print(user+people+word+time);
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1,word);
	pstmt.setString(2,time);
	pstmt.setString(3,user);
	pstmt.setString(4,people);
	
	System.out.print("a");
	try{
		
	int s=pstmt.executeUpdate();
	if (s>0){
	flag = true;

	}
	}catch(Exception e){
		e.printStackTrace();
	}

	return flag;

}




public    ArrayList<Message> myliuyan(String name)throws Exception{
	DbUtil db= new DbUtil();

	Connection con=null;
	con=db.getCon();
	PreparedStatement pstmt=null;
	String sql="select * from message where user=? order by time DESC";
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1,name);
	ResultSet rs=pstmt.executeQuery();
	 ArrayList<Message> MessageList = new ArrayList<Message>();
	 while(rs.next()){
		 // 实例化user
			Message Message=new Message();
			Message.setPeople(rs.getString("people"));
			Message.setWord(rs.getString("word"));
			Message.setTime(rs.getString("time"));
			System.out.print("1111111111111111111111");
			//System.out.print(resultUser.getPassword());

			MessageList.add(Message);

	}

	 return MessageList;
	
	}



//删除我的留言
public  boolean shanchuliuyan(String name,String people,String time)throws Exception{
DbUtil db= new DbUtil();

Connection con=null;
con=db.getCon();
PreparedStatement pstmt=null;
System.out.print("1ssssssssssssssssss");
//String sql="delete from users where name= ? ";
//String sql="delete from message where name='"+name+"'";
String sql="DELETE FROM message WHERE user='"+name+"'and people='"+people+"' and time='"+time+"'";


//String sql="DELETE FROM message WHERE user=?,people=?,time=?";
//pstmt.setString(1,name);
//pstmt.setString(2,people);
//pstmt.setString(3,time);

pstmt=con.prepareStatement(sql);
//pstmt.setString(1,name);
System.out.print("2ssssssssssssssssss2");
System.out.print(sql);
boolean flag=false;
int s=pstmt.executeUpdate();
System.out.print("66666666666");
if (s>0){
flag = true;
}else{flag = false;
}
return flag;
}


//举报
public   boolean jubao(String user,String people,String cause,String time)throws Exception{
	boolean flag=false;
	DbUtil db= new DbUtil();
	
	Connection con=null;
	con=db.getCon();
	System.out.print("a");
	PreparedStatement pstmt=null;
	String sql="INSERT INTO jubao(time,user,people,cause)VALUES(?,?,?,?)";
	System.out.print(user+people+cause+time);
	pstmt=con.prepareStatement(sql);
	
	pstmt.setString(1,time);
	pstmt.setString(2,user);
	pstmt.setString(3,people);
	pstmt.setString(4,cause);
	System.out.print("a");
	try{
		
	int s=pstmt.executeUpdate();
	if (s>0){
	flag = true;

	}
	}catch(Exception e){
		e.printStackTrace();
	}

	return flag;

}
//查看举报
public    ArrayList<jubaoBean> chakanjubao()throws Exception{
	DbUtil db= new DbUtil();

	Connection con=null;
	con=db.getCon();
	PreparedStatement pstmt=null;
	String sql="select * from jubao order by time DESC";
	pstmt=con.prepareStatement(sql);
	
	ResultSet rs=pstmt.executeQuery();
	 ArrayList<jubaoBean> jubaoBeanList =new ArrayList<jubaoBean>();
	 while(rs.next()){
		 // 实例化user
		 jubaoBean jubaoBean=new jubaoBean();
		 jubaoBean.setPeople(rs.getString("people"));
		 jubaoBean.setCause(rs.getString("cause"));
		 jubaoBean.setTime(rs.getString("time"));
		 jubaoBean.setUser(rs.getString("user"));

			System.out.print("1111111111111111111111");
			//System.out.print(resultUser.getPassword());

			jubaoBeanList.add(jubaoBean);

	}

	 return jubaoBeanList;
	
	}
//更改数据库中的ad地址
public   void ad(String newFileName)throws Exception{


	DbUtil db= new DbUtil();
	Dao dao=null;
	Connection con=null;
	con=db.getCon();

	PreparedStatement pstmt=null;
	String sql="UPDATE ad SET a =?";
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1,newFileName);
	

	int s=pstmt.executeUpdate();
	

}

public   String getad()throws Exception{


	DbUtil db= new DbUtil();

	Connection con=null;
	con=db.getCon();
	PreparedStatement pstmt=null;
	
	String sql="select * from ad";
	pstmt=con.prepareStatement(sql);
	ResultSet rs=pstmt.executeQuery();

	if(rs.next()){

	   String p=rs.getString("a");
	   return p;
	
	}else{
		String b="没照片.";

		return b;
	}

	

}

}