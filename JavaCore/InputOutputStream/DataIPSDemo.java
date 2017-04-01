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
		
		//打印file文件内容
//		IOUtil.printHex(file);
		
		DataInputStream dataIPS = new DataInputStream(
				new FileInputStream(file));
		
		//读取dataOPS.writeInt(10)的数据
		int i = dataIPS.readInt();
		System.out.println("readInt: " + i);
		i = dataIPS.readInt();
		System.out.println("第二次readInt: " + i);
		//读取dataOPS.writeLong(10l)的数据
		long l = dataIPS.readLong();
		System.out.println("readLong: " + l);
		//读取dataOPS.writeDouble(10.5)的数据
		double d = dataIPS.readDouble();
		System.out.println("readDouble: " + d);
		//读取dataOPS.writeUTF("中国")的数据
		String s = dataIPS.readUTF();
		System.out.println("readUTF: " + s);
		
		dataIPS.close();
		System.out.println("------ 读取结束,关闭数据流！------");
		
	}

}
