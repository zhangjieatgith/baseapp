package cn.zhang.jie.se.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler{

	private Object target = null;
	
	public JdkProxyExample() {
		System.out.println("constructor....");
	}
	
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("在调用代理对象前的逻辑...,and method name : " + method.getName());
		Object obj = method.invoke(target, args);
		System.out.println("在调用代理对象后的逻辑。。。");
		return obj;
	}
}