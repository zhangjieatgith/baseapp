package cn.zhang.jie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.service.UserInfoService;

@RequestMapping("/sysmanage")
@Controller
public class SysManageController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/usermanage")
	public String userManage() {
		return "sysmanage/usermanage";
	}
	
	@RequestMapping("/rolemanage")
	public String roleManage() {
		return "sysmanage/rolemanage";
	}
	
	@RequestMapping("/resomanage")
	public String resoManage() {
		return "sysmanage/resomanage";
	}
	
	@ResponseBody
	@RequestMapping("/selectUsers")
	public Object selectUsers() {
		return userInfoService.selectUsers();
	}
	
	@ResponseBody
	@RequestMapping("/initUserPass")
	public Object initUserPass(User user) {
		System.out.println("user : " + user);
		return userInfoService.initUserPass(user);
	}
	
	
	@ResponseBody
	@RequestMapping("/getAllAuths")
	public Object getAllAuths() {
		return userInfoService.getAllAuths();
	}
	
	@ResponseBody
	@RequestMapping("/getAllRoles")
	public Object getAllRoles() {
		return userInfoService.getAllRoles();
	}
	
	@ResponseBody
	@RequestMapping("/getAuthsForOneRoleByTree")
	public Object getAuthsForOneRoleByTree(@RequestParam("id") String id) {
		System.out.println("getAuthsForOneRoleByTree,id : " + id);
		return userInfoService.getAuthsForOneRoleByTree(id);
	}
	
	
	@ResponseBody
	@RequestMapping("/updateAuthOk")
	public Object updateAuthOk(@RequestParam("roleId") String roleId,@RequestParam("ids")String ids) {
		System.out.println("roleId : " + roleId);
		System.out.println("ids : " + ids);
		return userInfoService.updateAuthOk(roleId, ids.split(","));
	}
}
