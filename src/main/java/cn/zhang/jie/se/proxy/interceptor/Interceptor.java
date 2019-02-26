package cn.zhang.jie.se.proxy.interceptor;

import java.lang.reflect.Method;

/**
 * 一般的设计者会设计一个拦截器接口供开发者使用，开发者只需要知道拦截器接口的方法、含义和作用即可，无需知道动态代理是怎么实现的
 * @author zhangjie
 */
public interface Interceptor {
	//假设当返回值是true时，反射真实对象的方法，否则调用 around 方法
	public boolean before(Object proxy , Object target , Method method , Object [] args);
	public void around(Object proxy , Object target , Method method , Object [] args);
	public void after(Object proxy , Object target , Method method , Object [] args);
}
