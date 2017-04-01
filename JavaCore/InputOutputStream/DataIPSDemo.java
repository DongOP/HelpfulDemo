package InputOutputStream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

public class DataIPSDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file = "demo/dos.dat";
		
		//��ӡfile�ļ�����
//		IOUtil.printHex(file);
		
		DataInputStream dataIPS = new DataInputStream(
				new FileInputStream(file));
		
		//��ȡdataOPS.writeInt(10)������
		int i = dataIPS.readInt();
		System.out.println("readInt: " + i);
		i = dataIPS.readInt();
		System.out.println("�ڶ���readInt: " + i);
		//��ȡdataOPS.writeLong(10l)������
		long l = dataIPS.readLong();
		System.out.println("readLong: " + l);
		//��ȡdataOPS.writeDouble(10.5)������
		double d = dataIPS.readDouble();
		System.out.println("readDouble: " + d);
		//��ȡdataOPS.writeUTF("�й�")������
		String s = dataIPS.readUTF();
		System.out.println("readUTF: " + s);
		
		dataIPS.close();
		System.out.println("------ ��ȡ����,�ر���������------");
		
	}

}
