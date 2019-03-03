package cn.zhang.jie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ExpBaseappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpBaseappApplication.class, args);
	}
}


/**
 *	一些需求：
 *1.普通管理员在角色管理中能够授权的菜单是有限制的（比如，对于资源管理，普通管理员不应该具备读写的权限）。也就意味着，普通用户在进行授权的时候，授权不应该把后台所有的资源文件都带过来
 *2.一个问题：普通管理员还能创建普通管理员吗，其实还是上面的问题，授权时是否能够授予“用户管理”的权限
 */
