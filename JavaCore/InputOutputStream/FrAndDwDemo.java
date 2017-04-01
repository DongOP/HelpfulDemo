package InputOutputStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrAndDwDemo {

	/**
	 * FileReader、FileWriter不支持编码的转换，如GBK转换成UTF-8
	 * FileReader不用借助于FileInputStream
	 * FileReader相较于InputStreamReader，其语法简单些，功能差不多
	 */
	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("D:\\eclipse\\workspace\\FileReader.txt");
		//文件目录后面加上true的话，不会重写文件，而是在末尾append追加内容
//		FileWriter fw = new FileWriter("D:\\eclipse\\workspace\\FileWriter.txt", ture);
		FileWriter fw = new FileWriter("D:\\eclipse\\workspace\\FileWriter.txt");
		
		//字符流使用字符数组来批量读取或者写入
		char[] buffer = new char[2046];
		int c;
		while((c = fr.read(buffer, 0, buffer.length)) != -1) {
			//复制文件
			fw.write(buffer);
			fw.flush();//刷新缓冲
		}
		fr.close();
		fw.close();
		System.out.println("文件读取、备份完毕！");
	}

}
