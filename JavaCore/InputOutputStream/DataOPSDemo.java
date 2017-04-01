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
		//采用UTF-8编码写出
		dataOPS.writeUTF("中国");
		//采用UTF-16be
		dataOPS.writeChars("中国");
		dataOPS.close();
		
		//写入完毕后，读出file文件的数据
		IOUtil.printHex(file);
		System.out.println("\n写入数据完毕！\n正在以16进制显示该文件。");
		
	}
}
