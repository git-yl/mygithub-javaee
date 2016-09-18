package sockettest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ServerSocket sersoc = null;
		try{
			//建立服务器socket，并绑定到本地
			sersoc = new ServerSocket(3333,0,InetAddress.getByName("127.0.0.1"));
			//循环接收传来的信息
			while(true){
				//等待客户端发来的信息
				Socket socket = sersoc.accept();
				//启动一个新线程对其进行处理
				serThread st = new serThread(socket);
				st.start();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				sersoc.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
