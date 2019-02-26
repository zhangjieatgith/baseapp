package cn.zhang.jie.se.proxy.jdk;

public class TestProxyMain {
	public static void main(String[] args) {
		JdkProxyExample example = new JdkProxyExample();
		HelloWorld proxy = (HelloWorld) example.bind(new HelloWorldImpl());
		System.out.println(proxy);
		System.out.println("**********************");
		proxy.sayHelloWorld();
		System.out.println("**********************");
		Object obj = proxy.getString("haha", 123);
		System.out.println("obj : " + obj);
		System.out.println("-----------");
		SecondMapper secondMapper = (SecondMapper) example.bind(new SecondMapperImpl());
		secondMapper.show();
	}
}
