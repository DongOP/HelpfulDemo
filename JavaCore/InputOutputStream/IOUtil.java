package InputOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * copyFile\copyFileBuffer\copyFileByByte
 * ͨ�������ļ�������   ���ܶԱ�
 * ���ܣ�copyFile > copyFileBuffer > copyFileByByte
 * �ص㣺byte[] fileContent = new byte[8 * 1024];
 * 		while((b = in.read(fileContent, 0, fileContent.length)) != -1) {}
 */
public class IOUtil {
	
	/**
	 * ��ȡָ���ļ����ݣ�����16�������������̨
	 * ����ÿ���10��byte����
	 */
	public static void printHex(String fileName) throws IOException {
		
		//���ļ���Ϊ�ֽ������ж�д����
		FileInputStream in = new FileInputStream(fileName);
		int b;
		int i = 1;
		//����-1�൱�ڶ����ļ���β
		while((b = in.read()) != -1) {
			//��λ��16��������ǰ�油0
			if(b <= 0xf) {
				System.out.print("0");
			}
			System.out.print(Integer.toHexString(b) + "  ");
			//ÿ���10��byte����
			if(i++ % 10 == 0) {
				System.out.println();
			}
		}
		in.close();
	}
	
	
	/**
	 * ����һ���ļ����ֽ�������ȡ
	 * �����ļ�--->�������
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException{
		
		//�ж��ļ�exists �� �ж�isFile
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("Դ�ļ��������ڡ�");
		}
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "����һ���ļ���������");
		}
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		//������д������һ���ֽ�������Ϊ�ļ����ݵ�����
		byte[] fileContent = new byte[8 * 1024];
		int b;
		//д�����ݣ�ֱ�����е��ļ����ݶ������
		while((b = in.read(fileContent, 0, fileContent.length)) != -1) {
			out.write(fileContent, 0, b);
//			out.flush();//��ջ��棬FileOutputStream���п���
		}
		in.close();
		out.close();
	}
	
	
	/**
	 * BufferedInputStream/BufferedOutputStream
	 * �����ļ��Ŀ��������û�����ֽ���
	 * ���FileInputStream�Ķ�������������
	 */
	public static void copyFileBuffer(File srcFile, 
			File destFile) throws IOException {
		//�ж��ļ�exists �� �ж�isFile
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("Դ�ļ��������ڡ�");
		}
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "����һ���ļ���������");
		}
		
		//���建���������������
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFile));
		int c;
		while((c = bis.read()) != -1) {
			bos.write(c);
			//������Buffered����ˢ�»�����
			bos.flush();
		}
		
		//�ر����롢���������
		bis.close();
		bos.close();
	}
	
	
	/**
	 * copyFileByByte
	 * ���ֽڲ���������п���--->�������Ʒ������������
	 */
	public static void copyFileByByte(File srcFile, File destFile) throws IOException {
		//�ж��ļ�exists �� �ж�isFile
		if(!srcFile.exists()) {
			throw new IllegalArgumentException("Դ�ļ��������ڡ�");
		}
		if(!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "����һ���ļ���������");
		}
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		int c;
		while((c = in.read()) != -1) {
			out.write(c);
			out.flush();//�����������ɲ���flush()ˢ�»���
		}
		//�ر����롢���������
		in.close();
		out.close();
	}
	
}
