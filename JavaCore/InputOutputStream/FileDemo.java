package InputOutputStream;

import java.io.File;

public class FileDemo {
	public static void main(String args[]) {
		File file = new File("E:\\java");
		//判断file是否存在
		System.out.println("E:\\java文件是否存在: " + file.exists());
		//文件的创建与删除
//		if(!file.exists()) {
//			file.mkdir();//file.mkdirs();
//		}else {
//			file.delete();
//		}
		//判断file是否为一个目录
		System.out.println("file是否为一个目录: " + file.isDirectory());
		//是否是一个文件
		System.out.println("file是否是一个文件: " + file.isFile());
		
	}
}
