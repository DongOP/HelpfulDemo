package InputOutputStream;

import java.io.File;
import java.io.IOException;

public class IOUtilTest02 {
	public static void main(String args[]) throws IOException {
		IOUtil.copyFile(new File("D:\\eclipse\\workspace\\test02.txt"),
				new File("D:\\eclipse\\workspace\\testdest02.txt"));
		System.out.println("备份文件完毕！");
	}
}
