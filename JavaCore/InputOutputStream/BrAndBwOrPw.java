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
	 * BufferedWriter��Ҫע�⻻��
	 * ��ʹ��bw.newLine();���л���
	 */
	public static void main(String[] args)  throws IOException{
		//���ļ����ж�д����
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream("D:\\eclipse\\workspace\\BufferedReader.txt")));
		
		//���Ʋ���
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream("D:\\eclipse\\workspace\\BufferedWriter.txt")));
		String line;
		while((line = br.readLine()) != null) {
			//BufferedReader��ʶ����У�println��Ҫ����ln
			System.out.println(line);
			
			//�����ļ�����
			bw.write(line);
			//����,BufferedWriterû���Զ�����
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
	}

}
