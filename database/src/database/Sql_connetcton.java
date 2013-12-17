package database;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.sql.*;


public class Sql_connetcton {
	private static String ip= "local";
	private static String url="jdbc:sqlserver://JXY-THINK;instanceName=JXY_SQL_SERVER;database=学籍管理系统";
	private static Connection con=null;
	private static String sql;
	private static Statement stmt;

	public static void init(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static int login_s(String user,String password){
		try {
			con=DriverManager.getConnection(url, user, password);
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	public static String[][] find_student(int index,String s){
		if(s!=null && s.equals(""))s=null;
		sql="exec 学生_Find ";
		for(int i=0;i<5;i++){
			if(i==index&&s!=null)sql+="\""+s+"\"";
			else sql+="NULL";
			if(i!=4)sql+=",";
		}
		ResultSet result;
		String []now;
		ArrayList<String[]> a=new ArrayList<>();
		try{
			result=stmt.executeQuery(sql);
			int ind=0,i;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				now=new String[5];
				for(i=0;i<clen;i++){
					now[i]=result.getString(i+1);
					if(now[i]==null)now[i]="NULL";
				}
				now[4]=now[4].split(" ")[0];//去除日期的时间
				a.add(now);
			}	
			String [][]ans=new String[a.size()][5];
			for(ind=0;ind<a.size();ind++)
				ans[ind]=a.get(ind);
			return ans;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector<String>> getScoreDetail(String num){
		sql="exec 成绩_Find \""+num+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector<String>> ans=new Vector<Vector<String>>();
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector<String> s=new Vector<String>();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)s.set(i-1, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector<String>> getScoreDetail2(String num){
		sql="exec 成绩_Find2 \""+num+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector<String>> ans=new Vector<Vector<String>>();
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector<String> s=new Vector<String>();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)s.set(i-1, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector<String>> getScoreClass(Vector data){
		sql="exec 成绩课程_FIND";
		sql=add_sql(sql, data);
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector<String>> ans=new Vector<Vector<String>>();
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector<String> s=new Vector<String>();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)s.set(i-1, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<String> getPersons(String num){
		if(num==null||num.equals(""))return null;
		sql="exec 学分_Find \""+num+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			int clen=result.getMetaData().getColumnCount();
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=clen;i++)
					ans.add(result.getString(i));
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector> getAllStudent(){
		sql="exec 学生_Find NULL,NULL,NULL,NULL,NULL";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				for(int i=1;i<=4;i++){
					s.add(result.getString(i));
					if(s.lastElement()==null)s.set(i, "NULL");
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Map<String, String> getMajority(){
		sql="exec 专业_Find";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Map<String, String> ans=new HashMap<String, String>();
			while(result.next()){
				String s=result.getString(1);
				ans.put(result.getString(2), s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Map<String, String> getCoures(){
		sql="exec 课程_Find";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Map<String, String> ans=new HashMap<String, String>();
			while(result.next()){
				ans.put(result.getString(1), result.getString(2));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Map<String, String> getCouresByName(){
		sql="exec 课程_Find";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Map<String, String> ans=new HashMap<String, String>();
			while(result.next()){
				String t=result.getString(1);
				ans.put(result.getString(2), t);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Map<String, String> getScore(String code){
		sql="exec 成绩_Find \""+code+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Map<String, String> ans=new HashMap<String, String>();
			while(result.next()){
				String t=result.getString(1),s= result.getString(5);
				if(s==null)s="NULL";
				ans.put(t,s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Map<String, String> getScoreBu(String code){
		sql="exec 成绩_Find \""+code+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Map<String, String> ans=new HashMap<String, String>();
			while(result.next()){
				String t=result.getString(1),s= result.getString(6);
				if(s==null)s="NULL";
				ans.put(t,s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getCouresName(String code){
		sql="exec 课程_Find \""+code+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			if(result.next()){
				return result.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static Vector getClassDetial(String code){
		sql="exec 班级_Find \""+code+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector ans=new Vector();
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				for(int i=2;i<=clen;i++)
					ans.add(result.getString(i));
			}
			if(ans.size()<1){
				ans.add("");
				ans.add("");
				ans.add("");
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Vector<Vector> kill(Vector data){
		sql="exec 开除  ";
		sql=add_sql(sql, data);
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector> killDetail(Vector data){
		sql="exec 开除详情  ";
		sql=add_sql(sql, data);
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector> teacherFind(String key){
		if(key.equals(""))return null;
		sql="exec 教师_Find  \""+key+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			int ind=1;
			int clen=result.getMetaData().getColumnCount();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String add_sql(String sql,Vector<String> data){
		for(int i=0;i<data.size();i++){
			String s=data.get(i);
			if(s.equals("NULL")==false && s.equals("")==false){
				sql+="\""+data.get(i)+"\"";
			}else{
				sql+="NULL";
			}
			if(i!=data.size()-1)sql+=",";
		}
		return sql;
	}
	public static boolean update(Vector<String> data,String name){
		sql="exec "+name+"_UDA ";
		sql=add_sql(sql, data);
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
	public static boolean insert(Vector<String> data,String name){
			sql="exec "+name+"_INS ";
			sql=add_sql(sql, data);
			try {
				stmt.execute(sql);
				return true;
			} catch (SQLException e) {
				//e.printStackTrace();
				return false;
			}
	}
	public static boolean delScore(Vector<String> data){
		sql="exec 成绩_DEL ";
		sql=add_sql(sql, data);
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	public static boolean delPlan(Vector<String> data){
		sql="exec 教学计划_DEL ";
		sql=add_sql(sql, data);
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	public static boolean delStudent(String code){
		sql="exec 学生_DEL \""+code+"\"";
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	public static boolean delClass(String code){
		sql="exec 班级_DEL \""+code+"\"";
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public static void close(){
		try {
			if(stmt!=null)stmt.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean Do(String sql1){
		try {		
			stmt.execute(sql1);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Vector<String> getPlanGen(Vector data){
		sql="exec 教学计划学分_Find ";
		sql=add_sql(sql, data);
		Vector<String> ans=new Vector<String>();
		try {
			ResultSet result=stmt.executeQuery(sql);
			while(result.next()){
				for(int i=1;i<=3;i++){
					ans.add(result.getString(i));
				}
			}
			if(ans.size()<1){
				ans.add("0");ans.add("0");ans.add("0");
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			ans.clear();
			ans.add("0");ans.add("0");ans.add("0");
			return null;
		}
	}
	public static Vector<Vector> getPlanDetail(Vector data){
		sql="exec 教学计划_Find";
		sql=add_sql(sql, data);
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				int clen=result.getMetaData().getColumnCount();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Vector<Vector> getPlanDetail2(Vector data){
		sql="exec 教学计划_Find2";
		sql=add_sql(sql, data);
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(false);
				int clen=result.getMetaData().getColumnCount();
				for(int i=1;i<=clen;i++){
					s.add(result.getString(i));
				}
				ans.add(s);
			}
			return ans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		init();
		System.out.print(login_s("admin","admin"));
		//getClassDetial("031111");
		int a=0;
		//close();
		//System.out.print(find_student(1, null));
	}
	
}
