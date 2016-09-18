package sockettest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class serThread extends Thread {

	//便于得到socket
	Socket sk = null;
	
	public serThread(Socket socket) {
		// TODO 自动生成的构造函数存根
		sk = socket;
	}

	public void run(){
		//获取输入
		InputStream ins = null;
		InputStreamReader insr = null;
		BufferedReader bur = null;
		//输出数据到客户端
		OutputStream os = null;
		PrintWriter pw = null;
		
		try {
			//通过socket得到输入
			ins = sk.getInputStream();
			insr = new InputStreamReader(ins);
			bur = new BufferedReader(insr);
			
			//得到传来的字符串
			String data = bur.readLine();

			//将String转为StringBuffer并逆序
			StringBuffer data2 = new StringBuffer(data);
			data = data2.reverse().toString();
			
			//关闭得到输入
			sk.shutdownInput();
			
			//将逆序后的字符串传回客户端
			os = sk.getOutputStream();
			pw = new PrintWriter(os);
			pw.write(data);
			pw.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally{
			//关闭各种连接
			try {
				ins.close();
				insr.close();
				bur.close();
				os.close();
				pw.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
