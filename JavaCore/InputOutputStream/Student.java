package InputOutputStream;

import java.io.Serializable;

public class Student implements Serializable {

	private String stuNo;
	private String stuName;
	//transient�󣬸�Ԫ�ز������jvmĬ�ϵ����л�
	//Ҳ�����Լ�������Ԫ�ص����л�
	private transient int  stuAge;
	
	//��Source���Զ��������Ա�Ĺ��췽��
	public Student(String stuNo, String stuName, int stuAge) {
		super();
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuAge = stuAge;
	}

	//��Source���Զ�����getter\setter,�Լ�toString����
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	
	@Override
	public String toString() {
		return "Student [stuNo=" + stuNo + ", stuName=" + stuName + ", stuAge="
				+ stuAge + "]";
	}
	
	//ArrayList������writeObject�ҳ������ǩ��
	private void writeObject(java.io.ObjectOutputStream s)
	        throws java.io.IOException{
		//����Ĭ�ϵ����л�����
		s.defaultWriteObject();
		//�Լ����stuAge�����л��������������������
		s.writeInt(stuAge);//writeObject
	}
	
	//ArrayList������readObject�ҳ������ǩ��
	private void readObject(java.io.ObjectInputStream s)
	        throws java.io.IOException, ClassNotFoundException {
		//��jvmĬ�ϵķ����л�Ԫ�ؽ��з����л�����
		s.defaultReadObject();
		//�Լ����stuAge�ķ����л�����
		this.stuAge = s.readInt();
	}
	
}
