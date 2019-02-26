package cn.zhang.jie.se.proxy.interceptorss;

import cn.zhang.jie.se.proxy.interceptor.InterceptorJdkProxy;
import cn.zhang.jie.se.proxy.jdk.HelloWorld;
import cn.zhang.jie.se.proxy.jdk.HelloWorldImpl;


/**
 * 责任链模式：当一个对象在一条链上被多个拦截器拦截处理（拦截器也可以选择不拦截处理它）时，将这样的设计模式称为拦截器模式
 * 1.责任链模式就是层层代理，即在代理proxy1的基础上生成proxy2，在proxy2的基础上生成proxy3
 * 2.责任链模式的优点在于我们可以在传递链上加入新的拦截器，增加拦截逻辑。缺点是会增加代理和反射，而代理和反射的性能不高
 * @author zhangjie
 */
public class TestInterceptorss {
	public static void main(String[] args) {
		HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), "cn.zhang.jie.se.proxy.interceptorss.Interceptor1");
		HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1, "cn.zhang.jie.se.proxy.interceptorss.Interceptor2");
		HelloWorld proxy3 =  (HelloWorld) InterceptorJdkProxy.bind(proxy2, "cn.zhang.jie.se.proxy.interceptorss.Interceptor3");
		String str = proxy3.getString("rrr", 12);
		System.out.println("str = " + str);
	}
}
