package cn.zhang.jie.se.proxy;

public class HelloWorldImpl implements HelloWorld{

	@Override
	public void sayHelloWorld() {
		System.out.println("say helloworld..");
	}

	@Override
	public String getString(String p1, Integer p2) {
		System.out.println(" getString method...");
		return p1+"|||"+p2;
	}
}
