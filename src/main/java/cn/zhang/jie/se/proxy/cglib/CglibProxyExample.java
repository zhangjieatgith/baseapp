package cn.zhang.jie.se.proxy.cglib;

import java.lang.reflect.Method;

import cn.zhang.jie.se.proxy.jdk.HelloWorldImpl;
import cn.zhang.jie.se.proxy.jdk.SecondMapperImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * 使用 CGLIB 简单示例
 * @author zhangjie
 */
public class CglibProxyExample implements MethodInterceptor {

	/**
	 * 生成 cglib 代理对象
	 * @param cls
	 * @return
	 */
	public Object getProxy(Class cls) {
		//cglib enhancer 增强类对象
		Enhancer enhancer = new Enhancer();
		//设置增强类型（也就是将被代理的类作为代理类的父类）
		enhancer.setSuperclass(cls);
		//定义代理逻辑对象为当前对象，也就是说代理对象一定是 MethodInterceptor 接口的实现类
		enhancer.setCallback(this);
		//生成并返回代理对象
		return enhancer.create();
	}
	
	
	/**
	 * 代理的方法，它相当于 jdk 动态代理的 invoke 方法
	 * @param obj 代理对象
	 * @param method 方法
	 * @param args 方法参数
	 * @param proxy 方法代理
	 * @return 代理方法的返回
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用真实对象前...");
		System.out.println("intercept : method name : " + method.getName());
		//CGLIB反射调用真实对象方法
		Object result = methodProxy.invokeSuper(proxy, args);
		System.out.println("调用真实对象后...");
		return result;
	}

	
	public static void main(String[] args) {
		CglibProxyExample cpe = new CglibProxyExample();
		HelloWorldImpl obj = (HelloWorldImpl) cpe.getProxy(HelloWorldImpl.class);
		String string = obj.getString("mnmn", 999);
		System.out.println(string);
		System.out.println("*******************");
		System.out.println("obj : " + obj);
		System.out.println("*******************");
		SecondMapperImpl secondMapperImpl = (SecondMapperImpl) cpe.getProxy(SecondMapperImpl.class);
		secondMapperImpl.show();
	}
}
