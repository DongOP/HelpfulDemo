package InputOutputStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrAndDwDemo {

	/**
	 * FileReader��FileWriter��֧�ֱ����ת������GBKת����UTF-8
	 * FileReader���ý�����FileInputStream
	 * FileReader�����InputStreamReader�����﷨��Щ�����ܲ��
	 */
	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("D:\\eclipse\\workspace\\FileReader.txt");
		//�ļ�Ŀ¼�������true�Ļ���������д�ļ���������ĩβappend׷������
//		FileWriter fw = new FileWriter("D:\\eclipse\\workspace\\FileWriter.txt", ture);
		FileWriter fw = new FileWriter("D:\\eclipse\\workspace\\FileWriter.txt");
		
		//�ַ���ʹ���ַ�������������ȡ����д��
		char[] buffer = new char[2046];
		int c;
		while((c = fr.read(buffer, 0, buffer.length)) != -1) {
			//�����ļ�
			fw.write(buffer);
			fw.flush();//ˢ�»���
		}
		fr.close();
		fw.close();
		System.out.println("�ļ���ȡ��������ϣ�");
	}

}
