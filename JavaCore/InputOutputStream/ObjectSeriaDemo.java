package InputOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSeriaDemo {

	/**
	 * 序列化将一个已经实例化的类转成文件存储；
	 * 需要实例化的时候只要反序列化即可
	 * 
	 * 序列化ObjectOutputStream：
	 * 
	 * 反序列换ObjectInputStream
	 * 
	 * 都会使用到FileOutputStream、FileInputStream
	 */
	public static void main(String[] args) throws Exception {
		String file = "D:\\eclipse\\workspace\\BufferedReader.txt";
		//对象序列化ObjectOutputStream
//		ObjectOutputStream oos = new ObjectOutputStream(
//				new FileOutputStream(file));
//		Student stu = new Student("10001", "张三", 20);
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
