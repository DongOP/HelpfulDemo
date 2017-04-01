package InputOutputStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BrAndBwOrPw {

	/**
	 * BufferedWriter需要注意换行
	 * 可使用bw.newLine();进行换行
	 */
	public static void main(String[] args)  throws IOException{
		//对文件进行读写操作
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream("D:\\eclipse\\workspace\\BufferedReader.txt")));
		
		//复制操作
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream("D:\\eclipse\\workspace\\BufferedWriter.txt")));
		String line;
		while((line = br.readLine()) != null) {
			//BufferedReader不识别空行，println需要加上ln
			System.out.println(line);
			
			//复制文件操作
			bw.write(line);
			//换行,BufferedWriter没有自动换行
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
	}

}
