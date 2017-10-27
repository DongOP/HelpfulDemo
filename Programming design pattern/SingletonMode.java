//单例模式：一般使用的是懒汉式单例和饿汉式单例。
//参考：http://blog.csdn.net/jason0539/article/details/23297037/
     //懒汉式单例 推荐写法（实现了线程安全）：
     public class Singleton {
         private Singleton (){}

         private static class LazyHolder {
              private static final Singleton INSTANCE = new Singleton();
         }

         public static final Singleton getInstance() {
              return LazyHolder.INSTANCE;
         }
     }
	
     //饿汉式单例 推荐写法：
     //饿汉式单例类.在类初始化时，已经自行实例化
     public class Singleton1 {
         private Singleton1() {}

         private static final Singleton1 single = new Singleton1();
         //静态工厂方法
         public static Singleton1 getInstance() {
             return single;
         }
     }