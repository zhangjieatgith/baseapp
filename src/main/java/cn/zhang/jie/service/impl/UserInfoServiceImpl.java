package cn.zhang.jie.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.mapper.UserMapper;
import cn.zhang.jie.service.UserInfoService;
import cn.zhang.jie.utils.Md5Util;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserMapper userMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getUserInfo(String userName) {
		List<Map<String,Object>> sourceList = userMapper.getUserAuthInfo(userName);
		List<Map<String,Object>> resultList = new ArrayList<>();
		boolean isFirst = true;
		for (Map<String,Object> map : sourceList) {
			if(map.get("parent_id") == null) {
				//对于父级菜单
				Map<String,Object> tmpMap = new HashMap<>();
				tmpMap.put("text", map.get("auth_name"));
				tmpMap.put("itemId", map.get("id"));
				if(isFirst) {
					tmpMap.put("state", "open");
					isFirst = false;
				}
				resultList.add(tmpMap);
			}else {
				//对于子菜单
				for (Map<String,Object> map2 : resultList) {
					Map<String,Object> child = null;
					if(map.get("parent_id").toString().equals(map2.get("itemId").toString())) {
						child = new HashMap<>();
						child.put("text", map.get("auth_name"));
						child.put("data", map.get("auth_url"));
					}
					if(map.get("parent_id").toString().equals(map2.get("itemId").toString()) && map2.get("children") == null) {
						List<Map<String,Object>> children = new ArrayList<>();
						children.add(child);
						map2.put("children", children);
						break;
					}else if(map.get("parent_id").toString().equals(map2.get("itemId").toString()) && map2.get("children") != null) {
						List<Map<String,Object>> children = (List<Map<String, Object>>) map2.get("children");
						children.add(child);
						break;
					}
				}
			}
		}
		return resultList;
	}

	@Override
	public Map<String,Object> selectUsers() {
		List<Map<String,Object>> sourceList = userMapper.selectUsers();
		List<Map<String,Object>> targetList = new ArrayList<>();
		
		boolean flag = true;
		for (Map<String,Object> map : sourceList) {
			for (Map<String, Object> map2 : targetList) {
				if(map.get("id").toString().equals(map2.get("id").toString())) {
					map2.put("roleName", map2.get("roleName")+"," + map.get("roleName"));
					flag = false;
					break;
				}
			}
			if(flag) {
				targetList.add(map);
			}
			flag = true;
		}
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("total", "1");
		resultMap.put("rows", targetList);
		return resultMap;
	}
	
	@Override
	public Object initUserPass(User user) {
		String md5 = Md5Util.md5(user.getUserName()+user.getPassword());
		user.setPassword(md5);
		int i = userMapper.initUserPass(user);
		JSONObject result = new JSONObject();
		if(i > 0) {
			result.put("code", "200");
			result.put("msg", "操作成功!");
		}else {
			result.put("code", "500");
			result.put("msg", "操作失败!");
		}
		return result;
	}

	@Override
	public Object getAllAuths() {
		List<Map<String,Object>> list = userMapper.getAllAuths();
		Map<String,Object> result = new HashMap<>();
		result.put("total", 1);
		result.put("rows", list);
		return result;
	}
	

	@Override
	public Object getAllRoles() {
		Map<String,Object> result = new HashMap<>();
		result.put("total", 1);
		result.put("rows", userMapper.getAllRoles());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object getAuthsForOneRoleByTree(String roleId) {
		List<Map<String,Object>> allList = userMapper.getAuthsForOneRoleByTree(null);
		List<Map<String,Object>> existList = userMapper.getAuthsForOneRoleByTree(roleId);
		
		//对比权限
		for (Map<String, Object> map2 : existList) {
			for (Map<String,Object> map : allList) {
//				if(map2.get("parent_id") == null) continue;
				if(map2.get("id").toString().equals(map.get("id").toString())) {
					map.put("requiredChecked", true);
					break;
				}
			}
		}
		
		List<Map<String,Object>> targetList = new ArrayList<>();
		for (Map<String,Object> map : allList) {
			if(map.get("parent_id") == null) {
				Map<String,Object> parentMap = new HashMap<>();
				parentMap.put("id", map.get("id"));
				parentMap.put("myParentId", map.get("id"));
				parentMap.put("text", map.get("auth_name"));
//				parentMap.put("checked", map.get("requiredChecked") == null ? false : true);
				targetList.add(parentMap);
			}else {
				for (Map<String, Object> map2 : targetList) {
					if(map.get("parent_id").toString().equals(map2.get("myParentId").toString())) {
						Map<String,Object> child = new HashMap<>();
						child.put("id", map.get("id"));
						child.put("text", map.get("auth_name"));
						child.put("checked", map.get("requiredChecked") == null ? false : true);
						List<Map<String,Object>> children = new ArrayList<>();
						if(map2.get("children") == null) {
							map2.put("children", children);
						}else {
							children = (List<Map<String, Object>>) map2.get("children");
						}
						children.add(child);
					}
				}
			}
		}
		return targetList;
	}

	@Override
	public Object updateAuthOk(String roleId, String[] authIds) {
		userMapper.deleteMapRoleAuthByAuthIds(roleId);
		userMapper.insertMultiAuthForOneRole(roleId, authIds);
		return null;
	}
	
}
