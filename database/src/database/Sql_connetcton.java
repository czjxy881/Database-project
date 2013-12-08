package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import sun.jdbc.odbc.JdbcOdbcDriver;
public class Sql_connetcton {
	private static String url="jdbc:odbc:jxy";
	private static Connection con=null;
	private static String sql;
	private static Statement stmt;
	public static void init(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	public static int login_s(String user,String password){
		try {
			init();
			con=DriverManager.getConnection(url, user, password);
			stmt=con.createStatement();
		} catch (SQLException e) {
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
			while(result.next()){
				now=new String[5];
				for(i=0;i<5;i++){
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
			while(result.next()){
				Vector<String> s=new Vector<String>();
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
	public static Vector<String> getPersons(String num){
		sql="exec 学分_Find \""+num+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<String> ans=new Vector<String>();
			while(result.next()){
				for(int i=1;i<=6;i++)
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
	public static Map<String, String> getScore(String code){
		sql="exec 成绩_Find \""+code+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Map<String, String> ans=new HashMap<String, String>();
			while(result.next()){
				ans.put(result.getString(1), result.getString(4));
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Vector getClassDetial(String code){
		sql="exec 班级_Find \""+code+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector ans=new Vector();
			while(result.next()){
				for(int i=2;i<=3;i++)
					ans.add(result.getString(i));
			}
			if(ans.size()<1){
				ans.add("");
				ans.add("");
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Vector<Vector> kill(int key){
		sql="exec 开除  \""+String.valueOf(key)+"\"";
		try {
			ResultSet result=stmt.executeQuery(sql);
			Vector<Vector> ans=new Vector<Vector>();
			while(result.next()){
				Vector s=new Vector<String>();
				for(int i=1;i<=4;i++){
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
			while(result.next()){
				Vector s=new Vector<String>();
				s.add(ind++);
				for(int i=1;i<=2;i++){
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
			e.printStackTrace();
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
	public static void main(String[] args) {
		System.out.print(login_s("admin","admin"));
		getScore("03051002");
		int a=0;
		close();
		//System.out.print(find_student(1, null));
	}
	
}
