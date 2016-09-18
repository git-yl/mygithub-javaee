package sockettest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class serThread extends Thread {

	//���ڵõ�socket
	Socket sk = null;
	
	public serThread(Socket socket) {
		// TODO �Զ����ɵĹ��캯�����
		sk = socket;
	}

	public void run(){
		//��ȡ����
		InputStream ins = null;
		InputStreamReader insr = null;
		BufferedReader bur = null;
		//������ݵ��ͻ���
		OutputStream os = null;
		PrintWriter pw = null;
		
		try {
			//ͨ��socket�õ�����
			ins = sk.getInputStream();
			insr = new InputStreamReader(ins);
			bur = new BufferedReader(insr);
			
			//�õ��������ַ���
			String data = bur.readLine();

			//��StringתΪStringBuffer������
			StringBuffer data2 = new StringBuffer(data);
			data = data2.reverse().toString();
			
			//�رյõ�����
			sk.shutdownInput();
			
			//���������ַ������ؿͻ���
			os = sk.getOutputStream();
			pw = new PrintWriter(os);
			pw.write(data);
			pw.flush();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally{
			//�رո�������
			try {
				ins.close();
				insr.close();
				bur.close();
				os.close();
				pw.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
