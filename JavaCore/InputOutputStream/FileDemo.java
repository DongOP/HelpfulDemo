package InputOutputStream;

import java.io.File;

public class FileDemo {
	public static void main(String args[]) {
		File file = new File("E:\\java");
		//�ж�file�Ƿ����
		System.out.println("E:\\java�ļ��Ƿ����: " + file.exists());
		//�ļ��Ĵ�����ɾ��
//		if(!file.exists()) {
//			file.mkdir();//file.mkdirs();
//		}else {
//			file.delete();
//		}
		//�ж�file�Ƿ�Ϊһ��Ŀ¼
		System.out.println("file�Ƿ�Ϊһ��Ŀ¼: " + file.isDirectory());
		//�Ƿ���һ���ļ�
		System.out.println("file�Ƿ���һ���ļ�: " + file.isFile());
		
	}
}
