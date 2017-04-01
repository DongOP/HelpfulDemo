package InputOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IsrAndOswDemo {

	/**
	 * 批量读取文件
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
		 * 批量读取，放入buffer这个数组
		 * 此处用的char[]类型的字符数组；
		 * 需留意：
		 * 使用FileOutputStream时则使用的byte[]
		 */
		char[] buffer = new char[8 * 1024];
		int c;
		while((c = isr.read(buffer, 0, buffer.length)) != -1) {
			String s = new String(buffer, 0, c);
			//打印出read的内容
			System.out.println(s);
			
			//写入buffer数据
			osw.write(buffer);
		} 
		
		isr.close();
		osw.close();
		System.out.println("打印完成！");
	}

}
