package InputOutputStream;

import java.io.File;
import java.io.IOException;

public class IOUtilTest4 {

	/**
	 * ����BufferedInputStream\BufferedOutputStream����
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		IOUtil.copyFile(new File("D:\\eclipse\\workspace\\test4.txt"),
				new File("D:\\eclipse\\workspace\\testdest211.txt"));
		System.out.println("�������롢����ĸ��ƽ�����");
	}

}
