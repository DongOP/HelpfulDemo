package InputOutputStream;

import java.io.File;
import java.io.IOException;

/**
 * @author Dong
 * �г�File��һЩ���ò�����������ˡ������Ȳ���
 */
public class FileUtils {
	public static void listDirectory(File dir) throws IOException{
		if(!dir.exists()) {
			throw new IllegalArgumentException("Ŀ¼" + dir + "�����ڣ�");
		}
		if(!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "����Ŀ¼��");
		}
//		String[] filenames = dir.list();
//		for (String string : filenames) {
//			System.out.println(dir + "\\" + string);
//		}
		//���ص���ֱ����Ŀ¼�ĳ���
		File[] files = dir.listFiles();
		if(files != null && files.length > 0) {
			for(File file: files) {
				if(file.isDirectory()) {
					//�ݹ�
					listDirectory(file);
				}else{
					System.out.println(file);
				}
			}
		}
	}
}
