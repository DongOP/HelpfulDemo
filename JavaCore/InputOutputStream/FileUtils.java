package InputOutputStream;

import java.io.File;
import java.io.IOException;

/**
 * @author Dong
 * 列出File的一些常用操作，比如过滤、遍历等操作
 */
public class FileUtils {
	public static void listDirectory(File dir) throws IOException{
		if(!dir.exists()) {
			throw new IllegalArgumentException("目录" + dir + "不存在！");
		}
		if(!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "不是目录！");
		}
//		String[] filenames = dir.list();
//		for (String string : filenames) {
//			System.out.println(dir + "\\" + string);
//		}
		//返回的是直接子目录的抽象
		File[] files = dir.listFiles();
		if(files != null && files.length > 0) {
			for(File file: files) {
				if(file.isDirectory()) {
					//递归
					listDirectory(file);
				}else{
					System.out.println(file);
				}
			}
		}
	}
}
