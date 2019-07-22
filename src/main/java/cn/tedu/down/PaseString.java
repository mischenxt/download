package cn.tedu.down;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PaseString {
	private static void readLine() throws IOException {
		InputStream in = new FileInputStream("file");
		OutputStream out = new FileOutputStream("file2");
		StringBuilder builder = new StringBuilder();
		//c1��ʾ�ϴζ�ȡ���ַ���c2��ʾ���ζ�ȡ���ַ�
		int c1=-1,c2=-1;
		while((c2 = in.read())!=-1) {
			//�Ƿ�Ϊ������ȡ��CRLF  CR����Ϊ13,LF����Ϊ10
			builder.append((char)c2);
			if(c1==125&&c2==44) {
				builder.append("\r\n");
			}
			c1 = c2;
		}
		in.close();
		String line =  builder.toString();
		byte[] datas =  line.getBytes();
		out.write(datas);
		out.close();
	}
	

	
	
	public static void main(String[] args) throws IOException {
		readLine();
	}
}
