package InputOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IsrAndOswDemo {

	/**
	 * ������ȡ�ļ�
	 */
	public static void main(String[] args) throws IOException {
		
		FileInputStream in = new FileInputStream("D:\\eclipse\\workspace\\utf8Test.txt");
		InputStreamReader isr = new InputStreamReader(in, "utf-8");
//		InputStreamReader isr = new InputStreamReader(in);
		
		FileOutputStream out = new FileOutputStream("D:\\eclipse\\workspace\\utf8writeTest.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
//		int c;
//		while((c = isr.read()) != -1) {
//			System.out.print((char)c);
//		}

		/*
		 * ������ȡ������buffer�������
		 * �˴��õ�char[]���͵��ַ����飻
		 * �����⣺
		 * ʹ��FileOutputStreamʱ��ʹ�õ�byte[]
		 */
		char[] buffer = new char[8 * 1024];
		int c;
		while((c = isr.read(buffer, 0, buffer.length)) != -1) {
			String s = new String(buffer, 0, c);
			//��ӡ��read������
			System.out.println(s);
			
			//д��buffer����
			osw.write(buffer);
		} 
		
		isr.close();
		osw.close();
		System.out.println("��ӡ��ɣ�");
	}

}
