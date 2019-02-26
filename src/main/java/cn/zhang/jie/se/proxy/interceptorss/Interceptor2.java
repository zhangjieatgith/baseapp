package cn.zhang.jie.se.proxy.interceptorss;

import java.lang.reflect.Method;

import cn.zhang.jie.se.proxy.interceptor.Interceptor;

public class Interceptor2 implements Interceptor{

	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器2 before】");
		return true;
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器2 around】");
	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器2 after】");
	}
}
