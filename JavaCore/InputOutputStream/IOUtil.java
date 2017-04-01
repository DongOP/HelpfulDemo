package InputOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * copyFile\copyFileBuffer\copyFileByByte
 * 通过复制文件来进行   性能对比
 * 性能：copyFile > copyFileBuffer > copyFileByByte
 * 重点：byte[] fileContent = new byte[8 * 1024];
 * 		while((b = in.read(fileContent, 0, fileContent.length)) != -1) {}
 */
public class IOUtil {
	
	/**
	 * 读取指定文件内容，按照16进制输出到控制台
	 * 并且每输出10个byte换行
	 */
	public static void printHex(String fileName) throws IOException {
		
		//把文件作为字节流进行读写操作
		FileInputStream in = new FileInputStream(fileName);
		int b;
		int i = 1;
		//等于-1相当于读到文件结尾
		while((b = in.read()) != -1) {
			//单位的16进制数，前面补0
			if(b <= 0xf) {
				System.out.print("0");
			}
			System.out.print(Integer.toHexString(b) + "  ");
			//每输出10个byte换行
			if(i++ % 10 == 0) {
				System.out.println();
			}
		}
		in.close();
	}
	
	
	/**
	 * 复制一份文件，字节批量读取
	 * 复制文件--->性能最高
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException{
		
		//判断文件exists 及 判断isFile
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("源文件：不存在。");
		}
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是一个文件！！！。");
		}
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		//批量读写，声明一个字节数组作为文件内容的载体
		byte[] fileContent = new byte[8 * 1024];
		int b;
		//写入数据，直至所有的文件内容读入完毕
		while((b = in.read(fileContent, 0, fileContent.length)) != -1) {
			out.write(fileContent, 0, b);
//			out.flush();//清空缓存，FileOutputStream可有可无
		}
		in.close();
		out.close();
	}
	
	
	/**
	 * BufferedInputStream/BufferedOutputStream
	 * 进行文件的拷贝，利用缓冲的字节流
	 * 相比FileInputStream的读入性能有提升
	 */
	public static void copyFileBuffer(File srcFile, 
			File destFile) throws IOException {
		//判断文件exists 及 判断isFile
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("源文件：不存在。");
		}
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是一个文件！！！。");
		}
		
		//定义缓冲输入输出数据流
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFile));
		int c;
		while((c = bis.read()) != -1) {
			bos.write(c);
			//带缓冲Buffered可以刷新缓冲区
			bos.flush();
		}
		
		//关闭输入、输出数据流
		bis.close();
		bos.close();
	}
	
	
	/**
	 * copyFileByByte
	 * 单字节不带缓冲进行拷贝--->三个复制方法中性能最低
	 */
	public static void copyFileByByte(File srcFile, File destFile) throws IOException {
		//判断文件exists 及 判断isFile
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("源文件：不存在。");
		}
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是一个文件！！！。");
		}
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		int c;
		while((c = in.read()) != -1) {
			out.write(c);
			out.flush();//不带缓冲流可不用flush()刷新缓冲
		}
		//关闭输入、输出数据流
		in.close();
		out.close();
	}
	
}
