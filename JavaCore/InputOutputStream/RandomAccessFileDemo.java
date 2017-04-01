package InputOutputStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws IOException{
		
		File demo = new File("demo");
		if(!demo.exists()){
			//����Ŀ¼
			demo.mkdir();
		}
		
		File file = new File(demo, "raf.dat");
		if(!file.exists()){
			//�����ļ�
			file.createNewFile();
		}
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		//ָ���λ��
		System.out.println(raf.getFilePointer());
		
		raf.write('A');//д��һ���ֽ�
		System.out.println(raf.getFilePointer());
		raf.write('B');
		
		int i = 0x7fffffff;
		raf.writeInt(i);
		
		String s = "��";
		//��gbk��ת���ֽ�
		byte[] gbk = s.getBytes("gbk");
		raf.write(gbk);
		System.out.println(raf.getFilePointer());
		
		//���ļ��������ָ���Ƶ�ͷ��
		raf.seek(0);
		//һ���Զ�ȡ
		byte[] buf = new byte[(int)raf.length()];
		raf.read(buf);
		//Arrays.toString()�����������������������飬
		//������һ��һ������������е�Ԫ��
		System.out.println(Arrays.toString(buf));
		for(byte b:buf) {
			System.out.println(Integer.toHexString(b & 0xff) + " ");
		}
		
		raf.close();
	}
}
