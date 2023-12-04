package com.security.springboot.demoSecurity.dao;
import com.security.springboot.demoSecurity.entity.*;
public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
