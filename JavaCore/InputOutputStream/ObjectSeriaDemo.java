package InputOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSeriaDemo {

	/**
	 * ���л���һ���Ѿ�ʵ��������ת���ļ��洢��
	 * ��Ҫʵ������ʱ��ֻҪ�����л�����
	 * 
	 * ���л�ObjectOutputStream��
	 * 
	 * �����л�ObjectInputStream
	 * 
	 * ����ʹ�õ�FileOutputStream��FileInputStream
	 */
	public static void main(String[] args) throws Exception {
		String file = "D:\\eclipse\\workspace\\BufferedReader.txt";
		//�������л�ObjectOutputStream
//		ObjectOutputStream oos = new ObjectOutputStream(
//				new FileOutputStream(file));
//		Student stu = new Student("10001", "����", 20);
//		oos.writeObject(stu);
//		oos.flush();
//		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(file));
		Student stu = (Student) ois.readObject();
		System.out.println(stu);
		ois.close();
	}

}
