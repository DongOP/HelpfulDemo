package InputOutputStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws IOException{
		
		File demo = new File("demo");
		if(!demo.exists()){
			//创建目录
			demo.mkdir();
		}
		
		File file = new File(demo, "raf.dat");
		if(!file.exists()){
			//创建文件
			file.createNewFile();
		}
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		//指针的位置
		System.out.println(raf.getFilePointer());
		
		raf.write('A');//写了一个字节
		System.out.println(raf.getFilePointer());
		raf.write('B');
		
		int i = 0x7fffffff;
		raf.writeInt(i);
		
		String s = "中";
		//以gbk来转换字节
		byte[] gbk = s.getBytes("gbk");
		raf.write(gbk);
		System.out.println(raf.getFilePointer());
		
		//读文件，必须把指针移到头部
		raf.seek(0);
		//一次性读取
		byte[] buf = new byte[(int)raf.length()];
		raf.read(buf);
		//Arrays.toString()的作用是用来方便地输出数组，
		//而不用一个一个地输出数组中的元素
		System.out.println(Arrays.toString(buf));
		for(byte b:buf) {
			System.out.println(Integer.toHexString(b & 0xff) + " ");
		}
		
		raf.close();
	}
}
