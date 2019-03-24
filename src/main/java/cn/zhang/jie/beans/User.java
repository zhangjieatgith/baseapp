package cn.zhang.jie.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String password;
	private Date createTime;
	private Set<String> roles;
	private Set<String> auths;
	
	//加上一行注释
	
	public User() {
		super();
	}
	public User(String userName, Date createTime) {
		super();
		this.userName = userName;
		this.createTime = createTime;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Set<String> getAuths() {
		return auths;
	}
	public void setAuths(Set<String> auths) {
		this.auths = auths;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", createTime=" + createTime
				+ ", roles=" + roles + ", auths=" + auths + "]";
	}
}
