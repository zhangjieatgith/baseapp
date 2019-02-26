package cn.zhang.jie.se.proxy.interceptorss;

import java.lang.reflect.Method;

import cn.zhang.jie.se.proxy.interceptor.Interceptor;

public class Interceptor3 implements Interceptor{

	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器3 before】");
		return true;
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器3 around】");
	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("【拦截器3 after】");
	}
}
