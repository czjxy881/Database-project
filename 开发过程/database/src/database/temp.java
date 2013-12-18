package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class temp {
	static void isAddressAvailable(String ip){ 
		try{ 
		InetAddress address = InetAddress.getByName(ip);//ping this IP 

		if(address instanceof java.net.Inet4Address){ 
		System.out.println(ip + " is ipv4 address"); 
		}else 
		if(address instanceof java.net.Inet6Address){ 
		System.out.println(ip + " is ipv6 address"); 
		}else{ 
		System.out.println(ip + " is unrecongized"); 
		} 

		if(address.isReachable(5000)){ 
		System.out.println("SUCCESS - ping " + ip + " with no interface specified"); 
		}else{ 
		System.out.println("FAILURE - ping " + ip + " with no interface specified"); 
		} 

		System.out.println("\n-------Trying different interfaces--------\n"); 

		Enumeration<NetworkInterface> netInterfaces = 
		NetworkInterface.getNetworkInterfaces(); 
		while(netInterfaces.hasMoreElements()) { 
		NetworkInterface ni = netInterfaces.nextElement(); 
		System.out.println( 
		"Checking interface, DisplayName:" + ni.getDisplayName() + ", Name:" + ni.getName()); 
		if(address.isReachable(ni, 64, 5000)){ 
		System.out.println("SUCCESS - ping " + ip); 
		}else{ 
		System.out.println("FAILURE - ping " + ip); 
		} 

		Enumeration<InetAddress> ips = ni.getInetAddresses(); 
		while(ips.hasMoreElements()) { 
		System.out.println("IP: " + ips.nextElement().getHostAddress()); 
		} 
		System.out.println("-------------------------------------------"); 
		} 
		}catch(Exception e){ 
		System.out.println("error occurs."); 
		e.printStackTrace(); 
		} 
		} 
	static boolean can(String ip){
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		  Process process = null; // 声明处理类对象
		  String line = null; // 返回行信息
		  InputStream is = null; // 输入流
		  InputStreamReader isr = null; // 字节流
		  BufferedReader br = null;
		  boolean res = false;// 结果
		  try {
			  
		   process = runtime.exec("ping " + ip); // PING
		   is = process.getInputStream(); // 实例化输入流
		   isr = new InputStreamReader(is);// 把输入流转换成字节流
		   br = new BufferedReader(isr);// 从字节中读取文本
		   while ((line = br.readLine()) != null) {
		    if (line.contains("TTL")) {
		     res = true;
		     break;
		    }
		   }
		   is.close();
		   isr.close();
		   br.close();
		   return res;
		  } catch (IOException e) {
		   System.out.println(e);
		   runtime.exit(1);
		   return false;
		  }
	}


	public static void main(String[] args) {
			isAddressAvailable("115.155.45.4");
		System.out.println(can("115.155.45.4"));
	}

}
