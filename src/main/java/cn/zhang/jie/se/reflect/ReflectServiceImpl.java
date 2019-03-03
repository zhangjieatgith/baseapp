package cn.zhang.jie.se.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectServiceImpl {
	
	private String name;
	private Integer age;
	
	public ReflectServiceImpl() {}
	
	public ReflectServiceImpl(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void sayHello(String name) {
		System.out.println("hello " + name);
	}
	
	/**
	 * 展示如何生成无参构造器的对象
	 * @return
	 */
	public static ReflectServiceImpl getInstance() {
		ReflectServiceImpl serviceImpl = null;
		try {
			serviceImpl = (ReflectServiceImpl) Class.forName("cn.zhang.jie.se.reflect.ReflectServiceImpl").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return serviceImpl;
	}
	
	/**
	 * 展示如何生成带有构造器参数的对象
	 * @param name
	 * @param age
	 * @return
	 */
	public static ReflectServiceImpl getInstance(String name,Integer age) {
		ReflectServiceImpl serviceImpl = null;
		try {
			serviceImpl = (ReflectServiceImpl) Class.forName("cn.zhang.jie.se.reflect.ReflectServiceImpl")
				.getConstructor(String.class,Integer.class).newInstance(name,age);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return serviceImpl;
	}
	
	
	/**
	 * 演示如何通过反射调用方法
	 * @return
	 */
	public static Object reflectMethod() {
		Object returnObj = null;
		ReflectServiceImpl serviceImpl = new ReflectServiceImpl();
		try {
			Method method = serviceImpl.getClass().getMethod("sayHello", String.class);
			returnObj = method.invoke(serviceImpl, "zhaolz");
		} catch (NoSuchMethodException | SecurityException 
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	
	@Override
	public String toString() {
		return "ReflectServiceImpl [name=" + name + ", age=" + age + "]";
	}

	public static void main(String[] args) {
		ReflectServiceImpl serviceImpl = ReflectServiceImpl.getInstance();
		System.out.println(serviceImpl);
		serviceImpl.sayHello("zhangsan");
		System.out.println(serviceImpl);
		System.out.println("******************");
		serviceImpl = ReflectServiceImpl.getInstance("lisi",20);
		System.out.println(serviceImpl);
		System.out.println("******************");
		Object obj = ReflectServiceImpl.reflectMethod();
		System.out.println("obj : " + obj);
	}

}