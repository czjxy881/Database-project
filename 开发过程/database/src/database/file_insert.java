package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class file_insert {
	private static Scanner fin;
	private static Scanner now;


	public static Vector<Vector<String>> get(String formate,String path,String[] name,Vector<Integer> order){
		//TODO 根据formate 分隔
		try{
			FileReader freader=new FileReader(path);
			fin = new Scanner(freader);
			Vector<Vector<String>> a=new Vector<Vector<String>>();
			Vector<String> tb;
			String s;
			int num=1;
			while(fin.hasNext()){
				s=fin.nextLine();
				now = new Scanner(s);
				now.useDelimiter(","); //现在是根据逗号分隔的
				int ind=0;
				tb=new Vector<String>();
				tb.setSize(name.length);
				for(int i=0;i<name.length;i++)tb.set(i,"NULL");
				while(now.hasNext()&&order.size()>ind){
					tb.set(order.get(ind).intValue(), now.next());
					ind++;
				}
				a.add(tb);
			}
			return a;
		}catch (Exception e){
			//e.printStackTrace();
			System.out.println("fail");
			return null;
		}
	}
	
	private final static String[] name=new String[]{"班号","学号","姓名"};
	public static void main(String[] args) throws FileNotFoundException {
		Vector<Integer> order=new Vector<Integer>();
		order.add(new Integer(0));
		order.add(new Integer(1));
		order.add(new Integer(2));
		Vector<Vector<String>>s=get("","C:\\Users\\jxy1\\Documents\\GitHub\\Database-project\\导入数据\\sc.txt",name,order);
		System.out.println(s.size());
		int a=1;
	}

}
