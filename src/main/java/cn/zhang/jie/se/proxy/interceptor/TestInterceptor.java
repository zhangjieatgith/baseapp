package cn.zhang.jie.se.proxy.interceptor;

import cn.zhang.jie.se.proxy.jdk.HelloWorld;
import cn.zhang.jie.se.proxy.jdk.HelloWorldImpl;

public class TestInterceptor {
	public static void main(String[] args) {
		HelloWorld helloWorld = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl()
				, "cn.zhang.jie.se.proxy.interceptor.MyInterceptor");
		String str = helloWorld.getString("ttt", 10);
		System.out.println("str : " + str);
		System.out.println("********************");
		
	}
}
