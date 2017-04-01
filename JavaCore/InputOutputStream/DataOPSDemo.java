package InputOutputStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOPSDemo {
	public static void main(String[] args) throws IOException {
		String file = "demo/dos.txt";
		DataOutputStream dataOPS = new DataOutputStream(
				new FileOutputStream(file));
		dataOPS.writeInt(10);
		dataOPS.writeInt(-10);
		dataOPS.writeLong(10l);
		dataOPS.writeDouble(10.5);
		//����UTF-8����д��
		dataOPS.writeUTF("�й�");
		//����UTF-16be
		dataOPS.writeChars("�й�");
		dataOPS.close();
		
		//д����Ϻ󣬶���file�ļ�������
		IOUtil.printHex(file);
		System.out.println("\nд��������ϣ�\n������16������ʾ���ļ���");
		
	}
}
