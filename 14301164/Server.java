package sockettest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		ServerSocket sersoc = null;
		try{
			//����������socket�����󶨵�����
			sersoc = new ServerSocket(3333,0,InetAddress.getByName("127.0.0.1"));
			//ѭ�����մ�������Ϣ
			while(true){
				//�ȴ��ͻ��˷�������Ϣ
				Socket socket = sersoc.accept();
				//����һ�����̶߳�����д���
				serThread st = new serThread(socket);
				st.start();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				sersoc.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
