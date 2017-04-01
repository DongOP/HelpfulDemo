package InputOutputStream;

import java.io.Serializable;

public class Student implements Serializable {

	private String stuNo;
	private String stuName;
	//transient后，该元素不会进行jvm默认的序列化
	//也可以自己完成这个元素的序列化
	private transient int  stuAge;
	
	//在Source中自动创建其成员的构造方法
	public Student(String stuNo, String stuName, int stuAge) {
		super();
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuAge = stuAge;
	}

	//在Source中自动生成getter\setter,以及toString方法
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
	
	//ArrayList中搜索writeObject找出下面的签名
	private void writeObject(java.io.ObjectOutputStream s)
	        throws java.io.IOException{
		//进行默认的序列化操作
		s.defaultWriteObject();
		//自己完成stuAge的序列化，这样做可以提高性能
		s.writeInt(stuAge);//writeObject
	}
	
	//ArrayList中搜索readObject找出下面的签名
	private void readObject(java.io.ObjectInputStream s)
	        throws java.io.IOException, ClassNotFoundException {
		//把jvm默认的反序列化元素进行反序列化操作
		s.defaultReadObject();
		//自己完成stuAge的反序列化操作
		this.stuAge = s.readInt();
	}
	
}
