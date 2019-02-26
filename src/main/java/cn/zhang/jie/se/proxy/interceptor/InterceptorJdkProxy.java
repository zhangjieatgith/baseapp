package cn.zhang.jie.se.proxy.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class InterceptorJdkProxy implements InvocationHandler{

	private Object target;	//真实对象
	private String interceptorClass = null;	//拦截器的全限定名
	
	public InterceptorJdkProxy(Object target, String interceptorClass) {
		super();
		this.target = target;
		this.interceptorClass = interceptorClass;
	}

	/**
	 * 获得代理对象
	 * @param target
	 * @param interceptorClass
	 * @return
	 */
	public static Object bind(Object target,String interceptorClass) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), new InterceptorJdkProxy(target,interceptorClass));
	}
	
	/**
	 * 通过代理对象调用方法
	 * @param proxy 被代理的对象
	 * @param method 被调用的方法
	 * @param args 方法参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(interceptorClass == null) {
			//没有设置拦截器则直接反射原有方法
			return method.invoke(proxy, args);
		}
		Object result = null;
		//通过反射生成拦截器
		Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
		//调用前置方法
		if(interceptor.before(proxy, target, method, args)) {
			//反射原有对象方法
			result = method.invoke(target, args);
		} else {
			interceptor.around(proxy, target, method, args);
		}
		//调用后置方法
		interceptor.after(proxy, target, method, args);
		return result;
	}
}
